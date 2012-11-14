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
 * de.jwic.ecolib.objectlinktool.ObjectLinkModel
 * Created on 19.04.2007
 * $Id: ObjectLinkModel.java,v 1.1 2007/04/25 09:59:14 lordsam Exp $
 */
package de.jwic.ecolib.objectlinktool;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import de.jwic.ecolib.tableviewer.IContentProvider;
import de.jwic.ecolib.tableviewer.ITableLabelProvider;
import de.jwic.ecolib.tableviewer.Range;
import de.jwic.ecolib.tableviewer.RowContext;
import de.jwic.ecolib.tableviewer.TableColumn;

/**
 * The model for the ObjectLinkTool.
 * 
 * @author Florian Lippisch
 */
public class ObjectLinkModel implements Serializable {

	/**
	 * Simple type to manage the left and right list.
	 * @author Florian Lippisch
	 */
	private class ObjectList implements Serializable {
		ObjectListDef listDef = null;
		List data = null;
		int groupColumn = 0;
		RowElementContentProvider contentProvider = new RowElementContentProvider();
	}
	
	public final static int VIEW_MODE_NORMAL = 0;
	public final static int VIEW_MODE_FILL = 1;
	public final static int VIEW_MODE_GROUP = 2;
	public final static int VIEW_MODE_FIND = 3;
	public final static int LEFT = 1;
	public final static int RIGHT = 2;
	
	private final static int EVENT_LINK = 0;
	private final static int EVENT_DATA_MODIFIED = 1;
	private final static int EVENT_MODE_CHANGED = 2;

	private int precicion = 3;
	private double minScore = 0.4;
	
	private int viewMode = VIEW_MODE_NORMAL;
	private List linkedListeners = new LinkedList();
	private ObjectList left = new ObjectList();
	private ObjectList right = new ObjectList();

	private ILinkMatcher linkMatcher = null;
	
	/**
	 * @param leftObject
	 * @param rightObject
	 */
	public ObjectLinkModel(ObjectListDef leftObject, ObjectListDef rightObject) {

		left.listDef = leftObject;
		right.listDef = rightObject;
		loadData();
		setupData();
	}
	
	/**
	 * adds given listener to the listeners list
	 * 
	 * @param listener
	 */
	public synchronized void addLinkListener(IObjectLinkListener listener) {
		linkedListeners.add(listener);
	}

	/**
	 * 
	 */
	public void doGroup(int leftColumn, int rightColumn) {
		
		viewMode = VIEW_MODE_GROUP;
		fireEvent(new ObjectLinkEvent(this), EVENT_MODE_CHANGED);

		left.groupColumn = leftColumn;
		right.groupColumn = rightColumn;
		setupData();
		
	}

	/**
	 * 
	 */
	public boolean doLinkElements(String leftKey, String rightKey) {
		
		RowElement elmLeft = left.contentProvider.getElementByKey(leftKey);
		RowElement elmRight = right.contentProvider.getElementByKey(rightKey);
		if (elmLeft != null && !elmLeft.isEmpty() && elmRight != null && !elmRight.isEmpty()) {
			ObjectLinkEvent event = new ObjectLinkEvent(this, elmLeft.getKey(), elmRight.getKey()); 
			fireEvent(event, EVENT_LINK);
			if (!event.isCanceled()) {
				
				// remove the two elements from the list
				left.data.remove(elmLeft);
				right.data.remove(elmRight);
				setupData();
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 
	 */
	public void doNormalMode() {
		viewMode = VIEW_MODE_NORMAL;
		fireEvent(new ObjectLinkEvent(this), EVENT_MODE_CHANGED);
		setupData();
	}

	/**
	 * Notifies registered IObjectLinkListeners about elementsSelected
	 * event.
	 * @param event
	 */
	private void fireEvent(ObjectLinkEvent event, int type) {
		
		Object[] listeners = linkedListeners.toArray();
		for (int i = 0; i < listeners.length; i++) {
			IObjectLinkListener listener = (IObjectLinkListener)listeners[i];
			switch (type) {
			case EVENT_LINK:
				listener.linkElementsRequested(event);
				break;
			case EVENT_DATA_MODIFIED:
				listener.dataModified(event);
				break;
			case EVENT_MODE_CHANGED:
				listener.modeChanged(event);
				break;
			}
		}
		
	}

	/**
	 * @param left2
	 * @return
	 */
	public IContentProvider getContentProvider(int list) {
		switch (list) {
		case LEFT: 
			return left.contentProvider;
		case RIGHT:
			return right.contentProvider;
		default:
			throw new IllegalArgumentException("Unknown list.");
		}
	}

	
	/**
	 * @return the precicion
	 */
	public int getPrecicion() {
		return precicion;
	}

	/**
	 * @param left2
	 * @return
	 */
	public TableColumn[] getTableColumns(int list) {
		switch (list) {
		case LEFT: 
			return left.listDef.getTableColumns();
		case RIGHT:
			return right.listDef.getTableColumns();
		default:
			throw new IllegalArgumentException("Unknown list.");
		}
	}

	/**
	 * Load the initial data.
	 *
	 */
	public void loadData() {
		
		// index the columns
		TableColumn[] leftCols = left.listDef.getTableColumns();
		TableColumn[] rightCols = right.listDef.getTableColumns();
		for (int i = 0; i < leftCols.length; i++) {
			leftCols[i].setUserObject(new Integer(i));
		}
		for (int i = 0; i < rightCols.length; i++) {
			rightCols[i].setUserObject(new Integer(i));
		}
		left.data = loadData(left.listDef);
		right.data = loadData(right.listDef);
		
	}

	/**
	 * Load the data from the providers.
	 */
	private List loadData(ObjectListDef listDef) {

		List list = new ArrayList();
		IContentProvider contentProvider = listDef.getContentProvider();
		ITableLabelProvider labelProvider = listDef.getLabelProvider();
		TableColumn[] cols = listDef.getTableColumns();
		Iterator it = contentProvider.getContentIterator(new Range(0, -1));
		
		try {
			while (it.hasNext()) {
				Object row = it.next();
				String[] data = new String[cols.length];
				for (int i = 0; i < cols.length; i++) {
					TableColumn col = (TableColumn)cols[i].clone();
					col.setIndex(i);
					data[i] = labelProvider.getCellLabel(row, col, new RowContext()).toString();
				}
				String key = contentProvider.getUniqueKey(row);
				list.add(new RowElement(key, data));
			}
		} catch (CloneNotSupportedException e) {
			throw new RuntimeException("Ciritcal Error: clone failed." + e, e);
		}
		
		return list;
	}

	/**
	 * removes given listener from the listeners list
	 * 
	 * @param listener
	 */
	public synchronized void removeLinkListener(IObjectLinkListener listener) {
		linkedListeners.remove(listener);
	}

	/**
	 * Set the number of characters used to match two entries in GROUP mode.
	 * @param precicion the precicion to set
	 */
	public void setPrecicion(int precicion) {
		this.precicion = precicion;
	}

	/**
	 * 
	 */
	private void setupData() {

		List leftList = new ArrayList();
		List rightList = new ArrayList();
		
		switch (viewMode) {
		case VIEW_MODE_NORMAL:
			// in normal mode, we can simply display the original data.
			leftList = left.data;
			rightList = right.data;
			break;
			
		case VIEW_MODE_FIND:
			leftList = left.data;
			rightList = new ArrayList();
			break;
			
		case VIEW_MODE_GROUP:
			
			// group mode requires the data to be sorted by the "group" columns.
			removeSortIcon(LEFT);
			removeSortIcon(RIGHT);
			sortList(LEFT, left.listDef.getTableColumns()[left.groupColumn]);
			sortList(RIGHT, right.listDef.getTableColumns()[right.groupColumn]);
			
			Iterator itLeft = left.data.iterator();
			Iterator itRight = right.data.iterator();
			RowElement nextRight = null;
			RowElement nextLeft = null;
			
			while (itLeft.hasNext() || itRight.hasNext()) {
				if (nextLeft == null) {
					if (itLeft.hasNext()) {
						nextLeft = (RowElement)itLeft.next();
					} else {
						nextLeft = new RowElement(); 
					}
				}
				if (nextRight == null) {
					if (itRight.hasNext()) {
						nextRight = (RowElement)itRight.next();
					} else {
						nextRight = new RowElement();
					}
				}
				
				int comp = 0;
				if (!nextLeft.isEmpty() && !nextRight.isEmpty()) {
				
					String leftValue = nextLeft.getData()[left.groupColumn];
					String rightValue = nextRight.getData()[right.groupColumn];
					if (leftValue.length() > precicion) {
						leftValue = leftValue.substring(0, precicion);
					}
					if (rightValue.length() > precicion) {
						rightValue = rightValue.substring(0, precicion);
					}
				
					comp = leftValue.compareTo(rightValue);
					
				}
				
				if (comp <= 0) {
					leftList.add(nextLeft);
					nextLeft = null;
				} else {
					leftList.add(new RowElement());
				}
				if (comp >= 0) {
					rightList.add(nextRight);
					nextRight = null;
				} else {
					rightList.add(new RowElement());
				}
				
			}
			break;
		}
		
		
		left.contentProvider.setData(leftList);
		right.contentProvider.setData(rightList);
		fireEvent(new ObjectLinkEvent(this, LEFT | RIGHT), EVENT_DATA_MODIFIED);
	}

	/**
	 * @param left2
	 */
	private void removeSortIcon(int position) {
		ObjectList list = position == LEFT ? left : right;
		TableColumn[] cols = list.listDef.getTableColumns();
		for (int i = 0; i < cols.length; i++) {
			cols[i].setSortIcon(TableColumn.SORT_ICON_NONE);
		}
	}

	/**
	 * @param left2
	 * @param tableColumn
	 */
	public void sortList(int position, TableColumn tableColumn) {
		
		int idx = ((Integer)tableColumn.getUserObject()).intValue();
		
		ObjectList list = position == LEFT ? left : right;
		Collections.sort(list.data, new RowElementComparator(idx, tableColumn.getSortIcon() == TableColumn.SORT_ICON_UP));
		list.contentProvider.setData(list.data);
		
		// mark TableColumn "sorted"
		TableColumn[] cols = list.listDef.getTableColumns();
		for (int i = 0; i < cols.length; i++) {
			if (!cols[i].equals(tableColumn)) {
				cols[i].setSortIcon(TableColumn.SORT_ICON_NONE);
			}
		}
		if (tableColumn.getSortIcon() == TableColumn.SORT_ICON_UP) {
			tableColumn.setSortIcon(TableColumn.SORT_ICON_DOWN);
		} else {
			tableColumn.setSortIcon(TableColumn.SORT_ICON_UP);
		}
		
		ObjectLinkEvent event = new ObjectLinkEvent(this);
		event.setListNo(position);
		fireEvent(event, EVENT_DATA_MODIFIED);
		
	}

	/**
	 * @return the viewMode
	 */
	public int getViewMode() {
		return viewMode;
	}

	/**
	 * 
	 */
	public void doFindMode() {
		viewMode = VIEW_MODE_FIND;
		fireEvent(new ObjectLinkEvent(this), EVENT_MODE_CHANGED);
		
		TableColumn[] cols = right.listDef.getTableColumns();
		for (int i = 0; i < cols.length; i++) {
			cols[i].setSortIcon(TableColumn.SORT_ICON_NONE);
		}
		setupData();
	}

	/**
	 * Search for potential matches in the RIGHT list. 
	 * @param key from the RIGHT list.
	 */
	public void findMatches(String key) {
		
		if (linkMatcher == null) {
			throw new IllegalStateException("No matcher specified.");
		}			
		List matches = new ArrayList();
		RowElement element = left.contentProvider.getElementByKey(key);
		
		for (Iterator it = right.data.iterator(); it.hasNext(); ) {
			RowElement rightRow = (RowElement)it.next();
			double score = linkMatcher.evaluate(element, rightRow);
			rightRow.setScore(score);
			if (score >= minScore) {
				matches.add(rightRow);
			}
		}

		Collections.sort(matches, new RowElementComparator(-1));
		
		right.contentProvider.setData(matches);
		fireEvent(new ObjectLinkEvent(this, RIGHT), EVENT_DATA_MODIFIED);
	
		
	}

	/**
	 * @return the linkMatcher
	 */
	public ILinkMatcher getLinkMatcher() {
		return linkMatcher;
	}

	/**
	 * @param linkMatcher the linkMatcher to set
	 */
	public void setLinkMatcher(ILinkMatcher linkMatcher) {
		this.linkMatcher = linkMatcher;
	}

	/**
	 * @return the minScore
	 */
	public double getMinScore() {
		return minScore;
	}

	/**
	 * @param minScore the minScore to set
	 */
	public void setMinScore(double minScore) {
		this.minScore = minScore;
	}

}
