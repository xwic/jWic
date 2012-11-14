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
 * de.jwic.sandbox.TestApplication
 * Created on 20.06.2007
 * $Id: TestApplication.java,v 1.1 2007/06/20 07:25:20 lordsam Exp $
 */
package de.jwic.samples.layers;

import de.jwic.base.Application;
import de.jwic.base.Control;
import de.jwic.base.IControlContainer;

/**
 *
 * @author Florian Lippisch
 */
public class TestApplication extends Application {

	/* (non-Javadoc)
	 * @see de.jwic.base.Application#createRootControl(de.jwic.base.IControlContainer)
	 */
	public Control createRootControl(IControlContainer container) {

		TestPage page = new TestPage(container, null);
		
		return page;
	}

}
