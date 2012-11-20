/*
 * Copyright 2005-2007 jWic group (http://www.jwic.de)
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
 * Created on 20.04.2007
 * $Id: ApplicationEntry.java,v 1.2 2010/02/07 14:26:34 lordsam Exp $
 */

package de.jwic.ecolib.samples.stackpanel;

import de.jwic.base.Application;
import de.jwic.base.Control;
import de.jwic.base.IControlContainer;
import de.jwic.base.Page;
import de.jwic.controls.Button;
import de.jwic.controls.LabelControl;
import de.jwic.ecolib.controls.StackedContainer;
import de.jwic.events.SelectionEvent;
import de.jwic.events.SelectionListener;

/**
 * Demo for StackPanelControl.
 * 
 * @author Sebastian
 */
public class ApplicationEntry extends Application {
	private LabelControl lc;

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.jwic.base.Application#createRootControl(de.jwic.base.IControlContainer)
	 */
	public Control createRootControl(IControlContainer container) {

		Page page = new Page(container);
		page.setTitle("StackPanel Test");

		// create a label for info messages
		lc = new LabelControl(page);
		lc.setText("Messages...");

		// create stack control
		StackedContainer panelControl = new StackedContainer(page, "stack");

		// set stack height (height of the stackhead)
//		panelControl.setStackHeight(50);
//
//		// set width and height
//		panelControl.setWidth(250);
//		panelControl.setHeight(500);

		// create buttons
		// create one button for every stack

		Button b1 = new Button(panelControl);
		b1.setTitle("Button");
		b1.addSelectionListener(new SelectionListener() {
			public void objectSelected(SelectionEvent event) {
				showInfoMessage("button clicked in stack 1");
			}
		});

		Button b2 = new Button(panelControl);
		b2.setTitle("Button");
		b2.addSelectionListener(new SelectionListener() {
			public void objectSelected(SelectionEvent event) {
				showInfoMessage("button clicked in stack 2");
			}
		});

		Button b3 = new Button(panelControl);
		b3.setTitle("Button");
		b3.addSelectionListener(new SelectionListener() {
			public void objectSelected(SelectionEvent event) {
				showInfoMessage("button clicked in stack 3");
			}
		});

		// create stacks with corresponding buttons as children
//		panelControl.createStackControl("Stack 1", b1);
//		panelControl.createStackControl("Stack 2", b2);
//		Stack stck = panelControl.createStackControl("Stack 3", b3);
//
//		// select stack 3
//		panelControl.setSelectedStack(stck.getUniqueIdentifier());
//
//		panelControl.registerStackPanelChangeListener(new StackPanelChangeListener() {
//
//			public void stackPanelChanged(StackPanelChangeEvent evt) {
//				showInfoMessage("Changed Stack from '" + evt.getOldStack().getTitle() + 
//						" to " + evt.getSelectedStack().getTitle());
//			}
//
//		});

		return page;
	}

	private void showInfoMessage(String msg) {
		lc.setText(msg);
	}

}
