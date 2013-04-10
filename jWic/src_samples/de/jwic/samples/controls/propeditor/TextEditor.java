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
 * de.jwic.samples.controls.propeditor.TextEditor
 * Created on 16.09.2008
 * $Id: TextEditor.java,v 1.1 2008/09/16 21:55:44 lordsam Exp $
 */
package de.jwic.samples.controls.propeditor;

import java.util.ArrayList;
import java.util.List;

import de.jwic.base.Control;
import de.jwic.base.Field;
import de.jwic.base.IControlContainer;
import de.jwic.events.ValueChangedEvent;
import de.jwic.events.ValueChangedListener;

/**
 * Text editor for property values.
 * @author Florian Lippisch
 */
public class TextEditor extends Control {

	private Field field;
	private List<ValueChangedListener> listeners = new ArrayList<ValueChangedListener>();
	/**
	 * @param container
	 * @param name
	 */
	public TextEditor(IControlContainer container, String name) {
		super(container, name);
		field = new Field(this, "text");
	}
	
	/**
	 * Add a value changed listener.
	 * @param listener
	 */
	public void addValueChangedListener(ValueChangedListener listener) {
		listeners.add(listener);
	}
	
	/**
	 * Remove a value changed listener.
	 * @param listener
	 */
	public void removeValueChangedListener(ValueChangedListener listener) {
		listeners.remove(listener);
	}
	
	/**
	 * Apply the value.
	 */
	public void actionApply() {
		ValueChangedEvent event = new ValueChangedEvent(this);
		
		Object[] list = listeners.toArray();
		for (int i = 0; i < list.length; i++) {
			ValueChangedListener listener = (ValueChangedListener)list[i];
			listener.valueChanged(event);
		}
	}
	
	/**
	 * Returns the text.
	 * @return
	 */
	public String getText() {
		return field.getValue();
	}
	
	/**
	 * Set the text.
	 * @param text
	 */
	public void setText(String text) {
		field.setValue(text);
		requireRedraw();
	}
	
	

}
