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
package de.jwic.samples.controls.propeditor;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.Serializable;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.jwic.base.Control;
import de.jwic.base.ControlContainer;
import de.jwic.base.IControlContainer;
import de.jwic.controls.InputBox;
import de.jwic.controls.ListBoxControl;
import de.jwic.events.ElementSelectedEvent;
import de.jwic.events.ElementSelectedListener;
import de.jwic.events.SessionAdapter;
import de.jwic.events.SessionEvent;
import de.jwic.events.ValueChangedEvent;
import de.jwic.events.ValueChangedListener;

/**
 * Used to edit the properties of a bean.
 * @author Florian Lippisch
 */
public class PropertyEditorView extends ControlContainer {

	private Object bean = null;
	private transient List<String> propertyNames = null;
	private transient Map<String, PropInfo> controlMap = null;
	private String errorMessage = null;
	
	private ValueChangedListener changeListener;
	private ElementSelectedListener elementSelectedListener;
	
	private class PropInfo implements Serializable {
		PropertyDescriptor descriptor;
		String controlName;
		IPropertyMapper mapper;
	}
	
	/**
	 * @param container
	 * @param name
	 */
	public PropertyEditorView(IControlContainer container, String name) {
		super(container, name);
		
		changeListener = new ValueChangedListener() {
			public void valueChanged(ValueChangedEvent event) {
				onValueChange(event);
			}
		};
		
		elementSelectedListener = new ElementSelectedListener() {
			public void elementSelected(ElementSelectedEvent event) {
				onElementSelected(event);
			}
		};
		
		// listen to deserialization to restore data...
		getSessionContext().addSessionListener(new SessionAdapter() {
			/* (non-Javadoc)
			 * @see de.jwic.events.SessionAdapter#afterDeserialization(de.jwic.events.SessionEvent)
			 */
			@Override
			public void afterDeserialization(SessionEvent event) {
				autodetectProperties();
			}
		});
		
	}

	/**
	 * @param event
	 */
	protected void onElementSelected(ElementSelectedEvent event) {
		Control source = (Control)event.getEventSource();
		updateBySource(source);
	}

	/**
	 * @param event
	 */
	protected void onValueChange(ValueChangedEvent event) {
		
		Control source = (Control)event.getEventSource();
		updateBySource(source);
		
	}

	/**
	 * @param source
	 */
	private void updateBySource(Control source) {
		for (PropInfo pi : controlMap.values()) {
			if (pi.controlName.equals(source.getName())) {
				updateBean(pi, pi.mapper.getControlValue(source));
			}
		}
	}
	
	/**
	 * @param controlValue
	 */
	private void updateBean(PropInfo pi, Object value) {
		
		try {
			Method writeMethod = pi.descriptor.getWriteMethod();
			if (Integer.class.equals(pi.descriptor.getPropertyType()) || int.class.equals(pi.descriptor.getPropertyType())) {
				if (value instanceof String) {
					value = new Integer(Integer.parseInt((String)value));
				}
			}
			if (Double.class.equals(pi.descriptor.getPropertyType()) || double.class.equals(pi.descriptor.getPropertyType())) {
				if (value instanceof String) {
					value = new Double(Double.parseDouble((String)value));
				}
			}
			if (Character.class.equals(pi.descriptor.getPropertyType()) || char.class.equals(pi.descriptor.getPropertyType())) {
				if (value instanceof String) {
					value = new Character(((String)value).charAt(0));
				}
			}
			writeMethod.invoke(bean, value);
		} catch (Exception e) {
			setErrorMessage("Error updating value: " + e);
		}
		
	}

	/**
	 * @return the bean
	 */
	public Object getBean() {
		return bean;
	}

	/**
	 * @param bean the bean to set
	 */
	public void setBean(Object bean) {
		clearOldProperties();
		this.bean = bean;
		autodetectProperties();
	}

	/**
	 * Remove the previous created controls.
	 */
	private void clearOldProperties() {
		errorMessage = null;
		if (propertyNames != null) {
			propertyNames = null;
		}
		if (controlMap != null) {
			for(PropInfo pi : controlMap.values()) {
				removeControl(pi.controlName);
			}
			controlMap = null;
		}
		requireRedraw();
	}

	/**
	 * 
	 */
	private void autodetectProperties() {
		
		if (bean != null) {
			propertyNames = new ArrayList<String>();
			controlMap = new HashMap<String, PropInfo>();
			
			List<String> SKIP_NAMES = new ArrayList<String>();
			SKIP_NAMES.add("controls");
			SKIP_NAMES.add("sessionContext");
			SKIP_NAMES.add("requireRedraw");
			
			try {
				BeanInfo bi = Introspector.getBeanInfo(bean.getClass());
				PropertyDescriptor descriptors[] = bi.getPropertyDescriptors();
				
				for (int i = 0; i < descriptors.length; i++) {
					PropertyDescriptor prop = descriptors[i];
					
					if (SKIP_NAMES.contains(prop.getName())) {
						continue;
					}
					
					propertyNames.add(prop.getName());
					
					// create a control for each property
					PropInfo pi = new PropInfo();
					pi.descriptor = prop;
					
					Method writeMethod = prop.getWriteMethod();
					boolean readOnly = writeMethod == null || !Modifier.isPublic(writeMethod.getModifiers());
					Control control = null;
					if (!readOnly) {
						if (String.class.equals(prop.getPropertyType()) || Integer.class.equals(prop.getPropertyType()) || int.class.equals(prop.getPropertyType())
								|| Double.class.equals(prop.getPropertyType()) || double.class.equals(prop.getPropertyType()) || Character.class.equals(prop.getPropertyType()) || char.class.equals(prop.getPropertyType())) {
							TextEditor ctrl = new TextEditor(this, null);
							ctrl.addValueChangedListener(changeListener);
							control = ctrl;
							pi.mapper = new TextEditorMapper();
						} else if (Boolean.class.equals(prop.getPropertyType()) || boolean.class.equals(prop.getPropertyType())) {
							ListBoxControl lbc = new ListBoxControl(this, null);
							lbc.addElement("True", "true");
							lbc.addElement("False", "false");
							lbc.setChangeNotification(true);
							lbc.setFillWidth(false);
							lbc.setWidth(100);
							lbc.addElementSelectedListener(elementSelectedListener);
							lbc.setCssClass("small");
							control = lbc;
							pi.mapper = new BooleanMapper();
						}else if(prop.getPropertyType().isEnum()){
							EnumListBoxControl<Enum<?>> lbc = new EnumListBoxControl<Enum<?>>(this, null,(Class<Enum<?>>) prop.getPropertyType());
							lbc.setChangeNotification(true);
							lbc.setFillWidth(false);
							lbc.setWidth(100);
							lbc.addElementSelectedListener(elementSelectedListener);
							lbc.setCssClass("small");
							control = lbc;
							pi.mapper = new EnumMapper();
						}
					}
					if (control == null) {
						// property is readonly or no suiteable editor is available.
						InputBox inp = new InputBox(this);
						inp.setWidth(270);
						inp.setReadonly(true);
						inp.setCssClass("j-inputbox-intable");
						control = inp;
						pi.mapper = new InputBoxControlMapper();
					}
					
					pi.controlName = control.getName();
					controlMap.put(prop.getName(), pi);
					
				}

				Collections.sort(propertyNames);
				
				loadValues();
				
			} catch (IntrospectionException e) {
				log.error("Error reading bean properties.", e);
				setErrorMessage("Introspection error: " + e);
			}
		}
		
	}
	
	/**
	 * Action that is invoked from the refresh link on the page.
	 */
	public void actionRefresh() {
		loadValues();
	}

	/**
	 * 
	 */
	public void loadValues() {
		
		for (PropInfo pi : controlMap.values()) {
			
			try {
				Method mRead = pi.descriptor.getReadMethod();
				Object value = mRead.invoke(bean, new Object[0]);
				pi.mapper.updateControlValue(getControl(pi.controlName), value);
			} catch (Exception e) {
				setErrorMessage("Error reading property '" + pi.descriptor.getName() + "': " + e);
				log.error("Error reading property", e);
				//break;
			}
			
		}
		
	}

	/**
	 * @return the propertyNames
	 */
	public List<String> getPropertyNames() {
		return propertyNames;
	}
	
	/**
	 * Returns the name of the control that handles this property. 
	 * @param propertyName
	 * @return
	 */
	public String getControlName(String propertyName) {
		if (controlMap == null) {
			return null;
		}
		return controlMap.get(propertyName).controlName;
	}

	/**
	 * @return the errorMessage
	 */
	public String getErrorMessage() {
		return errorMessage;
	}

	/**
	 * @param errorMessage the errorMessage to set
	 */
	private void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
		requireRedraw();
	}

}
