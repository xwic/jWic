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
 * de.jwic.controls.ProgressBar
 */
package de.jwic.controls;

import de.jwic.base.Control;
import de.jwic.base.IControlContainer;
import de.jwic.base.JavaScriptSupport;
import de.jwic.events.ValueChangedEvent;
import de.jwic.events.ValueChangedListener;

/**
 * Displays a simple ProgressBar control.
 * @author lippisch
 */
@JavaScriptSupport
public class ProgressBar extends Control {

	private int width = 0;
	private int height = 0;
	
	private int autoRefreshDelay = 500; // 500ms delay between refreshes
	private boolean autoRefresh = false; 
	
	private boolean intermediate = false;
	private boolean showPercentage = false;
	private String intermediateMessage = null; 
	
	private IProgressMonitor monitor = null;
	private MyChangeListener changeListener = null;
	
	private class MyChangeListener implements ValueChangedListener {
		private static final long serialVersionUID = 1L;
		public void valueChanged(ValueChangedEvent event) {
			requireRedraw();
		}
	}

	/**
	 * @param container
	 * @param name
	 */
	public ProgressBar(IControlContainer container, String name) {
		super(container, name);
		changeListener = new MyChangeListener();
	}
	
	/**
	 * Returns the used ProgressMonitor.
	 * @return
	 */
	public IProgressMonitor getMonitor() {
		return monitor;
	}

	/**
	 * Set the progressMonitor.
	 * @param monitor The monitor to set.
	 */
	public void setMonitor(IProgressMonitor monitor) {
		if (this.monitor != null) {
			this.monitor.removeValueChangedListener(changeListener);	
		}
		this.monitor = monitor;
		// remove to prevent double registration when a monitor is set twice.
		if (monitor != null) {
			monitor.removeValueChangedListener(changeListener); 
			monitor.addValueChangedListener(changeListener);
		}
		requireRedraw();
	}

	/**
	 * @return Returns the height.
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * @param height The height to set.
	 */
	public void setHeight(int height) {
		this.height = height;
		requireRedraw();
	}

	/**
	 * @return Returns the width.
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * @param width The width to set.
	 */
	public void setWidth(int width) {
		this.width = width;
		requireRedraw();
	}
	
	/**
	 * Returns the current percent value as int.
	 * @return
	 */
	public int getPercent() {
		double total = monitor.getMaximum() - monitor.getMinimum();
		double value = monitor.getValue() - monitor.getMinimum();
		if (total == 0) {
			return 0; // avoid div 0
		}
		int proz = (int)(value / total * 100); 
		return proz > 100 ? 100 : proz < 0 ? 0 : proz;
	}

	/**
	 * @return the intermediate
	 */
	public boolean isIntermediate() {
		return intermediate;
	}

	/**
	 * @param intermediate the intermediate to set
	 */
	public void setIntermediate(boolean intermediate) {
		this.intermediate = intermediate;
		requireRedraw();
	}
	
	/**
	 * Calculates the content to be displayed in the info box.
	 * @return
	 */
	public String getInfoLabel() {
		if (intermediate) {
			if (intermediateMessage != null) {
				return intermediateMessage; 
			}
			return "";
		} else if (showPercentage) {
			return getPercent() + "%";
		} else if (monitor != null && monitor.getInfoText() != null) {
			return monitor.getInfoText();
		}
		return "";
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
		this.showPercentage = showPercentage;
	}

	/**
	 * @return the intermediateMessage
	 */
	public String getIntermediateMessage() {
		return intermediateMessage;
	}

	/**
	 * @param intermediateMessage the intermediateMessage to set
	 */
	public void setIntermediateMessage(String intermediateMessage) {
		this.intermediateMessage = intermediateMessage;
	}

	/**
	 * @return the autoRefreshDelay
	 */
	public int getAutoRefreshDelay() {
		return autoRefreshDelay;
	}

	/**
	 * @param autoRefreshDelay the autoRefreshDelay to set
	 */
	public void setAutoRefreshDelay(int autoRefreshDelay) {
		this.autoRefreshDelay = autoRefreshDelay;
	}

	/**
	 * @return the autoRefresh
	 */
	public boolean isAutoRefresh() {
		return autoRefresh;
	}

	/**
	 * @param autoRefresh the autoRefresh to set
	 */
	public void setAutoRefresh(boolean autoRefresh) {
		this.autoRefresh = autoRefresh;
		requireRedraw();
	}

}
