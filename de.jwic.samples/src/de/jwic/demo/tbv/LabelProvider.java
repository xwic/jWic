/*******************************************************************************
 * Copyright 2015 xWic group (http://www.xwic.de)
 *
 * Licensed under the Apache License, Version 2.0 (the "License").
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * 		http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software 
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and 
 * limitations under the License.
 *  
 *******************************************************************************/
package de.jwic.demo.tbv;

import de.jwic.base.ImageRef;
import de.jwic.controls.tableviewer.CellLabel;
import de.jwic.controls.tableviewer.ITableLabelProvider;
import de.jwic.controls.tableviewer.RowContext;
import de.jwic.controls.tableviewer.TableColumn;

/**
 * Used to transform the object into renderable data.
 * @author Florian Lippisch
 */
public class LabelProvider implements ITableLabelProvider {
	
	private static ImageRef IMG_CHECKED = new ImageRef(LabelProvider.class.getPackage(), "checked.gif");
	private static ImageRef IMG_UNCHECKED = new ImageRef(LabelProvider.class.getPackage(), "unchecked.gif");
	
	/* (non-Javadoc)
	 * @see de.jwic.ecolib.tableviewer.ITableLabelProvider#writeCellLabel(de.jwic.ecolib.tableviewer.CellLabel, java.lang.Object, de.jwic.ecolib.tableviewer.TableColumn)
	 */
	public CellLabel getCellLabel(Object row, TableColumn column, RowContext context) {
		CellLabel cellLabel = new CellLabel();
		
		DemoTask task = (DemoTask)row;
		switch (column.getIndex()) {
		case 0: // done 
			cellLabel.image = task.done ? IMG_CHECKED : IMG_UNCHECKED;
			break;
		case 1: // task 
			cellLabel.text = task.title;
			break;
		case 2: // owner
			cellLabel.text = task.owner;
			break;
		case 3: 
			cellLabel.text = task.completed + "%";
			if (task.completed < 15) {
				cellLabel.cssClass = "taskDemoRed";
			}
			break;
			
		}
		return cellLabel;
	}
}