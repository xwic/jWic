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
 * de.jwic.sourceviewer.main.SVModelEvent
 * Created on 26.04.2007
 * $Id: SVModelEvent.java,v 1.1 2007/04/26 16:07:19 lordsam Exp $
 */
package de.jwic.sourceviewer.main;

import de.jwic.base.Event;
import de.jwic.sourceviewer.model.NavigationElement;

/**
 *
 * @author Florian Lippisch
 */
public class SVModelEvent extends Event {

	private NavigationElement element = null;
	
	/**
	 * @param eventSource
	 */
	public SVModelEvent(Object eventSource) {
		super(eventSource);
	}

	/**
	 * @param eventSource
	 * @param element
	 */
	public SVModelEvent(Object eventSource, NavigationElement element) {
		super(eventSource);
		this.element = element;
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

}
