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

import java.io.Serializable;
import java.util.Map;

import de.jwic.base.Control;

/**
 *
 * @author Florian Lippisch
 */
public abstract class AbstractPropertyMapper implements IPropertyMapper, Serializable {
	
	/**
	 * @param value
	 * @return
	 */
	protected String toString(Object value) {
		if (value instanceof Class) {
			return ((Class<?>)value).getName();
		} else if (value instanceof Control) {
			Control ctrl = (Control)value;
			return ctrl.getClass().getName();
		} else if (value instanceof Map) {
			Map<?,?> map = (Map<?,?>)value;
			return "[map: " + map.size() + " entries]";
			
		} else if (value instanceof String[]) {
			StringBuilder sb = new StringBuilder();
			sb.append("[");
			String[] array = (String[])value;
			for (int i = 0; i < array.length; i++) {
				if (i > 0) {
					sb.append(", ");
				}
				sb.append("\"").append(array[i]).append("\"");
			}
			sb.append("]");
			return sb.toString();
		}
		return String.valueOf(value);
	}

}
