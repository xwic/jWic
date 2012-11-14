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
 * de.jwic.ecolib.tableviewer.TableViewer
 * Created on 12.03.2007
 * $Id: TableViewer.java,v 1.16 2010/11/02 08:04:49 cosote Exp $
 */

package de.jwic.ecolib.tableviewer;

import de.jwic.base.ControlContainer;
import de.jwic.base.Field;
import de.jwic.base.IControlContainer;
import de.jwic.base.RenderContext;
import de.jwic.controls.IHTMLElement;
import de.jwic.renderer.self.ISelfRenderingControl;
import de.jwic.renderer.self.SelfRenderer;

/**
 * Main control used to display data in a table. The control uses a ContentProvider and ITableLabelProvider
 * to render the table. It supports the following features:
 * <ul>
 * <li>resizable columns</li>
 * <li>expandable rows (tree like)</li>
 * <li>scrollable (both horizontal and vertical)</li>
 * <li>StatusBar to use paging and configure the max. number of lines to display</li>
 * <li>columns can be clicked for sorting/filtering</li>
 * <li>single- or multiselection</li>
 * <li>clean and easy to use backend architecture</li>
 * <li>layout customizing via css</li>
 * </ul>
 * 
 * <p>In the default configuration, most of the features are disabled. To enable
 * expandable rows, the expandableColumn must be set to the column that should 
 * contain the expand icon.</p>
 * 
 * <p>To use resizable columns, all TableColumns must have a fixed width. If a column
 * has no width specified, a default value is assigned.</p>
 * 
 * @author Florian Lippisch
 */
public class TableViewer extends ControlContainer implements ISelfRenderingControl, IHTMLElement {

	/** Serial UID for this type. */
	private static final long serialVersionUID = -4118250595088501001L;

	private TableModel model = new TableModel();
	private ITableRenderer tableRenderer = new DefaultTableRenderer();
	private ITableLabelProvider tableLabelProvider = null;
	
	private String cssClass = "tblViewer";
	private int height = 0;
	private int width = 0;
	private int rowHeightHint = 18; 
	private int expandableColumn = -1; // disabled by default

	private boolean fillWidth = false;
	private boolean enabled = true;
	private boolean showHeader = true;
	
	private boolean resizeableColumns = false;
	private boolean selectableColumns = true;
	private boolean scrollable = false;
	
	private StatusBarControl statusBar = null;
	
	/**
	 * @param container
	 */
	public TableViewer(IControlContainer container) {
		this(container, null);
		
	}

	/**
	 * @param container
	 * @param name
	 */
	public TableViewer(IControlContainer container, String name) {
		super(container, name);
		setRendererId(SelfRenderer.RENDERER_ID);
		
		
		new Field(this, "left");
		new Field(this, "top");

		statusBar = new StatusBarControl(this, "statusBar", getModel());
	}

	/**
	 * @param provider
	 */
	public void setContentProvider(IContentProvider<?> contentProvider) {
		getModel().setContentProvider(contentProvider);
		requireRedraw();
	}

	/**
	 * @param provider
	 */
	public void setTableLabelProvider(ITableLabelProvider labelProvider) {
		tableLabelProvider = labelProvider;
	}
	
	/**
	 * @return the label provider
	 */
	public ITableLabelProvider getTableLabelProvider() {
		return tableLabelProvider;
	}

	/* (non-Javadoc)
	 * @see de.jwic.renderer.self.ISelfRenderingControl#render(de.jwic.base.RenderContext)
	 */
	public void render(RenderContext renderContext) {
		
		tableRenderer.renderTable(renderContext, this, getModel(), tableLabelProvider);
		
	}

	/**
	 * A user selected a row.
	 * @param rowKey
	 */
	public void actionSelection(String rowKey) {
		getModel().selection(rowKey); // delegate to the model.
	}

	/**
	 * A user selected a row.
	 * @param rowKey
	 */
	public void actionDblClick(String rowKey) {
		getModel().selection(rowKey, true); // delegate to the model.
	}
	
	
	/**
	 * Expand a row.
	 * @param rowKey
	 */
	public void actionExpand(String rowKey) {
		getModel().expand(rowKey);
		requireRedraw();
	}
	
	/**
	 * Collapse a row.
	 * @param rowKey
	 */
	public void actionCollapse(String rowKey) {
		getModel().collapse(rowKey);
		requireRedraw();
	}

	/**
	 * Resize a column.
	 * @param keySize
	 * @return
	 */
	protected boolean resizeColumn(String keySize) {
		int idx = keySize.indexOf(';');
		if (idx != -1) {
			int colIdx = Integer.parseInt(keySize.substring(0, idx));
			int newWidth = Integer.parseInt(keySize.substring(idx + 1));
			getModel().setColumnWidth(colIdx, newWidth);
			return true;
		}
		return false;
	}
	
	/**
	 * Resize a column.
	 * @param keySize String with "colIdx;width"
	 */
	public void actionResizeColumn(String keySize) {
		if (resizeColumn(keySize)) {
			requireRedraw();
		}
	}
	
	/**
	 * Resize a column.
	 * @param keySize String with "colIdx;width"
	 */
	public void actionResizeColumnWithoutRedraw(String keySize) {
		resizeColumn(keySize);
	}
	
	/**
	 * A column got selected.
	 * @param columnIndex
	 */
	public void actionColumnSelection(String columnIndex) {
		getModel().selectColumn(Integer.parseInt(columnIndex));
	}
	
	/**
	 * @return the tableRenderer
	 */
	public ITableRenderer getTableRenderer() {
		return tableRenderer;
	}

	/**
	 * @param tableRenderer the tableRenderer to set
	 */
	public void setTableRenderer(ITableRenderer tableRenderer) {
		this.tableRenderer = tableRenderer;
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
		requireRedraw();
	}

	/**
	 * @return the enabled
	 */
	public boolean isEnabled() {
		return enabled;
	}

	/**
	 * @param enabled the enabled to set
	 */
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
		statusBar.setEnabled(enabled);
		requireRedraw();
	}

	/**
	 * @return the fillWidth
	 */
	public boolean isFillWidth() {
		return fillWidth;
	}

	/**
	 * @param fillWidth the fillWidth to set
	 */
	public void setFillWidth(boolean fillWidth) {
		this.fillWidth = fillWidth;
		requireRedraw();
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
		requireRedraw();
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
		requireRedraw();
	}

	/* (non-Javadoc)
	 * @see de.jwic.controls.IHTMLElement#forceFocus()
	 */
	public boolean forceFocus() {
		return false;
	}

	/**
	 * @return the model
	 */
	public TableModel getModel() {
		return model;
	}

	/**
	 * @return the showStatusBar
	 */
	public boolean isShowStatusBar() {
		return statusBar.isVisible();
	}

	/**
	 * @return the statusBar
	 */
	public StatusBarControl getStatusBar() {
		return statusBar;
	}
	
	/**
	 * If set to true, the status bar with the paging and rowsPerPage control
	 * is displayed.
	 * @param showStatusBar the showStatusBar to set
	 */
	public void setShowStatusBar(boolean showStatusBar) {
		statusBar.setVisible(showStatusBar);
		requireRedraw();
	}

	/**
	 * @return the resizeableColumns
	 */
	public boolean isResizeableColumns() {
		return resizeableColumns;
	}

	/**
	 * @param resizeableColumns the resizeableColumns to set
	 */
	public void setResizeableColumns(boolean resizeableColumns) {
		this.resizeableColumns = resizeableColumns;
		requireRedraw();
	}

	/**
	 * @return the scrollable
	 */
	public boolean isScrollable() {
		return scrollable;
	}

	/**
	 * Set to true, if the table should be placed inside a scrollable container.
	 * @param scrollable the scrollable to set
	 */
	public void setScrollable(boolean scrollable) {
		this.scrollable = scrollable;
		requireRedraw();
	}

	/**
	 * Returns true if the columns can be selected (clicked) by the user.
	 * @return the selectableColumns
	 */
	public boolean isSelectableColumns() {
		return selectableColumns;
	}

	/**
	 * Set to true if the columns can be selected (clicked) by the user.
	 * @param selectableColumns the selectableColumns to set
	 */
	public void setSelectableColumns(boolean selectableColumns) {
		this.selectableColumns = selectableColumns;
		requireRedraw();
	}
	
	/**
	 * @return the showHeader
	 */
	public boolean isShowHeader() {
		return showHeader;
	}

	/**
	 * @param showHeader the showHeader to set
	 */
	public void setShowHeader(boolean showHeader) {
		this.showHeader = showHeader;
		requireRedraw();
	}

	/**
	 * @return the expandableColumn
	 */
	public int getExpandableColumn() {
		return expandableColumn;
	}

	/**
	 * @param expandableColumn the expandableColumn to set
	 */
	public void setExpandableColumn(int expandableColumn) {
		this.expandableColumn = expandableColumn;
	}

	/**
	 * @return the rowHeightHint
	 */
	public int getRowHeightHint() {
		return rowHeightHint;
	}

	/**
	 * This "hint" helps the TableViewer to calculate the number of rows that can be displayed if the user selected '- Auto -' size. 
	 * @param rowHeightHint the rowHeightHint to set
	 */
	public void setRowHeightHint(int rowHeightHint) {
		this.rowHeightHint = rowHeightHint;
	}

}
