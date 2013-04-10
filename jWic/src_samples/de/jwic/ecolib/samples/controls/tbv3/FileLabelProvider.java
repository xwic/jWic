package de.jwic.ecolib.samples.controls.tbv3;

import de.jwic.base.ImageRef;
import de.jwic.ecolib.tableviewer.CellLabel;
import de.jwic.ecolib.tableviewer.ITableLabelProvider;
import de.jwic.ecolib.tableviewer.RowContext;
import de.jwic.ecolib.tableviewer.TableColumn;

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