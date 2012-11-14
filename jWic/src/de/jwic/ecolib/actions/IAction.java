/*
 * Copyright 2005 jWic group (http://www.jwic.de)
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
 * de.jwic.ecolib.actions.IAction
 * Created on 02.01.2006
 * $Id: IAction.java,v 1.6 2012/01/24 19:42:11 adrianionescu12 Exp $
 */
package de.jwic.ecolib.actions;

import java.beans.PropertyChangeListener;
import java.io.Serializable;

import de.jwic.base.ImageRef;

/**
 * Represents the backend side of a command which may be triggered by the end user. Actions
 * are typically added to navigators or menues. The UI is handled by an container that uses
 * the actions properties. When the user triggers the command via the container, actions 
 * rund method is invoked.
 * 
 * @author Florian Lippisch
 * @version $Revision: 1.6 $
 */
public interface IAction extends Runnable, Serializable {

	/**
	 * Add a property change listener to this action.
	 * @param listener
	 */
	public void addPropertyChangeListener(PropertyChangeListener listener);
	
	/**
	 * Remove a propertyChangeListener.
	 * @param listener
	 */
	public void removePropertyChangeListener(PropertyChangeListener listener);

	/**
	 * Returns the Title of the action.
	 * @return
	 */
	public String getTitle();
	
	/**
	 * Sets the Title of the action.
	 * @param title
	 */
	public void setTitle(String title);
	
	/**
	 * Returns the iconEnabled of the action.
	 * @return
	 */
	public ImageRef getIconEnabled();

	/**
	 * Sets the iconEnabled of the action.
	 * @param iconEnabled
	 */
	public void setIconEnabled(ImageRef iconEnabled);
	
	/**
	 * Returns the iconDisabled of the action.
	 * @return
	 */
	public ImageRef getIconDisabled();

	/**
	 * Sets the iconDisabled of the action.
	 * @param iconDisabled
	 */
	public void setIconDisabled(ImageRef iconDisabled);
	
	/**
	 * Returns true if the action is enabled.
	 * @return
	 */
	public boolean isEnabled();
	
	/**
	 * Sets the 'enabled' state of the action
	 * @param enabled
	 */
	public void setEnabled(boolean enabled);
	
	/**
	 * Returns true if the action is visible.
	 * @return
	 */
	public boolean isVisible();

	/**
	 * Sets the 'visible' state of the action
	 * @param visible
	 */
	public void setVisible(boolean visible);
	
	/**
	 * @return the tooltip
	 */
	public String getTooltip();

	/**
	 * @param tooltip the tooltip to set
	 */
	public void setTooltip(String tooltip);
}
