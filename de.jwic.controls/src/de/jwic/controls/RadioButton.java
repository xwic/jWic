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

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import de.jwic.base.Control;
import de.jwic.base.Field;
import de.jwic.base.IControlContainer;
import de.jwic.base.IHaveEnabled;
import de.jwic.base.IncludeJsOption;
import de.jwic.events.ValueChangedListener;

/**
 * Represents a single RadioButton element on a page. RadioButtons can be linked to each other
 * so that only one RadioButton can be selected. This allows individual placement of RadioButton
 * controls on a page.
 * 
 * @author lippisch
 */
public class RadioButton extends Control implements IHaveEnabled {
	private static final long serialVersionUID = 1L;
	private String cssClass = "j-radiobutton";
	
	private Field field = null;
	private RadioButton master = null;
	private List<RadioButton> allRadios = null;
	
	private boolean changeNotification = true;
	private boolean enabled = true; 
	private String title = "";
	
	private Serializable userObject = null;
	
	/**
	 * Create a new RadioButton instance as a master. Other RadioButtons of the same
	 * group should add this instance in the constructor.
	 * @param container
	 * @param name
	 */
	public RadioButton(IControlContainer container, String name) {
		super(container, name);
		field = new Field(this, "radio");
		master = this;
		allRadios = new ArrayList<RadioButton>();
		allRadios.add(this);
	}

	/**
	 * @param container
	 * @param name
	 */
	public RadioButton(IControlContainer container, String name, RadioButton linkedButton) {
		super(container, name);
		if (linkedButton == null) {
			throw new NullPointerException("The linkedButton must not be null.");
		}
		master = linkedButton.getMasterRadioButton();
		master.allRadios.add(this);	// register self
	}

	/**
	 * Returns the master radio button or self if the instance is the master.
	 * @return
	 */
	protected RadioButton getMasterRadioButton() {
		return master;
	}

	/**
	 * Flag all linked RadioButton controls to be redrawn.
	 */
	protected void redrawAll() {
		for (RadioButton rb : allRadios) {
			rb.requireRedraw();
		}
	}
	
	/**
	 * Returns the field shared by the radio buttons from that group.
	 * @return
	 */
	public Field getFormField() {
		if (field != null) {
			return field;
		} else if (master != null && master != this) {
			return master.field;
		}
		throw new IllegalStateException("No field in master element!?");
	}

	/**
	 * Returns true if this RadioButton is selected.
	 * @return
	 */
	public boolean isSelected() {
		Field fld = getFormField();
		return fld.getValue().equals(getControlID());
	}
	
	/**
	 * Select this element. 
	 * @param selected
	 */
	public void setSelected(boolean selected) {
		Field fld = getFormField();
		if (selected) {
			fld.setValue(getControlID());
		} else {
			if (isSelected()) {
				fld.setValue(""); // clear
			}
		}
		requireRedraw();
		getMasterRadioButton().redrawAll();
	}
	
	/**
	 * Returns the RadioButton control that is selected. 
	 * @return
	 */
	public RadioButton getSelectedRadioButton() {
		Field fld = getFormField();
		if (fld.getValue() != null && !fld.getValue().isEmpty()) {
			RadioButton rb = (RadioButton)getSessionContext().getControlById(fld.getValue());
			return rb;
		}
		return null;
	}
	
	/* (non-Javadoc)
	 * @see de.jwic.base.Control#actionPerformed(java.lang.String, java.lang.String)
	 */
	@Override
	public void actionPerformed(String actionId, String parameter) {
		if ("valuechanged".equals(actionId)) {
			// handle clicked event?
		}
	}
	
	/**
	 * @return the changeNotification
	 */
	public boolean isChangeNotification() {
		return changeNotification;
	}

	/**
	 * @param changeNotification the changeNotification to set
	 */
	public void setChangeNotification(boolean changeNotification) {
		this.changeNotification = changeNotification;
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
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the userObject
	 */
	public Serializable getUserObject() {
		return userObject;
	}

	/**
	 * @param userObject the userObject to set
	 */
	public void setUserObject(Serializable userObject) {
		this.userObject = userObject;
	}

	/**
	 * @return the cssClass
	 */
	@IncludeJsOption
	public String getCssClass() {
		return cssClass;
	}

	/**
	 * @param cssClass the cssClass to set
	 */
	public void setCssClass(String cssClass) {
		this.cssClass = cssClass;
	}

	/**
	 * Add a ValueChangedListener to the underlying field. This needs to be done only once on the
	 * master RadioButton, which is the first one created. If this is called not on the master, an
	 * exception is raised. This should prevent the error that a listener is registered multiple
	 * times.
	 * @param listener
	 * @see de.jwic.base.Field#addValueChangedListener(de.jwic.events.ValueChangedListener)
	 */
	public void addValueChangedListener(ValueChangedListener listener) {
		if (field != null) {
			field.addValueChangedListener(listener);
		} else {
			throw new IllegalArgumentException("You must register the listener at the master (first) RadioButton.");
		}
	}

	/**
	 * @param listener
	 * @see de.jwic.base.Field#removeValueChangedListener(de.jwic.events.ValueChangedListener)
	 */
	public void removeValueChangedListener(ValueChangedListener listener) {
		if (field != null) {
			field.removeValueChangedListener(listener);
		}
	}
}
