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
 * de.jwic.ecolib.util.ImageDef
 * Created on 12.03.2007
 * $Id: ImageRef.java,v 1.6 2010/09/22 15:47:43 lordsam Exp $
 */
package de.jwic.base;

import java.io.Serializable;

/**
 * References the location of an Image. Used by UI elements to generate
 * urls to an image.
 * @author Florian Lippisch
 */
public class ImageRef implements Serializable {

	private boolean bundled = false;
	private String path = "";
	private int width = -1;
	private int height = -1;
	private String text;
	
	/**
	 * Default constructor.
	 *
	 */
	public ImageRef() {
		
	}
	/**
	 * @param package1
	 * @param string
	 */
	public ImageRef(Package srcPackage, String resourceName) {
		this(srcPackage, resourceName, -1, -1);
	}

	/**
	 * @param package1
	 * @param string
	 */
	public ImageRef(Package srcPackage, String resourceName, int width, int height) {
		bundled = true;
		String packageName = srcPackage.getName();
		path = JWicRuntime.getJWicRuntime().getContextPath()  
				+ "/cp/"
				+ packageName.replace('.', '/')
				+ "/"
				+ resourceName;
		this.width = width;
		this.height = height;
	}
	
	/**
	 * @param string
	 */
	public ImageRef(String pathName) {
		bundled = false;
		if (pathName.startsWith("/")) {
			path = JWicRuntime.getJWicRuntime().getContextPath() + pathName; 
		} else {
			path = pathName; // relative URLs are stored as is.
		}
	}
	
	/**
	 * @param string
	 */
	public ImageRef(String pathName, int width, int height) {
		bundled = false;
		if (pathName.startsWith("/")) {
			path = JWicRuntime.getJWicRuntime().getContextPath() + pathName; 
		} else {
			path = pathName; // relative URLs are stored as is.
		}
		this.width = width;
		this.height = height;
	}

	/**
	 * @return the bundled
	 */
	public boolean isBundled() {
		return bundled;
	}
	/**
	 * @param bundled the bundled to set
	 */
	public void setBundled(boolean bundled) {
		this.bundled = bundled;
	}
	/**
	 * @return the path
	 */
	public String getPath() {
		return path;
	}
	/**
	 * @param path the path to set
	 */
	public void setPath(String path) {
		this.path = path;
	}
	/**
	 * @return the height
	 */
	public int getHeight() {
		return height;
	}
	/**
	 * @param height the height to set
	 */
	public void setHeight(int height) {
		this.height = height;
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
	 * Returns the IMG HTML tag.
	 * @return
	 */
	public String toImgTag() {
		return toImgTag(width, height);
	}
	
	/**
	 * Returns the IMG HTML tag.
	 * @param width
	 * @param height
	 * @return
	 */
	public String toImgTag(int width, int height) {
		return "<IMG src=\"" + path + "\" border=\"0\" align=\"absmiddle\"" +
		(width != -1 ? " width=\"" + width + "\"" : "") +
		(height != -1 ? " height=\"" + height + "\"" : "") +
		(text != null ? " title=\"" + text + "\"" : "") +
		"/>";
	}
	/**
	 * @return the text
	 */
	public String getText() {
		return text;
	}
	/**
	 * Set text that is added to the title attribute, which is displayed on mouse over.
	 * @param text the text to set
	 */
	public void setText(String text) {
		this.text = text;
	}
	
	
}
