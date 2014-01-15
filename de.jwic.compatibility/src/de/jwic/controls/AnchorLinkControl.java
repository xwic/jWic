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
 */

package de.jwic.controls;

import de.jwic.base.IControlContainer;

/**
 * Proxy to keep compatibility with old object name.  
 * @author lippisch
 * @deprecated Use AnchorLink instead
 */
public class AnchorLinkControl extends AnchorLink {

	public AnchorLinkControl(IControlContainer container) {
		super(container);
		setTemplateName(AnchorLink.class.getName());
	}

	public AnchorLinkControl(IControlContainer container, String name) {
		super(container, name);
		setTemplateName(AnchorLink.class.getName());
	}

}
