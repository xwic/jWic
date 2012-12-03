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
 * de.jwic.ecolib.controls.ActionButton
 * Created on Jun 8, 2011
 * $Id: ActionButton.java,v 1.2 2012/01/25 10:42:29 adrianionescu12 Exp $
 */
package de.jwic.ecolib.controls.actions;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import de.jwic.base.IControlContainer;
import de.jwic.base.ImageRef;
import de.jwic.base.JavaScriptSupport;
import de.jwic.controls.Button;
import de.jwic.ecolib.actions.IAction;
import de.jwic.events.SelectionEvent;
import de.jwic.events.SelectionListener;

/**
 * A Button which offers support for IAction
 * @author Adrian Ionescu
 * 
 */
@JavaScriptSupport
public class ActionButton extends Button {
	private static final long serialVersionUID = 1L;
	private IAction action;
	


	/**
	 * @param container
	 * @param action
	 */
	public ActionButton(IControlContainer container, IAction action) {
		this(container, null, action);
	}
	
	/**
	 * @param container
	 */
	public ActionButton(IControlContainer container, String name, IAction action) {
		super(container, name);
		
		setTemplateName(Button.class.getName());
		
		this.action = action;
		
		setTitle(action.getTitle());
		setIconEnabled(action.getIconEnabled());
		setIconDisabled(action.getIconDisabled());
		setEnabled(action.isEnabled());
		setVisible(action.isVisible());
		setTooltip(action.getTooltip());
		
		action.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
				requireRedraw();
			}
		});
		
		addSelectionListener(new SelectionListener() {
			private static final long serialVersionUID = 1L;
			public void objectSelected(SelectionEvent event) {
				onClick(event);
			}			
		});
	}

	/**
	 * @param event
	 */
	protected void onClick(SelectionEvent event) {
		action.run();		
	}

	/**
	 * @return the action
	 */
	public IAction getAction() {
		return action;
	}
	
	/* (non-Javadoc)
	 * @see de.jwic.controls.Button#getTitle()
	 */
	@Override
	public String getTitle() {
		return action.getTitle();
	}
	
	/* (non-Javadoc)
	 * @see de.jwic.controls.Button#getIconDisabled()
	 */
	@Override
	public ImageRef getIconDisabled() {
		return action.getIconDisabled() != null ? action.getIconDisabled() : action.getIconEnabled();
	}
	
	/* (non-Javadoc)
	 * @see de.jwic.controls.Button#getIconDisabled()
	 */
	@Override
	public ImageRef getIconEnabled() {
		return action.getIconEnabled();
	}
	
	/* (non-Javadoc)
	 * @see de.jwic.controls.Button#getIcon()
	 */
	@Override
	public ImageRef getIcon() {
		if (!isEnabled()) {
			return getIconDisabled();
		} else {
			return getIconEnabled();
		}
	}
	
	/* (non-Javadoc)
	 * @see de.jwic.base.Control#isVisible()
	 */
	@Override
	public boolean isVisible() {
		return action.isVisible();
	}
	
	/* (non-Javadoc)
	 * @see de.jwic.controls.HTMLElement#isEnabled()
	 */
	@Override
	public boolean isEnabled() {
		return action.isEnabled();
	}
	
	/* (non-Javadoc)
	 * @see de.jwic.controls.Button#getTooltip()
	 */
	@Override
	public String getTooltip() {
		return action.getTooltip();
	}
}