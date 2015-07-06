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

import de.jwic.base.Control;
import de.jwic.base.ControlContainer;
import de.jwic.base.IControlContainer;
import de.jwic.base.ImageRef;
import de.jwic.controls.Button;
import de.jwic.controls.LabelControl;
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
	private LabelControl lblInfo;
	
	/**
	 * Constructor.
	 * @param container
	 */
	public ButtonDemo(IControlContainer container) {
		super(container);
		
		// create the button instance
		button = new Button(this, "button");
		button.setTitle("Simple Button");
		button.addSelectionListener(new SelectionListener() {
			public void objectSelected(SelectionEvent event) {
				onButtonClick(event.getEventSource());
			}
		});

		Button button2 = new Button(this, "button2");
		button2.setTitle("With Image");
		button2.setIconEnabled(new ImageRef(getClass().getPackage(), "search_en.gif"));
		button2.setIconDisabled(new ImageRef(getClass().getPackage(), "search_dis.gif"));
		button2.addSelectionListener(new SelectionListener() {
			public void objectSelected(SelectionEvent event) {
				onButtonClick(event.getEventSource());
			}
		});

		Button button3 = new Button(this, "button3");
		button3.setTitle("Small Text");
		button3.setCssClass("j-button j-btn-small");
		button3.addSelectionListener(new SelectionListener() {
			public void objectSelected(SelectionEvent event) {
				onButtonClick(event.getEventSource());
			}
		});

		
		// create the property editor
		final PropertyEditorView propEditor = new PropertyEditorView(this, "propEditor");
		propEditor.setBean(button);

		lblInfo = new LabelControl(this, "lblInfo");
		lblInfo.setText("Click a button..");
		
	}

	/**
	 * @param eventSource
	 */
	protected void onButtonClick(Object eventSource) {
		
		lblInfo.setText("Button Clicked: " + ((Control)eventSource).getName());
		
	}
	
}
