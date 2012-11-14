/*
 * Copyright 2005 jWic Group (http://www.jwic.de)
 * $Id: SelectableControl.java,v 1.3 2008/09/17 15:19:46 lordsam Exp $
 */
package de.jwic.controls;

import java.util.ArrayList;
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
