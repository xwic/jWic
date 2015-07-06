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

/**
 * @author Florian Lippisch
 * @version $Revision: 1.2 $
 */
public class KeyEvent extends Event {

	private static final long serialVersionUID = 1L;
	private int keyCode = 0;
	
	/**
	 * @param eventSource
	 */
	public KeyEvent(Object eventSource, int keyCode) {
		super(eventSource);
		this.keyCode = keyCode;
	}

	/**
	 * @return Returns the keyCode.
	 */
	public int getKeyCode() {
		return keyCode;
	}

	/**
	 * @param keyCode The keyCode to set.
	 */
	public void setKeyCode(int keyCode) {
		this.keyCode = keyCode;
	}

}
