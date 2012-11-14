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
 * de.jwic.maildemo.viewer.MailPreviewControl
 * Created on 24.04.2007
 * $Id: MailPreviewControl.java,v 1.2 2007/04/25 08:54:49 lordsam Exp $
 */
package de.jwic.maildemo.viewer;

import de.jwic.base.IControlContainer;
import de.jwic.controls.ScrollableContainer;
import de.jwic.maildemo.api.IMailMessage;

/**
 *
 * @author Florian Lippisch
 */
public class MailPreviewControl extends ScrollableContainer {

	private IMailMessage message = null;
	
	/**
	 * @param container
	 * @param name
	 */
	public MailPreviewControl(IControlContainer container, String name) {
		super(container, name);
		
	}

	/**
	 * @return the message
	 */
	public IMailMessage getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(IMailMessage message) {
		this.message = message;
		requireRedraw();
	}

}
