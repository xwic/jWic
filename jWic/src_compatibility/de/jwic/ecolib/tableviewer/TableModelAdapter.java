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
 * de.jwic.ecolib.tableviewer.TableModelAdapter
 * Created on 15.03.2007
 * $Id: TableModelAdapter.java,v 1.4 2008/07/24 10:44:19 cosote Exp $
 */
package de.jwic.ecolib.tableviewer;


/**
 * Adapter for the ITableModelListener.
 * @author Florian Lippisch
 */
public abstract class TableModelAdapter implements ITableModelListener {
	private static final long serialVersionUID = 1L;
	/* (non-Javadoc)
	 * @see de.jwic.ecolib.tableviewer.ITableModelListener#columnSelected(de.jwic.ecolib.tableviewer.TableModelEvent)
	 */
	public void columnSelected(TableModelEvent event) {

	}

	/* (non-Javadoc)
	 * @see de.jwic.ecolib.tableviewer.ITableModelListener#rangeUpdated(de.jwic.ecolib.tableviewer.TableModelEvent)
	 */
	public void rangeUpdated(TableModelEvent event) {

	}

	/* (non-Javadoc)
	 * @see de.jwic.ecolib.tableviewer.ITableModelListener#nodeCollapsed(de.jwic.ecolib.tableviewer.TableModelEvent)
	 */
	public void nodeCollapsed(TableModelEvent event) {
		
	}

	/* (non-Javadoc)
	 * @see de.jwic.ecolib.tableviewer.ITableModelListener#nodeExpanded(de.jwic.ecolib.tableviewer.TableModelEvent)
	 */
	public void nodeExpanded(TableModelEvent event) {
		
	}
	/* (non-Javadoc)
	 * @see de.jwic.ecolib.tableviewer.ITableModelListener#columnResized(de.jwic.ecolib.tableviewer.TableModelEvent)
	 */
	public void columnResized(TableModelEvent event) {
		
	}
	
	/* (non-Javadoc)
	 * @see de.jwic.ecolib.tableviewer.ITableModelListener#contentChanged(de.jwic.ecolib.tableviewer.TableModelEvent)
	 */
	public void contentChanged(TableModelEvent event) {
		
	}
}
