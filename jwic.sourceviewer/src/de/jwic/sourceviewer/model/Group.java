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
 * de.jwic.sourceviewer.model.Group
 * Created on 25.04.2007
 * $Id: Group.java,v 1.2 2007/05/07 07:31:11 lordsam Exp $
 */
package de.jwic.sourceviewer.model;


/**
 * A group of projects. 
 * @author Florian Lippisch
 */
public class Group extends ContainerElement {

	/**
	 * Returns the element type. This is often used within
	 * templates to display the various elements in different styles. 
	 * @return
	 */
	public String getElementType() {
		return "group";
	}

	/**
	 * Add a group or project to the group.
	 * @param group
	 */
	public void addProject(Project project) {
		addChild(project);
	}
	
}
