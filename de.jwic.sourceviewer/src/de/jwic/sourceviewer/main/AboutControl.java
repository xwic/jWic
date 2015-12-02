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
package de.jwic.sourceviewer.main;

import de.jwic.base.ControlContainer;
import de.jwic.base.IControlContainer;
import de.jwic.controls.Button;
import de.jwic.events.SelectionListener;

/**
 * Displays a page with some informations about this tool.
 * @author Florian Lippisch
 */
public class AboutControl extends ControlContainer {

	private Button btBack;
	/**
	 * @param container
	 * @param name
	 */
	public AboutControl(IControlContainer container, String name) {
		super(container, name);
		
		btBack = new Button(this, "btBack");
		btBack.setTitle("Back");
		
	}

	/**
	 * Add a listener to the back button.
	 * @param listener
	 */
	public void addBackSelectionListener(SelectionListener listener) {
		btBack.addSelectionListener(listener);
	}

}
