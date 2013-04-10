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
 * de.jwic.ecolib.actions.Action
 * Created on 02.01.2006
 * $Id: Action.java,v 1.7 2012/01/24 19:42:11 adrianionescu12 Exp $
 */
package de.jwic.ecolib.actions;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import de.jwic.base.ImageRef;

/**
 * Default implementation of the IAction interface.
 * @see IAction
 * @author Florian Lippisch
 * @version $Revision: 1.7 $
 */
public abstract class Action implements IAction {
	private static final long serialVersionUID = 1L;
	private String title = "Untitled";
	private ImageRef iconEnabled = null;
	private ImageRef iconDisabled = null;
	private boolean visible = true;
	private boolean enabled = true;
	private String tooltip = "";
	
	private PropertyChangeSupport propChgSupport = new PropertyChangeSupport(this);
	
	/**
	 * Add a property change listener to this action.
	 * @param listener
	 */
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		propChgSupport.addPropertyChangeListener(listener);
	}
	
	/**
	 * Remove a propertyChangeListener.
	 * @param listener
	 */
	public void removePropertyChangeListener(PropertyChangeListener listener) {
		propChgSupport.removePropertyChangeListener(listener);
	}
	
	/* (non-Javadoc)
	 * @see de.jwic.ecolib.actions.IAction#getTitle()
	 */
	public String getTitle() {
		return title;
	}

	/*
	 * (non-Javadoc)
	 * @see de.jwic.ecolib.actions.IAction#setTitle(java.lang.String)
	 */
	public void setTitle(String title) {
		String old = this.title;
		this.title = title;
		propChgSupport.firePropertyChange("title", old, title);
	}
	
	/* (non-Javadoc)
	 * @see de.jwic.ecolib.actions.IAction#getIconEnabled()
	 */
	public ImageRef getIconEnabled() {
		return iconEnabled;
	}
	
	/*
	 * (non-Javadoc)
	 * @see de.jwic.ecolib.actions.IAction#setIconEnabled(de.jwic.base.ImageRef)
	 */
	public void setIconEnabled(ImageRef iconEnabled) {
		ImageRef old = this.iconEnabled;
		this.iconEnabled = iconEnabled;
		propChgSupport.firePropertyChange("iconEnabled", old, iconEnabled);
	}
	
	/* (non-Javadoc)
	 * @see de.jwic.ecolib.actions.IAction#getIconDisabled()
	 */
	public ImageRef getIconDisabled() {
		return iconDisabled;
	}
	
	/*
	 * (non-Javadoc)
	 * @see de.jwic.ecolib.actions.IAction#setIconDisabled(de.jwic.base.ImageRef)
	 */
	public void setIconDisabled(ImageRef iconDisabled) {
		ImageRef old = this.iconDisabled;
		this.iconDisabled = iconDisabled;
		propChgSupport.firePropertyChange("iconDisabled", old, iconDisabled);
	}

	/*
	 * (non-Javadoc)
	 * @see de.jwic.ecolib.actions.IAction#isVisible()
	 */
	public boolean isVisible() {
		return visible;
	}

	/*
	 * (non-Javadoc)
	 * @see de.jwic.ecolib.actions.IAction#setVisible(boolean)
	 */
	public void setVisible(boolean visible) {
		boolean old = this.visible;
		this.visible = visible;
		propChgSupport.firePropertyChange("visible", old, visible);
	}

	/*
	 * (non-Javadoc)
	 * @see de.jwic.ecolib.actions.IAction#isEnabled()
	 */
	public boolean isEnabled() {
		return enabled;
	}

	/*
	 * (non-Javadoc)
	 * @see de.jwic.ecolib.actions.IAction#setEnabled(boolean)
	 */
	public void setEnabled(boolean enabled) {
		boolean old = this.enabled; 
		this.enabled = enabled;
		propChgSupport.firePropertyChange("enabled", old, enabled);
	}

	/*
	 * (non-Javadoc)
	 * @see de.jwic.ecolib.actions.IAction#getTooltip()
	 */
	public String getTooltip() {
		return tooltip;
	}

	/*
	 * (non-Javadoc)
	 * @see de.jwic.ecolib.actions.IAction#setTooltip(java.lang.String)
	 */
	public void setTooltip(String tooltip) {
		String old = this.tooltip;
		this.tooltip = tooltip;
		propChgSupport.firePropertyChange("tooltip", old, tooltip);
	}
}
