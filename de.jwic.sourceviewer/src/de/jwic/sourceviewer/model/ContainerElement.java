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
package de.jwic.sourceviewer.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * A ContainerElement may contain other NavigationElements.
 * 
 * @author Florian Lippisch
 */
public abstract class ContainerElement extends NavigationElement {

	protected List childs = new ArrayList();
	
	/**
	 * Returns the element type. This is often used within
	 * templates to display the various elements in different styles. 
	 * @return
	 */
	public String getElementType() {
		return "container";
	}

	/**
	 * Add a child.
	 * @param element
	 */
	protected void addChild(NavigationElement element) {
		childs.add(element);
	}

	/**
	 * @return the childs
	 */
	public List getChilds() {
		return childs;
	}

	/**
	 * 
	 */
	public void sortChilds() {
		
		Collections.sort(childs);
		
	}

	/* (non-Javadoc)
	 * @see de.jwic.sourceviewer.model.NavigationElement#compareTo(java.lang.Object)
	 */
	public int compareTo(Object o) {
		if (o instanceof FileElement) {
			return -1;
		}
		return super.compareTo(o);
	}
	

	/**
	 * @param name
	 * @return
	 */
	public NavigationElement findElementByName(String name) {
		
		for (Iterator it = childs.iterator(); it.hasNext(); ) {
			NavigationElement elm = (NavigationElement)it.next();
			if (elm.getDisplayName().equals(name)) {
				return elm;
			} else if (elm instanceof ContainerElement) {
				elm = ((ContainerElement)elm).findElementByName(name);
				if (elm != null) {
					return elm;
				}
			}
		}
		return null;
	}

}
