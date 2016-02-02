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
package de.jwic.base;

import java.io.Serializable;

/**
 * References the location of an Image. Used by UI elements to generate
 * urls to an image.
 * @author Florian Lippisch
 */
public class ImageRef implements Serializable {
	private static final long serialVersionUID = 1L;
	
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
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (bundled ? 1231 : 1237);
		result = prime * result + height;
		result = prime * result + ((path == null) ? 0 : path.hashCode());
		result = prime * result + ((text == null) ? 0 : text.hashCode());
		result = prime * result + width;
		return result;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ImageRef other = (ImageRef) obj;
		if (bundled != other.bundled)
			return false;
		if (height != other.height)
			return false;
		if (path == null) {
			if (other.path != null)
				return false;
		} else if (!path.equals(other.path))
			return false;
		if (text == null) {
			if (other.text != null)
				return false;
		} else if (!text.equals(other.text))
			return false;
		if (width != other.width)
			return false;
		return true;
	}
	
	
}
