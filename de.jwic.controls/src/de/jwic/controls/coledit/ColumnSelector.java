/*******************************************************************************
 * Copyright 2015 xWic group (http://www.xwic.de)
 *
 * Licensed under the Apache License, Version 2.0 (the "License").
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * 		http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software 
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and 
 * limitations under the License.
 *  
 *******************************************************************************/
package de.jwic.controls.coledit;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import de.jwic.base.ControlContainer;
import de.jwic.base.Field;
import de.jwic.base.IControlContainer;
import de.jwic.base.JavaScriptSupport;
import de.jwic.controls.Button;
import de.jwic.controls.CheckBox;
import de.jwic.controls.ToolBar;
import de.jwic.controls.ToolBarGroup;
import de.jwic.events.SelectionEvent;
import de.jwic.events.SelectionListener;
import de.jwic.events.ValueChangedEvent;
import de.jwic.events.ValueChangedListener;
import de.jwic.util.JWicImageLibrary;

/**
 * Provides two lists where user can move elements in/out and sort
 * those elements. Typical sample is the usage as a column selector
 * for a list.
 * 
 * @author lippisch
 */
@JavaScriptSupport
public class ColumnSelector extends ControlContainer {
	private static final long serialVersionUID = 1L;
	private enum EventType {
		COLUMN_UPDATED
	}
	
	private int nextId = 0;
	private List<ColumnStub> columns = new ArrayList<ColumnStub>();
	private Map<Integer, ColumnStub> idColumnMap = new HashMap<Integer, ColumnStub>();
	private Map<Integer, CheckBox> checkBoxes = new HashMap<Integer, CheckBox>();
	
	private String cssClass = "j-listColSel";
	
	private Field fldOrder = null;
	private int width = 580;
	private int height = 300;
	
	private boolean inAddMode = false;
	private boolean immediateUpdate = false;
	private boolean showActions = true;
	
	private boolean hideInvisibles = false;
	private boolean hideDescription = true;
	
	private List<IColumnSelectorListener> listeners = new ArrayList<IColumnSelectorListener>();
	
	/** Listener for all checkboxes. */
	private ValueChangedListener checkboxListener = new ValueChangedListener() {
		private static final long serialVersionUID = 1L;
		public void valueChanged(ValueChangedEvent event) {
			onCheckboxStateChange();
		};
	};
	private Button btHideDescr;
	private Button btHideInvis;
	
	/**
	 * @param container
	 */
	public ColumnSelector(IControlContainer container) {
		super(container);
		init();
	}

	/**
	 * @param container
	 * @param name
	 */
	public ColumnSelector(IControlContainer container, String name) {
		super(container, name);
		init();
	}

	/**
	 * Add a listener.
	 * @param listener
	 */
	public void addColumnSelectorListener(IColumnSelectorListener listener) {
		listeners.add(listener);
	}
	
	/**
	 * Remove a listener.
	 * @param listener
	 */
	public void removeColumnSelectorListener(IColumnSelectorListener listener) {
		listeners.remove(listener);
	}
	
	/**
	 * Fire the specified event.
	 * @param type
	 * @param event
	 */
	protected void fireEvent(EventType type, ColumnSelectorEvent event) {
		IColumnSelectorListener[] l = new IColumnSelectorListener[listeners.size()];
		l = listeners.toArray(l);
		for (IColumnSelectorListener listener : l) {
			switch (type) {
			case COLUMN_UPDATED:
				listener.columnsUpdated(event);
			break;
			}
		}
	}
	
	/**
	 * 
	 */
	private void init() {
		fldOrder = new Field(this, "fldOrder");
		
		new Field(this, "filter"); // add a filter field to not loose the value
		
		fldOrder.addValueChangedListener(new ValueChangedListener() {
			private static final long serialVersionUID = 1L;
			public void valueChanged(ValueChangedEvent event) {
				updateOrder();
			}
		});
		
		// add Toolbar
		ToolBar tb = new ToolBar(this, "toolBar");
		tb.setCssClass("j-toolbar ui-corner-top");
		ToolBarGroup group = tb.addGroup();
		
		Button btSelectAll = group.addButton();
		btSelectAll.setTitle("All");
		btSelectAll.setIconEnabled(JWicImageLibrary.IMAGE_CHECK_ALL);
		btSelectAll.addSelectionListener(new SelectionListener() {
			@Override
			public void objectSelected(SelectionEvent event) {
				actionSelectAll();
			}
		});

		Button btSelectNone = group.addButton();
		btSelectNone.setTitle("None");
		btSelectNone.setIconEnabled(JWicImageLibrary.IMAGE_CHECK_NONE);
		btSelectNone.addSelectionListener(new SelectionListener() {
			@Override
			public void objectSelected(SelectionEvent event) {
				actionSelectNone();
			}
		});

		btHideInvis = group.addButton();
		btHideInvis.setTitle("Show Selected Only");
		btHideInvis.setTooltip("You can hide those columns that are not displayed. This helps to re-order the selected columns more easily");
		btHideInvis.setIconEnabled(JWicImageLibrary.IMAGE_CHECK_HIDE);
		btHideInvis.addSelectionListener(new SelectionListener() {
			@Override
			public void objectSelected(SelectionEvent event) {
				actionHideInvisible();
			}
		});

		btHideDescr = group.addButton();
		btHideDescr.setTitle("Show Description");
		btHideDescr.setIconEnabled(JWicImageLibrary.IMAGE_INFORMATION);
		btHideDescr.addSelectionListener(new SelectionListener() {
			@Override
			public void objectSelected(SelectionEvent event) {
				actionHideDescription();
			}
		});

	}

	/**
	 * Re-order the columns as from the list
	 */
	protected void updateOrder() {
		
		if (!inAddMode) {
			StringTokenizer stk = new StringTokenizer(fldOrder.getValue(), ";");
			int idx = 0;
			while (stk.hasMoreTokens()) {
				Integer id = Integer.parseInt(stk.nextToken());
				ColumnStub col = idColumnMap.get(id);
				if (col != null) {
					col.setSortIndex(idx);
				}
				idx++;
			}
			
			Collections.sort(columns);
			fireEvent(EventType.COLUMN_UPDATED, new ColumnSelectorEvent(this));
		}
		
	}

	/**
	 * @return the cssClass
	 */
	public String getCssClass() {
		return cssClass;
	}

	/**
	 * @param cssClass the cssClass to set
	 */
	public void setCssClass(String cssClass) {
		this.cssClass = cssClass;
	}

	/**
	 * The order was updated.
	 */
	public void actionOrderUpdated() {
		// nothing to do as the event is triggered from the field update.
	}
	
	/**
	 * Select all columns.
	 */
	public void actionSelectAll() {
		for (CheckBox chk : checkBoxes.values()) {
			chk.setChecked(true);
		}
		requireRedraw();
	}
	
	/**
	 * Toggle hidding the description.
	 */
	public void actionHideDescription() {
		this.hideDescription = !this.hideDescription;
		btHideDescr.setTitle(hideDescription ? "Show Description" : "Hide Description");
		requireRedraw();
	}
	
	/**
	 * Deselect all.
	 */
	public void actionSelectNone() {
		hideInvisibles = false;
		for (CheckBox chk : checkBoxes.values()) {
			chk.setChecked(false);
		}
		requireRedraw();
	}
	
	/**
	 * Toggle hide invisible state.
	 */
	public void actionHideInvisible() {
		hideInvisibles = !hideInvisibles;
		btHideInvis.setTitle(hideInvisibles ? "Show All" : "Show Selected Only");
		requireRedraw();
	}
	
	/**
	 * 
	 * @param column
	 */
	public void addColumn(ColumnStub column) {
		columns.add(column);
		
		// Assign Unique Id
		if (column.getId() == -1) {
			column.setId(nextId++);
		}
		column.setSortIndex(columns.size()); // add at the end.
		
		idColumnMap.put(column.getId(), column);
		
		if (!checkBoxes.containsKey("chk" + column.getId())) {
			CheckBox chkbox = new CheckBox(this, "chk" + column.getId());
			chkbox.setChecked(column.isVisible());
			//chkbox.setLabel(column.getTitle());
			chkbox.addValueChangedListener(checkboxListener);
			checkBoxes.put(column.getId(), chkbox);
		}
		
		try {
			inAddMode = true;
			fldOrder.setValue(fldOrder.getValue() + column.getId() + ";");
		} finally {
			inAddMode = false;
		}
	}
	
	/**
	 * @return the columns
	 */
	public List<ColumnStub> getColumns() {
		return columns;
	}

	/**
	 * @return the width
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * @param width the width to set
	 */
	public void setWidth(int width) {
		this.width = width;
	}

	/**
	 * @return the height
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * @param height the height to set
	 */
	public void setHeight(int height) {
		this.height = height;
	}

	/**
	 * @return the immediateUpdate
	 */
	public boolean isImmediateUpdate() {
		return immediateUpdate;
	}

	/**
	 * Set to true if a submit should be made after each order change.
	 * @param immediateUpdate the immediateUpdate to set
	 */
	public void setImmediateUpdate(boolean immediateUpdate) {
		this.immediateUpdate = immediateUpdate;
	}

	/**
	 * Invoked when the visible state was changed.
	 */
	protected void onCheckboxStateChange() {
		for (ColumnStub col : columns) {
			CheckBox ck = checkBoxes.get(col.getId());
			if (ck != null) {
				col.setVisible(ck.isChecked());
			}
		}
		fireEvent(EventType.COLUMN_UPDATED, new ColumnSelectorEvent(this));		
		if (hideInvisibles) {
			requireRedraw();
		}
	}

	/**
	 * @return the showActions
	 */
	public boolean isShowActions() {
		return showActions;
	}

	/**
	 * @param showActions the showActions to set
	 */
	public void setShowActions(boolean showActions) {
		this.showActions = showActions;
	}

	/**
	 * @return the hideInvisibles
	 */
	public boolean isHideInvisibles() {
		return hideInvisibles;
	}

	/**
	 * @param hideInvisibles the hideInvisibles to set
	 */
	public void setHideInvisibles(boolean hideInvisibles) {
		this.hideInvisibles = hideInvisibles;
		btHideInvis.setTitle(hideInvisibles ? "Show All" : "Show Selected Only");
	}

	/**
	 * @return the hideDescription
	 */
	public boolean isHideDescription() {
		return hideDescription;
	}

	/**
	 * @param hideDescription the hideDescription to set
	 */
	public void setHideDescription(boolean hideDescription) {
		this.hideDescription = hideDescription;
		btHideDescr.setTitle(hideDescription ? "Show Description" : "Hide Description");
	}


}
