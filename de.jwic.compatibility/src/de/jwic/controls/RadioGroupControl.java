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
 * de.jwic.controls.RadioGroupControl
 * Created on 03.11.2004
 * $Id: RadioGroupControl.java,v 1.2 2010/07/15 11:59:10 lordsam Exp $
 */
package de.jwic.controls;

import de.jwic.base.IControlContainer;

/**
 * Displays a list of radio buttons. 
 * @author Florian Lippisch
 * @version $Revision: 1.2 $
 */
public class RadioGroupControl extends ListControl {

	private static final long serialVersionUID = 2L;

	private int columns = 0;
	
	/**
	 * @param container
	 */
	public RadioGroupControl(IControlContainer container) {
		super(container, null);
		setCssClass("j-radiobutton");
	}
	/**
	 * @param container
	 * @param name
	 */
	public RadioGroupControl(IControlContainer container, String name) {
		super(container, name);
		setCssClass("j-radiobutton");
	}
	
	/**
	 * Used by the velocity template to determine if a new line is required.
	 * @param count
	 * @return
	 */
	public boolean isDoBreak(int count) {
		return columns != 0 && count % columns == 0;
	}
	
	/**
	 * @return Returns the columns.
	 */
	public int getColumns() {
		return columns;
	}
	/**
	 * @param columns The columns to set.
	 */
	public void setColumns(int columns) {
		this.columns = columns;
		requireRedraw();
	}
}
