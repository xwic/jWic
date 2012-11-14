/*
 * Copyright 2005-2007 jWic group (http://www.jwic.de)
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * de.jwic.ecolib.objectlinktool.ObjectLinkTool
 * Created on Apr 5, 2007
 * $Id: ObjectLinkTool.java,v 1.2 2012/08/29 07:46:52 lordsam Exp $
 */
package de.jwic.ecolib.objectlinktool;

import de.jwic.base.ControlContainer;
import de.jwic.base.IControlContainer;
import de.jwic.base.ImageRef;
import de.jwic.controls.ActionBarControl;
import de.jwic.controls.Button;
import de.jwic.controls.LabelControl;
import de.jwic.ecolib.tableviewer.IContentProvider;
import de.jwic.ecolib.tableviewer.TableColumn;
import de.jwic.ecolib.tableviewer.TableModel;
import de.jwic.ecolib.tableviewer.TableModelAdapter;
import de.jwic.ecolib.tableviewer.TableModelEvent;
import de.jwic.ecolib.tableviewer.TableViewer;
import de.jwic.events.ElementSelectedEvent;
import de.jwic.events.ElementSelectedListener;
import de.jwic.events.SelectionEvent;
import de.jwic.events.SelectionListener;

/**
 * This tool is useful when trying to view linked objects in 2 lists (tables).
 * 
 * @author Aron Cotrau
 */
public class ObjectLinkTool extends ControlContainer {

	private Button btLink = null;
	private Button btFindMode = null; 
	private CustomGroupControl customGroup = null;
	
	private int widthHint = 800;

	private ObjectLinkModel model = null;
	private TableViewer leftTableViewer = null;
	private TableViewer rightTableViewer = null;
	private TableColumn scoreCol = null;
	/**
	 * @param container
	 * @param leftObject
	 * @param rightObject
	 */
	public ObjectLinkTool(IControlContainer container, ObjectListDef leftObject, ObjectListDef rightObject) {
		this(container, null, leftObject, rightObject);
	}

	/**
	 * @param container
	 * @param name
	 * @param leftObject
	 * @param rightObject
	 * @param linkageColumnName
	 */
	public ObjectLinkTool(IControlContainer container, String name, ObjectListDef leftObject, ObjectListDef rightObject) {
		super(container, name);

		model = new ObjectLinkModel(leftObject, rightObject);
		model.addLinkListener(new ObjectLinkAdapter() {
			public void dataModified(ObjectLinkEvent event) {
				handleDataModified(event.getListNo());
			}
			public void modeChanged(ObjectLinkEvent event) {
				handleModeChange();
			}
		});
		init();
	}

	/**
	 * 
	 */
	protected void handleModeChange() {
		leftTableViewer.setSelectableColumns(model.getViewMode() != ObjectLinkModel.VIEW_MODE_GROUP);
		rightTableViewer.setSelectableColumns(model.getViewMode() == ObjectLinkModel.VIEW_MODE_NORMAL);
		scoreCol.setVisible(model.getViewMode() == ObjectLinkModel.VIEW_MODE_FIND);
	}

	/**
	 * @param listNo 
	 */
	protected void handleDataModified(int listNo) {

		if ((listNo & ObjectLinkModel.LEFT) > 0) {
			leftTableViewer.setRequireRedraw(true);
		}
		if ((listNo & ObjectLinkModel.RIGHT) > 0) {
			rightTableViewer.setRequireRedraw(true);
		}		
	}

	/**
	 * creates the tables
	 */
	private void init() {

		setupActionBar();
		
		customGroup = new CustomGroupControl(this, "customGroup", model);
		customGroup.setVisible(false);
		customGroup.setup(model.getTableColumns(ObjectLinkModel.LEFT), model.getTableColumns(ObjectLinkModel.RIGHT));
		
		leftTableViewer = new TableViewer(this, "leftTable");
		setupTable(leftTableViewer, model.getTableColumns(ObjectLinkModel.LEFT), model.getContentProvider(ObjectLinkModel.LEFT));
		leftTableViewer.getModel().addTableModelListener(new TableModelAdapter() {
			public void columnSelected(TableModelEvent event) {
				model.sortList(ObjectLinkModel.LEFT, event.getTableColumn());
			}
		});
		leftTableViewer.getModel().addElementSelectedListener(new ElementSelectedListener() {
			public void elementSelected(ElementSelectedEvent event) {
				if (model.getViewMode() == ObjectLinkModel.VIEW_MODE_FIND) {
					model.findMatches((String)event.getElement());
				}
			}
		});
		
		rightTableViewer = new TableViewer(this, "rightTable");
		scoreCol = new TableColumn("Score", 60);
		rightTableViewer.getModel().addColumn(scoreCol);
		scoreCol.setUserObject(new Integer(-1));
		scoreCol.setVisible(false);
		
		setupTable(rightTableViewer, model.getTableColumns(ObjectLinkModel.RIGHT), model.getContentProvider(ObjectLinkModel.RIGHT));
		rightTableViewer.getModel().addTableModelListener(new TableModelAdapter() {
			public void columnSelected(TableModelEvent event) {
				model.sortList(ObjectLinkModel.RIGHT, event.getTableColumn());
			}
		});

		setWidthHint(widthHint); // force width update
	}

	/**
	 * Set the link matcher used for the find mode.
	 * @param matcher
	 */
	public void setLinkMatcher(ILinkMatcher matcher) {
		model.setLinkMatcher(matcher);
		btFindMode.setVisible(matcher != null);
	}
	/**
	 * 
	 */
	private void setupActionBar() {
		
		ActionBarControl abar = new ActionBarControl(this, "abar");
		
		btLink = new Button(abar, "btLink");
		btLink.setIconEnabled(new ImageRef(getClass().getPackage(), "link.gif"));
		btLink.setTitle("Link Selected"); // TODO externalize
		btLink.addSelectionListener(new SelectionListener() {
			public void objectSelected(SelectionEvent event) {
				doLinkElements();
			}
		});
		
		new LabelControl(abar, "abar").setText("View:");
		
		Button btNormal = new Button(abar, "btNormal");
		btNormal.setTitle("Normal Mode");
		btNormal.setIconEnabled(new ImageRef(getClass().getPackage(), "normal.gif"));
		btNormal.addSelectionListener(new SelectionListener() {
			public void objectSelected(SelectionEvent event) {
				model.doNormalMode();				
			}
		});
		
		Button btCustomGroup = new Button(abar, "btGroup");
		btCustomGroup.setTitle("Group Mode...");
		btCustomGroup.addSelectionListener(new SelectionListener() {
			public void objectSelected(SelectionEvent event) {
				customGroup.setVisible(!customGroup.isVisible());
			}
		});
		
		btFindMode = new Button(abar, "btFindMode");
		btFindMode.setTitle("Find Mode");
		btFindMode.addSelectionListener(new SelectionListener() {
			public void objectSelected(SelectionEvent event) {
				doFindMode();
			}
		});
		btFindMode.setVisible(false);
		
	}

	/**
	 * 
	 */
	protected void doFindMode() {
		
		leftTableViewer.getModel().clearSelection();
		rightTableViewer.getModel().clearSelection();
		model.doFindMode();
		scoreCol.setSortIcon(TableColumn.SORT_ICON_DOWN);
		
	}

	/**
	 * 
	 */
	protected void doLinkElements() {
		
		if(model.doLinkElements(
				leftTableViewer.getModel().getFirstSelectedKey(), 
				rightTableViewer.getModel().getFirstSelectedKey())) {
			leftTableViewer.getModel().clearSelection();
			rightTableViewer.getModel().clearSelection();
		}
		
	}

	

	/**
	 * @param leftTableViewer2
	 * @param leftObject2
	 */
	private void setupTable(TableViewer tableViewer, TableColumn[] columns, IContentProvider contentProvider) {

		tableViewer.setResizeableColumns(true);
		tableViewer.setScrollable(true);
		tableViewer.setTableLabelProvider(new RowElementLabelProvider());
		tableViewer.setHeight(500);
		
		TableModel model = tableViewer.getModel();
		model.setSelectionMode(TableModel.SELECTION_SINGLE);
		
		// setup TableColumns
		for (int i = 0; i < columns.length; i++) {
			TableColumn column = columns[i];
			if (column.getWidth() == 0) {
				column.setWidth(100);
			}
			model.addColumn(columns[i]);
		}
		
		tableViewer.setContentProvider(contentProvider);
		
		model.addTableModelListener(new TableModelAdapter() {
			public void columnSelected(TableModelEvent event) {
				super.columnSelected(event);
			}
		});
		
	}

	/**
	 * adds given listener to the listeners list
	 * 
	 * @param listener
	 */
	public synchronized void addLinkListener(IObjectLinkListener listener) {
		model.addLinkListener(listener);
	}

	/**
	 * removes given listener from the listeners list
	 * 
	 * @param listener
	 */
	public synchronized void removeLinkListener(IObjectLinkListener listener) {
		model.removeLinkListener(listener);
	}

	/**
	 * @return the widthHint
	 */
	public int getWidthHint() {
		return widthHint;
	}

	/**
	 * @param widthHint the widthHint to set
	 */
	public void setWidthHint(int widthHint) {
		this.widthHint = widthHint;
		int w = Math.max(widthHint, 400) / 2;
		leftTableViewer.setWidth(w);
		rightTableViewer.setWidth(w);
	}

	/**
	 * @return the model
	 */
	public ObjectLinkModel getObjectLinkModel() {
		return model;
	}
}
