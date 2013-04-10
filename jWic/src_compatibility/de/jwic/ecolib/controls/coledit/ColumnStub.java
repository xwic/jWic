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
 * de.jwic.ecolib.controls.coledit.ColumnStub
 * Created on 05.06.2011
 * $Id: ColumnStub.java,v 1.1 2011/06/06 12:13:19 lordsam Exp $
 */
package de.jwic.ecolib.controls.coledit;

import java.io.Serializable;

/**
 * Column representation, that is used as stubs to encapsulate 
 * the users column implementation.
 * @author lippisch
 */
public class ColumnStub implements Comparable<ColumnStub>, Serializable {
	private static final long serialVersionUID = 1L;
	private int id = -1;	// Internal ID
	
	private boolean visible;
	private String title;
	private String description;
	private int sortIndex = 0;
	private Object userObject = null;

	/**
	 * 
	 */
	public ColumnStub() {
		super();
	}
	
	
	/**
	 * 
	 * @param visible
	 * @param title
	 */
	public ColumnStub(boolean visible, String title) {
		super();
		this.visible = visible;
		this.title = title;
	}

	
	

	/**
	 * @param visible
	 * @param title
	 * @param description
	 * @param userObject
	 */
	public ColumnStub(boolean visible, String title, String description,
			Object userObject) {
		super();
		this.visible = visible;
		this.title = title;
		this.description = description;
		this.userObject = userObject;
	}


	/**
	 * @param visible
	 * @param title
	 * @param description
	 * @param sortIndex
	 * @param userObject
	 */
	public ColumnStub(boolean visible, String title, String description,
			Object userObject, int sortIndex) {
		super();
		this.visible = visible;
		this.title = title;
		this.description = description;
		this.sortIndex = sortIndex;
		this.userObject = userObject;
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
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
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
	 * @return the sortIndex
	 */
	public int getSortIndex() {
		return sortIndex;
	}

	/**
	 * @param sortIndex the sortIndex to set
	 */
	public void setSortIndex(int sortIndex) {
		this.sortIndex = sortIndex;
	}


	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}


	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}


	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	public int compareTo(ColumnStub o) {
		return new Integer(sortIndex).compareTo(o.sortIndex);
	}
	
	
	
}
