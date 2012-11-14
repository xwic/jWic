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
 * de.jwic.ecolib.controls.stackpanel.Stack
 * Created on 20.04.2007
 * $Id: Stack.java,v 1.2 2007/04/20 08:39:33 lordsam Exp $
 */

package de.jwic.ecolib.controls.stackpanel;

import java.io.Serializable;

import de.jwic.base.Control;

/**
 * Represents a stack in the StackPanel.
 *
 * @author Sebastian
 */
public class Stack implements Serializable {
	
	/** the unique identifier of the stack */
	private String uniqueIdentifier;

	/** the stack title */
	private String title;

	/** name of the child control */
	private String controlName;

	/** the child control itself */
	private Control childControl;

	public Stack(String title, Control control, String controlName) {
		this.title = title;
		this.childControl = control;
		this.controlName = controlName;
	}

	/**
	 * @return the childControl
	 */
	public Control getChildControl() {
		return childControl;
	}

	/**
	 * @param childControl
	 *            the childControl to set
	 */
	public void setChildControl(Control childControl) {
		this.childControl = childControl;
	}

	/**
	 * @return the controlName
	 */
	public String getControlName() {
		return controlName;
	}

	/**
	 * @param controlName
	 *            the controlName to set
	 */
	public void setControlName(String controlName) {
		this.controlName = controlName;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title
	 *            the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the uniqueIdentifier
	 */
	public String getUniqueIdentifier() {
		return uniqueIdentifier;
	}

	/**
	 * @param uniqueIdentifier
	 * 				the uniqueIdentifier to set.
	 */
	public void setUniqueIdentifier(String uniqueIdentifier) {
		this.uniqueIdentifier = uniqueIdentifier;
	}

}
