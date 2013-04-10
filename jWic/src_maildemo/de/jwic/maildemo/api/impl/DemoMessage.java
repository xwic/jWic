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
 * de.jwic.maildemo.api.impl.DemoMessage
 * Created on 23.04.2007
 * $Id: DemoMessage.java,v 1.2 2007/04/27 09:29:51 aroncotrau Exp $
 */
package de.jwic.maildemo.api.impl;

import java.io.Serializable;

import de.jwic.maildemo.api.IMailMessage;


/**
 *
 * @author Florian Lippisch
 */
public class DemoMessage implements IMailMessage, Serializable {

	private String message = null;
	private String contentType = "text/plain";
	
	/**
	 * Empty constructor.
	 *
	 */
	public DemoMessage() {
		message = "";
	}
	
	/**
	 * Create a new message.
	 * @param message
	 */
	public DemoMessage(String message) {
		this.message = message;
	}


	/**
	 * @param message
	 * @param contentType
	 */
	public DemoMessage(String message, String contentType) {
		super();
		this.message = message;
		this.contentType = contentType;
	}

	/* (non-Javadoc)
	 * @see de.jwic.maildemo.api.impl.IMailMessage#getContentType()
	 */
	public String getContentType() {
		return contentType;
	}
	/* (non-Javadoc)
	 * @see de.jwic.maildemo.api.impl.IMailMessage#setContentType(java.lang.String)
	 */
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	/* (non-Javadoc)
	 * @see de.jwic.maildemo.api.impl.IMailMessage#getMessage()
	 */
	public String getMessage() {
		return message;
	}
	/* (non-Javadoc)
	 * @see de.jwic.maildemo.api.impl.IMailMessage#setMessage(java.lang.String)
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	
}
