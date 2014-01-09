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
 * de.jwic.sourceviewer.model.Folder
 * Created on 25.04.2007
 * $Id: Folder.java,v 1.2 2007/05/07 07:31:11 lordsam Exp $
 */
package de.jwic.sourceviewer.model;

import java.io.File;

/**
 *
 * @author Florian Lippisch
 */
public class Folder extends PathElement {

	/**
	 * Returns the element type. This is often used within
	 * templates to display the various elements in different styles. 
	 * @return
	 */
	public String getElementType() {
		return "folder";
	}

	/**
	 * Constructor.
	 */
	public Folder() {
		super();
	}
	
	/**
	 * @param file
	 */
	public Folder(File file) {
		super(file);
	}

}
