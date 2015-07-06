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
package de.jwic.async;

import java.io.Serializable;

import de.jwic.events.ValueChangedEvent;
import de.jwic.events.ValueChangedListener;

/**
 * Adapter implementation of the IProgressMonitor interface.
 * @see IProgressMonitor
 * @author Florian Lippisch
 * @version $Revision: 1.3 $
 */
public class ProgressMonitor implements IProgressMonitor, Serializable {

	private static final long serialVersionUID = 1L;
	private int maximum = 10;
	private int minimum = 0;
	private int value = 0;
	private String infoText = null;
	
	private ValueChangedListener[] listeners = null;
	
	/**
	 * Add a ValueChangedListener to this monitor.
	 * @param listener
	 */
	public synchronized void addValueChangedListener(ValueChangedListener listener) {
		if (listener == null) {
			throw new NullPointerException("Listener must not be null"); 
		}
		if (listeners == null) {
			listeners = new ValueChangedListener[] { listener };
		} else {
			ValueChangedListener[] tmp = new ValueChangedListener[listeners.length + 1];
			System.arraycopy(listeners, 0, tmp, 0, listeners.length);
			tmp[listeners.length] = listener;
			listeners = tmp;
		}
	}
	
	/**
	 * Removes the specified listener from the monitor.
	 * @param listener
	 */
	public synchronized void removeValueChangedListener(ValueChangedListener listener) {
		if (listeners != null && listeners.length > 0) {
			ValueChangedListener[] tmp = new ValueChangedListener[listeners.length - 1];
			int idx = 0;
			for (int i = 0; i < listeners.length; i++) {
				if (listeners[i] != listener) {
					if (idx == tmp.length) {
						// the listener was not registerd
						return; // early exit
					}
					tmp[idx++] = listeners[i];
				}
			}
			listeners = tmp;
		}
	}
	
	/**
	 * Fires the value changed event.
	 * @param event
	 */
	protected void fireValueChangedEvent(ValueChangedEvent event) {
		if (listeners != null) {
			for (int i = 0; i < listeners.length; i++) { 
				listeners[i].valueChanged(event);
			}
		}
	}
	
	/* (non-Javadoc)
	 * @see de.jwic.controls.IProgressMonitor#getMaximum()
	 */
	public int getMaximum() {
		return maximum;
	}

	/* (non-Javadoc)
	 * @see de.jwic.controls.IProgressMonitor#getMinium()
	 */
	public int getMinimum() {
		return minimum;
	}

	/* (non-Javadoc)
	 * @see de.jwic.controls.IProgressMonitor#getValue()
	 */
	public int getValue() {
		return value;
	}

	/**
	 * @param maximum The maximum to set.
	 */
	public void setMaximum(int maximum) {
		this.maximum = maximum;
	}

	/**
	 * @param minimum The minimum to set.
	 */
	public void setMinimum(int minimum) {
		this.minimum = minimum;
	}

	/**
	 * @param value The value to set.
	 */
	public void setValue(int value) {
		this.value = value;
		fireValueChangedEvent(new ValueChangedEvent(this));
	}
	
	/**
	 * Increases the value by the argument value.
	 * @param worked int
	 */
	public void worked(int worked) {
		value += worked;
		fireValueChangedEvent(new ValueChangedEvent(this));
	}

	/**
	 * @return the infoText
	 */
	public String getInfoText() {
		return infoText;
	}

	/**
	 * @param infoText the infoText to set
	 */
	public void setInfoText(String infoText) {
		this.infoText = infoText;
	}

}
