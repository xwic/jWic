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
 * de.jwic.maildemo.main.MailDemoApp
 * Created on 23.04.2007
 * $Id: MailDemoApp.java,v 1.1 2007/04/24 07:46:58 lordsam Exp $
 */
package de.jwic.maildemo.main;

import de.jwic.base.Application;
import de.jwic.base.Control;
import de.jwic.base.IControlContainer;
import de.jwic.maildemo.api.IMailServer;
import de.jwic.maildemo.api.impl.DemoMailServer;
import de.jwic.maildemo.client.MailClientPage;

/**
 * The main entry class for the MailDemo. The application is started
 * and configured by this class.
 * 
 * @author Florian Lippisch
 */
public class MailDemoApp extends Application {

	private MailModel model = null;
	
	/* (non-Javadoc)
	 * @see de.jwic.base.Application#createRootControl(de.jwic.base.IControlContainer)
	 */
	public Control createRootControl(IControlContainer container) {
		
		getSessionContext().setExitURL("byebye.html");

		model = new MailModel(getMailServer());
		IntroPage page = new IntroPage(container, model);
		page.setTitle("jWic MailClient Demo");

		// react on application state changes.
		model.addMailModelListener(new MailModelAdapter() {
			
			public void logonSuccess(MailModelEvent event) {
				handleLogon();
			}
			
			public void logoutSuccess(MailModelEvent event) {
				handleLogout();
			}
			
		});
				
		return page;
	}
	
	/**
	 * 
	 */
	protected void handleLogout() {
		
		// exit the application
		getSessionContext().exit();
		
	}

	/**
	 * 
	 */
	protected void handleLogon() {
		
		// create the main application page and put it on-top of the control stack.
		MailClientPage mcPage = new MailClientPage(getSessionContext(), model);
		getSessionContext().pushTopControl(mcPage);
		
	}

	/**
	 * Returns the mail server API. In a real application, this method would
	 * either look into some configuration file or would be pre-fillied by
	 * a component container (i.e. Spring).
	 * 
	 * @return
	 */
	public IMailServer getMailServer() {
		
		return DemoMailServer.getInstance();
		
	}

}
