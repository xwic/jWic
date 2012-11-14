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
 * de.jwic.maildemo.main.MailModelAdapter
 * Created on 23.04.2007
 * $Id: MailModelAdapter.java,v 1.2 2007/04/25 08:54:50 lordsam Exp $
 */
package de.jwic.maildemo.main;

/**
 * Adapter for the IMailModelListener.
 * @author Florian Lippisch
 */
public abstract class MailModelAdapter implements IMailModelListener {

	/* (non-Javadoc)
	 * @see de.jwic.maildemo.main.IMailModelListener#logonSuccess(de.jwic.maildemo.main.MailModelEvent)
	 */
	public void logonSuccess(MailModelEvent event) {

	}

	/* (non-Javadoc)
	 * @see de.jwic.maildemo.main.IMailModelListener#logoutSuccess(de.jwic.maildemo.main.MailModelEvent)
	 */
	public void logoutSuccess(MailModelEvent event) {
		
	}
	
	/* (non-Javadoc)
	 * @see de.jwic.maildemo.main.IMailModelListener#mailSelected(de.jwic.maildemo.main.MailModelEvent)
	 */
	public void mailSelected(MailModelEvent event) {
		
	}
	
}
