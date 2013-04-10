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
 * de.jwic.ecolib.tableviewer.TableColumn
 * Created on 12.03.2007
 * $Id: TableColumn.java,v 1.8 2008/01/16 15:14:13 scholarius Exp $
 */
package de.jwic.ecolib.tableviewer;

import java.io.Serializable;

import de.jwic.base.ImageRef;

/**
 * Specifies a column in a table. 
 * @author Florian Lippisch
 */
public class TableColumn implements Serializable, Cloneable {
	private static final long serialVersionUID = 1L;
	public final static int SORT_ICON_NONE = 0;
	public final static int SORT_ICON_UP = 1;
	public final static int SORT_ICON_DOWN = 2;
	
	private int index = 0;
	private String title = null;
	private ImageRef image = null;
	private int sortIcon = SORT_ICON_NONE;
	private Object userObject = null;
	private int width = 0; // no size
	private boolean visible = true;
	
	private String toolTip = null;
	
	/**
	 * @param title
	 * @param width
	 */
	public TableColumn(String title, int width) {
		super();
		this.title = title;
		this.width = width;
		
		//default: header is always mouse over
		this.toolTip = title;
	}

	/**
	 * @param title
	 * @param width
	 * @param userObject
	 */
	public TableColumn(String title, int width, Object userObject) {
		super();
		this.title = title;
		this.width = width;
		this.userObject = userObject;
		//default: header is always mouse over
		this.toolTip = title;
	}

	/**
	 * 
	 */
	public TableColumn() {
		super();
	}
	
	/**
	 * @param title
	 */
	public TableColumn(String title) {
		super();
		this.title = title;
		//default: header is always mouse over
		this.toolTip = title;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
		this.toolTip = title;
	}
	/**
	 * @return the userObject
	 */
	public Object getUserObject() {
		return userObject;
	}
	/**
	 * @param userObject the userObject to set
	 */
	public void setUserObject(Object userObject) {
		this.userObject = userObject;
	}
	/**
	 * @return the index
	 */
	public int getIndex() {
		return index;
	}
	/**
	 * @param index the index to set
	 */
	public void setIndex(int index) {
		this.index = index;
	}

	/**
	 * @return the width
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * @param width the width to set
	 */
	public void setWidth(int width) {
		this.width = width;
	}

	/**
	 * @return the sortIcon
	 */
	public int getSortIcon() {
		return sortIcon;
	}

	/**
	 * @param sortIcon the sortIcon to set
	 */
	public void setSortIcon(int sortIcon) {
		this.sortIcon = sortIcon;
	}

	/**
	 * @return the image
	 */
	public ImageRef getImage() {
		return image;
	}

	/**
	 * @param image the image to set
	 */
	public void setImage(ImageRef image) {
		this.image = image;
	}
	
	/**
	 * @return the visible
	 */
	public boolean isVisible() {
		return visible;
	}
	
	/**
	 * @param visible the visible to set
	 */
	public void setVisible(boolean visible) {
		this.visible = visible;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#clone()
	 */
	public Object clone() throws CloneNotSupportedException {
		TableColumn clone = (TableColumn)super.clone();
		clone.image = image;
		clone.index = index;
		clone.sortIcon = sortIcon;
		clone.title = title;
		clone.userObject = userObject;
		clone.visible = visible;
		clone.width = width;
		clone.toolTip = toolTip;
		return clone;
	}

	/**
	 * @return the toolTip
	 */
	public String getToolTip() {
		return toolTip;
	}

	/**
	 * @param toolTip the toolTip to set
	 */
	public void setToolTip(String toolTip) {
		this.toolTip = toolTip;
	}
	
}
