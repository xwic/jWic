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
 * de.jwic.ecolib.objectlinktool.RowElementLabelProvider
 * Created on 13.04.2007
 * $Id: RowElementLabelProvider.java,v 1.1 2007/04/25 09:59:13 lordsam Exp $
 */
package de.jwic.ecolib.objectlinktool;

import java.text.NumberFormat;

import de.jwic.ecolib.tableviewer.CellLabel;
import de.jwic.ecolib.tableviewer.ITableLabelProvider;
import de.jwic.ecolib.tableviewer.RowContext;
import de.jwic.ecolib.tableviewer.TableColumn;

/**
 * Creates the label data based on RowElements.
 * @author Florian Lippisch
 */
public class RowElementLabelProvider implements ITableLabelProvider {

	private final static CellLabel EMPTY_LABEL = new CellLabel("");
	
	/* (non-Javadoc)
	 * @see de.jwic.ecolib.tableviewer.ITableLabelProvider#getCellLabel(java.lang.Object, de.jwic.ecolib.tableviewer.TableColumn)
	 */
	public CellLabel getCellLabel(Object obj, TableColumn column, RowContext context) {
		RowElement row = (RowElement)obj;
		
		if (row.isEmpty()) {
			return EMPTY_LABEL;
		}
		
		Integer idx = (Integer)column.getUserObject();
		if (idx.intValue() == -1) {
			NumberFormat nf = NumberFormat.getPercentInstance();
			return new CellLabel(nf.format(row.getScore()));
		} 
		return new CellLabel(row.getData()[idx.intValue()]);
		
	}

}
