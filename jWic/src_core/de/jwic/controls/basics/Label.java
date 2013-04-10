/*
 * Copyright 2005 jWic group (http://www.jwic.de)
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
 * de.jwic.controls.LabelControl
 * $Id: LabelControl.java,v 1.5 2010/01/10 19:54:49 lordsam Exp $
 */package de.jwic.controls.basics;

import java.util.HashMap;
import java.util.Map;

import de.jwic.base.IControlContainer;
import de.jwic.controls.HTMLElement;

/**
 * Represents a label that displays just text. A style can be specified
 * to format the label.
 * @author Florian Lippisch
 */
public class Label extends HTMLElement {
	
	private static final long serialVersionUID = 1L;

	private String strText = "";
	private Map<String, String> styles = new HashMap<String, String>();

	/**
	 * @param container
	 */
	public Label(IControlContainer container) {
		super(container, null);
	}
	/**
	 * @param container
	 * @param name
	 */
	public Label(IControlContainer container, String name) {
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
