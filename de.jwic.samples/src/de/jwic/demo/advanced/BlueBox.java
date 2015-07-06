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

import de.jwic.base.Control;
import de.jwic.base.IControlContainer;

public class BlueBox extends Control{
	private int number;
	private String message;
	private String tooltipProvider;
	/**
	 * @param container
	 * @param name
	 */
	public BlueBox(IControlContainer container, String name) {
		super(container, name);
		this.setTemplateName("de.jwic.demo.advanced.BlueBox");
	}
	/**
	 * @return the number
	 */
	public int getNumber() {
		return number;
	}
	/**
	 * @param number the number to set
	 */
	public void setNumber(int number) {
		this.number = number;
	}
	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}
	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	/**
	 * @return the tooltipProvider
	 */
	public String getTooltipProvider() {
		return tooltipProvider;
	}
	/**
	 * @param tooltipProvider the tooltipProvider to set
	 */
	public void setTooltipProvider(String tooltipProvider) {
		this.tooltipProvider = tooltipProvider;
	}
	
	
	
	
	
}
