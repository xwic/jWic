package de.jwic.ecolib.controls.editablelistbox;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import de.jwic.base.ControlContainer;
import de.jwic.base.IControlContainer;
import de.jwic.events.ValueChangedEvent;
import de.jwic.events.ValueChangedListener;

/**
 *  Created on 26.09.2006
 * @author Mark Frewin
 */
public class EditableListBoxControl extends ControlContainer {
	private List<String> elements;
	private List<ValueChangedListener> listeners;
	/**
	 * 
	 * @param container
	 */
	public EditableListBoxControl(IControlContainer container) {
		this(container, null);
	}

	/**
	 * 
	 * @param container
	 * @param name
	 */
	public EditableListBoxControl(IControlContainer container, String name) {
		super(container, name);
		initialise();
	}
	
	/**
	 * Initialise the control.
	 *
	 */
	private void initialise() {
		elements = new ArrayList<String>();
		listeners = new ArrayList<ValueChangedListener>();
	}
	
	/**
	 * 
	 * @param title
	 */
	public void addElement(String title){
		elements.add(title);
	}	

	/**
	 * 
	 * @return
	 */
	public List<String> getElements() {
		return elements;
	}
	
	public void addValueChangedListener(ValueChangedListener listener) {
		listeners.add(listener);
	}
	
	public void removeValueChangedListener(ValueChangedListener listener) {
		listeners.remove(listener);
	}
	
	private void notifyListeners() {
		ValueChangedEvent event = new ValueChangedEvent(this);		
		for (Iterator<ValueChangedListener> it = listeners.iterator(); it.hasNext();){
			ValueChangedListener vclistener = it.next();
			vclistener.valueChanged(event);
		}		
	}

	public void actionPerformed(String actionId, String parameter) {		
		//super.actionPerformed(actionId, parameter);
		if ("valuechanged".equals(actionId)) {
			notifyListeners();
		}
		
	}
}
