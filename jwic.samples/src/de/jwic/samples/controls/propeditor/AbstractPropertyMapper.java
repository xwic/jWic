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
 * de.jwic.samples.controls.propeditor.AbstractPropertyMapper
 * Created on 16.09.2008
 * $Id: AbstractPropertyMapper.java,v 1.2 2008/09/17 15:19:58 lordsam Exp $
 */
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
		}
		return String.valueOf(value);
	}

}
