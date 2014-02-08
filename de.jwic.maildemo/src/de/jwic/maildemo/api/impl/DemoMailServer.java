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
 * de.jwic.maildemo.api.impl.DemoMailServer
 * Created on 23.04.2007
 * $Id: DemoMailServer.java,v 1.2 2007/04/27 11:13:37 aroncotrau Exp $
 */
package de.jwic.maildemo.api.impl;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import de.jwic.maildemo.api.AuthenticationFailedException;
import de.jwic.maildemo.api.IMail;
import de.jwic.maildemo.api.IMailServer;
import de.jwic.maildemo.api.ISession;

/**
 * Demo (dummy) implementation of the mail server api.
 *  
 * @author Florian Lippisch
 */
public class DemoMailServer implements IMailServer, Serializable {

	private static List MAILS = DemoDataSetup.createMailList(30);
	
	// for a faster access to mails by key, we store the "demo" mails in
	// a map. In a real environment, the database would handle this.
	private static Map MAIL_MAP = new HashMap();
	static {
		for (Iterator it = MAILS.iterator(); it.hasNext(); ) {
			IMail mail = (IMail)it.next();
			MAIL_MAP.put(mail.getUniqueID(), mail);
		}
	}
	/**
	 * Yet, a simple accessor.
	 * @return
	 */
	public static IMailServer getInstance() {
		return new DemoMailServer();
	}
	
	/* (non-Javadoc)
	 * @see de.jwic.maildemo.api.IMailServer#logon(java.lang.String, java.lang.String)
	 */
	public ISession logon(String username, String password) throws AuthenticationFailedException {

		if (!username.equalsIgnoreCase("Demouser")) {
			throw new AuthenticationFailedException("Wrong username - try 'Demouser'");			
		}
		if (!password.equalsIgnoreCase("jwic")) {
			throw new AuthenticationFailedException("Wrong password - try 'jwic'");
		}
		
		return new DemoSession(username, DemoDataSetup.initRootNode());
	}
	
	/**
	 * Returns the mail by ID.
	 * @param username
	 * @param uniqueID
	 * @return
	 */
	public IMail getMailByID(String username, String uniqueID) {
		return (IMail)MAIL_MAP.get(uniqueID);
	}

}
