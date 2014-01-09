/*
 * Copyright 2005 jWic group (http://www.jwic.de)
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
 * de.jwic.events.KeyPressedEvent
 * Created on 07.12.2005
 * $Id: KeyEvent.java,v 1.2 2006/08/14 09:34:59 lordsam Exp $
 */
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
