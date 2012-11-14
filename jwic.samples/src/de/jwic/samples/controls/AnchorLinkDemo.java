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
 * $Id: AnchorLinkDemo.java,v 1.3 2010/01/26 11:25:17 lordsam Exp $
 */
package de.jwic.samples.controls;

import java.text.DateFormat;
import java.util.Date;

import de.jwic.base.ControlContainer;
import de.jwic.base.IControlContainer;
import de.jwic.controls.AnchorLinkControl;
import de.jwic.controls.Button;
import de.jwic.controls.InputBoxControl;
import de.jwic.controls.ListBoxControl;
import de.jwic.events.ElementSelectedEvent;
import de.jwic.events.ElementSelectedListener;
import de.jwic.events.SelectionEvent;
import de.jwic.events.SelectionListener;

/**
 * 
 * Demonstrates the usage of the ButtonControl.
 * 
 * @author Florian Lippisch
 * @version $Revision: 1.3 $
 */
public class AnchorLinkDemo extends ControlContainer {

	private AnchorLinkControl anchor;
	private InputBoxControl text;
	private InputBoxControl infoText;
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
	public AnchorLinkDemo(IControlContainer container) {
		super(container);
		
		// create the button instance
		anchor = new AnchorLinkControl(this, "anchor");
		anchor.setTitle("Hit me.");
		
		// add the eventLog listener
		anchor.addSelectionListener(new EventLogListener());
		
		
		text = new InputBoxControl(this, "text");
		text.setText(anchor.getTitle());
		text.setWidth(150);	// width in px

		infoText = new InputBoxControl(this, "infotext");
		infoText.setText(anchor.getInfoMessage());
		infoText.setWidth(250);	// width in px

		Button btApply = new Button(this, "btApply");
		btApply.setTitle("Apply");
		btApply.addSelectionListener(new SelectionListener() {
			public void objectSelected(de.jwic.events.SelectionEvent event) {
				applyText();
			};
		});

		// Change Listbox Width
		ListBoxControl lbWidth = new ListBoxControl(this, "lbWidth");
		lbWidth.addElement("0 - Unspecified", "0");
		for (int i = 50; i < 401; i += 50) {
			lbWidth.addElement(Integer.toString(i) + "px", Integer.toString(i));
		}
		lbWidth.setSelectedKey(Integer.toString(anchor.getWidth()));
		lbWidth.setChangeNotification(true);
		lbWidth.addElementSelectedListener(new ElementSelectedListener() {
			public void elementSelected(ElementSelectedEvent event) {
				anchor.setWidth(Integer.parseInt((String)event.getElement()));
			};
		});
	
		// Change Listbox Height
		ListBoxControl lbHeight = new ListBoxControl(this, "lbHeight");
		lbHeight.addElement("0 - Unspecified", "0");
		for (int i = 50; i < 401; i += 50) {
			lbHeight.addElement(Integer.toString(i) + "px", Integer.toString(i));
		}
		lbHeight.setSelectedKey(Integer.toString(anchor.getHeight()));
		lbHeight.setChangeNotification(true);
		lbHeight.addElementSelectedListener(new ElementSelectedListener() {
			public void elementSelected(ElementSelectedEvent event) {
				anchor.setHeight(Integer.parseInt((String)event.getElement()));
			};
		});

		btVisible = new Button(this, "btVisible");
		btVisible.setTitle(anchor.isVisible() ? "Set Invisible" : "Set Visible");
		btVisible.addSelectionListener(new SelectionListener() {
			public void objectSelected(de.jwic.events.SelectionEvent event) {
				changeVisible();
			};
		});

		btEnabled = new Button(this, "btEnabled");
		btEnabled.setTitle(anchor.isEnabled() ? "Disable" : "Enable");
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
		
		anchor.setEnabled(!anchor.isEnabled());
		btEnabled.setTitle(anchor.isEnabled() ? "Disable" : "Enable");
		
	}

	/**
	 * Change the Visible property of the button.
	 */
	protected void changeVisible() {
		
		anchor.setVisible(!anchor.isVisible());
		btVisible.setTitle(anchor.isVisible() ? "Set Invisible" : "Set Visible");
		
	}

	/**
	 * Change the text of the Label.
	 */
	protected void applyText() {
		
		anchor.setTitle(text.getText());
		anchor.setInfoMessage(infoText.getText());
		
	}
	
}
