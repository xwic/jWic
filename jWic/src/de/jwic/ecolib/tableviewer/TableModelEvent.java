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
 * de.jwic.ecolib.tableviewer.TableModelEvent
 * Created on 13.03.2007
 * $Id: TableModelEvent.java,v 1.3 2007/04/20 12:46:20 lordsam Exp $
 */
package de.jwic.ecolib.tableviewer;

import de.jwic.base.Event;

/**
 * Event object for ITableModelListener.
 * @author Florian Lippisch
 */
public class TableModelEvent extends Event {
	private static final long serialVersionUID = 1L;
	private TableColumn tableColumn = null;
	private String rowKey = null;
	
	/**
	 * @param eventSource
	 * @param tableColumn
	 * @param rowKey
	 */
	public TableModelEvent(Object eventSource, String rowKey) {
		super(eventSource);
		this.rowKey = rowKey;
	}

	/**
	 * @param eventSource
	 */
	public TableModelEvent(Object eventSource) {
		super(eventSource);
	}

	/**
	 * @return the tableColumn
	 */
	public TableColumn getTableColumn() {
		return tableColumn;
	}

	/**
	 * @param tableColumn the tableColumn to set
	 */
	public void setTableColumn(TableColumn tableColumn) {
		this.tableColumn = tableColumn;
	}

	/**
	 * @return the rowKey
	 */
	public String getRowKey() {
		return rowKey;
	}

	/**
	 * @param rowKey the rowKey to set
	 */
	public void setRowKey(String rowKey) {
		this.rowKey = rowKey;
	}

}
