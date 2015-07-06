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
 * de.jwic.events.ObjectSelectedEvent
 * $Id: SelectionEvent.java,v 1.3 2010/02/07 14:24:23 lordsam Exp $
 */
package de.jwic.events;

import de.jwic.base.Control;
import de.jwic.base.Event;

/**
 * 
 * @author Florian Lippisch
 * @version $Revision: 1.3 $
 */
public class SelectionEvent extends Event {

	private static final long serialVersionUID = 1L;
	
	private boolean dblClick = false;
	
	/**
	 * @param sourceControl
	 */
	public SelectionEvent(Control sourceControl) {
		super(sourceControl);
	}
	
	/**
	 * SelectionEvent constructor
	 * @param eventSource
	 * @param dblClick
	 */
	public SelectionEvent(Object eventSource, boolean dblClick) {
		super(eventSource);
		this.dblClick = dblClick;
	}

	/**
	 * @return the dblClick
	 */
	public boolean isDblClick() {
		return dblClick;
	}

	
}
