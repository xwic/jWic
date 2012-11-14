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
 * de.jwic.ecolib.samples.objectlink.OrderLabelProvider
 * Created on 12.04.2007
 * $Id: OrderLabelProvider.java,v 1.1 2007/04/25 09:59:15 lordsam Exp $
 */
package de.jwic.ecolib.samples.objectlink;

import java.text.SimpleDateFormat;

import de.jwic.ecolib.tableviewer.CellLabel;
import de.jwic.ecolib.tableviewer.ITableLabelProvider;
import de.jwic.ecolib.tableviewer.RowContext;
import de.jwic.ecolib.tableviewer.TableColumn;

/**
 *
 * @author Florian Lippisch
 */
public class OrderLabelProvider implements ITableLabelProvider {

	private SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	
	/* (non-Javadoc)
	 * @see de.jwic.ecolib.tableviewer.ITableLabelProvider#getCellLabel(java.lang.Object, de.jwic.ecolib.tableviewer.TableColumn)
	 */
	public CellLabel getCellLabel(Object row, TableColumn column, RowContext context) {
		Order order = (Order)row;
		switch (column.getIndex()) {
		case 0: 
			return new CellLabel(order.id);
		case 1:
			return new CellLabel(order.customerName);
		case 2: 
			return new CellLabel(order.product);
		case 3:
			return new CellLabel(df.format(order.date)); 
		}
		return null;
	}

}
