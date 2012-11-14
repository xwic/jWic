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
 * de.jwic.ecolib.controls.stackpanel.StackPanelChangeEvent
 * Created on 20.04.2007
 * $Id: StackPanelChangeEvent.java,v 1.1 2007/04/20 08:35:50 lordsam Exp $
 */
package de.jwic.ecolib.controls.stackpanel;

import de.jwic.base.Event;

/**
 * Event object for the StackPanelChangeListener.
 * 
 * @author Sebastian
 */
public class StackPanelChangeEvent extends Event {
	
	/** the new selected stack */
	private Stack selectedStack;

	/** the old selected stack */
	private Stack oldStack;

	public StackPanelChangeEvent(Object eventSource, Stack oldStack,
			Stack selectedStack) {
		super(eventSource);

		this.selectedStack = selectedStack;
		this.oldStack = oldStack;
	}

	/**
	 * @return the oldStack
	 */
	public Stack getOldStack() {
		return oldStack;
	}

	/**
	 * @param oldStack
	 *            the oldStack to set
	 */
	public void setOldStack(Stack oldStack) {
		this.oldStack = oldStack;
	}

	/**
	 * @return the selectedStack
	 */
	public Stack getSelectedStack() {
		return selectedStack;
	}

	/**
	 * @param selectedStack
	 *            the selectedStack to set
	 */
	public void setSelectedStack(Stack selectedStack) {
		this.selectedStack = selectedStack;
	}

}
