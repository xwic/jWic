/*
 * Copyright 2005-2013 jWic group (http://www.jwic.de)
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
 * de.jwic.demo.basics.DemoBackgroundProcess
 */
package de.jwic.demo.basics;

import de.jwic.controls.ProgressMonitor;

/**
 * @author lippisch
 *
 */
public class DemoBackgroundProcess implements Runnable {

	private ProgressMonitor monitor;
	
	/**
	 * @param monitor
	 */
	public DemoBackgroundProcess() {
		super();
		this.monitor = new ProgressMonitor();
		monitor.setMaximum(500);
		monitor.setMinimum(0);
	}


	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		
		monitor.setInfoText("Initializing Connection");
		// open connection to database
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// nothing todo
		}

		monitor.worked(100);
		
		monitor.setInfoText("Executing Query...");
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// nothing todo
		}
		monitor.worked(100);
		
		monitor.setInfoText("Processing Records...");
		for (int i = 0; i < 250; i++) {
			try {
				Thread.sleep(20);
			} catch (InterruptedException e) {
				// nothing todo
			}
			monitor.worked(1);
		}
		
		monitor.setInfoText("Clean-Up");
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// nothing todo
		}
		monitor.worked(50);

	}


	/**
	 * @return the monitor
	 */
	public ProgressMonitor getMonitor() {
		return monitor;
	}

}
