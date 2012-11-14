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
 * de.jwic.ecolib.tableviewer.TableModel
 * Created on 12.03.2007
 * $Id: TableModel.java,v 1.15 2011/06/23 10:27:43 adrianionescu12 Exp $
 */
package de.jwic.ecolib.tableviewer;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import de.jwic.events.ElementSelectedEvent;
import de.jwic.events.ElementSelectedListener;

/**
 * Acts as a model for all TableViewer controls that maintains the state
 * of the list, columns, paging etc.
 * 
 * @author Florian Lippisch
 */
public class TableModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Selection is disabled.
	 */
	public static final int SELECTION_NONE = 0;
	/**
	 * The user can selected one line. If he selects another line
	 * the previous selected line is deselected.
	 */
	public static final int SELECTION_SINGLE = 1;
	/**
	 * Multiple lines can be selected. If a selected line is selected
	 * again, the line is deselected.
	 */
	public static final int SELECTION_MULTI = 2;
	
	protected static final int EVENT_RANGE_UPDATE = 1;
	protected static final int EVENT_COLUMN_SELECTED = 2;
	protected static final int EVENT_EXPANDED = 3;
	protected static final int EVENT_COLLAPSED = 4;
	protected static final int EVENT_COLUMN_RESIZED = 5;
	protected static final int EVENT_CONTENT_CHANGED = 6;
	
	
	protected List<TableColumn> columns = new ArrayList<TableColumn>();
	protected Range range = new Range();
	protected int lastRowRenderCount = 0;
	protected int selectionMode = SELECTION_NONE;
	protected boolean invertSelection = false;
	protected boolean invertExpansion = false;
	protected Set<String> selection = new HashSet<String>();
	private Set<String> expanded = new HashSet<String>();
	
	private List<ElementSelectedListener> selectionListeners = new ArrayList<ElementSelectedListener>();
	private List<ITableModelListener> modelListeners = new ArrayList<ITableModelListener>();

	protected IContentProvider<?> contentProvider = null;
	
	protected TableViewer tableViewer = null;
	
	protected int lastRenderedPageSize = -1;
	
	/**
	 * Default constructor.
	 */
	public TableModel() {
		super();
	}
	
	public TableModel(TableViewer tableViewer) {
		this.tableViewer = tableViewer;
	}

	/**
	 * Add a column to the list.
	 * @param column
	 */
	public void addColumn(TableColumn column) {

		addColumn(column, columns.size());
		
	}
	
	/**
	 * Add a column to the list at given index.
	 * @param column
	 * @param index
	 */
	public void addColumn(TableColumn column, int index) {

		columns.add(index, column);
		column.setIndex(index);
	
//RPF: This IF causes trouble, when adding columns to the model after others with indexes before the end
//Then the tableviewer column resize does not work properly. a reindexcolumns is always necessary!
//		if (index == columns.size() - 1) {
			reindexColumns();
//		}
		
	}

	/**
	 * Removes a column.
	 * @param column
	 */
	public void removeColumn(TableColumn column) {
		
		if (columns.remove(column)) {
			reindexColumns();
		}
		
	}
	
	/**
	 * Removes all columns.
	 */
	public void removeAllColumns() {
		columns.clear();
	}
	
	/**
	 * Reindex all columns.
	 */
	protected void reindexColumns() {
		for (int i = 0; i < columns.size(); i++) {
			TableColumn col = columns.get(i);
			col.setIndex(i);
		}
	}

	/**
	 * Returns an iterator for the columns.
	 * @return Iterator of TableColumn objects
	 */
	public Iterator<TableColumn> getColumnIterator() {
		return columns.iterator();
	}
	
	/**
	 * Returns the columns List size.
	 * @return
	 */
	public int getColumnsCount() {
		return columns.size();
	}

	/**
	 * Returns TableColumn at given index.
	 * @param index
	 * @return
	 */
	public TableColumn getColumn(int index) {
		return columns.get(index);
	}
	
	/**
	 * Add a listener.
	 * @param listener
	 */
	public void addTableModelListener(ITableModelListener listener) {
		modelListeners.add(listener);
	}

	/**
	 * Remove a listener.
	 * @param listener
	 */
	public void removeTableModelListener(ITableModelListener listener) {
		modelListeners.remove(listener);
	}

	/**
	 * Add ElementSelectedListener
	 * @param listener
	 */
	public void addElementSelectedListener(ElementSelectedListener listener) {
		selectionListeners.add(listener);
	}

	/**
	 * Remove ElementSelectedListener.
	 * @param listener
	 */
	public void removeElementSelectedListener(ElementSelectedListener listener) {
		selectionListeners.remove(listener);
	}

	/**
	 * Internal fire event handler.
	 * @param eventType
	 */
	protected void fireTableEvent(int eventType, TableModelEvent event) {
		Object[] list = modelListeners.toArray();
		for (int i = 0; i < list.length; i++) {
			ITableModelListener listener = (ITableModelListener)list[i];
			switch (eventType) {
			case EVENT_RANGE_UPDATE: 
				listener.rangeUpdated(event);
				break;
			case EVENT_COLUMN_SELECTED:
				listener.columnSelected(event);
				break;
			case EVENT_COLUMN_RESIZED:
				listener.columnResized(event);
				break;
			case EVENT_COLLAPSED:
				listener.nodeCollapsed(event);
				break;
			case EVENT_EXPANDED:
				listener.nodeExpanded(event);
				break;
			case EVENT_CONTENT_CHANGED:
				listener.contentChanged(event);
				break;
			}
		}
	}

	/**
	 * Notify ElementSelectedListeners
	 * @param selectedElement
	 */
	protected void fireSelectionEvent(String selectedElement, boolean dblClick) {
		ElementSelectedEvent event = new ElementSelectedEvent(this, selectedElement, dblClick);
		Object[] list = selectionListeners.toArray();
		for (int i = 0; i < list.length; i++) {
			ElementSelectedListener listener = (ElementSelectedListener)list[i];
			listener.elementSelected(event);
		}
	}

	/**
	 * @return the range
	 */
	public Range getRange() {
		return range;
	}

	/**
	 * Set the maximum number of displayed lines per page. If the value is set
	 * to -1, all rows are displayed and the start is set to 0 (first page).
	 * @param maxLines
	 */
	public void setMaxLines(int maxLines) {
		range.setMax(maxLines);
		if (maxLines == -1) {
			range.setStart(0);
		}
		fireTableEvent(EVENT_RANGE_UPDATE, new TableModelEvent(this));
	}

	/**
	 * Returns the maximum number of lines.
	 * @return
	 */
	public int getMaxLines() {
		return range.getMax();
	}

	/**
	 * Goto first page. 
	 */
	public void pageFirst() {
		range.setStart(0);
		fireTableEvent(EVENT_RANGE_UPDATE, new TableModelEvent(this));
	}

	/**
	 * 
	 */
	public void pagePrev() {
		int pgSize = range.getMax();
		if (pgSize == 0) {
			pgSize = lastRenderedPageSize;
		}

		if (pgSize > 0) {
			int next = range.getStart() - pgSize;
			if (next < 0) { 
				next = 0;
			}
			range.setStart(next);
			fireTableEvent(EVENT_RANGE_UPDATE, new TableModelEvent(this));
		}
	}

	/**
	 * 
	 */
	public void pageNext() {
		int pgSize = range.getMax();
		if (pgSize == 0) {
			pgSize = lastRenderedPageSize;
		}
		if (pgSize > 0) {
			int total = getContentProvider().getTotalSize();
			int next = range.getStart() + pgSize;
			if (next < total) {
				range.setStart(next);
				fireTableEvent(EVENT_RANGE_UPDATE, new TableModelEvent(this));
			}
		}
	}

	/**
	 * 
	 */
	public void pageLast() {
		// calculate last page
		int pgSize = range.getMax();
		if (pgSize == 0) {
			pgSize = lastRenderedPageSize;;
		}
		if (pgSize > 0) {
			int total = getContentProvider().getTotalSize();
			int pages =  total / pgSize;
			if (total % pgSize == 0) {
				pages -= 1;
			}
			range.setStart(pages * pgSize);
			fireTableEvent(EVENT_RANGE_UPDATE, new TableModelEvent(this));
		}
	}

	/**
	 * @return the lastRowRenderCount
	 */
	public int getLastRowRenderCount() {
		return lastRowRenderCount;
	}

	/**
	 * @param lastRowRenderCount the lastRowRenderCount to set
	 */
	public void setLastRowRenderCount(int lastRowRenderCount) {
		this.lastRowRenderCount = lastRowRenderCount;
	}

	/**
	 * @return the selectionMode
	 */
	public int getSelectionMode() {
		return selectionMode;
	}

	/**
	 * Sets the selection mode to either
	 * <li>SELECTION_NONE
	 * <li>SELECTION_SINGLE
	 * <li>SELECTION_MULTI
	 * <p>Note that this method does not fire an event nor sets the
	 * TableViewer to requireRedraw. If this property is changed
	 * during runtime, the viewer must be set to redraw manually.
	 * @param selectionMode the selectionMode to set
	 */
	public void setSelectionMode(int selectionMode) {
		this.selectionMode = selectionMode;
		clearSelection();
	}
	
	/**
	 * A selection occured.
	 * @param rowKey
	 */
	public void selection(String rowKey) {
		selection(rowKey, false);
	}
	
	/**
	 * Selection of a row.
	 * @param rowKey
	 * @param dblClick
	 */
	public void selection(String rowKey, boolean dblClick) {
		switch (selectionMode) {
		case SELECTION_SINGLE: 
			if (selection.contains(rowKey)) {
				// selection.clear();
				// no delselection
				fireSelectionEvent(rowKey, dblClick);
			} else {
				selection.clear();
				selection.add(rowKey);
				fireSelectionEvent(rowKey, dblClick);
			}
			break;
		case SELECTION_MULTI: 
			if (selection.contains(rowKey)) {
				if (!dblClick) { // ignore de-selection on dbl-click.
					selection.remove(rowKey);
					fireSelectionEvent(null, dblClick); // element got removed
				}
			} else {
				selection.add(rowKey);
				fireSelectionEvent(rowKey, dblClick);
			}
			break;
		case SELECTION_NONE:
			break;
		}
	}

	/**
	 * Returns true if the specified rowKey is selected.
	 * @param rowKey
	 * @return
	 */
	public boolean isSelected(String rowKey) {
		boolean isSelected = selection.contains(rowKey);
		return invertSelection ^ isSelected;
	}
	
	/**
	 * @return the invertSelection
	 */
	public boolean isInvertSelection() {
		return invertSelection;
	}
	
	/**
	 * @param invertSelection the invertSelection to set
	 */
	public void setInvertSelection(boolean invertSelection) {
		this.invertSelection = invertSelection;
	}
	
	/**
	 * @return the invertExpansion
	 */
	public boolean isInvertExpansion() {
		return invertExpansion;
	}
	
	/**
	 * @param invertExpansion the invertExpansion to set
	 */
	public void setInvertExpansion(boolean invertExpansion) {
		this.invertExpansion = invertExpansion;
	}

	/**
	 * Clear the selection.
	 */
	public void clearSelection() {
		selection.clear();
		invertSelection = false;
		
		fireSelectionEvent("", false);
	}

	/**
	 * Clear the expansion.
	 */
	public void clearExpansion() {
		expanded.clear();
		invertExpansion = false;
	}

	/**
	 * Returns the first selected key or null if no key
	 * is selected.
	 * @return
	 */
	public String getFirstSelectedKey() {
		if (selection.size() > 0) {
			return selection.iterator().next();
		}
		return null;
	}
	
	/**
	 * Retruns the selected keys or deselected in invert mode.
	 * @return
	 */
	public Collection<String> getSelection() {
		return selection;
	}

	/**
	 * @param row
	 * @return
	 */
	public boolean isExpanded(String rowKey) {
		boolean isExpanded = expanded.contains(rowKey);
		return invertExpansion ^ isExpanded;
	}

	/**
	 * @param rowKey
	 */
	public void expand(String rowKey) {
		if (!invertExpansion) {
			expanded.add(rowKey);
		} else {
			expanded.remove(rowKey);
		}
		fireTableEvent(EVENT_EXPANDED, new TableModelEvent(this, rowKey));
	}

	/**
	 * @param rowKey
	 */
	public void collapse(String rowKey) {
		if (!invertExpansion) {
			expanded.remove(rowKey);
		} else {
			expanded.add(rowKey);
		}
		fireTableEvent(EVENT_COLLAPSED, new TableModelEvent(this, rowKey));
	}

	/**
	 * @param colIdx
	 * @param newWidth
	 */
	public void setColumnWidth(int colIdx, int newWidth) {
		TableColumn col = columns.get(colIdx);
		col.setWidth(newWidth);
		TableModelEvent event =  new TableModelEvent(this);
		event.setTableColumn(col);
		fireTableEvent(EVENT_COLUMN_RESIZED, event);
	}

	/**
	 * Notify listeners that a column header has been selected.
	 * @param colIdx
	 */
	public void selectColumn(int colIdx) {
		TableColumn col = columns.get(colIdx);
		TableModelEvent event = new TableModelEvent(this);
		event.setTableColumn(col);
		fireTableEvent(EVENT_COLUMN_SELECTED, event);
	}
	
	/**
	 * @param contentProvider the contentProvider to set
	 */
	public void setContentProvider(IContentProvider<?> contentProvider) {
		this.contentProvider = contentProvider;
	}

	/**
	 * @return the contentProvider
	 */
	public IContentProvider<?> getContentProvider() {
		return contentProvider;
	}
	
	/**
	 * Fire content changed event.
	 * Sets require redraw flag of TableViewer (if available).
	 */
	public void contentChanged() {
		contentChanged(this);
	}
	/**
	 * Fire content changed event.
	 * Sets require redraw flag of TableViewer (if available).
	 * @param eventSource the eventSource to use
	 */
	public void contentChanged(Object eventSource) {
		fireTableEvent(EVENT_CONTENT_CHANGED, new TableModelEvent(eventSource));
		if (tableViewer != null) {
			tableViewer.setRequireRedraw(true);
		}
	}

	/**
	 * @return the lastRenderedPageSize
	 */
	public int getLastRenderedPageSize() {
		return lastRenderedPageSize;
	}

	/**
	 * @param lastRenderedPageSize the lastRenderedPageSize to set
	 */
	public void setLastRenderedPageSize(int lastRenderedPageSize) {
		this.lastRenderedPageSize = lastRenderedPageSize;
	}
}

