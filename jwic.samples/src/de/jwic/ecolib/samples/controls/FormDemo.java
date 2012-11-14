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
 * de.jwic.ecolib.samples.controls.FormDemo
 * Created on Feb 4, 2010
 * $Id: FormDemo.java,v 1.3 2011/06/02 14:05:35 lordsam Exp $
 */
package de.jwic.ecolib.samples.controls;

import de.jwic.base.ControlContainer;
import de.jwic.base.IControlContainer;
import de.jwic.controls.Button;
import de.jwic.controls.InputBoxControl;
import de.jwic.ecolib.controls.ErrorWarningControl;
import de.jwic.events.SelectionEvent;
import de.jwic.events.SelectionListener;

/**
 * Demo for typical form elements.
 * @author lippisch
 */
public class FormDemo extends ControlContainer {

	private InputBoxControl inpName;
	private InputBoxControl inpAge;
	private Button btOk;
	private ErrorWarningControl errorWarning;
	
	/**
	 * @param container
	 * @param name
	 */
	public FormDemo(IControlContainer container, String name) {
		super(container, name);
		
		createControls();
		
	}

	/**
	 * Create some form controls...
	 */
	private void createControls() {
		
		errorWarning = new ErrorWarningControl(this, "notifyer");
		errorWarning.setAutoCloseDelay(3000); // 3sec
		
		inpName = new InputBoxControl(this, "inpName");
		inpName.setWidth(250);
		inpName.setEmptyInfoText("Lastname, Firstname");
		
		inpAge = new InputBoxControl(this, "inpAge");
		inpAge.setWidth(80);
		inpAge.setMaxLength(3);
		
		btOk = new Button(this, "btOk");
		btOk.setTitle("Ok");
		btOk.addSelectionListener(new SelectionListener() {
			public void objectSelected(SelectionEvent event) {
				onOkAction();
			}
		});
		
	}

	protected void onOkAction() {

		try {
			int age = Integer.parseInt(inpAge.getText());
			if (age < 18) {
				errorWarning.showError("Sorry, 18+ only");
			} else {
				errorWarning.showWarning("Allright");
			}
			inpAge.setFlagAsError(false);
		} catch(Exception e) {
			errorWarning.showError(e);
			inpAge.setFlagAsError(true);
		}
		
	}

}
