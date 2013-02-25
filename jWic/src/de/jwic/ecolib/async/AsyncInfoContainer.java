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
 * de.jwic.ecolib.async.AsyncInfoContainer
 * Created on 29.04.2008
 * $Id: AsyncInfoContainer.java,v 1.3 2011/06/02 14:05:35 lordsam Exp $
 */
package de.jwic.ecolib.async;

import de.jwic.base.ControlContainer;
import de.jwic.base.IControlContainer;
import de.jwic.base.SessionContext;
import de.jwic.controls.IHTMLElement;
import de.jwic.controls.LabelControl;
import de.jwic.ecolib.controls.StackedContainer;

/**
 * This container creates an IFrame that is able to display 
 * status information about asynchronous background processes
 * and their result.
 * 
 * @author Florian Lippisch
 */
public class AsyncInfoContainer extends ControlContainer implements IHTMLElement, IProcessListener {
	private static final long serialVersionUID = 1L;
	private final static String LAYER_PREFIX = "async.";
	
	private String cssClass = "";
	private boolean enabled = true;
	private int height = 200;
	private int width = 400;
	private boolean fillWidth = false;
	
	private String layerId;
	private StackedContainer frameContainer;
	
	private LabelControl lblInfo;
	private ProgressInfoControl progressInfo = null;
	private IResultViewer resultViewer = null;
	private IAsyncProcess currProcess = null;
	
	/**
	 * @param container
	 */
	public AsyncInfoContainer(IControlContainer container) {
		super(container);
		init();
	}

	/**
	 * @param container
	 * @param name
	 */
	public AsyncInfoContainer(IControlContainer container, String name) {
		super(container, name);
		init();
	}

	/**
	 * 
	 */
	private void init() {
		frameContainer = new StackedContainer(this, "frameContainer");
		
		SessionContext context = getSessionContext();
		// create a layer for the container
		int num = 0;
		do {
			layerId = LAYER_PREFIX + num;
			num++;
		} while (context.getControlByLayerID(layerId) != null);
		
		context.addLayer(layerId, frameContainer);
		
		lblInfo = new LabelControl(frameContainer, "_info");
		frameContainer.setCurrentControlName("_info");
		
	}
	
	/**
	 * Used to add controls to the frame.
	 * @return
	 */
	public IControlContainer getFrameContainer() {
		return frameContainer;
	}
	
	/**
	 * The result viewer is displayed when the process is finished.
	 * @param control
	 */
	public void setResultViewer(IResultViewer control) {
		if (control.getContainer() != frameContainer) {
			throw new IllegalArgumentException("The result reciever must be a child of the FrameContainer");
		}
		resultViewer = control;
	}
	
	/**
	 * Set the info message. The message is displayed in the initial state.
	 * @param msg
	 */
	public void setInfoMessage(String msg) {
		lblInfo.setText(msg);
	}
	
	/**
	 * Returns the layer URL. The URL contains the timestamp 
	 * to make sure the page is refrehed and not returned from
	 * the browser cache.
	 * @return
	 */
	public String getLayerURL() {
		return getSessionContext().getCallBackURL() + "&layerid=" + layerId + "&refresh=" + System.currentTimeMillis();
	}
	
	/* (non-Javadoc)
	 * @see de.jwic.base.ControlContainer#destroy()
	 */
	public void destroy() {
		super.destroy();
		getSessionContext().removeLayer(layerId);
	}

	/**
	 * @return the cssClass
	 */
	public String getCssClass() {
		return cssClass;
	}

	/**
	 * @param cssClass the cssClass to set
	 */
	public void setCssClass(String cssClass) {
		this.cssClass = cssClass;
	}

	/**
	 * @return the enabled
	 */
	public boolean isEnabled() {
		return enabled;
	}

	/**
	 * @param enabled the enabled to set
	 */
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	/**
	 * @return the fillWidth
	 */
	public boolean isFillWidth() {
		return fillWidth;
	}

	/**
	 * @param fillWidth the fillWidth to set
	 */
	public void setFillWidth(boolean fillWidth) {
		this.fillWidth = fillWidth;
	}

	/**
	 * @return the height
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * @param height the height to set
	 */
	public void setHeight(int height) {
		this.height = height;
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

	/* (non-Javadoc)
	 * @see de.jwic.controls.IHTMLElement#forceFocus()
	 */
	public boolean forceFocus() {
		return false; // not supported
	}

	/**
	 * @param process
	 */
	public void showProcessStatus(IAsyncProcess process) {
		
		if (progressInfo == null) {
			progressInfo = new ProgressInfoControl(frameContainer, "_progInfo");
			progressInfo.setWidth(this.width - 20);
		}
		
		if (currProcess != null) {
			currProcess.removeProcessListener(this); // remove listener from other processes.
		}
		currProcess = process;
		
		frameContainer.setCurrentControlName("_progInfo");
		
		progressInfo.setProcess(process);
		process.addProcessListener(this);
		requireRedraw();
		
	}

	/* (non-Javadoc)
	 * @see de.jwic.ecolib.async.IProcessListener#processFinished(de.jwic.ecolib.async.ProcessEvent)
	 */
	public void processFinished(ProcessEvent e) {
		
		IAsyncProcess process = e.getProcess();
		if (currProcess != process) {
			// this may happen if the active process is replaced by another process
			// just in the same time the old process is finished.
			return; 
		}
		if (resultViewer != null) {
			resultViewer.setResult(process.getResult());
			frameContainer.setCurrentControlName(resultViewer.getName());
		} else {
			lblInfo.setText("Finished: " + process.getResult());
			frameContainer.setCurrentControlName(lblInfo.getName());
		}
		process.removeProcessListener(this);
		progressInfo.setProcess(null);
		currProcess = null;
		
	}

}
