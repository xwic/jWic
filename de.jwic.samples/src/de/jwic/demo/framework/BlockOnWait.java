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
package de.jwic.demo.framework;

import java.text.DateFormat;
import java.util.Date;

import de.jwic.base.ControlContainer;
import de.jwic.base.IControlContainer;
import de.jwic.controls.InputBox;
import de.jwic.controls.ListBoxControl;
import de.jwic.controls.Button;
import de.jwic.events.SelectionEvent;
import de.jwic.events.SelectionListener;

/**
 * Demonstrates what happens if a button triggers a long running process.
 * 
 * @author Florian Lippisch
 * @version $Revision: 1.3 $
 */
public class BlockOnWait extends ControlContainer {

	private Button button;
	
	/**
	 * Constructor.
	 * @param container
	 */
	public BlockOnWait(IControlContainer container) {
		super(container);
		
		// create the button instance
		button = new Button(this, "button");
		button.setTitle("Start long operation... (3 sec.).");
		
		// add the eventLog listener
		button.addSelectionListener(new SelectionListener() {
			public void objectSelected(SelectionEvent event) {
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					// nothing to do
				}
			}
		});
		
		
		// create the button instance
		Button anotherButton = new Button(this, "anotherButton");
		anotherButton.setTitle("Click Me");
		
		// add the eventLog listener
		anotherButton.addSelectionListener(new SelectionListener() {
			public void objectSelected(SelectionEvent event) {
				getSessionContext().notifyMessage("You clicked me", "info");
			}
		});

		
	}


	
}
