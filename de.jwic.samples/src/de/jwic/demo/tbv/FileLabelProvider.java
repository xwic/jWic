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
class FileLabelProvider implements ITableLabelProvider {
	
	private static ImageRef IMG_FOLDER = new ImageRef(FileLabelProvider.class.getPackage(), "folder.png");
	private static ImageRef IMG_OPENFOLDER = new ImageRef(FileLabelProvider.class.getPackage(), "openfolder.png");
	
	/*(non-Javadoc)
	 * @see de.jwic.ecolib.treeviewer.ITreeLabelProvider#getCellLabel(java.lang.Object, de.jwic.ecolib.tableviewer.TableColumn, de.jwic.ecolib.treeviewer.RowContext)
	 */
	public CellLabel getCellLabel(Object row, TableColumn column, RowContext rowContext) {
		CellLabel cellLabel = new CellLabel();
		
		FileTreeNode file = (FileTreeNode)row;
		switch (column.getIndex()) {
		case 0: // done 
			cellLabel.text = file.toString();
			cellLabel.image = rowContext.isExpanded() ? IMG_OPENFOLDER : IMG_FOLDER;
			break;
		}
		return cellLabel;
	}
}