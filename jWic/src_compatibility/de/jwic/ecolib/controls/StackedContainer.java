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
 * de.jwic.ecolib.controls.StackedContainer
 * Created on 10.01.2006
 * $Id: StackedContainer.java,v 1.7 2008/09/18 18:19:34 lordsam Exp $
 */
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
	
}
