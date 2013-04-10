/*
 * Copyright 2005 jWic group (http://www.jwic.de)
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
 * de.jwic.samples.controls.LabelDemo
 * Created on 28.10.2005
 * $Id: ButtonDemo.java,v 1.4 2010/01/26 11:25:17 lordsam Exp $
 */
package de.jwic.demo.basics;

import de.jwic.base.Control;
import de.jwic.base.ControlContainer;
import de.jwic.base.IControlContainer;
import de.jwic.base.ImageRef;
import de.jwic.controls.basics.Button;
import de.jwic.controls.basics.Label;
import de.jwic.events.SelectionEvent;
import de.jwic.events.SelectionListener;
import de.jwic.samples.controls.propeditor.PropertyEditorView;

/**
 * 
 * Demonstrates the usage of the ButtonControl.
 * 
 * @author Florian Lippisch
 * @version $Revision: 1.4 $
 */
public class ButtonDemo extends ControlContainer {

	private Button button;
	private Label lblInfo;
	
	/**
	 * Constructor.
	 * @param container
	 */
	public ButtonDemo(IControlContainer container) {
		super(container);
		
		// create the button instance
		button = new Button(this, "button");
		button.setTitle("A Button");
		button.setTooltip("Click me! Click me!");
		button.setIconEnabled(new ImageRef("gfx/search_en.gif"));
		button.setIconDisabled(new ImageRef("gfx/search_dis.gif"));
		button.setConfirmMsg("Are you sure you want to proceed?");
		button.addSelectionListener(new SelectionListener() {
			public void objectSelected(SelectionEvent event) {
				onButtonClick(event.getEventSource());
			}
		});

		Button button2 = new Button(this, "button2");
		button2.setTitle("Simple Plain Button");
		button2.addSelectionListener(new SelectionListener() {
			public void objectSelected(SelectionEvent event) {
				onButtonClick(event.getEventSource());
			}
		});

		Button button3 = new Button(this, "button3");
		button3.setTitle("I am disabled");
		button3.setEnabled(false);
		button3.addSelectionListener(new SelectionListener() {
			public void objectSelected(SelectionEvent event) {
				onButtonClick(event.getEventSource());
			}
		});

		
		// create the property editor
		final PropertyEditorView propEditor = new PropertyEditorView(this, "propEditor");
		propEditor.setBean(button);

		lblInfo = new Label(this, "lblInfo");
		lblInfo.setText("Click a button..");
		
	}

	/**
	 * @param eventSource
	 */
	protected void onButtonClick(Object eventSource) {
		
		lblInfo.setText("Button Clicked: " + ((Control)eventSource).getName());
		getSessionContext().notifyMessage(((Button)eventSource).getTitle() + " was clicked.");
		
	}
	
}
