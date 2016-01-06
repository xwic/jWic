package de.jwic.mobile.controls.mgrid;

/**
 * 
 * @author karolinamarek
 *
 */
public class MGridDataModel {

	private IGridCellRenderer cellRenderer;

	public MGridDataModel(IGridCellRenderer cellRenderer) {
		this.cellRenderer = cellRenderer;
	}

	public IGridCellRenderer getCellRenderer() {
		return cellRenderer;
	}

	public void setCellRenderer(IGridCellRenderer cellRenderer) {
		this.cellRenderer = cellRenderer;
	}

}
