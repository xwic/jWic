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

import java.util.HashMap;
import java.util.Map;

import de.jwic.base.IControlContainer;

/**
 * @author dotto
 * @date Jul 6, 2015
 * 
 */
public class LabelControl extends HTMLElement {
	
	private static final long serialVersionUID = 1L;

	private String strText = "";
	private Map<String, String> styles = new HashMap<String, String>();

	/**
	 * @param container
	 */
	public LabelControl(IControlContainer container) {
		super(container, null);
	}
	/**
	 * @param container
	 * @param name
	 */
	public LabelControl(IControlContainer container, String name) {
		super(container, name);
	}
	/**
	 *
	 * @return java.lang.String
	 */
	public String getText() {
		return strText;
	}
	/**
	 *
	 * @param newText java.lang.String
	 */
	public void setText(String newText) {
		strText = newText;
		setRequireRedraw(true);
	}
	
	/**
	 * Add a style.
	 * @param style
	 * @param value
	 */
	public void setStyle(String style, String value) {
		if (value == null) {
			styles.remove(style);
		} else {
			styles.put(style, value);
		}
		requireRedraw();
	}
	
	/**
	 * @return the styles
	 */
	public Map<String, String> getStyles() {
		return styles;
	}
	
	/**
	 * Compute the complete style entry.
	 * @return
	 */
	public String computeStyle() {
		StringBuilder sb = new StringBuilder();
		for (String key : styles.keySet()) {
			sb.append(key).append(": ").append(styles.get(key)).append(";");
		}
		return sb.toString();
	}
	
}
