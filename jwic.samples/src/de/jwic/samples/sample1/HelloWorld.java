/*
 * Copyright 2005 jWic Group (http://www.jwic.de)
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
 * de.jwic.samples.sample1.HelloWorld
 * Created on 14.04.2005
 * $Id: HelloWorld.java,v 1.3 2012/07/31 09:48:49 lordsam Exp $
 */
package de.jwic.samples.sample1;

import de.jwic.base.Application;
import de.jwic.base.Control;
import de.jwic.base.IControlContainer;
import de.jwic.controls.Button;
import de.jwic.controls.InputBoxControl;
import de.jwic.controls.LabelControl;
import de.jwic.controls.TabControl;
import de.jwic.controls.TabStripControl;
import de.jwic.controls.layout.TableLayoutContainer;
import de.jwic.events.SelectionEvent;
import de.jwic.events.SelectionListener;

/**
 * Displays a label that says... guess what.. Hello World :).
 * @author Florian Lippisch
 * @version $Revision: 1.3 $
 */
public class HelloWorld extends Application {

	private static final long serialVersionUID = 3L;
	private LabelControl label;
	
	private int clickCount = 0;
	private AttachmentListModel model;
	private InputBoxControl ibName;

	/* (non-Javadoc)
	 * @see de.jwic.base.Application#createRootControl(de.jwic.base.IControlContainer)
	 */
	public Control createRootControl(IControlContainer container) {
		
		getSessionContext().notifyMessage("The Demo App has started...");
		
		// Create a page and add the label
		model = new AttachmentListModel();

		DemoPage page = new DemoPage(container, "page");
		
		TabStripControl tabStrip = new TabStripControl(page, "tabStrip");
		TabControl tab = tabStrip.addTab("My Label");
		label = new LabelControl(tab, "label");
		
		TabControl tab2 = tabStrip.addTab("Another Tab");
		InputBoxControl ib = new InputBoxControl(tab2, "ib");
		ib.setText("Test");

		TabControl tab3 = tabStrip.addTab("Attachments");
		TableLayoutContainer tlCont = new TableLayoutContainer(tab3);
		
		new AttachmentListControl(tlCont, "attm", model);

		new AttachmentListControl(page, "attm", model);
		
		ibName = new InputBoxControl(tlCont);
		
		Button btAdd = new Button(tlCont);
		btAdd.setTitle("Add Attachment");
		btAdd.addSelectionListener(new SelectionListener() {
			public void objectSelected(SelectionEvent event) {
				onAddAttachment();
			}
		});
		
		
		// Set the text of the label.
		label.setText("Hello World");
		
		Button bt = new Button(page, "btClick");
		bt.setTitle("Click Me");
		bt.addSelectionListener(new SelectionListener() {
			public void objectSelected(SelectionEvent event) {	
				onActionClicked();
			}
		});
		
		Button btExit = new Button(page, "btExit");
		btExit.setTitle("Exit");
		btExit.addSelectionListener(new SelectionListener() {
			public void objectSelected(SelectionEvent event) {
				onActionExit();
			}
		});
		
		
		
		return page;
		
	}

	/**
	 * 
	 */
	protected void onAddAttachment() {

		String name = ibName.getText().trim();
		if (name.isEmpty()) {
			ibName.setFlagAsError(true);
		} else {
			ibName.setFlagAsError(false);
			
			model.addAttachment(name);
			//getSessionContext().notifyMessage("Added attachment '" + name + "' to the list.");
			//getSessionContext().queueScriptCall("alert('Adi is full awake');");
		}
		
	}

	/**
	 * 
	 */
	protected void onActionClicked() {

		label.setText("Clicked Me " + (clickCount++) + " times");
		
		//label.destroy();
		
	}

	/**
	 * 
	 */
	protected void onActionExit() {
		getSessionContext().setExitURL("byebye.html");
		getSessionContext().exit();
		
	}
	
}
