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
 * de.jwic.maildemo.main.MailAppModel
 * Created on 23.04.2007
 * $Id: MailModel.java,v 1.2 2007/04/25 08:54:51 lordsam Exp $
 */
package de.jwic.maildemo.main;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import de.jwic.maildemo.api.AuthenticationFailedException;
import de.jwic.maildemo.api.IMail;
import de.jwic.maildemo.api.IMailServer;
import de.jwic.maildemo.api.ISession;

/**
 * The core model for the MailDemo application. 
 * @author Florian Lippisch
 */
public class MailModel implements Serializable {

	private IMailServer mailServer;
	private ISession session = null;
	
	private IMail selectedMail = null;
	
	private List listeners = Collections.synchronizedList(new ArrayList());
	
	private final static int EVENT_LOGON = 1;
	private final static int EVENT_LOGOUT = 2;
	private final static int EVENT_MAILSELECTION = 3;
	
	
	/**
	 * Constructor.
	 * @param mailServer
	 */
	public MailModel(IMailServer mailServer) {
		this.mailServer = mailServer;
	}
	
	/**
	 * Add a MailModelListener.
	 * @param listener
	 */
	public void addMailModelListener(IMailModelListener listener) {
		listeners.add(listener);
	}
	
	/**
	 * Remove a MailModelListener.
	 * @param listener
	 */
	public void removeMailModelListener(IMailModelListener listener) {
		listeners.remove(listener);
	}
	
	
	/**
	 * Fire an event.
	 * @param eventType
	 * @param event
	 */
	private void fireEvent(int eventType, MailModelEvent event) {
		
		Object[] lst = listeners.toArray();
		for (int i = 0; i < lst.length; i++) {
			IMailModelListener listener = (IMailModelListener)lst[i];
			switch (eventType) {
			case EVENT_LOGON:
				listener.logonSuccess(event);
				break;
			case EVENT_LOGOUT:
				listener.logoutSuccess(event);
				break;
			case EVENT_MAILSELECTION: 
				listener.mailSelected(event);
				break;
			}
			
		}
		
	}
	
	/**
	 * Logon to the server.
	 * @param username
	 * @param password
	 * @return
	 * @throws AuthenticationFailedException
	 */
	public ISession logon(String username, String password) throws AuthenticationFailedException {
		session = mailServer.logon(username, password);
		fireEvent(EVENT_LOGON, new MailModelEvent(this));
		return session;
	}
	
	/**
	 * Logout the current user and close the session.
	 */
	public void logout() {
		session.close();
		fireEvent(EVENT_LOGOUT, new MailModelEvent(this));
	}

	/**
	 * @return the session
	 */
	public ISession getSession() {
		return session;
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
		fireEvent(EVENT_MAILSELECTION, new MailModelEvent(this, selectedMail));
	}
	
}
