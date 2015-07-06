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
package de.jwic.async;

import de.jwic.events.ValueChangedListener;

/**
 * Used to monitor the progress of an operation. Monitors are used by controls like the
 * ProgressMonitor to visualise the progress of an operation.
 *  
 * @author Lippisch
 */
public interface IProgressMonitor {

	/**
	 * Add a ValueChangedListener. The listener must be invoked when the
	 * value has been changed.
	 * @param listener
	 */
	public void addValueChangedListener(ValueChangedListener listener);
	
	/**
	 * Removes the specified listener.
	 * @param listener
	 */
	public void removeValueChangedListener(ValueChangedListener listener);
	
	/**
	 * Returns the maximum value that the monitor can reach.
	 * @return
	 */
	public int getMaximum();
	
	/**
	 * Returns the minium value that the progress starts with.
	 * @return
	 */
	public int getMinimum();
	
	/**
	 * Returns the current progress value.
	 * @return
	 */
	public int getValue();
	
	/**
	 * Returns an info text.
	 * @return
	 */
	public String getInfoText();
	
}
