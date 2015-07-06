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

import java.util.HashMap;
import java.util.Map;

import de.jwic.base.IControlContainer;
import de.jwic.controls.ListBoxControl;

/**
 * Small helper for selection of enums.
 * @author dotto
 *
 * @param <T>
 */
public class EnumListBoxControl<T extends Enum<?>> extends ListBoxControl {

	private Map<String, T> enumMap = new HashMap<String, T>();
	public EnumListBoxControl(IControlContainer container, String name, Class<T> enumClass) {
		super(container, name);
		setTemplateName(ListBoxControl.class.getName());
		for (T enumObj : enumClass.getEnumConstants()) {
			addElement(enumObj.name());
			enumMap.put(enumObj.name(), enumObj);
		}
	}
	
	/**
	 * 
	 * @return
	 */
	public T getEnumObject(){
		if(getSelectedKey() == null || getSelectedKey().length() == 0)
			return null;
		return enumMap.get(getSelectedKey());
	}

}
