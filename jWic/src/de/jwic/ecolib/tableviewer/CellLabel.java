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
 * de.jwic.ecolib.tableviewer.CellLabel
 * Created on 12.03.2007
 * $Id: CellLabel.java,v 1.6 2010/04/06 14:36:17 cosote Exp $
 */
package de.jwic.ecolib.tableviewer;

import de.jwic.base.ImageRef;

/**
 * Contains text, image and style informations used to render a
 * specific cell. The CellLabel object is used to generate
 * @author Florian Lippisch
 */
public class CellLabel {

	/**
	 * The text displayed in a cell.
	 */
	public String text = null;
	
	/**
	 * The optional Object representing the cell's content.
	 * Used by ITableLabelProvider's that need more information about the content. 
	 */
	public Object object = null;
	
	/**
	 * The specifiecation of an image that is displayed in
	 * the cell.
	 */
	public ImageRef image = null;
	
	/**
	 * The name of a custom css Class.
	 */
	public String cssClass = null;
	
	/**
	 * The mouseover text of this cell text
	 */
	public String toolTip = null;
	
	/**
	 * The colspan of this cell.
	 * null when not used.
	 */
	public Integer colspan = null;
	
	/**
	 * @param image
	 */
	public CellLabel(ImageRef image) {
		super();
		this.image = image;
	}


	/**
	 * @param text
	 */
	public CellLabel(String text) {
		super();
		this.text = text;
	}


	/**
	 * 
	 */
	public CellLabel() {
		super();
	}


	/**
	 * @param text
	 * @param image
	 */
	public CellLabel(String text, ImageRef image) {
		super();
		this.text = text;
		this.image = image;
	}

	/**
	 * Clears all values.
	 */
	public void clear() {
		text = null;
		image = null;
		cssClass = null;
		toolTip = null;
		colspan = null;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return object != null ? object.toString() : text;
	}
}
