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
package de.jwic.base;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * A ControlContainer is a control that can contain other controls. 
 * @author Florian Lippisch
 * @version $Revision: 1.9 $
 */
public class ControlContainer extends Control implements IControlContainer {

	private static final long serialVersionUID = 2L;
	
	private Map<String, Control> controls = new LinkedHashMap<String, Control>();

	/**
	 * @param container
	 */
	public ControlContainer(IControlContainer container) {
		super(container, null);
	}
	
	/**
	 * @param container
	 */
	public ControlContainer(IControlContainer container, String name) {
		super(container, name);
	}
	
	/* (non-Javadoc)
	 * @see de.jwic.base.IControlContainer#adopt(de.jwic.base.Control)
	 */
	public void adopt(Control control, String name) {
		
		// notify the old container that the control has been moved (remove it)
		IControlContainer oldParent = control.getContainer();
		if (oldParent == this) {
			return; // nothing to do
		}
		oldParent.unregisterControl(control);
		registerControl(control, name);
		
	}
	
	/* (non-Javadoc)
	 * @see de.jwic.base.Control#setControlID(java.lang.String)
	 */
	void setControlID(String newControlID) {
		super.setControlID(newControlID);
		// update childs, if there are already any
		if (controls != null) {
			for (Iterator<Control> it = getControls(); it.hasNext(); ) {
				Control child = it.next();
				child.setControlID(newControlID + "." + child.getName());
			}
		}
	}
	
	/* (non-Javadoc)
	 * @see de.jwic.base.IControlContainer#addControl(java.lang.String, de.jwic.base.Control)
	 */
	public void registerControl(Control control, String name) throws JWicException {
		
		if (control.getContainer() != null) {
			throw new JWicException("The control has already been registerd to a container.");
		}

		if (name == null) {
			int idx = controls.size();
			name = "c" + idx;
			while (controls.containsKey(name)) {
				idx++;
				name = "c" + idx;
			}
		} else {
			// the control must not contain spaces or dot's
			for (int i = 0; i < name.length(); i++) {
				char c = name.charAt(i);
				if (c == ' ' || c == '.') {
					throw new IllegalArgumentException("A control-name must not contain spaces or dot's." + name);
				}
			}
		}
		
		if(controls.containsKey(name)) {
			throw new JWicException("A control with that name does already exist. " + name);
		}
		
		control.setSessionContext(getSessionContext());
		control.setContainer(this);
		control.setName(name);
		control.setControlID(getControlID() + "." + control.getName());
		controls.put(control.getName(), control);
		setRequireRedraw(true);

	}
	
	/* (non-Javadoc)
	 * @see de.jwic.base.IControlContainer#unregisterControl(de.jwic.base.Control)
	 */
	public void unregisterControl(Control control) {
		
		if (control.getContainer() == this) {
			controls.remove(control.getName());
			control.setContainer(null);		
			setRequireRedraw(true);
		}
		
	}
	
	/**
	 * Remove all controls in the context and destroy them.
	 *
	 */
	public void destroy() {
		super.destroy();
		
		for (Iterator<Control> it = controls.values().iterator(); it.hasNext(); ) {
			Control control = it.next();
			it.remove();
			control.destroy();
		}
		
	}

	/* (non-Javadoc)
	 * @see de.jwic.base.IControlContainer#getControl(java.lang.String)
	 */
	public Control getControl(String name) {

		return controls.get(name);
	}

	/* (non-Javadoc)
	 * @see de.jwic.base.IControlContainer#getControls()
	 */
	public Iterator<Control> getControls() {
		return controls.values().iterator();
	}

	/* (non-Javadoc)
	 * @see de.jwic.base.IControlContainer#removeControl(java.lang.String)
	 */
	public void removeControl(String controlName) {
		Control control = getControl(controlName);
		if (control != null) {
			unregisterControl(control);
			control.destroy();
		}
	}

	/**
	 * Returns true if the specified childControl is visible. This method is
	 * used by the rendering engine to determine if the specified control can
	 * be rendered. By default, all child controls are relevant for rendering.
	 * Some container implementations (like the TabStripControl) can override 
	 * this method to prevent rendering of a child, i.e. a TabControl that is
	 * visible but not 'active'.
	 * 
	 * @param childControl
	 * @return
	 */
	public boolean isRenderingRelevant(Control childControl) {
		return true; 
	}
	
}
