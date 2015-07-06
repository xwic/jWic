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

import de.jwic.base.ControlContainer;
import de.jwic.base.IControlContainer;
import de.jwic.controls.Button;
import de.jwic.controls.CheckBoxGroup;
import de.jwic.controls.InputBox;
import de.jwic.events.ElementSelectedEvent;
import de.jwic.events.ElementSelectedListener;
import de.jwic.events.SelectionListener;
import de.jwic.samples.controls.propeditor.PropertyEditorView;

/**
 * 
 * Demonstrates the usage of the Checkbox Control.
 * 
 * @author Florian Lippisch
 * @version $Revision: 1.4 $
 */
public class CheckBoxGroupDemo extends ControlContainer {

	private CheckBoxGroup checkbox;
	
	private InputBox txtTitle;
	private InputBox txtKey;
	
	private Button btRemove;
	
	/**
	 * Constructor.
	 * @param container
	 */
	public CheckBoxGroupDemo(IControlContainer container) {
		super(container);
		
		// create the button instance
		checkbox = new CheckBoxGroup(this, "checkbox");
		
		// add some entries
		checkbox.addElement("Red");
		checkbox.addElement("Green");
		checkbox.addElement("Blue");
		
		
		// create the property editor
		final PropertyEditorView propEditor = new PropertyEditorView(this, "propEditor");
		propEditor.setBean(checkbox);
		
		checkbox.addElementSelectedListener(new ElementSelectedListener() {
			
			@Override
			public void elementSelected(ElementSelectedEvent event) {
				propEditor.loadValues();	 // reload values.			
			}
		});
		
		propEditor.loadValues(); // refresh values.

		
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
		txtTitle = new InputBox(this, "title");
		txtTitle.setWidth(150);	// width in px
		txtTitle.setEmptyInfoText("Title");

		txtKey = new InputBox(this, "key");
		txtKey.setWidth(120);	// width in px
		txtKey.setEmptyInfoText("Key is optional");

		Button btAdd = new Button(this, "btAdd");
		btAdd.setTitle("Add");
		btAdd.addSelectionListener(new SelectionListener() {
			public void objectSelected(de.jwic.events.SelectionEvent event) {
				addText();
			};
		});
		
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
			checkbox.removeElementByKey(keys[i]);
		}
		
	}

	/**
	 * Change the text of the Label.
	 */
	protected void addText() {
		
		try {
			if (txtKey.getText().length() != 0) {
				checkbox.addElement(txtTitle.getText(), txtKey.getText());
			} else {
				// if no key is given, the title is used as key.
				checkbox.addElement(txtTitle.getText());
			}
		} catch (Exception e) {
			getSessionContext().notifyMessage("Error: " + e.toString(), "error");
		}
		
	}
	
}
