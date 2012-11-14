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
 * de.jwic.sandbox.OtherPage
 * Created on 20.06.2007
 * $Id: OtherPage.java,v 1.3 2010/01/26 11:25:18 lordsam Exp $
 */
package de.jwic.samples.layers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import de.jwic.base.IControlContainer;
import de.jwic.base.Page;
import de.jwic.controls.Button;
import de.jwic.controls.ListBoxControl;
import de.jwic.events.ElementSelectedEvent;
import de.jwic.events.ElementSelectedListener;
import de.jwic.events.SelectionEvent;
import de.jwic.events.SelectionListener;

/**
 *
 * @author Florian Lippisch
 */
public class OtherPage extends Page {

	protected List<ElementSelectedListener> selectionListeners = null;

	private ListBoxControl lb;
	private boolean closePage = false;
	
	/**
	 * @param container
	 * @param name
	 */
	public OtherPage(IControlContainer container, String name) {
		super(container, name);
		
		lb = new ListBoxControl(this, "listbox");
		lb.addElement("Test 1");
		lb.addElement("Red");
		lb.addElement("Green");
		
		Button button = new Button(this, "button");
		button.setTitle("Apply");
		button.addSelectionListener(new SelectionListener() {
			public void objectSelected(SelectionEvent event) {
				doApply();
			}
		});
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
	 * Send the element selected event to the registerd listeners.
	 */
	protected void sendElementSelectedEvent() {
		
		if (selectionListeners != null) {
			ElementSelectedEvent e = new ElementSelectedEvent(this, lb.getSelectedKey());
			for (Iterator<ElementSelectedListener> it = selectionListeners.iterator(); it.hasNext(); ) {
				ElementSelectedListener osl = it.next();
				osl.elementSelected(e);
			}
		}

	}

	private void doApply() {
		
		// Notify "listeners" 
		sendElementSelectedEvent();
		
		// "close" the window and refresh the parent page.
		setClosePage(true);
	}

	/**
	 * @return the closePage
	 */
	public boolean isClosePage() {
		return closePage;
	}

	/**
	 * @param closePage the closePage to set
	 */
	public void setClosePage(boolean closePage) {
		this.closePage = closePage;
		requireRedraw();
	}

}
