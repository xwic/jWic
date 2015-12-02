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

import javax.servlet.http.HttpServletRequest;

import org.json.JSONException;
import org.json.JSONWriter;

import de.jwic.base.IControlContainer;
import de.jwic.base.JavaScriptSupport;
import de.jwic.json.JsonResourceControl;

/**
 * This control displays the progress information of an IAsyncProcess. The
 * information is updated using async JSON calls that do not interrupt the primary
 * UI thread.
 * 
 * The control can initiate a global refresh by calling a regular JWic.fireAction event
 * that causes the UI to refresh. This is useful after the process finished.
 * 
 * @author lippisch
 */
@JavaScriptSupport
public class ProcessInfo extends JsonResourceControl {

	private static final long serialVersionUID = -7366612108036127156L;

	private IProgressMonitor progressMonitor = null;
	private boolean active = false;
	private boolean globalRefresh = false;
	private boolean showPercentage = false;
	private boolean showValues = true;
	private boolean compactView = false;
	
	private int width = 400;

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
		IProgressMonitor pi = progressMonitor;
		jsonOut.object();
		jsonOut.key("active");
		jsonOut.value(active);
		
		if (globalRefresh) {
			jsonOut.key("globalRefresh");
			jsonOut.value(true);
			globalRefresh = false;
		}
		
		if (pi != null) {
			jsonOut.key("monitor");
			jsonOut.object();
			jsonOut.key("infoText");
			jsonOut.value(pi.getInfoText());
			jsonOut.key("min").value(pi.getMinimum());
			jsonOut.key("max").value(pi.getMaximum());
			jsonOut.key("value").value(pi.getValue());
			jsonOut.endObject();

			jsonOut.key("status");
			jsonOut.value(pi.getInfoText());
		}
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

	/**
	 * Returns the current % value.
	 * @return
	 */
	public int getPercent() {
		if (progressMonitor != null) {
			int total = progressMonitor.getMaximum() - progressMonitor.getMinimum();
			int val = progressMonitor.getValue() - progressMonitor.getMinimum();
			if (total > 0) {
				return val * 100 / total;
			}
		}
		return -1;
	}

	/**
	 * @return the showPercentage
	 */
	public boolean isShowPercentage() {
		return showPercentage;
	}

	/**
	 * @param showPercentage the showPercentage to set
	 */
	public void setShowPercentage(boolean showPercentage) {
		if (this.showPercentage != showPercentage) {
			this.showPercentage = showPercentage;
			requireRedraw();
		}
	}

	/**
	 * @return the showValues
	 */
	public boolean isShowValues() {
		return showValues;
	}

	/**
	 * @param showValues the showValues to set
	 */
	public void setShowValues(boolean showValues) {
		if (this.showValues != showValues) {
			this.showValues = showValues;
			requireRedraw();
		}
	}

	/**
	 * @return the compactView
	 */
	public boolean isCompactView() {
		return compactView;
	}

	/**
	 * @param compactView the compactView to set
	 */
	public void setCompactView(boolean compactView) {
		if (this.compactView != compactView) {
			this.compactView = compactView;
			requireRedraw();
		}
	}

	/**
	 * @return the width
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * @param width the width to set
	 */
	public void setWidth(int width) {
		this.width = width;
	}
	
}
