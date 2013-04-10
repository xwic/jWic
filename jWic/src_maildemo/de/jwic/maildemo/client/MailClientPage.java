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
 * de.jwic.maildemo.client.MailClientPage
 * Created on 23.04.2007
 * $Id: MailClientPage.java,v 1.3 2012/08/29 07:46:51 lordsam Exp $
 */
package de.jwic.maildemo.client;

import de.jwic.base.Dimension;
import de.jwic.base.IControlContainer;
import de.jwic.base.Page;
import de.jwic.controls.Button;
import de.jwic.events.SelectionEvent;
import de.jwic.events.SelectionListener;
import de.jwic.maildemo.main.MailModel;

/**
 * The page for the main application.
 * @author Florian Lippisch
 */
public class MailClientPage extends Page {

	private MailModel model;
	private MailClientControl mailClient;
	
	/**
	 * @param container
	 */
	public MailClientPage(IControlContainer container, MailModel model) {
		super(container);
		this.model = model;
		
		init();
	} 

	/**
	 * Initialize controls.
	 *
	 */
	private void init() {

		setTitle("jWic MailClient (" + model.getSession().getUsername() + ")");
		Button btLogout = new Button(this, "btLogout");
		btLogout.setTitle("Logout");
		btLogout.addSelectionListener(new SelectionListener() {
			public void objectSelected(SelectionEvent event) {
				model.logout();
			}
		});
	
		mailClient = new MailClientControl(this, "mailClient", model);
		
	}
	
	@Override
	public void setPageSize(Dimension pageSize) {
		super.setPageSize(pageSize);
		mailClient.setHeight(pageSize.height - 120);
		mailClient.setWidth(pageSize.width- 80);
	}
	
}
