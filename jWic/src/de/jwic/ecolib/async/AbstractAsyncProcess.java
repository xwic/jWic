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
 * de.jwic.ecolib.async.AbstractAsyncProcess
 * Created on 29.04.2008
 * $Id: AbstractAsyncProcess.java,v 1.4 2012/08/16 21:58:43 lordsam Exp $
 */
package de.jwic.ecolib.async;

import java.util.ArrayList;
import java.util.List;

import de.jwic.controls.IProgressMonitor;
import de.jwic.controls.ProgressMonitor;

/**
 *
 * @author Florian Lippisch
 */
public abstract class AbstractAsyncProcess implements IAsyncProcess, Runnable {

	protected String statusMessage = null;
	protected ProgressMonitor monitor = new ProgressMonitor();

	protected boolean cancelled = false;
	
	private boolean finished = false;
	private Object result = null;
	
	private List<IProcessListener> listeners = new ArrayList<IProcessListener>();
	
	
	/* (non-Javadoc)
	 * @see de.jwic.ecolib.async.IAsyncProcess#addProcessListener(de.jwic.ecolib.async.IProcessListener)
	 */
	public void addProcessListener(IProcessListener listener) {
		listeners.add(listener);		
	}
	
	/* (non-Javadoc)
	 * @see de.jwic.ecolib.async.IAsyncProcess#removeProcessListener(de.jwic.ecolib.async.IProcessListener)
	 */
	public void removeProcessListener(IProcessListener listener) {
		listeners.remove(listener);		
	}
	
	/**
	 * Notify listeners.
	 * @param e
	 */
	protected void fireProcessEvent(ProcessEvent e) {
		Object[] lst = listeners.toArray();
		for (int i = 0; i < lst.length; i++) {
			IProcessListener listener = (IProcessListener)lst[i];
			listener.processFinished(e);
		}
	}
	
	/* (non-Javadoc)
	 * @see de.jwic.ecolib.async.IAsyncProcess#getStatusMessage()
	 */
	public String getStatusMessage() {
		return monitor.getInfoText();
	}
	/**
	 * @param statusMessage the statusMessage to set
	 */
	public void setStatusMessage(String statusMessage) {
		monitor.setInfoText(statusMessage);
	}
	
	/**
	 * Increases the step by 1.
	 */
	protected void worked() {
		worked(1);
	}
	
	/**
	 * Increases the step by the specified numbers.
	 */
	protected void worked(int steps) {
		int step = monitor.getValue();
		int maxSteps = monitor.getMaximum();
		step += steps;
		if (step > maxSteps) {
			step = maxSteps;
		}
		monitor.setValue(step);
	}
	
	/**
	 * Run the process.
	 *
	 */
	public final void run() {
		try {
			result = runProcess();
		} catch (Throwable t) {
			result = t;
		} finally {
			finished = true;
			fireProcessEvent(new ProcessEvent(this));
		}
	}
	
	/* (non-Javadoc)
	 * @see de.jwic.ecolib.async.IAsyncProcess#canCancel()
	 */
	public boolean canCancel() {
		return false;
	}
	
	/* (non-Javadoc)
	 * @see de.jwic.ecolib.async.IAsyncProcess#cancel()
	 */
	public boolean cancel() {
		if (canCancel()) {
			cancelled = true;
			return true;
		}
		return false;
	}
	
	protected abstract Object runProcess();
	
	/**
	 * @return the monitor
	 */
	public IProgressMonitor getMonitor() {
		return monitor;
	}

	/* (non-Javadoc)
	 * @see de.jwic.ecolib.async.IAsyncProcess#getResult()
	 */
	public Object getResult() {
		return result;
	}
	
	/* (non-Javadoc)
	 * @see de.jwic.ecolib.async.IAsyncProcess#isFinished()
	 */
	public boolean isFinished() {
		return finished;
	}
	
}
