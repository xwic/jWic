/*
 * Copyright 2005-2013 jWic group (http://www.jwic.de)
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
 * de.jwic.controls.InputBoxControl
 */
package de.jwic.controls;

import de.jwic.base.IControlContainer;

/**
 * InputBox proxy for compatibility with older jWic 4.x versions.
 * @author lippisch
 * @deprecated Use InputBox instead
 */
public class InputBoxControl extends InputBox {

	/**
	 * @param container
	 */
	public InputBoxControl(IControlContainer container) {
		super(container);
		setTemplateName(InputBox.class.getName());
	}

	/**
	 * @param container
	 * @param name
	 */
	public InputBoxControl(IControlContainer container, String name) {
		super(container, name);
		setTemplateName(InputBox.class.getName());
	}

}
