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
 * de.jwic.ecolib.samples.controls.tbv.dialog.AddDemoTaskDialog
 * Created on Apr 4, 2007
 * $Id: AddDemoTaskDialog.java,v 1.2 2010/02/07 14:26:34 lordsam Exp $
 */
package de.jwic.ecolib.samples.tableviewer.dialog;

import de.jwic.base.IControlContainer;
import de.jwic.controls.Button;
import de.jwic.controls.InputBoxControl;
import de.jwic.controls.LabelControl;
import de.jwic.controls.WindowControl;
import de.jwic.controls.layout.TableLayoutContainer;
import de.jwic.ecolib.dialogs.BasicDialog;
import de.jwic.ecolib.samples.tableviewer.DemoTask;
import de.jwic.events.SelectionEvent;
import de.jwic.events.SelectionListener;

/**
 *
 * @author Aron Cotrau
 */
public class AddDemoTaskDialog extends BasicDialog {

	private InputBoxControl ibTask = null;
	private InputBoxControl ibOwner = null;
	private InputBoxControl ibCompleted = null;
	
	/**
	 * @param parent
	 */
	public AddDemoTaskDialog(IControlContainer parent) {
		super(parent);
	}

	/* (non-Javadoc)
	 * @see de.jwic.ecolib.dialogs.BasicDialog#createControls(de.jwic.base.IControlContainer)
	 */
	protected void createControls(IControlContainer container) {
		WindowControl window = new WindowControl(container, "window");
		window.setWidth("650");
		window.setAlign("center");
		window.setTitle("Add task...");
		
		TableLayoutContainer layout = new TableLayoutContainer(window);
		layout.setColumnCount(2);
		
		LabelControl taskLabel = new LabelControl(layout);
		taskLabel.setText("Task name: ");
		
		ibTask = new InputBoxControl(layout);

		LabelControl ownerLabel = new LabelControl(layout);
		ownerLabel.setText("Owner name: ");
		
		ibOwner = new InputBoxControl(layout);
		
		LabelControl completedLabel = new LabelControl(layout);
		completedLabel.setText("Completed (%): ");
		
		ibCompleted = new InputBoxControl(layout);
		
		Button abort = new Button(layout, "abort");
		abort.setTitle("Abort");
		abort.addSelectionListener(new SelectionListener() {
			public void objectSelected(SelectionEvent event) {
				abort();
			}
		});
		
		Button finish = new Button(layout, "finish");
		finish.setTitle("Finish");
		finish.addSelectionListener(new SelectionListener() {
			public void objectSelected(SelectionEvent event) {
				finish();
			}
		});
	}
	
	/**
	 * @return the Demo task object
	 */
	public DemoTask getDemoTask() {
		int completed = 0;
		if (!"".equals(ibCompleted.getText())) {
			completed = Integer.parseInt(ibCompleted.getText());
		}
		
		DemoTask task = new DemoTask(ibTask.getText(), ibOwner.getText(), completed);
		return task;
	}
}
