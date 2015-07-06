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
package de.jwic.controls;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import de.jwic.base.Control;
import de.jwic.base.Field;
import de.jwic.base.IControlContainer;
import de.jwic.base.IResourceControl;

/**
 * Creates a JavaScript on the client that polls the control if a 'submit' is
 * required. This enables the application to force a refresh of the controls on a 
 * users page from another thread (i.e. a background process).
 * 
 * This control works ONLY if the users browser supports XmlHttpRequest (ajax).
 * 
 * @author Florian Lippisch
 * @version $Revision: 1.2 $
 */
public class ClientNotifierControl extends Control implements IResourceControl {

	private static final long serialVersionUID = 1L;
	
	private int interval = 2000; // intervall in milliseconds
	private int sleepTime = 200;
	
	private int stateValue = 0;
	private Thread waitThread = null;
	
	private Field fldStateValue;
	
	/**
	 * @param container
	 * @param name
	 */
	public ClientNotifierControl(IControlContainer container) {
		this(container, null);
	}

	/**
	 * @param container
	 * @param name
	 */
	public ClientNotifierControl(IControlContainer container, String name) {
		super(container, name);
		fldStateValue = new Field(this, "stateValue");
		fldStateValue.setValue(Integer.toString(stateValue));
	}

	/* (non-Javadoc)
	 * @see de.jwic.base.Control#actionPerformed(java.lang.String, java.lang.String)
	 */
	public void actionPerformed(String actionId, String parameter) {
		if (actionId.equals("refresh")) {
			requireRedraw();	// force redraw
		}
		
	}

	/* (non-Javadoc)
	 * @see de.jwic.base.IResourceControl#attachResource(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	public void attachResource(HttpServletRequest req, HttpServletResponse res) throws IOException {

		log.debug("checkStateMonitor IN");
		
		try {
			String value = req.getParameter(fldStateValue.getId());
			if (value == null || value.equals(fldStateValue.getValue())) {
				if (waitThread != null) {
					log.warn("A thread is already waiting!");
				}
				waitThread = Thread.currentThread();
				Thread.sleep(sleepTime);
				waitThread = null;
			}
		} catch (InterruptedException e) {
			// as expected
			waitThread = null;
		}
		
		res.setContentType("text/plain");
		PrintWriter pw = res.getWriter();
		pw.write(Integer.toString(stateValue));
		pw.flush();
		
		log.debug("checkStateMonitor OUT");
		
	}

	/**
	 * @return Returns the interval.
	 */
	public int getInterval() {
		return interval;
	}

	/**
	 * @param interval The interval to set.
	 */
	public void setInterval(int interval) {
		this.interval = interval;
	}

	/**
	 * @return Returns the sleepTime.
	 */
	public int getSleepTime() {
		return sleepTime;
	}

	/**
	 * @param sleepTime The sleepTime to set.
	 */
	public void setSleepTime(int sleepTime) {
		this.sleepTime = sleepTime;
	}

	/**
	 * 
	 */
	public void notifyClient() {
		stateValue++;
		requireRedraw();
		fldStateValue.setValue(Integer.toString(stateValue));
		if (waitThread != null) {
			waitThread.interrupt();
		}
	}
	
	/**
	 * Returns a unique ID based upon the control ID that is used by the template to
	 * render unique javascript functions.
	 * @return
	 */
	public String getUniqueID() {
		return getControlID().replace('.', '_');
	}

	/**
	 * @return Returns the stateValue.
	 */
	public int getStateValue() {
		return stateValue;
	}

	/**
	 * @param stateValue The stateValue to set.
	 */
	public void setStateValue(int stateValue) {
		this.stateValue = stateValue;
		fldStateValue.setValue(Integer.toString(stateValue));
	}

}
