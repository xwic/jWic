package de.jwic.mobile.controls.mtable;

import java.io.Serializable;
import java.util.List;

/**
 * 
 * @author Karolina Marek (karolina-marek.eu)
 *
 * @date 02.01.2016
 */
public class MTableModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7653038764082543151L;

	private List<MColumn> columns;

	private List<Object> rows;
	
	private IMTableCellRenderer cellRenderer =new DefaultMTableCellRenderer();

	public MTableModel() {
		this(null, null);
	}

	public MTableModel(List<MColumn> columns) {
		this(columns, null);
		
	}

	public MTableModel(List<MColumn> columns, List<Object> rows) {
		this.columns = columns;
		this.rows = rows;
		
	}

	public List<MColumn> getColumns() {
		return columns;
	}

	public void setColumns(List<MColumn> columns) {
		this.columns = columns;
	}

	public List<Object> getRows() {
		return rows;
	}

	public void setRows(List<Object> rows) {
		this.rows = rows;
	}

	public IMTableCellRenderer getCellRenderer() {
		return cellRenderer;
	}

	public void setCellRenderer(IMTableCellRenderer cellRenderer) {
		this.cellRenderer = cellRenderer;
	}
	
	

}
