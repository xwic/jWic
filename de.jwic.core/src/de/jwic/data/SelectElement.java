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
package de.jwic.data;

import java.io.Serializable;

import de.jwic.base.ImageRef;


/**
 *
 * @author lippisch
 */
public class SelectElement implements ISelectElement, Serializable {
	private static final long serialVersionUID = 1L;
	private String title = null;
	private String key = null;
	private boolean selectable = true;
	private ImageRef image = null;
	
	/**
	 * Constructor.
	 */
	public SelectElement() {
		
	}
	
	public SelectElement(String title) {
		super();
		this.title = title;
	}

	public SelectElement(String title, String key) {
		super();
		this.title = title;
		this.key = key;
	}
	
	public SelectElement(String title, String key, ImageRef image) {
		super();
		this.title = title;
		this.key = key;
		this.image = image;
	}

	public SelectElement(String title, String key, ImageRef image, boolean selectable) {
		super();
		this.title = title;
		this.key = key;
		this.image = image;
		this.selectable = selectable;
	}
	
	public SelectElement(String title, String key, boolean selectable) {
		super();
		this.title = title;
		this.key = key;
		this.selectable = selectable;
	}

	/**
	 * @return the key
	 */
	public String getKey() {
		return key;
	}
	/**
	 * @param key the key to set
	 */
	public void setKey(String key) {
		this.key = key;
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
	 * @return the selectable
	 */
	public boolean isSelectable() {
		return selectable;
	}
	/**
	 * @param selectable the selectable to set
	 */
	public void setSelectable(boolean selectable) {
		this.selectable = selectable;
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
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		if (key != null) {
			sb.append("key: ");
			sb.append(key).append(", ");
		}
		sb.append("title: " + title);
		return sb.toString();
	}
	
}
