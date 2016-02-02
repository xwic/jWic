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
 * de.jwic.controls.InputBoxControl
 * $Id: InputBoxControl.java,v 1.10 2010/01/25 11:03:10 lordsam Exp $
 */
package de.jwic.controls;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import de.jwic.base.Control;
import de.jwic.base.Field;
import de.jwic.base.IControlContainer;
import de.jwic.base.IncludeJsOption;
import de.jwic.base.JavaScriptSupport;
import de.jwic.base.Page;
import de.jwic.base.SessionContext;
import de.jwic.events.KeyEvent;
import de.jwic.events.KeyListener;
import de.jwic.events.ValueChangedListener;

/**
 * An InputBox is a control where the user can enter data.  
 * @author Florian Lippisch
 * @version $Revision: 1.10 $
 */
@JavaScriptSupport
public class InputBox extends HTMLElement {

	/**
	 * Defines possible input types that are available in HTML5. Note that not 
	 * all browsers may support them, so always be prepared to handle regular
	 * text input.
	 */
	public enum InputType {
		COLOR,
		EMAIL,
		NUMBER,
		PASSWORD,
		SEARCH,
		TEL,
		TEXT,
		URL
	}
	
	private static final long serialVersionUID = 1L;
	private final static String ACTION_KEYPRESSED = "keyPressed";

	protected int maxLength = 0;
	protected int rows = 1;
	protected int cols = 20;
	protected boolean multiLine = false;
	protected boolean readonly = false;
	
	/** @deprecated - use the type property instead. */
	protected boolean password = false;
	
	protected boolean updateOnBlur = false;
	protected int listenKeyCode = 0;	// 0 == dont listen to any keycode
	
	protected String emptyInfoText = null;
	protected boolean flagAsError = false;
	
	protected InputType type = InputType.TEXT;
	
	protected Field field = null;
	
	private List<KeyListener> keyListeners = null;

	/**
	 * @param container
	 */
	public InputBox(IControlContainer container) {
		super(container, null);
		init();
	}
	/**
	 * @param container
	 * @param name
	 */
	public InputBox(IControlContainer container, String name) {
		super(container, name);
		init();
	}
	
	/* (non-Javadoc)
	 * @see de.jwic.base.IControl#actionPerformed(java.lang.String, java.lang.String)
	 */
	public void actionPerformed(String actionId, String parameter) {
		
		if (actionId.equals(ACTION_KEYPRESSED)) {
			// notify listeners
			if (keyListeners != null) {
				KeyEvent event = new KeyEvent(this, Integer.parseInt(parameter));
				for (Iterator<KeyListener> it = keyListeners.iterator(); it.hasNext(); ) {
					KeyListener listener = it.next();
					listener.keyPressed(event);
				}
			}
		}
	}
	
	/**
	 * Add a value changed listener to the <b>field</b> used by this
	 * input box control.
	 * @param listener
	 */
	public void addValueChangedListener(ValueChangedListener listener) {
		field.addValueChangedListener(listener);
	}
	
	/**
	 * Add a keyListener. The listener(s) are invoked when the key that is specified
	 * in the <code>listenKeyCode</code> property is pressed. If no <code>listenKeyCode</code>
	 * is specified, the listener will never be invoked.
	 * @param keyListener
	 */
	public void addKeyListener(KeyListener keyListener) {
		if (keyListeners == null) {
			keyListeners = new ArrayList<KeyListener>();
		}
		keyListeners.add(keyListener);
	}
	
	/* (non-Javadoc)
	 * @see de.jwic.base.Control#init()
	 */
	private void init() {
		setCssClass("ui-widget ui-widget-content j-inputbox");
		field = new Field(this);
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
				page.setForceFocusElement(field.getId());
				return true;
			}
		}
		return false;
	}
	
	/**
	 * The field representation used by the InputBoxControl on the HTML form.
	 * This property is used by the renderer to generate the proper HTML code.
	 * Use the <code>text</code> property to change the InputBoxControls data.
	 * @return
	 */
	public Field getField() {
		return field;
	}
	
	/**
	 * Returns the text in the textbox.
	 * @return
	 */
	public String getText() {
		return field.getValue();
	}
	
	/**
	 * Set the text of the control.
	 * @param newText
	 */
	public void setText(String newText) {
		field.setValue(newText);
		setRequireRedraw(true);
	}

	/**
	 * @return Returns the maxLength.
	 */
	public int getMaxLength() {
		return maxLength;
	}
	/**
	 * Set to 0 if the length should be unlimited.
	 * @param maxLength The maxLength to set.
	 */
	public void setMaxLength(int maxLength) {
		this.maxLength = maxLength;
		requireRedraw();
	}
	/**
	 * @return Returns the multiLine.
	 */
	public boolean isMultiLine() {
		return multiLine;
	}
	/**
	 * @param multiLine The multiLine to set.
	 */
	public void setMultiLine(boolean multiLine) {
		this.multiLine = multiLine;
		requireRedraw();
	}
	/**
	 * @return Returns the rows.
	 */
	public int getRows() {
		return rows;
	}
	/**
	 * @param rows The rows to set.
	 */
	public void setRows(int rows) {
		this.rows = rows;
		requireRedraw();
	}
    /**
     * @return Returns the cols.
     */
    public int getCols() {
        return cols;
    }
    /**
     * @param cols The cols to set.
     */
    public void setCols(int cols) {
        this.cols = cols;
        requireRedraw();
    }
	/**
	 * @return Returns true if the input field is of type "password".
	 */
	public boolean isPassword() {
		return InputType.PASSWORD.equals(type);
	}
	/**
	 * Set to true if the input field should be of type "password". This property
	 * applies only to non-multiline fields.
	 * 
	 * <p>Calling this method with a <code>true</code> argument will set the type to PASSWORD,
	 * calling it with <code>false</code> will set the type to TEXT.
	 * 
	 * @param password boolean
	 * @deprecated Use setType(..) instead. 
	 */
	public void setPassword(boolean password) {
		if (password) {
			setType(InputType.PASSWORD);
		} else {
			setType(InputType.TEXT);
		}
		requireRedraw();
	}
	
	/**
	 * Removes the specified listener from the <b>field</b> used by this input box control.
	 * @param listener
	 */
	public void removeValueChangedListener(ValueChangedListener listener) {
		field.removeValueChangedListener(listener);
	}

	/**
	 * Removes the specified listener from the <b>field</b> used by this input box control.
	 * @param listener
	 */
	public void removeKeyListener(KeyListener listener) {
		if (keyListeners != null) {
			keyListeners.remove(listener);
		}
	}
	
	/**
	 * @return Returns the listenKeyCode.
	 */
	public int getListenKeyCode() {
		return listenKeyCode;
	}
	/**
	 * @param listenKeyCode The listenKeyCode to set.
	 */
	public void setListenKeyCode(int listenKeyCode) {
		this.listenKeyCode = listenKeyCode;
	}
	/**
	 * @return Returns the readonly.
	 */
	@IncludeJsOption
	public boolean isReadonly() {
		return readonly;
	}
	/**
	 * @param readonly The readonly to set.
	 */
	public void setReadonly(boolean readonly) {
		this.readonly = readonly;
		requireRedraw();
	}
	/**
	 * @see #setUpdateOnBlur(boolean)
	 * @return the updateOnBlur
	 */
	@IncludeJsOption
	public boolean isUpdateOnBlur() {
		return updateOnBlur;
	}
	/**
	 * The control initiates a submit of the form wich updates the field value when the
	 * focus on the control is lost (OnBlur). 
	 * @param updateOnBlur the updateOnBlur to set
	 */
	public void setUpdateOnBlur(boolean updateOnBlur) {
		if (updateOnBlur != this.updateOnBlur) {
			this.updateOnBlur = updateOnBlur;
			requireRedraw();
		}
	}
	
	/**
	 * @return the emptyInfoText
	 */
	public String getEmptyInfoText() {
		return emptyInfoText;
	}
	
	/**
	 * Set a text that is displayed when the text field is empty. If empty, the
	 * css class 'empty' is added to indicate visually that the field is empty.
	 * @param emptyInfoText the emptyInfoText to set
	 */
	public void setEmptyInfoText(String emptyInfoText) {
		this.emptyInfoText = emptyInfoText;
		requireRedraw();
	}
	/**
	 * @return the flagAsError
	 */
	@IncludeJsOption
	public boolean isFlagAsError() {
		return flagAsError;
	}
	/**
	 * @param flagAsError the flagAsError to set
	 */
	public void setFlagAsError(boolean flagAsError) {
		if (this.flagAsError != flagAsError) {
			this.flagAsError = flagAsError;
			requireRedraw();
		}
	}
	
	/**
	 * @return the type
	 */
	@IncludeJsOption
	public InputType getType() {
		return type;
	}
	
	/**
	 * Set the type of input the field accepts. The value will define the 'type' attribute
	 * of the 'input' element, so that HTML5 capable browsers can provide respective input
	 * fields. A tablet for example may offer the user different keys for an email field than
	 * for a regular text field.
	 * 
	 * <p>Note that the type only applies to non-multiline fields</p>
	 * 
	 * @param type the type to set
	 */
	public void setType(InputType type) {
		this.type = type;
	}
	
}
