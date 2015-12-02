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
package de.jwic.ecolib.controls;

import de.jwic.base.IControlContainer;

/**
 * A ControlContainer that displays only one control at a time.
 * 
 * @author Florian Lippisch
 * @version $Revision: 1.7 $
 */
public class StackedContainer extends de.jwic.controls.StackedContainer {

	/**
	 * @param container
	 * @param name
	 */
	public StackedContainer(IControlContainer container, String name) {
		super(container, name);
		setTemplateName(de.jwic.controls.StackedContainer.class.getName());
	}

	/**
	 * @param container
	 */
	public StackedContainer(IControlContainer container) {
		super(container);
		setTemplateName(de.jwic.controls.StackedContainer.class.getName());
	}

	/**
	 * @param widthHint
	 * @deprecated use setWidth(int)
	 */
	public void setWidthHint(String widthHint) {
		if (widthHint == null || widthHint.isEmpty() || widthHint.equals("100%")) {
			setWidth(0);
		} else {
			if (widthHint.endsWith("%")) {
				throw new IllegalArgumentException("% values other than 100% is no longer supported");
			}
			if (widthHint.endsWith("px")) {
				widthHint = widthHint.substring(0, widthHint.length() - 2);
			}
			setWidth(Integer.parseInt(widthHint));
		}
	}

	/**
	 * @param heightHint
	 * @deprecated use setHeight(int)
	 */
	public void setHeightHint(String heightHint) {
		if (heightHint == null || heightHint.isEmpty() || heightHint.equals("100%")) {
			setWidth(0);
		} else {
			if (heightHint.endsWith("%")) {
				throw new IllegalArgumentException("% values other than 100% is no longer supported");
			}
			if (heightHint.endsWith("px")) {
				heightHint = heightHint.substring(0, heightHint.length() - 2);
			}
			setHeight(Integer.parseInt(heightHint));
		}
	}
	
	
	
}
