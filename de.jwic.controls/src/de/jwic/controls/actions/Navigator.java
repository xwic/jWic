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
package de.jwic.controls.actions;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import de.jwic.base.Control;
import de.jwic.base.IControlContainer;

/**
 * Displays a simple list of actions.
 * 
 * @see de.jwic.ecolib.actions.Action
 * @author Florian Lippisch
 * @version $Revision: 1.3 $
 */
public class Navigator extends Control {

	private static final long serialVersionUID = 1411314684986651120L;
	public static final int VERTICAL = 1;
	public static final int HORIZONTAL = 0;
	
	protected boolean enabled = true;
	protected int count = 0;
	protected int orientation = HORIZONTAL;
	protected List<Integer> actionOrder = new ArrayList<Integer>();
	protected Map<Integer, IAction> actionMap = new HashMap<Integer, IAction>();
	
	protected ActionUpdateListener actionListener = null;
	
	/**
	 * Used to flag the navigator to be redrawn when an action has
	 * been modified.
	 * @author Florian Lippisch
	 * @version $Revision: 1.3 $
	 */
	private class ActionUpdateListener implements PropertyChangeListener {
		public void propertyChange(PropertyChangeEvent evt) {
			requireRedraw(); // requires update.			
		}
	}
	
	/**
	 * @param container
	 * @param name
	 */
	public Navigator(IControlContainer container, String name) {
		super(container, name);
		actionListener = new ActionUpdateListener();
	}

	/**
	 * Adds an action to the navigator.
	 * @param action
	 */
	public void addAction(IAction action) {
		Integer key = new Integer(count++);
		actionOrder.add(key);
		actionMap.put(key, action);
		action.addPropertyChangeListener(actionListener);
	}
	
	/* (non-Javadoc)
	 * @see de.jwic.base.Control#destroy()
	 */
	public void destroy() {
		// remove the listener from all actions
		for (Iterator<IAction> it = actionMap.values().iterator(); it.hasNext(); ) {
			IAction action = it.next();
			action.removePropertyChangeListener(actionListener);
		}
		super.destroy();
	}
	
	/**
	 * Returns the list of action-Keys.
	 * @return
	 */
	public List<Integer> getActionKeys() {
		return actionOrder;
	}
	
	/**
	 * Returns the Action with the specified key.
	 * @param key
	 * @return
	 */
	public IAction getActionByKey(Integer key) {
		return actionMap.get(key);
	}
	
	/* (non-Javadoc)
	 * @see de.jwic.base.Control#actionPerformed(java.lang.String, java.lang.String)
	 */
	public void actionPerformed(String actionId, String parameter) {
		
		if (actionId.equals("click")) {
			IAction action = actionMap.get(new Integer(parameter));
			if (action != null) {
				if (action.isEnabled()) {
					action.run();
				} else {
					log.warn("Action with the ID '" + parameter + "' was clicked but is disabled.");
				}
			} else {
				log.warn("Action with the ID '" + parameter + "' does not exist.");
			}
		}

	}

	/**
	 * @return Returns the orientation.
	 */
	public int getOrientation() {
		return orientation;
	}

	/**
	 * @param orientation The orientation to set.
	 */
	public void setOrientation(int orientation) {
		this.orientation = orientation;
	}

	/**
	 * @param b
	 */
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
		
	}
	
	public boolean isEnabled() {
		return enabled;
	}

}
