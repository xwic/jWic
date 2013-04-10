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
 * de.jwic.sourceviewer.model.Project
 * Created on 25.04.2007
 * $Id: Project.java,v 1.2 2007/05/07 07:31:11 lordsam Exp $
 */
package de.jwic.sourceviewer.model;

/**
 * A project contains one or more source folders, standard folders and files.
 *  
 * @author Florian Lippisch
 */
public class Project extends ContainerElement {

	/**
	 * Returns the element type. This is often used within
	 * templates to display the various elements in different styles. 
	 * @return
	 */
	public String getElementType() {
		return "project";
	}

	public void addSourceFolder(SourceFolder folder) {
		addChild(folder);
	}

	public void addFolder(Folder folder) {
		addChild(folder);
	}

}
