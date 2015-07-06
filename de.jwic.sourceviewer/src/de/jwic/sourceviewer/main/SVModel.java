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
package de.jwic.sourceviewer.main;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

import de.jwic.sourceviewer.model.Group;
import de.jwic.sourceviewer.model.NavigationElement;
import de.jwic.sourceviewer.model.Workspace;

/**
 * The application model.
 * 
 * @author Florian Lippisch
 */
public class SVModel implements Serializable {

	private final static int EVENT_GROUP_SELECTED = 0;
	private final static int EVENT_ELEMENT_SELECTED = 1;

	private Workspace workspace = null;
	private Group currentGroup = null;
	private NavigationElement currentElement = null;

	private Stack history = new Stack();
	
	private List listeners = Collections.synchronizedList(new ArrayList());

	/**
	 * Add a ISVModelListener.
	 * 
	 * @param listener
	 */
	public void addSVModelListener(ISVModelListener listener) {
		listeners.add(listener);
	}

	/**
	 * Remove a ISVModelListener.
	 * 
	 * @param listener
	 */
	public void removeSVModelListener(ISVModelListener listener) {
		listeners.remove(listener);
	}

	/**
	 * Fire an event.
	 * 
	 * @param eventType
	 * @param event
	 */
	private void fireEvent(int eventType, SVModelEvent event) {

		Object[] lst = listeners.toArray();
		for (int i = 0; i < lst.length; i++) {
			ISVModelListener listener = (ISVModelListener) lst[i];
			switch (eventType) {
			case EVENT_GROUP_SELECTED:
				listener.groupSelected(event);
				break;
			case EVENT_ELEMENT_SELECTED:
				listener.elementSelected(event);
				break;
			}
		}

	}

	/**
	 * @return the workspace
	 */
	public Workspace getWorkspace() {
		return workspace;
	}

	/**
	 * @param workspace
	 *            the workspace to set
	 */
	public void setWorkspace(Workspace workspace) {
		this.workspace = workspace;
		if (workspace.getChilds().size() != 0) {
			Group group = (Group) workspace.getChilds().iterator().next();
			setCurrentGroup(group);
		}
	}

	/**
	 * @return the currentGroup
	 */
	public Group getCurrentGroup() {
		return currentGroup;
	}

	/**
	 * @param currentGroup
	 *            the currentGroup to set
	 */
	public void setCurrentGroup(Group currentGroup) {
		this.currentGroup = currentGroup;
		fireEvent(EVENT_GROUP_SELECTED, new SVModelEvent(this, currentGroup));
	}

	/**
	 * @return the currentElement
	 */
	public NavigationElement getCurrentElement() {
		return currentElement;
	}

	/**
	 * @param currentElement
	 *            the currentElement to set
	 */
	public void setCurrentElement(NavigationElement currentElement) {
		this.currentElement = currentElement;
		/* DISABLED
		if (history.size() == 0 || ((HistoryElement) history.peek()).getElement() != currentElement) {
			history.push(new HistoryElement(currentElement, currentGroup));
		}
		*/
		fireEvent(EVENT_ELEMENT_SELECTED, new SVModelEvent(this, currentElement));
	}

	/**
	 * handles "back". sets the former element as the current one.
	 */
	public void back() {
		if (history.size() > 1) {
			history.pop();
			HistoryElement peek = (HistoryElement) history.peek();
			NavigationElement element = peek.getElement();
			
			this.currentElement = element;
			fireEvent(EVENT_ELEMENT_SELECTED, new SVModelEvent(this, peek.getElement()));
			if (currentGroup != peek.getGroup()) {
				setCurrentGroup(peek.getGroup());
			}
		} 
	}
	
	/**
	 * Returns true if a history is available.
	 * @return
	 */
	public boolean isHistoryAvailable() {
		return history.size() > 1;
	}

	/**
	 * Search for an element with the specified name and select it.
	 * @param name
	 */
	public void openEntryByName(String name) {
		
		// iterate over the groups
		for (Iterator it = workspace.getChilds().iterator(); it.hasNext(); ) {
			Group group = (Group)it.next();
			NavigationElement element = group.findElementByName(name);
			if (element != null) {
				if (group != currentGroup) {
					setCurrentGroup(group);
				}
				setCurrentElement(element);
				break;
			}
		}
		
	}
}
