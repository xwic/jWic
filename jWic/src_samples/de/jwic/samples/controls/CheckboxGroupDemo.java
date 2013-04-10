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
 * $Id: CheckboxDemo.java,v 1.4 2010/01/26 11:25:17 lordsam Exp $
 */
package de.jwic.samples.controls;

import java.text.DateFormat;
import java.util.Date;

import de.jwic.base.ControlContainer;
import de.jwic.base.IControlContainer;
import de.jwic.controls.Button;
import de.jwic.controls.CheckboxControl;
import de.jwic.controls.InputBoxControl;
import de.jwic.controls.LabelControl;
import de.jwic.controls.ListBoxControl;
import de.jwic.events.ElementSelectedEvent;
import de.jwic.events.ElementSelectedListener;
import de.jwic.events.SelectionListener;

/**
 * 
 * Demonstrates the usage of the Checkbox Control.
 * 
 * @author Florian Lippisch
 * @version $Revision: 1.4 $
 */
public class CheckboxGroupDemo extends ControlContainer {

	private CheckboxControl checkbox;
	
	private InputBoxControl txtTitle;
	private InputBoxControl txtKey;
	
	private Button btVisible;
	private Button btEnabled;
	private Button btChange;
	private Button btRemove;
	private ListBoxControl eventLog;
	
	private LabelControl lblSelected;
	
	private class EventLogListener implements ElementSelectedListener {
		public void elementSelected(ElementSelectedEvent event) {
			
			DateFormat df = DateFormat.getDateTimeInstance();
			String eventInfo = df.format(new Date()) + ": elementSelected (" + event.getElement() + ")";
			eventLog.addElement(eventInfo);
		}
	}
	
	/**
	 * Constructor.
	 * @param container
	 */
	public CheckboxGroupDemo(IControlContainer container) {
		super(container);
		
		// create the button instance
		checkbox = new CheckboxControl(this, "checkbox");
		
		// add the eventLog listener
		checkbox.addElementSelectedListener(new EventLogListener());
		
		// add some entries
		checkbox.addElement("Red");
		checkbox.addElement("Green");
		checkbox.addElement("Blue");
		
		// add Label that displays the selected key.
		lblSelected = new LabelControl(this, "lblSelected");
		checkbox.addElementSelectedListener(new ElementSelectedListener() {
			public void elementSelected(ElementSelectedEvent event) {
				lblSelected.setText(checkbox.getSelectedKey());				
			};
		});
		
		// add 'Remove Selected' button
		btRemove = new Button(this, "btRemove");
		btRemove.setTitle("Remove Selected");
		btRemove.addSelectionListener(new SelectionListener() {
			public void objectSelected(de.jwic.events.SelectionEvent event) {
				removeSelected();
			};
		});
		
		// add 'Clear button'
		Button btClearAll = new Button(this, "btClear");
		btClearAll.setTitle("Clear Entries");
		btClearAll.addSelectionListener(new SelectionListener() {
			public void objectSelected(de.jwic.events.SelectionEvent event) {
				clearEntries();
			};
		});
		
		// Change Combobox Columns
		ListBoxControl lbSize = new ListBoxControl(this, "lbSize");
		lbSize.addElement("0 - Default", "0");
		for (int i = 1; i < 10; i++) {
			lbSize.addElement(Integer.toString(i));
		}
		lbSize.setSelectedKey(Integer.toString(checkbox.getColumns()));
		lbSize.setChangeNotification(true);
		lbSize.addElementSelectedListener(new ElementSelectedListener() {
			public void elementSelected(ElementSelectedEvent event) {
				setColumns(Integer.parseInt((String)event.getElement()));
			};
		});
		
		// Change Width
		ListBoxControl lbWidth = new ListBoxControl(this, "lbWidth");
		lbWidth.addElement("0 - Unspecified", "0");
		for (int i = 50; i < 401; i += 50) {
			lbWidth.addElement(Integer.toString(i) + "px", Integer.toString(i));
		}
		lbWidth.setSelectedKey(Integer.toString(checkbox.getWidth()));
		lbWidth.setChangeNotification(true);
		lbWidth.addElementSelectedListener(new ElementSelectedListener() {
			public void elementSelected(ElementSelectedEvent event) {
				checkbox.setWidth(Integer.parseInt((String)event.getElement()));
			};
		});
	
		// Change Height
		ListBoxControl lbHeight = new ListBoxControl(this, "lbHeight");
		lbHeight.addElement("0 - Unspecified", "0");
		for (int i = 50; i < 401; i += 50) {
			lbHeight.addElement(Integer.toString(i) + "px", Integer.toString(i));
		}
		lbHeight.setSelectedKey(Integer.toString(checkbox.getHeight()));
		lbHeight.setChangeNotification(true);
		lbHeight.addElementSelectedListener(new ElementSelectedListener() {
			public void elementSelected(ElementSelectedEvent event) {
				checkbox.setHeight(Integer.parseInt((String)event.getElement()));
			};
		});

		// Add text
		txtTitle = new InputBoxControl(this, "title");
		txtTitle.setWidth(300);	// width in px

		txtKey = new InputBoxControl(this, "key");
		txtKey.setWidth(100);	// width in px

		Button btAdd = new Button(this, "btAdd");
		btAdd.setTitle("Add");
		btAdd.addSelectionListener(new SelectionListener() {
			public void objectSelected(de.jwic.events.SelectionEvent event) {
				addText();
			};
		});

		
		btVisible = new Button(this, "btVisible");
		btVisible.setTitle(checkbox.isVisible() ? "Set Invisible" : "Set Visible");
		btVisible.addSelectionListener(new SelectionListener() {
			public void objectSelected(de.jwic.events.SelectionEvent event) {
				changeVisible();
			};
		});

		btEnabled = new Button(this, "btEnabled");
		btEnabled.setTitle(checkbox.isEnabled() ? "Disable" : "Enable");
		btEnabled.addSelectionListener(new SelectionListener() {
			public void objectSelected(de.jwic.events.SelectionEvent event) {
				changeEnabled();
			};
		});

		btChange = new Button(this, "btChange");
		btChange.setTitle(checkbox.isChangeNotification() ? "Disable" : "Enable");
		btChange.addSelectionListener(new SelectionListener() {
			public void objectSelected(de.jwic.events.SelectionEvent event) {
				changeChangeNotification();
			};
		});
		
		eventLog = new ListBoxControl(this, "eventLog");
		eventLog.setSize(8);
		
	}

	/**
	 * Removes all entries from the list.
	 */
	protected void clearEntries() {

		checkbox.clear();
		
	}

	/**
	 * Remove the selected entries.
	 */
	protected void removeSelected() {
		
		String[] keys = checkbox.getSelectedKeys();
		for (int i = 0; i < keys.length; i++) {
			checkbox.removeElement(keys[i]);
		}
		
	}

	/**
	 * Enable/Disable the changeNotification.
	 */
	protected void changeChangeNotification() {
		
		checkbox.setChangeNotification(!checkbox.isChangeNotification());
		btChange.setTitle(checkbox.isChangeNotification() ? "Disable" : "Enable");
		
	}

	/**
	 * Set the size of the ListBoxControl.
	 * @param i
	 */
	protected void setColumns(int i) {
		checkbox.setColumns(i);
	}

	/**
	 * Change the Enabled property of the button.
	 */
	protected void changeEnabled() {
		
		checkbox.setEnabled(!checkbox.isEnabled());
		btEnabled.setTitle(checkbox.isEnabled() ? "Disable" : "Enable");
		
	}

	/**
	 * Change the Visible property of the button.
	 */
	protected void changeVisible() {
		
		checkbox.setVisible(!checkbox.isVisible());
		btVisible.setTitle(checkbox.isVisible() ? "Set Invisible" : "Set Visible");
		
	}

	/**
	 * Change the text of the Label.
	 */
	protected void addText() {
		
		if (txtKey.getText().length() != 0) {
			checkbox.addElement(txtTitle.getText(), txtKey.getText());
		} else {
			// if no key is given, the title is used as key.
			checkbox.addElement(txtTitle.getText());
		}
		
	}
	
}
