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

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import de.jwic.base.IControlContainer;
import de.jwic.events.SelectionEvent;
import de.jwic.events.SelectionListener;

/**
 * Implements event handling for controls that can be selected.
 * @version $Revision: 1.3 $
 * @author Florian Lippisch
 */
public abstract class SelectableControl extends HTMLElement {
	private static final long serialVersionUID = 1L;
	private List<SelectionListener> selectedListeners = null;
	
	/**
	 * @param container
	 */
	public SelectableControl(IControlContainer container) {
		super(container, null);
	}
	/**
	 * @param container
	 * @param name
	 */
	public SelectableControl(IControlContainer container, String name) {
		super(container, name);
	}
	/**
	 * Register a listener that will be notified when the anchor will be clicked.
	 * @param listener
	 */
	public void addSelectionListener(SelectionListener listener) {
		if (selectedListeners == null) {
			selectedListeners = new ArrayList<SelectionListener>();
		}
		selectedListeners.add(listener);
	}
	
	/**
	 * Removes the specified listener from the list of objects that are notified when the control will be clicked.
	 * @param listener
	 */
	public void removeSelectionListener(SelectionListener listener) {
		if (selectedListeners != null) {
			selectedListeners.remove(listener);
		}
		
	}
	
	/**
	 * 
	 * @return a read only list of registered listeners
	 */
	public List<SelectionListener> getSelectionListeners(){
		if (null == selectedListeners){
			selectedListeners = new ArrayList<SelectionListener>();
		}
		return Collections.unmodifiableList(selectedListeners);
	} 
	
	/**
	 * Called when the button was clicked by the user. If there are SelectionListeners 
	 * registerd they are notified.
	 */
	public void click() {
		// notify the listeners
		if (selectedListeners != null) {
			SelectionEvent se = new SelectionEvent(this);
			for (Iterator<SelectionListener> it = selectedListeners.iterator(); it.hasNext(); ) {
				SelectionListener osl = it.next();
				osl.objectSelected(se);
			}
		}

	}

}
