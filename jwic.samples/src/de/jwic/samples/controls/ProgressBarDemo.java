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
 * $Id: ProgressBarDemo.java,v 1.2 2010/01/26 11:25:17 lordsam Exp $
 */
package de.jwic.samples.controls;

import de.jwic.base.ControlContainer;
import de.jwic.base.IControlContainer;
import de.jwic.controls.Button;
import de.jwic.controls.InputBoxControl;
import de.jwic.controls.ProgressBarControl;
import de.jwic.controls.ProgressMonitor;
import de.jwic.events.SelectionListener;

/**
 * 
 * Demonstrates the usage of the LabelControl.
 * 
 * @author Florian Lippisch
 * @version $Revision: 1.2 $
 */
public class ProgressBarDemo extends ControlContainer {

	private ProgressBarControl progBar;
	private ProgressMonitor monitor;
	private InputBoxControl textMin;
	private InputBoxControl textMax;
	private InputBoxControl textValue;

	private Button btVisible;
	private Button btSmooth;
	
	public ProgressBarDemo(IControlContainer container) {
		super(container);
		
		monitor = new ProgressMonitor();
		progBar = new ProgressBarControl(this, "progBar");
		progBar.setMonitor(monitor);
		
		textMin = new InputBoxControl(this, "textMin");
		textMin.setText(Integer.toString(monitor.getMinimum()));
		textMin.setWidth(100);	// width in px

		textMax = new InputBoxControl(this, "textMax");
		textMax.setText(Integer.toString(monitor.getMaximum()));
		textMax.setWidth(100);	// width in px

		textValue = new InputBoxControl(this, "textValue");
		textValue.setText(Integer.toString(monitor.getValue()));
		textValue.setWidth(100);	// width in px
		
		Button btApply = new Button(this, "btApply");
		btApply.setTitle("Apply");
		btApply.addSelectionListener(new SelectionListener() {
			public void objectSelected(de.jwic.events.SelectionEvent event) {
				applyText();
			};
		});

		Button btWorked = new Button(this, "btWorked");
		btWorked.setTitle("Worked (+1)");
		btWorked.addSelectionListener(new SelectionListener() {
			public void objectSelected(de.jwic.events.SelectionEvent event) {
				doWorked();
			};
		});

		
		btVisible = new Button(this, "btVisible");
		btVisible.setTitle(progBar.isVisible() ? "Set Invisible" : "Set Visible");
		btVisible.addSelectionListener(new SelectionListener() {
			public void objectSelected(de.jwic.events.SelectionEvent event) {
				changeVisible();
			};
		});

		btSmooth = new Button(this, "btSmooth");
		btSmooth.setTitle(progBar.isSmooth() ? "Disable Smooth" : "Enable Smooth");
		btSmooth.addSelectionListener(new SelectionListener() {
			public void objectSelected(de.jwic.events.SelectionEvent event) {
				changeSmoth();
			};
		});

		
	}

	/**
	 * 
	 */
	protected void changeSmoth() {
		progBar.setSmooth(!progBar.isSmooth());
		btSmooth.setTitle(progBar.isSmooth() ? "Disable Smooth" : "Enable Smooth");
	}

	/**
	 * Increase monitor value by 1.
	 */
	protected void doWorked() {
		monitor.worked(1);
		textValue.setText(Integer.toString(monitor.getValue()));
	}

	/**
	 * Change the Visible flag of the label.
	 */
	protected void changeVisible() {
		
		progBar.setVisible(!progBar.isVisible());
		btVisible.setTitle(progBar.isVisible() ? "Set Invisible" : "Set Visible");
		
	}

	/**
	 * Change the text of the Label.
	 */
	protected void applyText() {
		
		try {
			monitor.setMaximum(Integer.parseInt(textMax.getText()));
		} catch (NumberFormatException nfe) {
			textMax.setText("NUMBER!");
		}
		try {
			monitor.setMinimum(Integer.parseInt(textMin.getText()));
		} catch (NumberFormatException nfe) {
			textMin.setText("NUMBER!");
		}
		try {
			monitor.setValue(Integer.parseInt(textValue.getText()));
		} catch (NumberFormatException nfe) {
			textValue.setText("NUMBER!");
		}
		
	}
	
}
