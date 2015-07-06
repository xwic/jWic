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

import de.jwic.base.ControlContainer;
import de.jwic.base.IControlContainer;
import de.jwic.util.IHTMLElement;

/**
 * Implements the common methods/properties defined by the IHTMLElement interface for containers..
 * 
 * @author Florian Lippisch
 * @version $Revision: 1.1 $
 */
public abstract class HTMLElementContainer extends ControlContainer implements IHTMLElement {
	private static final long serialVersionUID = 1L;
	protected String cssClass = null;
	protected boolean enabled = true;
	/** sets the width of the control to 100% to fill the available space **/ 
	protected boolean fillWidth = false;
	protected int width = 0;	// 0 = not set
	protected int height = 0;	// 0 = not set
	
	/**
	 * @param container
	 * @param name
	 */
	public HTMLElementContainer(IControlContainer container, String name) {
		super(container, name);
	}

	/* (non-Javadoc)
	 * @see de.jwic.controls.IHTMLElement#getCssClass()
	 */
	public String getCssClass() {
		return cssClass;
	}

	/* (non-Javadoc)
	 * @see de.jwic.controls.IHTMLElement#setCssClass(java.lang.String)
	 */
	public void setCssClass(String cssClass) {
		this.cssClass = cssClass;
		requireRedraw();
	}

	/* (non-Javadoc)
	 * @see de.jwic.base.IHaveEnabled#isEnabled()
	 */
	@Override
	public boolean isEnabled() {
		return enabled;
	}

	/* (non-Javadoc)
	 * @see de.jwic.base.IHaveEnabled#setEnabled(boolean)
	 */
	@Override
	public void setEnabled(boolean enabled) {
		if (enabled != this.enabled) {
			requireRedraw();
		}
		this.enabled = enabled;
	}

	/* (non-Javadoc)
	 * @see de.jwic.controls.IHTMLElement#getHeight()
	 */
	public int getHeight() {
		return height;
	}

	/* (non-Javadoc)
	 * @see de.jwic.controls.IHTMLElement#setHeight(int)
	 */
	public void setHeight(int height) {
		if (this.height != height) {
			requireRedraw();
		}
		this.height = height;
	}

	/* (non-Javadoc)
	 * @see de.jwic.controls.IHTMLElement#getWidth()
	 */
	public int getWidth() {
		return width;
	}

	/* (non-Javadoc)
	 * @see de.jwic.controls.IHTMLElement#setWidth(int)
	 */
	public void setWidth(int width) {
		if (this.width != width) {
			requireRedraw();
		}
		this.width = width;
	}

	/*
	 * (non-Javadoc)
	 * @see de.jwic.controls.IHTMLElement#isFillWidth()
	 */
	public boolean isFillWidth() {
		return fillWidth;
	}

	/*
	 * (non-Javadoc)
	 * @see de.jwic.controls.IHTMLElement#setFillWidth(boolean)
	 */
	public void setFillWidth(boolean fillWidth) {
		if (this.fillWidth != fillWidth) {
			requireRedraw();
		}
		this.fillWidth = fillWidth;
	}

	/* (non-Javadoc)
	 * @see de.jwic.controls.IHTMLElement#forceFocus()
	 */
	public boolean forceFocus() {
		return false;
	}

}
