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
 * de.jwic.ecolib.objectlinktool.ObjectLinkEvent
 * Created on 16.04.2007
 * $Id: ObjectLinkEvent.java,v 1.1 2007/04/25 09:59:13 lordsam Exp $
 */
package de.jwic.ecolib.objectlinktool;

import de.jwic.base.Event;

/**
 *
 * @author Florian Lippisch
 */
public class ObjectLinkEvent extends Event {

	private String keyLeft = null;
	private String keyRight = null;
	private boolean canceled = false;
	private int listNo = 0;
	/**
	 * @param eventSource
	 */
	public ObjectLinkEvent(Object eventSource) {
		super(eventSource);
	}
	
	/**
	 * @param eventSource
	 */
	public ObjectLinkEvent(Object eventSource, int listNo) {
		super(eventSource);
		this.listNo = listNo;
	}

	/**
	 * @param eventSource
	 * @param keyLeft
	 * @param keyRight
	 */
	public ObjectLinkEvent(Object eventSource, String keyLeft, String keyRight) {
		super(eventSource);
		this.keyLeft = keyLeft;
		this.keyRight = keyRight;
	}

	/**
	 * @return the keyLeft
	 */
	public String getKeyLeft() {
		return keyLeft;
	}

	/**
	 * @param keyLeft the keyLeft to set
	 */
	public void setKeyLeft(String keyLeft) {
		this.keyLeft = keyLeft;
	}

	/**
	 * @return the keyRight
	 */
	public String getKeyRight() {
		return keyRight;
	}

	/**
	 * @param keyRight the keyRight to set
	 */
	public void setKeyRight(String keyRight) {
		this.keyRight = keyRight;
	}

	/**
	 * @return the canceled
	 */
	public boolean isCanceled() {
		return canceled;
	}

	/**
	 * @param canceled the canceled to set
	 */
	public void setCanceled(boolean canceled) {
		this.canceled = canceled;
	}

	/**
	 * @return the listNo
	 */
	public int getListNo() {
		return listNo;
	}

	/**
	 * @param listNo the listNo to set
	 */
	public void setListNo(int listNo) {
		this.listNo = listNo;
	}

}
