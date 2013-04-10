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
 * de.jwic.ecolib.tableviewer.ITableModelListener
 * Created on 12.03.2007
 * $Id: ITableModelListener.java,v 1.5 2008/07/24 10:44:19 cosote Exp $
 */
package de.jwic.ecolib.tableviewer;

import java.io.Serializable;

/**
 * Listener for TableModel events.
 * 
 * @author Florian Lippisch
 */
public interface ITableModelListener extends Serializable {

	/**
	 * Fired when the user selected a different page or changed
	 * the maximum number of visible lines. 
	 * @param event
	 */
	public void rangeUpdated(TableModelEvent event);
	
	/**
	 * Fired when the user selected a column. The event object
	 * contains the selected TableColumn;
	 * @param event
	 */
	public void columnSelected(TableModelEvent event);
	
	/**
	 * Fired when the user expanded a node. The event contains the
	 * uniqueKey of the expanded node.
	 * @param event
	 */
	public void nodeExpanded(TableModelEvent event);

	/**
	 * Fired when the user collapsed a node. The event contains the 
	 * uniqueKey of the collapsed node.
	 * @param event
	 */
	public void nodeCollapsed(TableModelEvent event);

	/**
	 * Fired when a column has been resized.
	 * @param event
	 */
	public void columnResized(TableModelEvent event);

	/**
	 * Fired when the content changed and TableViewer is redrawn.
	 * @param event
	 */
	public void contentChanged(TableModelEvent event);
}
