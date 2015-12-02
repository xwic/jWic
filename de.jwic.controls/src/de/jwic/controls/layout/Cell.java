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

import java.io.Serializable;

/**
 * Specifies the cell in a table.
 * @author Florian Lippisch
 * @version $Revision: 1.2 $
 */
public class Cell implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String width = "";
	private String controlName = "";
	private int rowNum = 0;
	private int colNum = 0;
	private boolean render = true;
	
	private TableData tableData = null;
	
	/**
	 * Default Constructor.
	 * @param rowNum
	 * @param colNum
	 */
	public Cell(int rowNum, int colNum) {
		this.rowNum = rowNum;
		this.colNum = colNum;
	}
	
	/**
	 * Default Constructor.
	 * @param rowNum
	 * @param colNum
	 */
	public Cell(int rowNum, int colNum, TableData td) {
		this.rowNum = rowNum;
		this.colNum = colNum;
		this.tableData = td;
	}

	/**
	 * @return Returns the colNum.
	 */
	public int getColNum() {
		return colNum;
	}

	/**
	 * @param colNum The colNum to set.
	 */
	public void setColNum(int colNum) {
		this.colNum = colNum;
	}

	/**
	 * @return Returns the controlName.
	 */
	public String getControlName() {
		return controlName;
	}

	/**
	 * @param controlName The controlName to set.
	 */
	public void setControlName(String controlName) {
		this.controlName = controlName;
	}

	/**
	 * @return Returns the rowNum.
	 */
	public int getRowNum() {
		return rowNum;
	}

	/**
	 * @param rowNum The rowNum to set.
	 */
	public void setRowNum(int rowNum) {
		this.rowNum = rowNum;
	}

	/**
	 * @return Returns the width.
	 */
	public String getWidth() {
		return width;
	}

	/**
	 * @param width The width to set.
	 */
	public void setWidth(String width) {
		this.width = width;
	}

	/**
	 * @return Returns the colSpan.
	 */
	public int getColSpan() {
		return tableData != null ? tableData.getColSpan() : 1; 
	}

	/**
	 * @return Returns the rowSpan.
	 */
	public int getRowSpan() {
		return tableData != null ? tableData.getRowSpan() : 1;
	}

	/**
	 * @return Returns the render.
	 */
	public boolean isRender() {
		return render;
	}

	/**
	 * @param render The render to set.
	 */
	public void setRender(boolean render) {
		this.render = render;
	}

	/**
	 * @return Returns the align.
	 */
	public String getAlign() {
		return tableData != null ? tableData.getAlign() : "";
	}

	/**
	 * @return Returns the vAlign.
	 */
	public String getVAlign() {
		return tableData != null ? tableData.getVAlign() : "";
	}
	
	/**
	 * Returns the CSS class for the cell (td tag).
	 * @return
	 */
	public String getCSSClass() {
		return tableData != null ? tableData.getCSSClass() : "";
	}

}
