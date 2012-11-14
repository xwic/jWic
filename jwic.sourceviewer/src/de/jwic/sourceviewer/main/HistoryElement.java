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
 * de.jwic.sourceviewer.navigator.HistoryElement
 * Created on May 7, 2007
 * $Id: HistoryElement.java,v 1.1 2007/05/08 09:26:19 lordsam Exp $
 */
package de.jwic.sourceviewer.main;

import java.io.Serializable;

import de.jwic.sourceviewer.model.Group;
import de.jwic.sourceviewer.model.NavigationElement;

/**
 * 
 *
 * @author Aron Cotrau
 */
public class HistoryElement implements Serializable {
	
	private NavigationElement element = null;
	private Group group = null;
	
	/**
	 * @param element
	 * @param group
	 */
	public HistoryElement(NavigationElement element, Group group) {
		super();
		this.element = element;
		this.group = group;
	}
	
	/**
	 * @return the element
	 */
	public NavigationElement getElement() {
		return element;
	}
	
	/**
	 * @param element the element to set
	 */
	public void setElement(NavigationElement element) {
		this.element = element;
	}
	
	/**
	 * @return the group
	 */
	public Group getGroup() {
		return group;
	}
	
	/**
	 * @param group the group to set
	 */
	public void setGroup(Group group) {
		this.group = group;
	}
	
}
