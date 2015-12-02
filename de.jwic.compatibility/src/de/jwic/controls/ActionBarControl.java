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
/*
 * de.jwic.controls.TabStripControl
 * $Id: ActionBarControl.java,v 1.2 2010/02/23 08:27:25 lordsam Exp $
 */
package de.jwic.controls;

import java.util.ArrayList;
import java.util.List;

import de.jwic.base.Control;
import de.jwic.base.ControlContainer;
import de.jwic.base.IControlContainer;
import de.jwic.base.JWicException;
/**
 * An ActionBarControl can contain Controls like ButtonControls and ListBoxControls. 
 * 
 * TODO Add the ability to specify an index of the controls or to arrange them.
 * 
 * @author Adi Nitzu
 */
public class ActionBarControl extends ControlContainer {

	public enum Align {
		LEFT,
		RIGHT
	}
	
	private static final long serialVersionUID = 1L;

	private List<Control> elements = new ArrayList<Control>();
	private List<Control> rightElements = new ArrayList<Control>();
	
	/**
	 * @param container
	 */
	public ActionBarControl(IControlContainer container) {
		super(container);
	}
	/**
	 * @param container
	 * @param name
	 */
	public ActionBarControl(IControlContainer container, String name) {
		super(container, name);
	}
	
	/* (non-Javadoc)
	 * @see de.jwic.base.ControlContainer#registerControl(de.jwic.base.Control, java.lang.String)
	 */
	public void registerControl(Control control, String name) throws JWicException {
		super.registerControl(control, name);
		elements.add(control);
		if (control instanceof ButtonControl) {
			control.setTemplateName(control.getTemplateName() + "_Action");
		}
	}
	
	/* (non-Javadoc)
	 * @see de.jwic.base.ControlContainer#unregisterControl(de.jwic.base.Control)
	 */
	public void unregisterControl(Control control) {
		super.unregisterControl(control);
		elements.remove(control);
		rightElements.remove(control);
	}
	
	/**
	 * Get the controls in the right order as they were inserted
	 */	
	public List<Control> getElements() {
		return elements;
	}

	/**
	 * Returns the controls aligned to the right.
	 * @return
	 */
	public List<Control> getRightElements() {
		return rightElements;
	}

	/**
	 * Change the alignment of a control.
	 * @param control
	 * @param align
	 */
	public void setPosition(Control control, Align align) {
		
		// move the element between lists
		if (!(elements.contains(control) || rightElements.contains(control))) {
			throw new IllegalArgumentException("The specified control is not a member of this action bar.");
		}
		
		List<Control> a = (align == Align.LEFT ? rightElements : elements);
		List<Control> b = (align == Align.LEFT ? elements : rightElements);
		
		if (a.contains(control)) { // only move if it is not already there...
			a.remove(control);
			b.add(control);
		}
		
	}
	
}
