package de.jwic.ecolib.controls.editablelistbox;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import de.jwic.base.Field;
import de.jwic.base.IControlContainer;
import de.jwic.controls.HTMLElement;
import de.jwic.events.ValueChangedEvent;
import de.jwic.events.ValueChangedListener;

/**
 *  Created on 27.09.2006
 * @author Mark Frewin
 */
public class EditableListBox2 extends HTMLElement {

	private Field field;
	private List<String> elements;
	private List<ValueChangedListener> listeners;
	
	public EditableListBox2(IControlContainer container, String name) {
		super(container, name);
		init();
	}
	
	private void init() {
		elements = new ArrayList<String>();
		listeners = new ArrayList<ValueChangedListener>();
		
		field = new Field(this);
		field.addValueChangedListener(new ValueChangedListener() {

			public void valueChanged(ValueChangedEvent event) {
				notifyListeners(event);
			}
			
		});
	}
	
	public void addValueChangedListener(ValueChangedListener listener) {
		listeners.add(listener);
	}
	
	public void removeValueChangedListener(ValueChangedListener listener) {
		listeners.remove(listener);
	}
	
	private void notifyListeners(ValueChangedEvent event) {		
		for (Iterator<ValueChangedListener> it = listeners.iterator(); it.hasNext();){
			ValueChangedListener vclistener = it.next();
			vclistener.valueChanged(event);
		}		
	}
	
	public void addElement(String title) {
		elements.add(title);
	}
	
	public void removeElement(String title) {
		if (elements.contains(title)) {
			elements.remove(title);
		}
	}
	
}
