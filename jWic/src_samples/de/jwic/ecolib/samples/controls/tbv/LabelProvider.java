package de.jwic.ecolib.samples.controls.tbv;

import de.jwic.base.ImageRef;
import de.jwic.ecolib.tableviewer.CellLabel;
import de.jwic.ecolib.tableviewer.ITableLabelProvider;
import de.jwic.ecolib.tableviewer.RowContext;
import de.jwic.ecolib.tableviewer.TableColumn;

/**
 * Used to transform the object into renderable data.
 * @author Florian Lippisch
 */
class LabelProvider implements ITableLabelProvider {
	
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