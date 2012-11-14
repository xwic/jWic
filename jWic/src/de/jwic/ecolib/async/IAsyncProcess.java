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
 * de.jwic.ecolib.async.IAsyncProcess
 * Created on 29.04.2008
 * $Id: IAsyncProcess.java,v 1.2 2012/08/16 21:58:43 lordsam Exp $
 */
package de.jwic.ecolib.async;

import de.jwic.controls.IProgressMonitor;

/**
 *
 * @author Florian Lippisch
 */
public interface IAsyncProcess extends Runnable {

	/**
	 * Add a process listener.
	 * @param listener
	 */
	public void addProcessListener(IProcessListener listener);
	
	/**
	 * Remove a process listener.
	 * @param listener
	 */
	public void removeProcessListener(IProcessListener listener);
	
	/**
	 * @return the statusMessage
	 */
	public abstract String getStatusMessage();

	/**
	 * @return the step
	 */
	public abstract IProgressMonitor getMonitor();
	
	/**
	 * Must return true when the process is finished.
	 * @return
	 */
	public abstract boolean isFinished();
	
	/**
	 * Returns true if the process can be cancelled.
	 * @return
	 */
	public abstract boolean canCancel();
	
	/**
	 * Sets the cancelled flag. Returns true if the flag could be set
	 * successfully.
	 * @return
	 */
	public boolean cancel();
	
	/**
	 * Returns the result of the process - if there is any.
	 * @return
	 */
	public abstract Object getResult();

}