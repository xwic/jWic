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
/*
 * de.jwic.samples.controls.SlowRenderingControl 
 */
package de.jwic.demo.framework;

import de.jwic.base.Control;
import de.jwic.base.IControlContainer;

/**
 * This control is simmulating a control that takes a few seconds to render
 * because it loads data from some slow data source.
 * 
 * @author lippisch
 */
public class SlowRenderingControl extends Control {

	/**
	 * @param container
	 * @param name
	 */
	public SlowRenderingControl(IControlContainer container, String name) {
		super(container, name);
	}

	
	public String getTheAnswer() {
		try {
			Thread.sleep(3000);// sleep 3 seconds
		} catch (InterruptedException e) {
			e.printStackTrace();
		} 
		
		return "42";
	}
	
	
}
