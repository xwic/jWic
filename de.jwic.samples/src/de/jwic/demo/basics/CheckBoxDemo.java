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
import de.jwic.controls.CheckBox;
import de.jwic.events.ValueChangedEvent;
import de.jwic.events.ValueChangedListener;
import de.jwic.samples.controls.propeditor.PropertyEditorView;

/**
 * 
 * Demonstrates the usage of the CheckBox control.
 * 
 * @author Florian Lippisch
 * @version $Revision: 1.1 $
 */
public class CheckBoxDemo extends ControlContainer {

	private CheckBox checkBox;
	
	public CheckBoxDemo(IControlContainer container) {
		super(container);
		
		checkBox = new CheckBox(this, "control");
		checkBox.setLabel("Click Me");
		checkBox.setInfoText("The checkbox will toggle if you select on this element! Be carefull!!!");
		
		// create the property editor
		final PropertyEditorView propEditor = new PropertyEditorView(this, "propEditor");
		propEditor.setBean(checkBox);
		
		checkBox.addValueChangedListener(new ValueChangedListener() {
			/* (non-Javadoc)
			 * @see de.jwic.events.ValueChangedListener#valueChanged(de.jwic.events.ValueChangedEvent)
			 */
			public void valueChanged(ValueChangedEvent event) {
				propEditor.loadValues();	 // reload values.			
			}
		});
		
		
		propEditor.loadValues(); // refresh values.
		
	}
		
}
