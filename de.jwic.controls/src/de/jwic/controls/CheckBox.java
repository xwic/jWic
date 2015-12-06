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

import de.jwic.base.Control;
import de.jwic.base.Field;
import de.jwic.base.IControlContainer;
import de.jwic.base.IHaveEnabled;
import de.jwic.base.IncludeJsOption;
import de.jwic.events.ValueChangedListener;

/**
 * Displays a single checkbox with an optional label.
 * @author Florian Lippisch
 */
public class CheckBox extends Control implements IHaveEnabled {

	private static final long serialVersionUID = -6365924495011308643L;

	protected final static String SELECTED_VALUE = "1";
	
	protected Field value;
	protected String label = null;
	protected String infoText = null;
	protected boolean enabled = true;
	protected String cssClass = "j-checkbox ui-widget";
	
	protected boolean changeNotification = false;
	
	/**
	 * @param container
	 * @param name
	 */
	public CheckBox(IControlContainer container, String name) {
		super(container, name);

		value = new Field(this, "value");
		
	}
	
	/**
	 * Invoked to refresh the control.
	 */
	public void actionClicked() {
		
	}
	
	/**
	 * Register a listener that will be notified when the checkbox was selected/changed.
	 * @param listener
	 */
	public synchronized void addValueChangedListener(ValueChangedListener listener) {
		value.addValueChangedListener(listener);
		changeNotification = true;
	}

	/**
	 * Removes a registered listener.
	 * @param listener
	 */
	public synchronized void removeValueChangedListener(ValueChangedListener listener) {
		value.removeValueChangedListener(listener);
	}


	/**
	 * Returns true if this checkbox is checked.
	 * @return
	 */
	@IncludeJsOption
	public boolean isChecked() {
		if (value == null) {	// might be null during deserialization, so we need to take care..
			return false;
		}
		return SELECTED_VALUE.equals(value.getValue());
	}
	
	/**
	 * Change the checked value.
	 * @param checked
	 */
	public void setChecked(boolean checked) {
		value.setValue(checked ? SELECTED_VALUE : "");
		requireRedraw();
	}

	/**
	 * Returns the label of the checkbox.
	 * @return the label
	 */
	public String getLabel() {
		return label;
	}

	/**
	 * Set the label of the checkbox. Will not be displayed if set to null.
	 * @param label the label to set
	 */
	public void setLabel(String label) {
		this.label = label;
		requireRedraw();
	}

	/**
	 * Returns true if the browser should immediately send a selection event
	 * to the server on change. This is set automatically to true if listeners are registered.
	 * @return the changeNotification
	 */
	@IncludeJsOption
	public boolean isChangeNotification() {
		return changeNotification;
	}

	/**
	 * Set to true if the browser should immediately send an event update
	 * to the control when the user changes the value of the item. 
	 * @param changeNotification the changeNotification to set
	 */
	public void setChangeNotification(boolean changeNotification) {
		this.changeNotification = changeNotification;
		requireRedraw();
	}

	/* (non-Javadoc)
	 * @see de.jwic.base.IHaveEnabled#isEnabled()
	 */
	@Override
	@IncludeJsOption
	public boolean isEnabled() {
		return enabled;
	}

	/* (non-Javadoc)
	 * @see de.jwic.base.IHaveEnabled#setEnabled(boolean)
	 */
	@Override
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
		requireRedraw();
	}

	/**
	 * Returns the text that should be displayed when the mouse hovers over the element.
	 * @return the infoText
	 */
	public String getInfoText() {
		return infoText;
	}

	/**
	 * Set the text that should be displayed when the mouse hovers over the element.
	 * @param infoText the infoText to set
	 */
	public void setInfoText(String infoText) {
		this.infoText = infoText;
		requireRedraw();
	}

	/**
	 * @return the cssClass
	 */
	public String getCssClass() {
		return cssClass;
	}

	/**
	 * @param cssClass the cssClass to set
	 */
	public void setCssClass(String cssClass) {
		this.cssClass = cssClass;
	}
	
}
