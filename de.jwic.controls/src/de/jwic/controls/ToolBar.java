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
package de.jwic.controls;

import de.jwic.base.ControlContainer;
import de.jwic.base.IControlContainer;

/**
 *
 * @author lippisch
 */
public class ToolBar extends ControlContainer {
	private static final long serialVersionUID = 1L;
	private String cssClass = "j-toolbar ui-corner-all"; // 
	
	/**
	 * @param container
	 */
	public ToolBar(IControlContainer container) {
		super(container);
	}

	/**
	 * @param container
	 * @param name
	 */
	public ToolBar(IControlContainer container, String name) {
		super(container, name);
	}

	/**
	 * Add a group floating at the left side.
	 * @return
	 */
	public ToolBarGroup addGroup() {
		ToolBarGroup grp = new ToolBarGroup(this);
		grp.setCssClass("j-toolbar-grp-left");
		return grp;
	}


	/**
	 * Add a group floating at the left side.
	 * @return
	 */
	public ToolBarGroup addRightGroup() {
		ToolBarGroup grp = new ToolBarGroup(this);
		grp.setCssClass("j-toolbar-grp-right");
		return grp;
	}
	
	/**
	 * @return the cssClass
	 */
	public String getCssClass() {
		return cssClass;
	}

	/**
	 * @param cssClass the cssClass to set
	 */
	public void setCssClass(String cssClass) {
		this.cssClass = cssClass;
	}

}
