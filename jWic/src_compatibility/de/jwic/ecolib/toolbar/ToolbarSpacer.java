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
 * de.jwic.ecolib.toolbar.ToolbarSpacer
 * Created on 13.06.2011
 * $Id: ToolbarSpacer.java,v 1.1 2011/06/13 20:29:12 lordsam Exp $
 */
package de.jwic.ecolib.toolbar;

import de.jwic.base.Control;
import de.jwic.base.IControlContainer;

/**
 * A simple spacer within a toolbar.
 * @author lippisch
 */
public class ToolbarSpacer extends Control {
	private static final long serialVersionUID = 1L;

	/**
	 * @param container
	 */
	public ToolbarSpacer(IControlContainer container) {
		super(container, null);
	}

	
	/**
	 * @param container
	 * @param name
	 */
	public ToolbarSpacer(IControlContainer container, String name) {
		super(container, name);
	}

	
	
}
