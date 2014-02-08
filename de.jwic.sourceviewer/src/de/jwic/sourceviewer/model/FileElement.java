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
 * de.jwic.sourceviewer.model.FileElement
 * Created on 26.04.2007
 * $Id: FileElement.java,v 1.3 2007/05/07 09:45:51 aroncotrau Exp $
 */
package de.jwic.sourceviewer.model;

import java.io.File;
import java.util.Iterator;

/**
 *
 * @author Florian Lippisch
 */
public class FileElement extends NavigationElement {

	protected File file = null;
	protected FileType type = FileType.UNKNOWN;
	
	/**
	 * Returns the element type. This is often used within
	 * templates to display the various elements in different styles. 
	 * @return
	 */
	public String getElementType() {
		return "file";
	}

	public FileElement() {
		
	}
	
	public FileElement(File file) {
		this.file = file;
		setName(file.getName());
	}

	/**
	 * @return the file
	 */
	public File getFile() {
		return file;
	}

	/**
	 * @param file the file to set
	 */
	public void setFile(File file) {
		this.file = file;
	}
	
	/**
	 * Determine Filetype.
	 */
	public void setName(String name) {
		super.setName(name);
	
		// determine file type.
		for (Iterator it = FileType.TYPES.iterator(); it.hasNext(); ) {
			FileType ft = (FileType)it.next();
			if (ft.isMatch(name)) {
				type = ft;
				break;
			}
		}
	}

	/**
	 * @return the type
	 */
	public FileType getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(FileType type) {
		this.type = type;
	}
	
	/* (non-Javadoc)
	 * @see de.jwic.sourceviewer.model.NavigationElement#compareTo(java.lang.Object)
	 */
	public int compareTo(Object o) {
		if (!(o instanceof FileElement)) {
			return 1;
		}
		return super.compareTo(o);
	}
	
}
