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
 * $Id: ButtonImageDemo.java,v 1.3 2010/01/26 11:25:17 lordsam Exp $
 */
package de.jwic.samples.controls;

import java.text.DateFormat;
import java.util.Date;

import de.jwic.base.ControlContainer;
import de.jwic.base.IControlContainer;
import de.jwic.controls.Button;
import de.jwic.controls.InputBoxControl;
import de.jwic.controls.ListBoxControl;
import de.jwic.events.SelectionEvent;
import de.jwic.events.SelectionListener;

/**
 * 
 * Demonstrates the usage of the ButtonControl.
 * 
 * @author Florian Lippisch
 * @version $Revision: 1.3 $
 */
public class ButtonImageDemo extends ControlContainer {

	private ButtonImageControl button;
	private InputBoxControl text;
	private Button btVisible;
	private Button btEnabled;
	private ListBoxControl eventLog;
	
	private class EventLogListener implements SelectionListener {
		public void objectSelected(SelectionEvent event) {
			
			DateFormat df = DateFormat.getDateTimeInstance();
			String eventInfo = df.format(new Date()) + ": objectSelected " + event.getEventSource();
			
			eventLog.addElement(eventInfo);
		}
	}
	
	/**
	 * Constructor.
	 * @param container
	 */
	public ButtonImageDemo(IControlContainer container) {
		super(container);
		
		// create the button instance
		button = new ButtonImageControl(this, "button");
		button.setTitle("Hit me.");
		button.setCreateUniqueURL(true);
		
		// add the eventLog listener
		button.addSelectionListener(new EventLogListener());
		
		
		text = new InputBoxControl(this, "text");
		text.setText(button.getTitle());
		text.setWidth(400);	// width in px
		
		Button btApply = new Button(this, "btApply");
		btApply.setTitle("Apply");
		btApply.addSelectionListener(new SelectionListener() {
			public void objectSelected(de.jwic.events.SelectionEvent event) {
				applyText();
			};
		});

		btVisible = new Button(this, "btVisible");
		btVisible.setTitle(button.isVisible() ? "Set Invisible" : "Set Visible");
		btVisible.addSelectionListener(new SelectionListener() {
			public void objectSelected(de.jwic.events.SelectionEvent event) {
				changeVisible();
			};
		});

		btEnabled = new Button(this, "btEnabled");
		btEnabled.setTitle(button.isEnabled() ? "Disable" : "Enable");
		btEnabled.addSelectionListener(new SelectionListener() {
			public void objectSelected(de.jwic.events.SelectionEvent event) {
				changeEnabled();
			};
		});

		
		eventLog = new ListBoxControl(this, "eventLog");
		eventLog.setSize(8);
		
	}

	/**
	 * Change the Enabled property of the button.
	 */
	protected void changeEnabled() {
		
		button.setEnabled(!button.isEnabled());
		btEnabled.setTitle(button.isEnabled() ? "Disable" : "Enable");
		
	}

	/**
	 * Change the Visible property of the button.
	 */
	protected void changeVisible() {
		
		button.setVisible(!button.isVisible());
		btVisible.setTitle(button.isVisible() ? "Set Invisible" : "Set Visible");
		
	}

	/**
	 * Change the text of the Label.
	 */
	protected void applyText() {
		
		button.setTitle(text.getText());
		
	}
	
}
