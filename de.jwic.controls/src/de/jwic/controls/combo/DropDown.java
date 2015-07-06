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
package de.jwic.controls.combo;

import java.util.ArrayList;
import java.util.List;

import de.jwic.base.IControlContainer;
import de.jwic.base.ImageRef;
import de.jwic.data.ISelectElement;
import de.jwic.data.SelectElement;
import de.jwic.data.SelectElementBaseLabelProvider;
import de.jwic.data.SelectElementContentProvider;

/**
 * Basic Drop-Down control. The content may either be added directly, to be
 * stored in the control or it is provided by a custom IContentProvider.
 * 
 * @author lippisch
 */
public class DropDown extends Combo<ISelectElement> {
	private static final long serialVersionUID = 1L;
	protected List<ISelectElement> elements = null; // default to null
	
	/**
	 * @param container
	 * @param name
	 */
	public DropDown(IControlContainer container, String name) {
		super(container, name);
		baseLabelProvider = new SelectElementBaseLabelProvider();
		comboBehavior.setOpenContentOnTextboxFocus(true);
		comboBehavior.setSelectTextOnFocus(true);
		comboBehavior.setTextEditable(true);
		
	}
	
	/**
	 * Add an element.
	 * @param element
	 */
	public void addElement(ISelectElement element) {
		if (elements == null) {
			elements = new ArrayList<ISelectElement>();
			setContentProvider(new SelectElementContentProvider(elements));
		}
		elements.add(element);
	}

	/**
	 * Add an element. The key will automatically be assigned.
	 * @param title
	 */
	public ISelectElement addElement(String title) {
		SelectElement elm = new SelectElement(title);
		addElement(elm);
		return elm;
	}
	
	/**
	 * Add the element with a custom key.
	 * @param title
	 * @param key
	 */
	public ISelectElement addElement(String title, String key) {
		SelectElement elm = new SelectElement(title, key);
		addElement(elm);
		return elm;
	}

	/**
	 * Add the element with a custom key.
	 * @param title
	 * @param key
	 */
	public ISelectElement addElement(String title, String key, ImageRef image) {
		SelectElement elm = new SelectElement(title, key, image); 
		addElement(elm);
		return elm;
	}

	/**
	 * Select the element with the specified key. Works only with elements that 
	 * do have a key.
	 * @param key
	 */
	public void selectedByKey(String key) {
		if (key != null && elements != null) {
			for (ISelectElement se : elements) {
				if (key.equals(se.getKey())) {
					setSelectedElement(se);
					return;
				}
			}
		}
		setSelectedKey(null);
	}
	
	/**
	 * 
	 */
	public void clear() {
		if(elements != null){
			elements.clear();
		}
		setSelectedElement(null);
		requireRedraw();
	}
}
