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
 * de.jwic.controls.ListBoxControl
 * $Id: ListControl.java,v 1.11 2008/09/17 15:19:46 lordsam Exp $
 */
package de.jwic.controls;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import de.jwic.base.Control;
import de.jwic.base.Field;
import de.jwic.base.IControlContainer;
import de.jwic.base.Page;
import de.jwic.base.SessionContext;
import de.jwic.events.ElementSelectedEvent;
import de.jwic.events.ElementSelectedListener;
import de.jwic.events.ValueChangedEvent;
import de.jwic.events.ValueChangedListener;

/**
 * This control renders list like a list box, radio group or checkbox.
 * 
 * @author Florian Lippisch
 * @version $Revision: 1.11 $
 */
public abstract class ListControl extends HTMLElement {
	private static final long serialVersionUID = 1L;
	protected Map<String, ListEntry> elements = new LinkedHashMap<String, ListEntry>();
	protected boolean changeNotification = false;
	protected List<ElementSelectedListener> selectionListeners = null;
	protected Field field = null;
	
	/**
	 * @param container
	 * @param name
	 */
	public ListControl(IControlContainer container, String name) {
		super(container, name);
		init();
	}
	
	/* (non-Javadoc)
	 * @see de.jwic.base.Control#init()
	 */
	private void init() {
		
		field = new Field(this);
		field.setValue("");
		
		field.addValueChangedListener(new ValueChangedListener() {
			private static final long serialVersionUID = 1L;
			public void valueChanged(ValueChangedEvent event) {
				sendElementSelectedEvent();	// transform the event.
			}
		});
		
	}
	
	/**
	 * Invoked when the value has been changed.
	 *
	 */
	public void actionValuechanged() {
		// nothing to do, as the valueChanged is triggered directly by the field.
		// but we must leave this method as it is invoked by the onChanged event.
	}
	/**
	 * Send the element selected event to the registerd listeners.
	 */
	protected void sendElementSelectedEvent() {
		
		if (selectionListeners != null) {
			ElementSelectedEvent e = new ElementSelectedEvent(this, getSelectedKey());
			for (Iterator<ElementSelectedListener> it = selectionListeners.iterator(); it.hasNext(); ) {
				ElementSelectedListener osl = it.next();
				osl.elementSelected(e);
			}
		}

	}
	/**
	 * Add an element to the list where the title is used as the key. 
	 * @param title
	 */
	public ListEntry addElement(String title) {
		return addElement(title, title);
	}
	/**
	 * Add an element to the list with a title and a seperate key.
	 * @param title
	 * @param key
	 */
	public ListEntry addElement(String title, String key) {
		ListEntry entry = new ListEntry(title, key);
		elements.put(key, entry);
		requireRedraw();
		return entry;
	}
	
	/**
	 * Register a listener that will be notified when an element has been selected.
	 * @param listener
	 */
	public void addElementSelectedListener(ElementSelectedListener listener) {
		if (selectionListeners == null) {
			selectionListeners = new ArrayList<ElementSelectedListener>();
		}
		selectionListeners.add(listener);
	}
	
	/**
	 * Removes the specified listener.
	 * @param listener
	 */
	public void removeElementSelectedListener(ElementSelectedListener listener) {
		if (selectionListeners != null) {
			selectionListeners.remove(listener);
		}
	}

	
	/**
	 * Remove all entries from the list.
	 */
	public void clear() {

		elements.clear();
		field.setValue("");		// reset the key.
		requireRedraw();
		
	}
	/**
	 * Returns the collection of elements.
	 * @return List of ListEntry objects.
	 */
	public Collection<ListEntry> getElements() {
		return elements.values();
	}
	
	/**
	 * @return the map of elements
	 */
	public Map<String, ListEntry> getElementsMap() {
		return elements;
	}
	
	/**
	 * @param the elements to set
	 */
	public void setElementsMap(Map<String, ListEntry> elelemts ) {
		this.elements = elelemts;
	}
	
	/**
	 * Returns the collection of elements keys.
	 * @return
	 */
	public Collection<String> getElementsKeys() {
		return elements.keySet();
	}
	
	/**
	 * The field representation used by the ListControl on the HTML form.
	 * This property is used by the renderer to generate the proper HTML code.
	 * Use the <code>selectedKey</code> property to change the ListControls data.
	 * @return
	 */
	public Field getField() {
		return field;
	}
	
	/**
	 * Returns the key of the selected element as String. If
	 * multiple elements are selected, the keys are seprated by a ';'. 
	 */
	public String getSelectedKey() {

		return field.getValue();
		
	}

	/**
	 * Returns the selected keys as String[]. 
	 * @return
	 */
	public String[] getSelectedKeys() {
		return field.getValues();
	}

	/**
	 * Set the key of the selected element.
	 * @param key
	 * @return
	 */
	public void setSelectedKey(String key) {
		field.setValue(key);
		requireRedraw();
	}

	/**
	 * Returns true if the page will be submited when the value has been changed
	 * by the user.
	 * @return Returns the changeNotification.
	 */
	public boolean isChangeNotification() {
		return changeNotification;
	}
	/**
	 * Set to true if the page should be submited when the value has been changed
	 * by the user.
	 * @param changeNotification boolean
	 */
	public void setChangeNotification(boolean changeNotification) {
		this.changeNotification = changeNotification;
		requireRedraw();
	}
	
    /**
     * @param key the element beeing testet
     * handle also the multiselect case
     */    
    public boolean isKeySelected(String key){
    	String[] selection = field.getValues();
    	for (int i = 0; i < selection.length; i++) {
    		if (key.equalsIgnoreCase(selection[i])) {
    			return true;
    		}
    	}
        return false;
    }
	
	/**
	 * Remove an element by its key. Returns the number of elements removed.
	 * @param key
	 */
	public int removeElement(String key) {
		
		int count = 0;
		String[] selection = field.getValues();
		List<String> newSelection = new ArrayList<String>(selection.length);
		
		for (Iterator<ListEntry> it = elements.values().iterator(); it.hasNext(); ) {
			ListEntry entry = it.next();
			if (entry.key.equals(key)) {
				it.remove();
				count++;
			} else {
				if (isKeySelected(entry.key)) {
					newSelection.add(entry.key);
				}
			}
		}
		if (newSelection.size() != selection.length) { // a selected element has been removed
			String[] newList = new String[newSelection.size()];
			for (int i = 0; i < newSelection.size(); i++) {
				newList[i] = newSelection.get(i);
			}
			field.setValues(newList);
		}
		
		requireRedraw();
		return count; // no element removed.
	}
	
	/**
	 * Forces focus for this field. Returns <code>true</code> if the 
	 * field Id could have been set.
	 */
	public boolean forceFocus() {
		
		// Check if the current top-control is a parent of this input box.
		// if so, force focus.
		SessionContext context = getSessionContext();
		Control topCtrl = context.getTopControl();
		if (topCtrl == null) {
			// initialization phase -> walk up the control hieracy to find a Page control
			IControlContainer container = getContainer();
			while (container != null && !(container instanceof SessionContext)) {
				Control ctrl = (Control)container;
				if (ctrl instanceof Page) {
					topCtrl = ctrl;
					break;
				}
				container = ctrl.getContainer();
			}
		}
		if (topCtrl != null && getControlID().startsWith(topCtrl.getControlID() + ".")) {
			if (topCtrl instanceof Page) {
				Page page = (Page)topCtrl;
				page.setForceFocusElement(getForceFocusElement());
				return true;
			}
		}
		return false;
	}
	
	/**
	 * @return Name of form element that is used to set the focus. 
	 */
	public String getForceFocusElement() {
		return getField().getId();
	}
}
