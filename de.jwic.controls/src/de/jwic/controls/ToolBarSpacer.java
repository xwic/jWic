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

import de.jwic.base.Control;
import de.jwic.base.IControlContainer;

/**
 * A simple spacer within a toolbar.
 * @author lippisch
 */
public class ToolBarSpacer extends Control {
	private static final long serialVersionUID = 1L;

	/**
	 * @param container
	 */
	public ToolBarSpacer(IControlContainer container) {
		super(container, null);
	}

	
	/**
	 * @param container
	 * @param name
	 */
	public ToolBarSpacer(IControlContainer container, String name) {
		super(container, name);
	}

	
	
}
