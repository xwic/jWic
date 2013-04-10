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
 * de.jwic.ecolib.controls.LogControl
 * Created on 06.12.2005
 * $Id: LogControl.java,v 1.3 2006/09/23 20:46:46 lordsam Exp $
 */
package de.jwic.ecolib.controls;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import de.jwic.base.IControlContainer;
import de.jwic.base.IResourceControl;
import de.jwic.controls.ScrollableContainer;

/**
 * Displays a scrollable layer that displays a list of messages from a 
 * backend thread. The control polls the server for updates via AJAX and
 * adds new messages to the list without using the standard update mechanisms.
 *  
 * @author Florian Lippisch
 * @version $Revision: 1.3 $
 */
public class LogControl extends ScrollableContainer implements IResourceControl {

	private static final long serialVersionUID = 6958796620325663011L;
	private StringBuffer sbLog;
	private StringBuffer sbAppend;
	private Thread waitThread = null;
	private boolean pollForUpdates = false;
	
	private int sleepTime = 1000;
	
	/**
	 * @param container
	 * @param name
	 */
	public LogControl(IControlContainer container, String name) {
		super(container, name);
		sbLog = new StringBuffer();
		sbAppend = new StringBuffer();
	}

	
	/**
	 * Append text to the log.
	 * @param message
	 */
	public void append(String message) {
		sbLog.append(message);
		sbAppend.append(message);
		if (waitThread != null) {
			waitThread.interrupt();
		}

	}
	
	/**
	 * Write a message to the log. Automatically adds a line break.
	 * @param message
	 */
	public void writeln(String message) {
		append(message + "<br>");
		requireRedraw();
		setTop(9999999);	// at the end...
	}
	
	/**
	 * Returns the unique control ID where the '.' are replaced by '_' chars.  
	 * @return
	 */
	public String getUniqueID() {
		return getControlID().replace('.', '_');
	}
	
	/**
	 * Returns the complete content of the log and clears the appendLog.
	 * @return
	 */
	public String getLogContent() {
		sbAppend.setLength(0);
		return sbLog.toString();
	}

	/**
	 * Used by the control to update the log via AJAX.
	 */
	public void attachResource(HttpServletRequest req, HttpServletResponse res) throws IOException {
		
		try {
			if (sbAppend.length() == 0) {
				if (waitThread != null) {
					//log.warn("A thread is already waiting!");
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
		pw.write("LOG:" + sbAppend.toString());
		sbAppend.setLength(0);
		pw.flush();
		
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
	 * @return Returns the pollForUpdates.
	 */
	public boolean isPollForUpdates() {
		return pollForUpdates;
	}


	/**
	 * @param pollForUpdates The pollForUpdates to set.
	 */
	public void setPollForUpdates(boolean pollForUpdates) {
		this.pollForUpdates = pollForUpdates;
	}
	
}
