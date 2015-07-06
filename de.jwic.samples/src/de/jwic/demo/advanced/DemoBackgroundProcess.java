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

import de.jwic.async.AbstractAsyncProcess;

/**
 * @author lippisch
 *
 */
public class DemoBackgroundProcess extends AbstractAsyncProcess {

	/**
	 * @param monitor
	 */
	public DemoBackgroundProcess() {
		super();

		monitor.setMaximum(0);
		canCancel = true;
		
	}

	/* (non-Javadoc)
	 * @see de.jwic.async.AbstractAsyncProcess#runProcess()
	 */
	@Override
	protected Object runProcess() {

		setStatusMessage("Initializing Connection");
		
		// open connection to database
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// nothing todo
		}

		setStatusMessage("Executing Query...");
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			// nothing todo
		}
		monitor.setMaximum(250);
		setStatusMessage("Processing Records...");
		for (int i = 0; (i < 250 && !cancelled); i++) {
			try {
				Thread.sleep(20);
			} catch (InterruptedException e) {
				// nothing todo
			}
			worked();
		}
		
		setStatusMessage("Clean-Up");
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// nothing todo
		}
		completed();

		return "42"; // optional: return the result of the long process 
	}

}
