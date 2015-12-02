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
package de.jwic.events;

import de.jwic.base.Event;
import de.jwic.util.StringTool;

/**
 * @author Florian Lippisch
 * @version $Revision: 1.2 $
 */
public class ValueChangedEvent extends Event {

	private static final long serialVersionUID = 1L;
	private String oldValues[] = null;
	private String newValues[] = null;
	
	/**
	 * Default Constructor.
	 * @param eventSource
	 */
	public ValueChangedEvent(Object eventSource) {
		super(eventSource);
	}
	
	/**
	 * @param eventSource
	 */
	public ValueChangedEvent(Object eventSource, String[] oldValues, String[] newValues) {
		super(eventSource);
		this.oldValues = oldValues;
		this.newValues = newValues;
	}
	
	/**
	 * Returns the old value. If the value is an array, the values are
	 * returned seperated by semmicolon (;).
	 * @return
	 */
	public String getOldValue() {
		return StringTool.getSingleString(oldValues);
	}
	
	/**
	 * Returns the old values as array.
	 * @return
	 */
	public String[] getOldValues() {
		return oldValues;
	}
	
	/**
	 * Returns the new value. If the value is an array, the values are 
	 * returned seperated by semmicolon (;).
	 * @return
	 */
	public String getNewValue() {
		return StringTool.getSingleString(newValues);
	}

	/**
	 * Returns the new values as array.
	 * @return
	 */
	public String[] getNewValues() {
		return newValues;
	}
	
}
