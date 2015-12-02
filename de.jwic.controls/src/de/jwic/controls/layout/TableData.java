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
 * The TableData class is intended to hold specific layoutdata
 * of a single control. <p>
 * 
 * The TableData is used in the TableLayoutContainer to layout the
 * controls. At the moment simple layoutinfos like align, width,
 * height etc. are possible. <br>
 * 
 * @author Ronny Pfretzschner
 * @author Florian Lippisch
 */
public class TableData implements Serializable {

	private static final long serialVersionUID = 1L;

	public final static String ALIGN_TOP = "top";
	public final static String ALIGN_BOTTOM = "bottom";
	
	public final static String ALIGN_LEFT = "left";
	public final static String ALIGN_RIGHT = "right";
	public final static String ALIGN_CENTER = "center";

	private String width = "";
	private String height = "";
	private String align = ""; // default
	private String vAlign = ""; // default
	private String sCSSClass = "";
	
	private int colSpan = 1;
	private int rowSpan = 1;
	
	
	/**
	 * Default Constructor.
	 *
	 */
	public TableData() {
		
	}
	
	/**
	 * Construct a new TableData with a specified alignment, row- and colspan.
	 * @param align
	 * @param colSpan
	 * @param rowSpan
	 */
	public TableData(String align, int colSpan, int rowSpan) {
		this.align = align;
		if (colSpan < 1) 
			colSpan = 1;
		if (rowSpan < 1)
			rowSpan = 1;
		
		this.colSpan = colSpan;
		this.rowSpan = rowSpan;
	}
	
	/**
	 * Construct a new TableData with a specified alignment, row- and colspan.
	 * @param align
	 * @param colSpan
	 * @param rowSpan
	 */
	public TableData(String align, String valign, int colSpan, int rowSpan) {
		this.align = align;
		this.vAlign = valign;
		if (colSpan < 1) 
			colSpan = 1;
		if (rowSpan < 1)
			rowSpan = 1;
		
		this.colSpan = colSpan;
		this.rowSpan = rowSpan;
	}
	
	/**
	 * @return Returns the align.
	 */
	public String getAlign() {
		return align;
	}
	/**
	 * @param align The align to set.
	 */
	public void setAlign(String align) {
		this.align = align;
	}
	/**
	 * If the property grabVerticalSize is set true, 
	 * this returns 100%.
	 * 
	 * @return Returns the height.
	 */
	public String getHeight() {
		return height;
	}
	/**
	 * @param height The height to set.
	 */
	public void setHeight(String height) {
		this.height = height;
	}
	/**
	 * If the property grabHorizontalSize is set true, 
	 * this returns 100%.
	 * 
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
		return colSpan;
	}
	/**
	 * @param colSpan The colSpan to set.
	 */
	public void setColSpan(int colSpan) {
		this.colSpan = colSpan;
	}
	/**
	 * @return Returns the rowSpan.
	 */
	public int getRowSpan() {
		return rowSpan;
	}
	/**
	 * @param rowSpan The rowSpan to set.
	 */
	public void setRowSpan(int rowSpan) {
		this.rowSpan = rowSpan;
	}

	/**
	 * @return Returns the vAlign.
	 */
	public String getVAlign() {
		return vAlign;
	}

	/**
	 * @param align The vAlign to set.
	 */
	public void setVAlign(String align) {
		vAlign = align;
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
	}
}
