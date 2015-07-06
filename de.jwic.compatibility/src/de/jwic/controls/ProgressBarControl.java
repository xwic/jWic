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

import de.jwic.async.IProgressMonitor;
import de.jwic.base.Control;
import de.jwic.base.IControlContainer;
import de.jwic.events.ValueChangedEvent;
import de.jwic.events.ValueChangedListener;

/**
 * Displays a bar that indicates the progress of an operation. The state (or progress)
 * is read from an IProgressMonitor implementation, that is queried when the state is
 * rendered. 
 * 
 * @author Florian Lippisch
 * @version $Revision: 1.3 $
 */
public class ProgressBarControl extends Control {

	private static final long serialVersionUID = 1L;
	private IProgressMonitor monitor = null;
	private int width = 200;
	private int height = 20;
	private String cssClass = "progressBar";
	private boolean smooth = false;
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
	public ProgressBarControl(IControlContainer container, String name) {
		super(container, name);
		changeListener = new MyChangeListener();
	}

	/* (non-Javadoc)
	 * @see de.jwic.base.Control#actionPerformed(java.lang.String, java.lang.String)
	 */
	public void actionPerformed(String actionId, String parameter) {

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
	 * @return Returns the smooth.
	 */
	public boolean isSmooth() {
		return smooth;
	}

	/**
	 * @param smooth The smooth to set.
	 */
	public void setSmooth(boolean smooth) {
		this.smooth = smooth;
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
	 * Creates a list of Boolean objects that indicate if a value is reached
	 * or not. Used by the renderer-template to draw 'boxes'
	 * @return
	 */
	public boolean[] buildBlocks() {
		int count = width / (height - 2);
		boolean[] blocks = new boolean[count];
		int too = (int)((double)getPercent() / 100 * count);
		for (int i = 0; i < count; i++) {
			blocks[i] = i < too;
		}
		return blocks;
	}
	
	/**
	 * @return Returns the cssClass.
	 */
	public String getCssClass() {
		return cssClass;
	}

	/**
	 * @param cssClass The cssClass to set.
	 */
	public void setCssClass(String cssClass) {
		this.cssClass = cssClass;
	}
	
}
