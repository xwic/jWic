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
package de.jwic.json;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.util.HashSet;
import java.util.Set;

import org.json.JSONException;
import org.json.JSONWriter;

import de.jwic.base.ImageRef;

/**
 * Serialize the property values to json. Properties that contain NULL values are
 * not serialized.
 * @author lippisch
 */
public class BeanToJsonSerializer implements IObjectToJsonSerializer {
	private static final long serialVersionUID = 1L;
	private transient Class<?> lastClass = null;
	private transient PropertyDescriptor[] readers = null;
	
	private Set<String> ignoreProperties = new HashSet<String>();
	
	/**
	 * Constructor.
	 */
	public BeanToJsonSerializer() {
		ignoreProperties.add("class");
	}
	
	/* (non-Javadoc)
	 * @see de.jwic.controls.combo.IObjectToJsonSerializer#serialize(java.lang.Object, org.json.JSONWriter)
	 */
	public void serialize(Object object, JSONWriter writer) throws JSONException {

		if (object != null) {
			
			if (lastClass == null || lastClass != object.getClass()) {
				try {
					evaluateReaders(object.getClass());
				} catch (IntrospectionException e) {
					throw new RuntimeException("Error inspecting bean " + object.getClass().getName());
				}
			}
			
			writer.object();
			
			for (PropertyDescriptor pd : readers) {
				try {
					Object value = pd.getReadMethod().invoke(object);
					if (value != null) {
						writer.key(pd.getName());
						if (value instanceof ImageRef) {
							ImageRef img = (ImageRef)value;
							writer.object();
							writer.key("path");
							writer.value(img.getPath());
							writer.key("imgTag");
							writer.value(img.toImgTag());
							writer.key("width");
							writer.value(img.getWidth());
							writer.key("height");
							writer.value(img.getHeight());
							writer.endObject();
						} else {
							writer.value(value);
						}
					}
				} catch (Exception ite) {
					writer.key(pd.getName()).value("!Error! " + ite.toString());
				}
			}
			
			addExtraElements(object, writer);
			
			
			writer.endObject();
			
		}

	}

	
	
	/**
	 * This method may be overriden to add custom elements such as 'title' if
	 * they do not exist naturally on the object.
	 * @param object
	 * @param writer
	 */
	protected void addExtraElements(Object object, JSONWriter writer) throws JSONException {
		
	}

	/**
	 * @param class1
	 * @throws IntrospectionException 
	 */
	private void evaluateReaders(Class<? extends Object> beanClass) throws IntrospectionException {

		BeanInfo beanInfo = Introspector.getBeanInfo(beanClass);
		PropertyDescriptor[] pd = beanInfo.getPropertyDescriptors();
		PropertyDescriptor[] tmp = new PropertyDescriptor[pd.length];
		int mIdx = 0;
		for (int i = 0; i < pd.length; i++) {
			if (!ignoreProperties.contains(pd[i].getName()) && pd[i].getReadMethod() != null) { 
				tmp[mIdx++] = pd[i];
			}
		}
		readers = new PropertyDescriptor[mIdx];
		System.arraycopy(tmp, 0, readers, 0, mIdx);
		
	}

	/**
	 * @return the ignoreProperties
	 */
	public Set<String> getIgnoreProperties() {
		return ignoreProperties;
	}

	/**
	 * @param ignoreProperties the ignoreProperties to set
	 */
	public void setIgnoreProperties(Set<String> ignoreProperties) {
		this.ignoreProperties = ignoreProperties;
	}

}
