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
 * de.jwic.base.TestApplication
 * Created on 08.11.2005
 * $Id: TestApplication.java,v 1.1 2006/01/16 08:30:40 lordsam Exp $
 */
package de.jwic.base;

/**
 * This Application is used to 'test' the lifecycle of the jWic-Runtime.
 * 
 * @author Florian Lippisch
 * @version $Revision: 1.1 $
 */
public class TestApplication extends Application {

	/* (non-Javadoc)
	 * @see de.jwic.base.Application#createRootControl(de.jwic.base.IControlContainer)
	 */
	public Control createRootControl(IControlContainer container) {
		
		Page page = new Page(container);
		
		return page;
	}

}
