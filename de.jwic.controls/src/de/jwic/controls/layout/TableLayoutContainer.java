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

package de.jwic.controls.layout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import de.jwic.base.Control;
import de.jwic.base.ControlContainer;
import de.jwic.base.IControlContainer;
import de.jwic.base.JWicException;

/**
 * The TableLayoutContainer is a special form of a IControlContainer. <p>
 * 
 * This Container follows a simple layoutstructure in form of a table. <br>
 * The Controls are added in sequence and are displayed in the given columns partition.
 * The columns can be set through constructor or setter. <br>
 * TableLayoutContainer.vtl is the template for this container. <br> 
 * The template simply uses table tags to form the layout of the control.
 * <br><br> 
 * This container also has its own layoutdata like alignment, width, height and border.
 * 
 * @author Ronny Pfretzschner
 * @author Florian Lippisch
 *
 */
public class TableLayoutContainer extends ControlContainer {

	private static final long serialVersionUID = 1L;

	public final static String ALIGN_DEFAULT = "";
	public final static String ALIGN_LEFT = "left";
	public final static String ALIGN_CENTER = "center";
	public final static String ALIGN_RIGHT = "right";
	
	// layout properties
	private String align = ALIGN_DEFAULT;
	private int borderSize = -1;	// -1 == default
	private int cellSpacing = -1;	// -1 == default
	private int cellPadding = -1;	// -1 == default
	private String sCSSClass = "";
	
	// size settings
	private String height= "";
	private String width = "";

	private Map<String, TableData> allLayoutInfos = new HashMap<String, TableData>();
	private List<String> childNames = new ArrayList<String>();
	
	private int columnCount = 1;
	private int[] colWidths = new int[1];
	private Cell[][] cells = null;
	private Row[] rows = null;
	private int rowCount = -1; // will be calculated if required.
	
	
	/**
	 * Constructs a new TableLayoutContainer with a default name.
	 * 
	 */
	public TableLayoutContainer(IControlContainer container){
		this(container, null);
	}

	/**
	 * Constructs a new TableLayoutContainer with the specified name.
	 * 
	 */
	public TableLayoutContainer(IControlContainer container, String name){
		super(container, name);
	}

	/**
	 * Constructor. <p>
	 * 
	 * @param cols Amount of columns, which the Container will be devided in.
	 */
	public TableLayoutContainer(IControlContainer container, String name, int columnCount){
		super(container, name);
		if (columnCount < 1) {
			throw new IllegalArgumentException("Argument wrong in " + getClass().getName() + " constructor: Set column amount not negative!");
		}
		this.columnCount = columnCount;
		this.colWidths = new int[columnCount];
	}
	
	/**
	 * @return Returns the align.
	 */
	public String getAlign() {
		return align;
	}
	
	/**
	 * Returns the list of childs.
	 * @return
	 */
	public List<String> getChildNames() {
		return childNames;
	}
	
	/**
	 * Returns the cols amount as an int. <p>
	 * 
	 * @return int The amount of columns
	 */
	public int getColumnCount() {
		return columnCount;
	}

	/**
	 * @return Returns the height.
	 */
	public String getHeight() {
		return height;
	}
	
	
	/**
	 * Get the TableData of a specific name of a control. <p>
	 * 
	 * null is only returned, if the controls name is belonging to a GridLayoutContainer. <p>
	 * 
	 * @param controlName
	 * @return GridData
	 */
	public TableData getLayoutData(String controlName) {
		TableData test = allLayoutInfos.get(controlName);
		return test;
	}
	
	/**
	 * Returns the rows required to render this table.
	 * @return
	 */
	public Row[] getRows() {
		
		if (rows == null) {
			layout();
		}
		return rows;
	}
	
	/**
	 * Returns the cells to the specified row.
	 * @param row
	 * @return
	 */
	public Cell[] getCells(Row row) {
		
		if (cells == null) { 
			layout();
		}
		return cells[row.getNum()];
	}
	
	/**
	 * @return Returns the width.
	 */
	public String getWidth() {
		return width;
	}
	
	/**
	 * Calculates the layout of the table.
	 */
	public void layout() {

		if (childNames.size() == 0) { // no controls added
			rows = new Row[0];
			cells = new Cell[0][0];
		} else {
		
			// step #1: calculate the number of rows
			int column = 0;
			int rowNum = 0;
			int[] spawn = new int[columnCount];
			
			for (Iterator<String> it = childNames.iterator(); it.hasNext(); ) {
				if (spawn[column] > 1) {
					spawn[column]--;
					column++;
				} else {
					String name = it.next();
					TableData td = getLayoutData(name);
					spawn[column] = td != null ? td.getRowSpan() : 1;
					column += td != null ? td.getColSpan() : 1;
				}
				if (column >= columnCount) {
					column = 0;
					if (it.hasNext()) {
						rowNum++;
					}
				}
			}
			rowCount = rowNum + 1;
			
			// step #2: setup the cells
			column = 0;
			rowNum = 0;
			int colIdx = 0;
			spawn = new int[columnCount];
			cells = new Cell[rowCount][columnCount];
			Row[] rowData = new Row[rowCount]; // create a max size row data
			rowData[rowNum] = new Row(rowNum);

			for (Iterator<String> it = childNames.iterator(); it.hasNext(); ) {
				if (spawn[column] > 1) {
					spawn[column]--;
					column++;
				} else {
					String name = it.next();
					TableData td = getLayoutData(name);
					spawn[column] = td.getRowSpan();
					Cell cell = new Cell(rowNum, column, td);
					cells[rowNum][colIdx] = cell;
					cell.setControlName(name);
					if (colWidths[column] != 0) {
						cell.setWidth(Integer.toString(colWidths[column]));
					}
					column += td.getColSpan();
					if (column > this.columnCount) {
						column = columnCount;
					}
					colIdx++;
				}
				
				if (colIdx < column) { // fill 'empty' cells
					for (int c = colIdx; c < column; c++) {
						cells[rowNum][c] = new Cell(rowNum, c);
						cells[rowNum][c].setRender(false);
					}
					colIdx = column;
				}
				
				if (column >= columnCount) {
					if (it.hasNext()) {
						column = 0;
						colIdx = 0;
						rowNum++;
						rowData[rowNum] = new Row(rowNum);
					}
				}
			}
			
			if (colIdx < columnCount) {
				for (int c = colIdx; c < columnCount; c++) {
					cells[rowNum][c] = new Cell(rowNum, c);
					cells[rowNum][c].setRender(false);
				}
			}
			
			rows = rowData;
			
		}
		
	}
	
	/* (non-Javadoc)
	 * @see de.jwic.base.ControlContainer#registerControl(de.jwic.base.Control, java.lang.String)
	 */
	public void registerControl(Control control, String name)
			throws JWicException {
		super.registerControl(control, name);
		childNames.add(control.getName());
		// add default GridData
		allLayoutInfos.put(control.getName(), new TableData());
		cells = null; // --> forces re-layout on next rendering
	}
	
	/**
	 * Removes a control from the container with the given controlName. <p>
	 * 
	 * @param String controlName 
	 */
	public void removeControl(String controlName) {
		allLayoutInfos.remove(controlName);
		childNames.remove(controlName);
		cells = null; // --> forces re-layout on next rendering
		super.removeControl(controlName);
	}
	
	/* (non-Javadoc)
	 * @see de.jwic.base.ControlContainer#unregisterControl(de.jwic.base.Control)
	 */
	public void unregisterControl(Control control) {
		allLayoutInfos.remove(control.getName());
		childNames.remove(control.getName());
		cells = null; // --> forces re-layout on next rendering
		super.unregisterControl(control);
	}
	
	/**
	 * @param align The align to set.
	 */
	public void setAlign(String align) {
		this.align = align;
		requireRedraw();
	}
	
	/**
	 * Sets the amount of available columns of the GridLayoutContainer. <p>
	 * 
	 * The default value is 1. Do not use negative values. An IllegalArgumentException<br>
	 * is thrown in that case.
	 * 
	 * @param cols The amount of columns of the GridLayoutContainer
	 */
	public void setColumnCount(int cols) {
		if (cols < 1) {
			throw new IllegalArgumentException("Argument wrong: Set column amount not 0 and not negative!");
		}
		this.columnCount = cols;
		cells = null;
		if (colWidths == null) {
			colWidths = new int[cols];
		} else {
			int[] newCols = new int[cols];
			System.arraycopy(colWidths, 0, newCols, 0, colWidths.length > cols ? cols : colWidths.length);
			colWidths = newCols;
		}
		requireRedraw();
	}

	/**
	 * Set the width of a specified column. Set a column width to -1 if you do not want
	 * to specify a size.
	 * @param col
	 * @param width
	 */
	public void setColWidth(int col, int width) {
		colWidths[col] = width;
		cells = null;
		requireRedraw();
	}

	/**
	 * @param height The height to set.
	 */
	public void setHeight(String height) {
		this.height = height;
		requireRedraw();
	}
	
	/**
	 * Set the layout of a control inside of this container.
	 * 
	 * If null is used for the GridData and the Control is NOT a GridLayoutContainer, <br>
	 * a default GridData is set with left alignment. <br><br>
	 * GridLayoutContainers can also be added. In this case, use null for layoutData.<br>
	 * This is anyway the only possible setting, if you do not use null, null will be set automatically. <br>
	 * GridLayoutContainers should use their own layoutproperties.
	 * 
	 * @param IControl control The control, which will be added in proper sequence to the LayoutContainer
	 * @param TableData layoutData The layoutData of the specific control. null, if it is a GridLayoutContainer
	 */
	public void setLayoutData(Control control, TableData layoutData) {

		if (control.getContainer() != this) {
			throw new IllegalArgumentException("The specified control is not member of this container.");
		}
		
//		if (control instanceof TableLayoutContainer) {
//			allLayoutInfos.put(control.getName(), null);
//		}
//		else {
			allLayoutInfos.put(control.getName(), layoutData != null ? layoutData : new TableData());
//		}
		requireRedraw();
		cells = null;
	}
	
	/**
	 * @param width The width to set.
	 */
	public void setWidth(String width) {
		this.width = width;
		requireRedraw();
	}

	/**
	 * @return Returns the borderSize.
	 */
	public int getBorderSize() {
		return borderSize;
	}

	/**
	 * The size of the border. If set to -1, the border property will
	 * not be specified on the table.
	 * @param borderSize The borderSize to set.
	 */
	public void setBorderSize(int borderSize) {
		this.borderSize = borderSize;
		requireRedraw();
	}

	/**
	 * @return Returns the cellPadding.
	 */
	public int getCellPadding() {
		return cellPadding;
	}

	/**
	 * Set the cellpadding property. If set to -1, the cellpadding 
	 * will not be specified on the table. (default)
	 * @param cellPadding The cellPadding to set.
	 */
	public void setCellPadding(int cellPadding) {
		this.cellPadding = cellPadding;
		requireRedraw();
	}

	/**
	 * @return Returns the cellSpacing.
	 */
	public int getCellSpacing() {
		return cellSpacing;
	}

	/**
	 * Set the cellspacing property. If set to -1, the cellspacing 
	 * will not be specified on the table. (default)
	 * @param cellSpacing The cellSpacing to set.
	 */
	public void setCellSpacing(int cellSpacing) {
		this.cellSpacing = cellSpacing;
		requireRedraw();
	}

	/**
	 * @return Returns the sCSSClass.
	 */
	public String getCSSClass() {
		return sCSSClass;
	}

	/**
	 * @param class1 The sCSSClass to set.
	 */
	public void setCSSClass(String cssClass) {
		sCSSClass = cssClass;
		requireRedraw();
	}

	
}
