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

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import de.jwic.base.Control;
import de.jwic.base.Field;
import de.jwic.base.IControlContainer;
import de.jwic.base.IHaveEnabled;
import de.jwic.base.JavaScriptSupport;
import de.jwic.base.Page;
import de.jwic.base.SessionContext;
import de.jwic.data.IBaseLabelProvider;
import de.jwic.data.IContentProvider;
import de.jwic.events.ElementSelectedEvent;
import de.jwic.events.ElementSelectedListener;
import de.jwic.events.ValueChangedEvent;
import de.jwic.events.ValueChangedListener;
import de.jwic.util.StringTool;

/**
 * Superclass for controls who display data elements in a list form.
 * 
 * @author lippisch
 */
@JavaScriptSupport
public class AbstractListControl<A> extends Control implements IHaveEnabled {

	private static final long serialVersionUID = 5L;

	protected boolean enabled = true;
	protected boolean changeNotification = true;
	protected boolean multiSelect = false;
	protected Field valueField;
	
	protected List<ElementSelectedListener> listeners = new ArrayList<ElementSelectedListener>();
	
	protected IContentProvider<A> contentProvider = null;
	protected IBaseLabelProvider<A> baseLabelProvider = null;
	
	/**
	 * @param container
	 * @param name
	 */
	public AbstractListControl(IControlContainer container, String name) {
		super(container, name);
		valueField = new Field(this, "values");
		valueField.addValueChangedListener(new ValueChangedListener() {
			private static final long serialVersionUID = 1L;
			public void valueChanged(ValueChangedEvent event) {
				sendElementSelectedEvent();	// transform the event.
			}
		});

	}
	
	/**
	 * Send the element selected event to the registerd listeners.
	 */
	protected void sendElementSelectedEvent() {
		
		if (listeners != null) {
			ElementSelectedEvent e = new ElementSelectedEvent(this, getSelectedKey());
			for (Iterator<ElementSelectedListener> it = listeners.iterator(); it.hasNext(); ) {
				ElementSelectedListener osl = it.next();
				osl.elementSelected(e);
			}
		}

	}

	/* (non-Javadoc)
	 * @see de.jwic.base.Control#actionPerformed(java.lang.String, java.lang.String)
	 */
	@Override
	public void actionPerformed(String actionId, String parameter) {
		
		if ("elementSelected".equals(actionId)) {
			fireElementSelectedEvent(new ElementSelectedEvent(this, getSelectedElement()));
		} else {
			super.actionPerformed(actionId, parameter);
		}
	}
	
	/**
	 * Add ElementSelectedListener.
	 * @param listener
	 */
	public void addElementSelectedListener(ElementSelectedListener listener) {
		listeners.add(listener);
	}

	/**
	 * Remove ElementSelectedListener.
	 * @param listener
	 */
	public void removeElementSelectedListener(ElementSelectedListener listener) {
		listeners.remove(listener);
	}

	/**
	 * Fire the element selected event.
	 * @param event
	 */
	protected void fireElementSelectedEvent(ElementSelectedEvent event) {
		ElementSelectedListener[] ls = new ElementSelectedListener[listeners.size()];
		ls = listeners.toArray(ls);
		for (ElementSelectedListener listener : ls) {
			listener.elementSelected(event);
		}
	}
	
	/**
	 * Returns the key of the element selected.
	 * @return
	 */
	public String getSelectedKey() {
		return StringTool.getSingleString(getSelectedKeys());
	}
	

	/**
	 * Returns the selected keys as String[]. 
	 * @return
	 */
	public String[] getSelectedKeys() {
		// the valueField may contain empty strings if a value is not selected
		// for easier processing afterwards, we filter those out
		String[] values = valueField.getValues();
		if (values != null) {
			String[] keys = new String[values.length];
			int cnt = 0;
			for (int i = 0; i < values.length; i++) {
				if (values[i] != null && !values[i].isEmpty()) {
					keys[cnt++] = values[i];
				}
			}
			if (cnt != values.length) {
				String[] tmp = new String[cnt];
				System.arraycopy(keys, 0, tmp, 0, cnt);
				return tmp;
			}
		}
		return values == null ? new String[0] : values;
	}
	
	/**
	 * Returns the first selected element.
	 * @return
	 */
	public A getSelectedElement() {
		String[] keys = getSelectedKeys();
		if (keys != null && keys.length > 0 && keys[0] != null && !keys[0].isEmpty()) {
			return contentProvider.getObjectFromKey(keys[0]);
		}
		return null;
	}
	
	/**
	 * Set the selected element. 
	 * @param elm
	 */
	public void setSelectedElement(A elm) {
		if (elm != null) {
			setSelectedKey(contentProvider.getUniqueKey(elm));
		} else {
			setSelectedKey(null);
		}
		
		fireElementSelectedEvent(new ElementSelectedEvent(this, getSelectedElement()));
	}
	
	/**
	 * Set the selected key.
	 * @param key
	 */
	public void setSelectedKey(String key) {
		requireRedraw();
		if(key != null && key.length() > 0){
			String[] keys = key.split("\\;");
			if(keys.length > 1){
				valueField.setValues(keys);
				return;
			}
		}
		valueField.setValue(key);
	}
	
	/**
	 * @return the contentProvider
	 */
	public IContentProvider<A> getContentProvider() {
		return contentProvider;
	}

	/**
	 * @param contentProvider the contentProvider to set
	 */
	public void setContentProvider(IContentProvider<A> contentProvider) {
		this.contentProvider = contentProvider;
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

	/**
	 * @return the multiSelect
	 */
	public boolean isMultiSelect() {
		return multiSelect;
	}

	/**
	 * @param multiSelect the multiSelect to set
	 */
	public void setMultiSelect(boolean multiSelect) {
		if (this.multiSelect != multiSelect) {
			this.multiSelect = multiSelect;
			requireRedraw();
		}
	}

	/**
	 * @return the baseLabelProvider
	 */
	public IBaseLabelProvider<A> getBaseLabelProvider() {
		return baseLabelProvider;
	}

	/**
	 * @param baseLabelProvider the baseLabelProvider to set
	 */
	public void setBaseLabelProvider(IBaseLabelProvider<A> baseLabelProvider) {
		this.baseLabelProvider = baseLabelProvider;
	}

	/* (non-Javadoc)
	 * @see de.jwic.base.IHaveEnabled#isEnabled()
	 */
	@Override
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
		return valueField.getId();
	}
	
}
