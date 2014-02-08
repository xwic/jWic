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
 * de.jwic.sourceviewer.model.PathElement
 * Created on 25.04.2007
 * $Id: PathElement.java,v 1.1 2007/04/26 16:07:35 lordsam Exp $
 */
package de.jwic.sourceviewer.model;

import java.io.File;

/**
 * A navigation element that points to a path.
 * @author Florian Lippisch
 */
public abstract class PathElement extends ContainerElement {

	private String path = null;
	private File filePath = null;
	private boolean badPath = false;
	private boolean scan = true;
	
	/**
	 * 
	 */
	public PathElement() {
		super();
	}


	/**
	 * @return the scan
	 */
	public boolean isScan() {
		return scan;
	}

	/**
	 * @param scan the scan to set
	 */
	public void setScan(boolean scan) {
		this.scan = scan;
	}

	
	
	/**
	 * @param filePath
	 */
	public PathElement(File filePath) {
		super();
		this.filePath = filePath;
		path = filePath.getAbsolutePath();
		badPath = !filePath.exists();
		setName(filePath.getName());
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
	 * Add a file element.
	 * @param file
	 */
	public void addFileElement(FileElement file) {
		addChild(file);
	}
	
	/**
	 * Add a file element.
	 * @param file
	 */
	public void addPathElement(PathElement path) {
		addChild(path);
	}

	/**
	 * @return the badPath
	 */
	public boolean isBadPath() {
		return badPath;
	}

	/**
	 * @param badPath the badPath to set
	 */
	public void setBadPath(boolean badPath) {
		this.badPath = badPath;
	}

	/**
	 * @return the filePath
	 */
	public File getFilePath() {
		return filePath;
	}

	/**
	 * @param filePath the filePath to set
	 */
	public void setFilePath(File filePath) {
		this.filePath = filePath;
	}
	
}
