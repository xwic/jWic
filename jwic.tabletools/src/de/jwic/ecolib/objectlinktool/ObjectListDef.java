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
 * de.jwic.ecolib.objectlinktool.ObjectListDef
 * Created on Apr 5, 2007
 * $Id: ObjectListDef.java,v 1.1 2007/04/25 09:59:13 lordsam Exp $
 */
package de.jwic.ecolib.objectlinktool;

import java.io.Serializable;

import de.jwic.ecolib.tableviewer.IContentProvider;
import de.jwic.ecolib.tableviewer.ITableLabelProvider;
import de.jwic.ecolib.tableviewer.TableColumn;

/**
 * This object is used by the ObjectLink Tool.
 *
 * @author Aron Cotrau
 */
public class ObjectListDef implements Serializable {

	private IContentProvider contentProvider = null;
	private ITableLabelProvider labelProvider = null;
	private TableColumn[] tableColumns = null;
	

	/**
	 * 
	 */
	public ObjectListDef() {
		super();
	}

	/**
	 * @param contentProvider
	 * @param labelProvider
	 * @param tableColumns
	 */
	public ObjectListDef(IContentProvider contentProvider, ITableLabelProvider labelProvider, TableColumn[] tableColumns) {
		super();
		this.contentProvider = contentProvider;
		this.labelProvider = labelProvider;
		this.tableColumns = tableColumns;
	}

	/**
	 * @return the contentProvider
	 */
	public IContentProvider getContentProvider() {
		return contentProvider;
	}

	/**
	 * @param contentProvider the contentProvider to set
	 */
	public void setContentProvider(IContentProvider contentProvider) {
		this.contentProvider = contentProvider;
	}

	/**
	 * @return the labelProvider
	 */
	public ITableLabelProvider getLabelProvider() {
		return labelProvider;
	}

	/**
	 * @param labelProvider the labelProvider to set
	 */
	public void setLabelProvider(ITableLabelProvider labelProvider) {
		this.labelProvider = labelProvider;
	}

	/**
	 * @return the tableColumns
	 */
	public TableColumn[] getTableColumns() {
		return tableColumns;
	}

	/**
	 * @param tableColumns the tableColumns to set
	 */
	public void setTableColumns(TableColumn[] tableColumns) {
		this.tableColumns = tableColumns;
	}
}
