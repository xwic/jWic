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
package de.jwic.samples.controls;

import java.text.DateFormat;
import java.util.Date;

import de.jwic.base.ControlContainer;
import de.jwic.base.IControlContainer;
import de.jwic.controls.Button;
import de.jwic.controls.InputBox;
import de.jwic.controls.ListBoxControl;
import de.jwic.events.KeyEvent;
import de.jwic.events.KeyListener;
import de.jwic.events.SelectionEvent;
import de.jwic.events.SelectionListener;
import de.jwic.events.ValueChangedEvent;
import de.jwic.events.ValueChangedListener;
import de.jwic.samples.controls.propeditor.PropertyEditorView;

/**
 * 
 * Demonstrates the usage of the LabelControl.
 * 
 * @author Florian Lippisch
 * @version $Revision: 1.7 $
 */
public class InputBoxDemo extends ControlContainer {

	private InputBox inputbox;
	private ListBoxControl eventLog;
	
	private class EventLogListener implements ValueChangedListener {
		/* (non-Javadoc)
		 * @see de.jwic.events.ValueChangedListener#valueChanged(de.jwic.events.ValueChangedEvent)
		 */
		public void valueChanged(ValueChangedEvent event) {
			
			DateFormat df = DateFormat.getDateTimeInstance();
			String eventInfo = df.format(new Date()) + ": valueChanged - old value: '" + event.getOldValue() + "' new value: '" + event.getNewValue() + "'";
			
			eventLog.addElement(eventInfo);
		}
	}

	public InputBoxDemo(IControlContainer container) {
		super(container);
		
		
		
		inputbox = new InputBox(this, "inputbox");
		inputbox.setText("");
		inputbox.setEmptyInfoText("Enter some text here.");
		
		final PropertyEditorView propEditor = new PropertyEditorView(this, "propEditor");
		
		
	
		inputbox.addValueChangedListener(new EventLogListener());
		inputbox.addKeyListener(new KeyListener() {
			public void keyPressed(KeyEvent event) {
				log.info("Pressed " + event.getKeyCode() + " code (value=" + inputbox.getText() + ")");
			}
		});
		
		Button b = new Button(this, "Button");
		b.addSelectionListener(new SelectionListener() {
			
			@Override
			public void objectSelected(SelectionEvent event) {
				inputbox.setText("Testing");
				
			}
		});
		
	
		propEditor.setBean(inputbox);
		
		eventLog = new ListBoxControl(this, "eventLog");
		eventLog.setSize(8);
	
	}
	
}
