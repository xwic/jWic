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
package de.jwic.maildemo.api.impl;

import java.io.Serializable;
import java.util.Date;

import de.jwic.maildemo.api.IMail;
import de.jwic.maildemo.api.IMailMessage;

/**
 * Mail dummy.
 * @author Florian Lippisch
 */
public class DemoMail implements IMail, Serializable {

	private DemoMessage message = null;
	
	private String subject = null;
	private String uniqueID = null;
	private String from = null;
	private String[] to = null;
	private String[] cc = null;
	private String[] bcc = null;
	
	private Date sent = null;
	private Date recieved = null;

	private long size = 0;
	
	private boolean containsAttachments = false;
	

	/**
	 * 
	 */
	public DemoMail() {
		super();
	}

	/**
	 * @param message
	 * @param subject
	 * @param from
	 * @param to
	 * @param size
	 */
	public DemoMail(String id, DemoMessage message, String subject, String from, String[] to, long size) {
		super();
		this.uniqueID = id;
		this.message = message;
		this.subject = subject;
		this.from = from;
		this.to = to;
		this.size = size;
		this.sent = new Date();
		this.recieved = new Date();
	}

	/* (non-Javadoc)
	 * @see de.jwic.maildemo.api.IMail#getMessage()
	 */
	public IMailMessage getMessage() {
		return message;
	}
	
	/* (non-Javadoc)
	 * @see de.jwic.maildemo.api.impl.IMail#getBcc()
	 */
	public String[] getBcc() {
		return bcc;
	}

	/* (non-Javadoc)
	 * @see de.jwic.maildemo.api.impl.IMail#setBcc(java.lang.String[])
	 */
	public void setBcc(String[] bcc) {
		this.bcc = bcc;
	}

	/* (non-Javadoc)
	 * @see de.jwic.maildemo.api.impl.IMail#getCc()
	 */
	public String[] getCc() {
		return cc;
	}

	/* (non-Javadoc)
	 * @see de.jwic.maildemo.api.impl.IMail#setCc(java.lang.String[])
	 */
	public void setCc(String[] cc) {
		this.cc = cc;
	}

	/* (non-Javadoc)
	 * @see de.jwic.maildemo.api.impl.IMail#getFrom()
	 */
	public String getFrom() {
		return from;
	}

	/* (non-Javadoc)
	 * @see de.jwic.maildemo.api.impl.IMail#setFrom(java.lang.String)
	 */
	public void setFrom(String from) {
		this.from = from;
	}

	/* (non-Javadoc)
	 * @see de.jwic.maildemo.api.impl.IMail#getSent()
	 */
	public Date getSent() {
		return sent;
	}

	/* (non-Javadoc)
	 * @see de.jwic.maildemo.api.impl.IMail#setSent(java.util.Date)
	 */
	public void setSent(Date sent) {
		this.sent = sent;
	}

	/* (non-Javadoc)
	 * @see de.jwic.maildemo.api.impl.IMail#getSubject()
	 */
	public String getSubject() {
		return subject;
	}

	/* (non-Javadoc)
	 * @see de.jwic.maildemo.api.impl.IMail#setSubject(java.lang.String)
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}

	/* (non-Javadoc)
	 * @see de.jwic.maildemo.api.impl.IMail#getTo()
	 */
	public String[] getTo() {
		return to;
	}

	/* (non-Javadoc)
	 * @see de.jwic.maildemo.api.impl.IMail#setTo(java.lang.String[])
	 */
	public void setTo(String[] to) {
		this.to = to;
	}

	/* (non-Javadoc)
	 * @see de.jwic.maildemo.api.impl.IMail#isContainsAttachments()
	 */
	public boolean isContainsAttachments() {
		return containsAttachments;
	}

	/* (non-Javadoc)
	 * @see de.jwic.maildemo.api.impl.IMail#setContainsAttachments(boolean)
	 */
	public void setContainsAttachments(boolean containsAttachments) {
		this.containsAttachments = containsAttachments;
	}

	/* (non-Javadoc)
	 * @see de.jwic.maildemo.api.impl.IMail#getRecieved()
	 */
	public Date getRecieved() {
		return recieved;
	}

	/* (non-Javadoc)
	 * @see de.jwic.maildemo.api.impl.IMail#setRecieved(java.util.Date)
	 */
	public void setRecieved(Date recieved) {
		this.recieved = recieved;
	}

	/* (non-Javadoc)
	 * @see de.jwic.maildemo.api.impl.IMail#getSize()
	 */
	public long getSize() {
		return size;
	}

	/* (non-Javadoc)
	 * @see de.jwic.maildemo.api.impl.IMail#setSize(long)
	 */
	public void setSize(long size) {
		this.size = size;
	}

	/**
	 * @return the uniqueID
	 */
	public String getUniqueID() {
		return uniqueID;
	}

	/**
	 * @param uniqueID the uniqueID to set
	 */
	public void setUniqueID(String uniqueID) {
		this.uniqueID = uniqueID;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(DemoMessage message) {
		this.message = message;
	}
	
}
