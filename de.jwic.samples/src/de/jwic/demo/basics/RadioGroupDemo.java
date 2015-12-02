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
package de.jwic.demo.basics;

import java.text.DateFormat;
import java.util.Date;

import de.jwic.base.ControlContainer;
import de.jwic.base.IControlContainer;
import de.jwic.controls.Button;
import de.jwic.controls.InputBox;
import de.jwic.controls.LabelControl;
import de.jwic.controls.ListBox;
import de.jwic.controls.RadioGroup;
import de.jwic.events.ElementSelectedEvent;
import de.jwic.events.ElementSelectedListener;
import de.jwic.events.SelectionListener;

/**
 * 
 * Demonstrates the usage of the ButtonControl.
 * 
 * @author Florian Lippisch
 * @version $Revision: 1.3 $
 */
public class RadioGroupDemo extends ControlContainer {

	private RadioGroup radiogroup;
	
	private InputBox txtTitle;
	private InputBox txtKey;
	
	private Button btVisible;
	private Button btEnabled;
	private Button btChange;
	private Button btRemove;
	private ListBox eventLog;
	
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
	public RadioGroupDemo(IControlContainer container) {
		super(container);
		
		// create the button instance
		radiogroup = new RadioGroup(this, "radiogroup");
		
		// add the eventLog listener
		radiogroup.addElementSelectedListener(new EventLogListener());
		
		// add some entries
		radiogroup.addElement("Red");
		radiogroup.addElement("Green");
		radiogroup.addElement("Blue");
		
		// add Label that displays the selected key.
		lblSelected = new LabelControl(this, "lblSelected");
		radiogroup.addElementSelectedListener(new ElementSelectedListener() {
			public void elementSelected(ElementSelectedEvent event) {
				lblSelected.setText(radiogroup.getSelectedKey());				
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
		
		// Change Columns
		
		ListBox lbSize = new ListBox(this, "lbSize");
		lbSize.addElement("0 - Default", "0");
		for (int i = 1; i < 10; i++) {
			lbSize.addElement(Integer.toString(i));
		}
		lbSize.setSelectedKey(Integer.toString(radiogroup.getColumns()));
		lbSize.setChangeNotification(true);
		lbSize.addElementSelectedListener(new ElementSelectedListener() {
			public void elementSelected(ElementSelectedEvent event) {
				setColumns(Integer.parseInt((String)event.getElement()));
			};
		});
		
		// Change Width
		ListBox lbWidth = new ListBox(this, "lbWidth");
		lbWidth.addElement("0 - Unspecified", "0");
		for (int i = 50; i < 401; i += 50) {
			lbWidth.addElement(Integer.toString(i) + "px", Integer.toString(i));
		}
		lbWidth.setSelectedKey(Integer.toString(radiogroup.getWidth()));
		lbWidth.setChangeNotification(true);
		lbWidth.addElementSelectedListener(new ElementSelectedListener() {
			public void elementSelected(ElementSelectedEvent event) {
				radiogroup.setWidth(Integer.parseInt((String)event.getElement()));
			};
		});
	
		// Change Height
		ListBox lbHeight = new ListBox(this, "lbHeight");
		lbHeight.addElement("0 - Unspecified", "0");
		for (int i = 50; i < 401; i += 50) {
			lbHeight.addElement(Integer.toString(i) + "px", Integer.toString(i));
		}
		lbHeight.setSelectedKey(Integer.toString(radiogroup.getHeight()));
		lbHeight.setChangeNotification(true);
		lbHeight.addElementSelectedListener(new ElementSelectedListener() {
			public void elementSelected(ElementSelectedEvent event) {
				radiogroup.setHeight(Integer.parseInt((String)event.getElement()));
			};
		});

		// Add text
		txtTitle = new InputBox(this, "title");
		txtTitle.setWidth(300);	// width in px

		txtKey = new InputBox(this, "key");
		txtKey.setWidth(100);	// width in px

		Button btAdd = new Button(this, "btAdd");
		btAdd.setTitle("Add");
		btAdd.addSelectionListener(new SelectionListener() {
			public void objectSelected(de.jwic.events.SelectionEvent event) {
				addText();
			};
		});

		
		btVisible = new Button(this, "btVisible");
		btVisible.setTitle(radiogroup.isVisible() ? "Set Invisible" : "Set Visible");
		btVisible.addSelectionListener(new SelectionListener() {
			public void objectSelected(de.jwic.events.SelectionEvent event) {
				changeVisible();
			};
		});

		btEnabled = new Button(this, "btEnabled");
		btEnabled.setTitle(radiogroup.isEnabled() ? "Disable" : "Enable");
		btEnabled.addSelectionListener(new SelectionListener() {
			public void objectSelected(de.jwic.events.SelectionEvent event) {
				changeEnabled();
			};
		});

		btChange = new Button(this, "btChange");
		btChange.setTitle(radiogroup.isChangeNotification() ? "Disable" : "Enable");
		btChange.addSelectionListener(new SelectionListener() {
			public void objectSelected(de.jwic.events.SelectionEvent event) {
				changeChangeNotification();
			};
		});
		
		eventLog = new ListBox(this, "eventLog");
		eventLog.setSize(8);
		
	}

	/**
	 * Removes all entries from the list.
	 */
	protected void clearEntries() {

		radiogroup.clear();
		
	}

	/**
	 * Remove the selected entries.
	 */
	protected void removeSelected() {
		
		String[] keys = radiogroup.getSelectedKeys();
		for (int i = 0; i < keys.length; i++) {
			radiogroup.removeElementByKey(keys[i]);
		}
		
	}

	/**
	 * Enable/Disable the changeNotification.
	 */
	protected void changeChangeNotification() {
		
		radiogroup.setChangeNotification(!radiogroup.isChangeNotification());
		btChange.setTitle(radiogroup.isChangeNotification() ? "Disable" : "Enable");
		
	}

	/**
	 * Set the size of the ListBox.
	 * @param i
	 */
	protected void setColumns(int i) {
		radiogroup.setColumns(i);
	}

	/**
	 * Change the Enabled property of the button.
	 */
	protected void changeEnabled() {
		
		radiogroup.setEnabled(!radiogroup.isEnabled());
		btEnabled.setTitle(radiogroup.isEnabled() ? "Disable" : "Enable");
		
	}

	/**
	 * Change the Visible property of the button.
	 */
	protected void changeVisible() {
		
		radiogroup.setVisible(!radiogroup.isVisible());
		btVisible.setTitle(radiogroup.isVisible() ? "Set Invisible" : "Set Visible");
		
	}

	/**
	 * Change the text of the Label.
	 */
	protected void addText() {
		
		if (txtKey.getText().length() != 0) {
			radiogroup.addElement(txtTitle.getText(), txtKey.getText());
		} else {
			// if no key is given, the title is used as key.
			radiogroup.addElement(txtTitle.getText());
		}
		
	}
	
}
