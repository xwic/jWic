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
/*
 * de.jwic.events.ElementSelectedEvent
 * $Id: ElementSelectedEvent.java,v 1.4 2010/02/07 14:24:23 lordsam Exp $
 */
package de.jwic.events;

import de.jwic.base.Event;

/**
 * This event is fired when an element of an control has been selected.
 * An element can be something like an entry in a list or a node in a tree.
 * 
 * @author Florian Lippisch
 * @version $Revision: 1.4 $
 */
public class ElementSelectedEvent extends Event {

	private static final long serialVersionUID = 1L;
	private Object element = null;
	private boolean dblClick = false;
	
	/**
	 * @param sourceControl
	 */
	public ElementSelectedEvent(Object source, Object selectedElement) {
		super(source);
		this.element = selectedElement;
	}

	/**
	 * @param sourceControl
	 */
	public ElementSelectedEvent(Object source, Object selectedElement, boolean dblClick) {
		super(source);
		this.element = selectedElement;
		this.dblClick = dblClick;
	}

	/**
	 * @return Returns the element.
	 */
	public Object getElement() {
		return element;
	}

	/**
	 * @return the dblClick
	 */
	public boolean isDblClick() {
		return dblClick;
	}
}
