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
 * de.jwic.maildemo.logon.LogonControl
 * Created on 23.04.2007
 * $Id: LogonControl.java,v 1.2 2012/08/29 07:46:51 lordsam Exp $
 */
package de.jwic.maildemo.logon;

import de.jwic.base.ControlContainer;
import de.jwic.base.IControlContainer;
import de.jwic.controls.Button;
import de.jwic.controls.InputBox;
import de.jwic.ecolib.controls.ErrorWarningControl;
import de.jwic.events.KeyEvent;
import de.jwic.events.KeyListener;
import de.jwic.events.SelectionEvent;
import de.jwic.events.SelectionListener;
import de.jwic.maildemo.api.AuthenticationFailedException;
import de.jwic.maildemo.main.MailModel;

/**
 * Displays a little box with a username and password field.
 * @author Florian Lippisch
 */
public class LogonControl extends ControlContainer {

	private InputBox inpUsername;
	private InputBox inpPassword;
	private ErrorWarningControl errorInfo;
	
	private MailModel model;

	/**
	 * @param container
	 * @param name
	 */
	public LogonControl(IControlContainer container, String name, MailModel model) {
		super(container, name);
		
		this.model = model;
		
		inpUsername = new InputBox(this, "inpUsername");
		inpUsername.setText("Demouser");
		inpUsername.setFillWidth(true);
		
		inpPassword = new InputBox(this, "inpPassword");
		inpPassword.setPassword(true); 
		inpPassword.setFillWidth(true);
		inpPassword.forceFocus();
		inpPassword.setListenKeyCode(13); // listen to ENTER key
		inpPassword.addKeyListener(new KeyListener() {
			public void keyPressed(KeyEvent event) {
				doLogon();	// call logon on ENTER key
			}
		});
		
		errorInfo = new ErrorWarningControl(this, "errorInfo");
		errorInfo.setAutoClose(true);
		
		Button btSubmit = new Button(this, "btSubmit");
		btSubmit.setTitle("Logon");
		btSubmit.addSelectionListener(new SelectionListener() {
			public void objectSelected(SelectionEvent event) {
				doLogon();				
			}
		});
		
	}

	/**
	 * 
	 */
	protected void doLogon() {

		try {
			model.logon(inpUsername.getText(), inpPassword.getText());
		} catch (AuthenticationFailedException e) {
			errorInfo.showError("Authentication failed (" + e.getMessage() + ")");
			inpPassword.forceFocus();
		}
		inpPassword.setText("");
		
	}


}
