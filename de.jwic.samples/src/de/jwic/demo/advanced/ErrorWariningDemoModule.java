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
package de.jwic.demo.advanced;

import de.jwic.base.IControlContainer;
import de.jwic.controls.ErrorWarning;
import de.jwic.demo.DemoModule;

public class ErrorWariningDemoModule extends DemoModule {
	
	public ErrorWariningDemoModule() {
		this.setTitle("Error Warning Demo");
		this.setDescription("The Error Warning Control. Since we all have errors, we should also have an fancy error warning control to show them");
		this.setGroup("Advanced");
	}

	@Override
	public void createModule(IControlContainer container) {
		
		new ErrorWarningDemo(container);
		
	}

}
