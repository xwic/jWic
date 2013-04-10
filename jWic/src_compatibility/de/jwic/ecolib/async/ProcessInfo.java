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
 * de.jwic.ecolib.async.ProcessInfo
 * Created on 27.04.2012
 * $Id: ProcessInfo.java,v 1.1 2012/08/16 21:58:43 lordsam Exp $
 */
package de.jwic.ecolib.async;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONException;
import org.json.JSONWriter;

import de.jwic.base.IControlContainer;
import de.jwic.base.JavaScriptSupport;
import de.jwic.controls.IProgressMonitor;
import de.jwic.json.JsonResourceControl;

/**
 * This control displays the progress information of an IAsyncProcess. The
 * information is updated using async JSON calls, which are more efficient than
 * an IFRAME.
 * 
 * @author lippisch
 */
@JavaScriptSupport
public class ProcessInfo extends JsonResourceControl {

	private static final long serialVersionUID = -7366612108036127156L;

	private IProgressMonitor progressMonitor = null;
	private boolean active = false;
	private boolean globalRefresh = false;
	

	/**
	 * @param container
	 * @param name
	 */
	public ProcessInfo(IControlContainer container, String name) {
		super(container, name);
	}

	/* (non-Javadoc)
	 * @see de.jwic.json.JsonResourceControl#handleJSONResponse(javax.servlet.http.HttpServletRequest, org.json.JSONWriter)
	 */
	@Override
	public void handleJSONResponse(HttpServletRequest req, JSONWriter jsonOut) throws JSONException {
		jsonOut.object();
		jsonOut.key("active");
		jsonOut.value(active);
		
		if (globalRefresh) {
			jsonOut.key("globalRefresh");
			jsonOut.value(true);
			globalRefresh = false;
		}
		
		if (progressMonitor != null) {
			jsonOut.key("monitor");
			jsonOut.object();
			jsonOut.key("infoText");
			jsonOut.value(progressMonitor.getInfoText());
			jsonOut.key("min").value(progressMonitor.getMinimum());
			jsonOut.key("max").value(progressMonitor.getMaximum());
			jsonOut.key("value").value(progressMonitor.getValue());
			jsonOut.endObject();
		}
		
		jsonOut.key("status");
		jsonOut.value(progressMonitor.getInfoText());
		jsonOut.endObject();
	}
	
	/**
	 * @return the progressMonitor
	 */
	public IProgressMonitor getProgressMonitor() {
		return progressMonitor;
	}

	/**
	 * @param progressMonitor the progressMonitor to set
	 */
	public void setProgressMonitor(IProgressMonitor progressMonitor) {
		this.progressMonitor = progressMonitor;
		active = progressMonitor != null;
		requireRedraw();
	}

	/**
	 * @return the active
	 */
	public boolean isActive() {
		return active;
	}

	/**
	 * 
	 */
	public void stopRefresh() {
		active = false;
	}

	/**
	 * @param globalRefresh the globalRefresh to set
	 */
	public void globalRefresh() {
		this.globalRefresh = true;
	}

}
