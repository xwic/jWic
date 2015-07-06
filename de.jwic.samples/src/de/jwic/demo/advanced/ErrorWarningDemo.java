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
package de.jwic.demo.advanced;

import de.jwic.base.ControlContainer;
import de.jwic.base.IControlContainer;
import de.jwic.controls.Button;
import de.jwic.controls.ErrorWarning;
import de.jwic.events.SelectionEvent;
import de.jwic.events.SelectionListener;

public class ErrorWarningDemo extends ControlContainer {
	private final ErrorWarning errorWarning;
	private final Button button;
	private final Button buttonClose;
	private final Button buttonHide;
	public ErrorWarningDemo(IControlContainer container) {
		this(container,null);
	}

	public ErrorWarningDemo(IControlContainer container, String name) {
		super(container, name);
		this.errorWarning = new ErrorWarning(this, "errorWarning");
		this.button = new Button(this,"clickMe");
		button.setTitle("Click To Show Error");
		this.buttonClose = new Button(this, "clickClose");
		this.buttonClose.setTitle("Click to Close the Error");
		
		this.buttonHide = new Button(this, "clickHide");
		this.buttonHide.setTitle("Click to Hide/Slide Up");
		
		init();
		
	}

	private void init() {
		this.button.addSelectionListener(new SelectionListener() {
			
			@Override
			public void objectSelected(SelectionEvent event) {
				errorWarning.showError("Some random error has occured, but thankfully you can show in a pretty way.");
			}
		});
		
		this.buttonClose.addSelectionListener(new SelectionListener() {
			
			@Override
			public void objectSelected(SelectionEvent event) {
				errorWarning.close();
			}
		});
		
		this.buttonHide.addSelectionListener(new SelectionListener() {
			
			@Override
			public void objectSelected(SelectionEvent event) {
				errorWarning.hide();
			}
		});
	}

}
