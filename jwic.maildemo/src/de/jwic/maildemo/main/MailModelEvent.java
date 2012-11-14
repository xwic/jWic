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
 * de.jwic.maildemo.main.MailModelEvent
 * Created on 23.04.2007
 * $Id: MailModelEvent.java,v 1.2 2007/04/25 08:54:51 lordsam Exp $
 */
package de.jwic.maildemo.main;

import de.jwic.base.Event;
import de.jwic.maildemo.api.IMail;

/**
 *
 * @author Florian Lippisch
 */
public class MailModelEvent extends Event {

	private IMail selectedMail = null;
	/**
	 * @param eventSource
	 */
	public MailModelEvent(Object eventSource) {
		super(eventSource);
		
	}
	
	/**
	 * @param eventSource
	 * @param selectedMail
	 */
	public MailModelEvent(Object eventSource, IMail selectedMail) {
		super(eventSource);
		this.selectedMail = selectedMail;
	}

	/**
	 * @return the selectedMail
	 */
	public IMail getSelectedMail() {
		return selectedMail;
	}
	/**
	 * @param selectedMail the selectedMail to set
	 */
	public void setSelectedMail(IMail selectedMail) {
		this.selectedMail = selectedMail;
	}

}
