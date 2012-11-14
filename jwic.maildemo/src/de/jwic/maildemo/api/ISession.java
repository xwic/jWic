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
 * de.jwic.maildemo.api.ISession
 * Created on 23.04.2007
 * $Id: ISession.java,v 1.2 2007/04/27 09:29:52 aroncotrau Exp $
 */
package de.jwic.maildemo.api;



/**
 * Represents the session handle to a users mail box.
 * @author Florian Lippisch
 */
public interface ISession {

	/**
	 * Close the session and logout the user from the mailserver.
	 */
	public void close();
	
	/**
	 * Returns the default root folder.
	 * @return
	 */
	public IFolder getRootFolder();
	
	/**
	 * Returns the folder with the specified name.
	 * @param name
	 * @return
	 */
	public IFolder getFolder(String name);
	
	/**
	 * Create an empty mail that may be send.
	 * @return
	 */
	public IMail createMail();
	
	/**
	 * Sends the specified mail.
	 * @param mail
	 */
	public void sendMail(IMail mail);

	/**
	 * Returns the username of this session.
	 * @return
	 */
	public String getUsername();

	/**
	 * Returns the mail for the ID.
	 * @param uniqueID
	 * @return
	 */
	public IMail getMailByID(String uniqueID); 
}
