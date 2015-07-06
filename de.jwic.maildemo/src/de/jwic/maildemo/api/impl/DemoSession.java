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
import java.util.Iterator;

import de.jwic.maildemo.api.IFolder;
import de.jwic.maildemo.api.IMail;
import de.jwic.maildemo.api.ISession;

/**
 *
 * @author Florian Lippisch
 */
public class DemoSession implements ISession, Serializable {

	private String username;
	private IFolder rootFolder;
	
	/**
	 * @param username
	 * @param mails 
	 */
	public DemoSession(String username, IFolder rootFolder) {
		this.username = username;
		this.rootFolder = rootFolder;
	}

	/* (non-Javadoc)
	 * @see de.jwic.maildemo.api.ISession#close()
	 */
	public void close() {

	}

	/* (non-Javadoc)
	 * @see de.jwic.maildemo.api.ISession#createMail()
	 */
	public IMail createMail() {
		return null;
	}

	/* (non-Javadoc)
	 * @see de.jwic.maildemo.api.ISession#getFolder(java.lang.String)
	 */
	public IFolder getFolder(String name) {
		return findFolderRecursive(name, rootFolder, null);
	}

	private IFolder findFolderRecursive(String name, IFolder parent, IFolder f) {
		if (null == f) {
			for (Iterator it = parent.listFolders().iterator(); it.hasNext();) {
				IFolder folder = (IFolder) it.next();
				f = findFolderRecursive(name, folder, f);
				
				if (folder.getName().equals(name)) {
					f = folder;
				} 
			}
		}
		
		return f;
	}
	
	/* (non-Javadoc)
	 * @see de.jwic.maildemo.api.ISession#getInbox()
	 */
	public IFolder getRootFolder() {
		return rootFolder; 
	}

	/* (non-Javadoc)
	 * @see de.jwic.maildemo.api.ISession#sendMail(de.jwic.maildemo.api.IMail)
	 */
	public void sendMail(IMail mail) {

	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/* (non-Javadoc)
	 * @see de.jwic.maildemo.api.ISession#getMailByID(java.lang.String)
	 */
	public IMail getMailByID(String uniqueID) {
		return DemoMailServer.getInstance().getMailByID(username, uniqueID);
	}

}
