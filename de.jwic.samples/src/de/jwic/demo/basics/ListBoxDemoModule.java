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
/**
 * 
 */
package de.jwic.demo.basics;

import de.jwic.base.IControlContainer;
import de.jwic.demo.DemoModule;

/**
 * @author lippisch
 *
 */
public class ListBoxDemoModule extends DemoModule {

	/**
	 * 
	 */
	public ListBoxDemoModule() {
		setTitle("ListBox");
		setDescription("A list of elements represented by the HTML SELECT element.");
		
	}
	
	/* (non-Javadoc)
	 * @see de.jwic.demo.DemoModule#createModule(de.jwic.base.IControlContainer)
	 */
	@Override
	public void createModule(IControlContainer container) {

		
		new ListBoxDemo(container);
		

	}

}
