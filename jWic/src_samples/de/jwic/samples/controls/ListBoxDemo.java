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
 * $Id: ListBoxDemo.java,v 1.7 2010/02/07 14:24:24 lordsam Exp $
 */
package de.jwic.samples.controls;

import java.text.DateFormat;
import java.util.Date;

import de.jwic.base.ControlContainer;
import de.jwic.base.IControlContainer;
import de.jwic.controls.Button;
import de.jwic.controls.InputBoxControl;
import de.jwic.controls.LabelControl;
import de.jwic.controls.ListBoxControl;
import de.jwic.events.ElementSelectedEvent;
import de.jwic.events.ElementSelectedListener;
import de.jwic.events.SelectionEvent;
import de.jwic.events.SelectionListener;
import de.jwic.samples.controls.propeditor.PropertyEditorView;

/**
 * 
 * Demonstrates the usage of the ButtonControl.
 * 
 * @author Florian Lippisch
 * @version $Revision: 1.7 $
 */
public class ListBoxDemo extends ControlContainer {

	private ListBoxControl listbox;
	
	private InputBoxControl txtTitle;
	private InputBoxControl txtKey;
	
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
	private class SelectionEventLogListener implements SelectionListener {
		public void objectSelected(SelectionEvent event) {
			
			DateFormat df = DateFormat.getDateTimeInstance();
			String eventInfo = df.format(new Date()) + ": SelectionListener.objectSelected()";
			if (event.isDblClick()) {
				eventInfo = eventInfo + " - DoubleClicked";
			}
			eventLog.addElement(eventInfo);
		}
	}
	
	/**
	 * Constructor.
	 * @param container
	 */
	public ListBoxDemo(IControlContainer container) {
		super(container);
		
		// create the button instance
		listbox = new ListBoxControl(this, "listbox");
		
		// add the eventLog listener
		listbox.addElementSelectedListener(new EventLogListener());
		listbox.addSelectionListener(new SelectionEventLogListener());
		listbox.setChangeNotification(true);
		
		// add some entries
		listbox.addElement("Red");
		listbox.addElement("Green");
		listbox.addElement("Blue");
		
		final PropertyEditorView propEditor = new PropertyEditorView(this, "propEditor");
		propEditor.setBean(listbox);

		
		// add Label that displays the selected key.
		lblSelected = new LabelControl(this, "lblSelected");
		listbox.addElementSelectedListener(new ElementSelectedListener() {
			public void elementSelected(ElementSelectedEvent event) {
				lblSelected.setText(listbox.getSelectedKey());				
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
		
		// Add text
		txtTitle = new InputBoxControl(this, "title");
		txtTitle.setWidth(300);	// width in px
		txtTitle.setEmptyInfoText("Element Title");

		txtKey = new InputBoxControl(this, "key");
		txtKey.setWidth(100);	// width in px
		txtKey.setEmptyInfoText("Element Key");

		Button btAdd = new Button(this, "btAdd");
		btAdd.setTitle("Add");
		btAdd.addSelectionListener(new SelectionListener() {
			public void objectSelected(de.jwic.events.SelectionEvent event) {
				addText();
			};
		});

		eventLog = new ListBoxControl(this, "eventLog");
		eventLog.setSize(8);
		eventLog.setWidth(450);
		
	}

	/**
	 * Removes all entries from the list.
	 */
	protected void clearEntries() {

		listbox.clear();
		
	}

	/**
	 * Remove the selected entries.
	 */
	protected void removeSelected() {
		
		String[] keys = listbox.getSelectedKeys();
		for (int i = 0; i < keys.length; i++) {
			listbox.removeElement(keys[i]);
		}
		
	}

	/**
	 * Change the text of the Label.
	 */
	protected void addText() {
		
		if (txtKey.getText().length() != 0) {
			listbox.addElement(txtTitle.getText(), txtKey.getText());
		} else {
			// if no key is given, the title is used as key.
			listbox.addElement(txtTitle.getText());
		}
		
	}
	
}
