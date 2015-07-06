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

/**
 * This interface defines methods required by containers.
 * @author Florian Lippisch
 */
public interface IControlContainer {

	/**
	 * Adopt a control that has already been created with a different parent. The
	 * control is removed from its current parent and added to this container.
	 * @param control
	 * @param name - new name of the control or <code>null</code> if the name should be assigned automatically
	 */
	public void adopt(Control control, String name);
	
	/**
	 * Returns a Control by its name.
	 * @param name java.lang.String
	 */
	public Control getControl(String name);
	/**
	 * Returns an Iterator for all controls in this container.
	 * @return Iterator of Control objects
	 */
	public Iterator<Control> getControls();
	/**
	 * Returns the SessionContext. 
	 * @return
	 */
	public SessionContext getSessionContext();
	
	/**
	 * INTERNAL method used to registers a control with this container. This method is called automatically
	 * within the constructor of a control. Do not use this method directly, thought that subclasses
	 * may override this method to get notified if a control is registered.
	 * @param control the Control 
	 */
	public void registerControl(Control control, String name);

	/**
	 * INTERNAL method used to unregisters a control from this container. The control is removed but not
	 * destroyed. Do not use this method directly, thought that subclasses may override this method to
	 * get notified if a control is removed. 
	 * @param control the Control 
	 */
	public void unregisterControl(Control control);

	/**
	 * Remove a control from the container. 
	 */
	public void removeControl(String name);
	
	/**
	 * Returns true if the control has been changed since the last rendering
	 * and must be rendered again to reflect the last changes.
	 * @return boolean
	 */
	public abstract boolean isRequireRedraw();
	
	/**
	 * Set to true if the control needs to be rendered again to reflect the changes
	 * of the control since it was last rendered. 
	 * @param requireRedraw
	 */
	public abstract void setRequireRedraw(boolean requireRedraw);

	/**
	 * Returns true if the specified childControl is visible. This method is
	 * used by the rendering engine to determine if the specified control can
	 * be rendered. By default, it simply returns the childControls visible
	 * property. But some container implementations (like the TabStripControl) 
	 * can override this method to prevent rendering of a child (i.e. a TabControl)
	 * that is visible but not 'active.
	 * 
	 * @param childControl
	 * @return
	 */
	public boolean isRenderingRelevant(Control childControl);

}