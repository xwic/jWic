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
 * de.jwic.ecolib.async.ProgressInfoControl
 * Created on 29.04.2008
 * $Id: ProgressInfoControl.java,v 1.2 2008/08/29 15:09:45 lordsam Exp $
 */
package de.jwic.ecolib.async;

import de.jwic.base.ControlContainer;
import de.jwic.base.IControlContainer;
import de.jwic.controls.ProgressBarControl;

/**
 *
 * @author Florian Lippisch
 */
public class ProgressInfoControl extends ControlContainer {
	private static final long serialVersionUID = 1L;
	private ProgressBarControl progBar;
	private IAsyncProcess process = null;
	
	/**
	 * @param container
	 * @param name
	 */
	public ProgressInfoControl(IControlContainer container, String name) {
		super(container, name);
		init();
	}

	/**
	 * @param container
	 */
	public ProgressInfoControl(IControlContainer container) {
		super(container);
		init();
	}

	/**
	 * 
	 */
	private void init() {
		progBar = new ProgressBarControl(this, "progBar");
		progBar.setWidth(200);
		progBar.setSmooth(true);
	}

	public void actionUpdate() {
		requireRedraw();
	}
	
	public void setWidth(int width) {
		progBar.setWidth(width - 4);
	}
	/**
	 * @return the process
	 */
	public IAsyncProcess getProcess() {
		return process;
	}

	/**
	 * @param process the process to set
	 */
	public void setProcess(IAsyncProcess process) {
		this.process = process;
		if (process != null) {
			progBar.setMonitor(process.getMonitor());
		} else {
			progBar.setMonitor(null);
		}
	}
	
	/**
	 * The message.
	 * @return
	 */
	public String getMessage() {
		return process != null ? process.getStatusMessage() : "";
	}
	
	/**
	 * The step info
	 */
	public String getStepInfo() {
		if (process != null) {
			return process.getMonitor().getValue() + " / " + process.getMonitor().getMaximum();
		} else {
			return "";
		}
	}
	

}
