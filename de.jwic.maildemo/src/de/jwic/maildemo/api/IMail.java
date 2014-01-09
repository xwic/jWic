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
 * de.jwic.maildemo.api.IMail
 * Created on 23.04.2007
 * $Id: IMail.java,v 1.1 2007/04/24 07:46:57 lordsam Exp $
 */
package de.jwic.maildemo.api;

import java.util.Date;

/**
 * Represents a mail that the user has reciept or sent. This object
 * does only contain meta informations about the mail. The content
 * is stored in the IMailContent or IMailAttachment object.
 *  
 * @author Florian Lippisch
 */
public interface IMail {
	
	/**
	 * Returns a unique ID for this mail.
	 * @return
	 */
	public abstract String getUniqueID();
	
	/**
	 * Returns the message (content) of this mail.
	 * @return
	 */
	public abstract IMailMessage getMessage();

	/**
	 * @return the bcc
	 */
	public abstract String[] getBcc();

	/**
	 * @param bcc the bcc to set
	 */
	public abstract void setBcc(String[] bcc);

	/**
	 * @return the cc
	 */
	public abstract String[] getCc();

	/**
	 * @param cc the cc to set
	 */
	public abstract void setCc(String[] cc);

	/**
	 * @return the from
	 */
	public abstract String getFrom();

	/**
	 * @param from the from to set
	 */
	public abstract void setFrom(String from);

	/**
	 * @return the sent
	 */
	public abstract Date getSent();

	/**
	 * @param sent the sent to set
	 */
	public abstract void setSent(Date sent);

	/**
	 * @return the subject
	 */
	public abstract String getSubject();

	/**
	 * @param subject the subject to set
	 */
	public abstract void setSubject(String subject);

	/**
	 * @return the to
	 */
	public abstract String[] getTo();

	/**
	 * @param to the to to set
	 */
	public abstract void setTo(String[] to);

	/**
	 * @return the containsAttachments
	 */
	public abstract boolean isContainsAttachments();

	/**
	 * @param containsAttachments the containsAttachments to set
	 */
	public abstract void setContainsAttachments(boolean containsAttachments);

	/**
	 * @return the recieved
	 */
	public abstract Date getRecieved();

	/**
	 * @param recieved the recieved to set
	 */
	public abstract void setRecieved(Date recieved);

	/**
	 * @return the size
	 */
	public abstract long getSize();

	/**
	 * @param size the size to set
	 */
	public abstract void setSize(long size);

}
