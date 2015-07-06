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
package de.jwic.demo.advanced;

import de.jwic.async.IProcessListener;
import de.jwic.async.ProcessEvent;
import de.jwic.async.ProcessInfo;
import de.jwic.base.ControlContainer;
import de.jwic.base.IControlContainer;
import de.jwic.controls.Button;
import de.jwic.controls.CheckBox;
import de.jwic.events.SelectionEvent;
import de.jwic.events.SelectionListener;
import de.jwic.events.ValueChangedEvent;
import de.jwic.events.ValueChangedListener;

/**
 * 
 * Demonstrates the usage of the ButtonControl.
 * 
 * @author Florian Lippisch
 * @version $Revision: 1.4 $
 */
public class ProcessInfoDemo extends ControlContainer {

	private Thread myBackgroundThread = null;
	private ProcessInfo processInfo;
	
	/**
	 * Constructor.
	 * @param container
	 */
	public ProcessInfoDemo(IControlContainer container) {
		super(container);

		processInfo = new ProcessInfo(this, "processInfo");
		processInfo.setShowPercentage(true);
		processInfo.setShowValues(true);
		//processInfo.setCompactView(true);
		processInfo.setWidth(600);
		
		Button btStart = new Button(this, "btStart");
		btStart.setTitle("Start Process");
		btStart.addSelectionListener(new SelectionListener() {
			@Override
			public void objectSelected(SelectionEvent event) {
				onProcessStart();
			}
		});

		final CheckBox chkCompactView = new CheckBox(this, "chkCompView");
		chkCompactView.setLabel("Compact View");
		chkCompactView.addValueChangedListener(new ValueChangedListener() {
			@Override
			public void valueChanged(ValueChangedEvent event) {
				processInfo.setCompactView(chkCompactView.isChecked());
			}
		});

		final CheckBox chkShowPerc = new CheckBox(this, "chkShowPerc");
		chkShowPerc.setLabel("Show Percentage");
		chkShowPerc.setChecked(true);
		chkShowPerc.addValueChangedListener(new ValueChangedListener() {
			@Override
			public void valueChanged(ValueChangedEvent event) {
				processInfo.setShowPercentage(chkShowPerc.isChecked());
			}
		});

		final CheckBox chkShowValues = new CheckBox(this, "chkShowValues");
		chkShowValues.setLabel("Show Values");
		chkShowValues.setChecked(true);
		chkShowValues.addValueChangedListener(new ValueChangedListener() {
			@Override
			public void valueChanged(ValueChangedEvent event) {
				processInfo.setShowValues(chkShowValues.isChecked());
			}
		});

	}

	/**
	 * 
	 */
	protected void onProcessStart() {
		
		if (myBackgroundThread == null || !myBackgroundThread.isAlive()) {
			
			DemoBackgroundProcess dbp = new DemoBackgroundProcess();
			
			processInfo.setProgressMonitor(dbp.getMonitor());
			
			dbp.addProcessListener(new IProcessListener() {
				@Override
				public void processFinished(ProcessEvent e) {
					processInfo.globalRefresh();
					processInfo.stopRefresh();
					getSessionContext().notifyMessage("And the result was: " + e.getProcess().getResult());
				}
			});
			
			myBackgroundThread = new Thread(dbp);
			myBackgroundThread.start();
						
		} else {
			getSessionContext().notifyMessage("A background process is already running..", "warning");
		}
		
	}
}
