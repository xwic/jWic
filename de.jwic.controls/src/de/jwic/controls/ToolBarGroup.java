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
import de.jwic.controls.actions.IAction;
import de.jwic.controls.actions.ActionAnchorLink;
import de.jwic.controls.actions.ActionButton;


/**
 * Groups controls displayed in a toolbar.
 * @author lippisch
 */
public class ToolBarGroup extends ControlContainer {
	private static final long serialVersionUID = 1L;
	private String cssClass = "j-toolbar-grp";
	
	/**
	 * @param container
	 */
	public ToolBarGroup(IControlContainer container) {
		super(container);
	}

	/**
	 * @param container
	 * @param name
	 */
	public ToolBarGroup(IControlContainer container, String name) {
		super(container, name);
	}
	
	/**
	 * Add a spacer into this group.
	 * @return
	 */
	public ToolBarSpacer addSpacer() {
		ToolBarSpacer spacer = new ToolBarSpacer(this);
		return spacer;
	}
	
	/**
	 * Add an action wrapped in a button.
	 * @param action
	 * @return
	 */
	public Button addAction(IAction action) {
		Button bt = new ActionButton(this, action);
		bt.setCssClass("j-button-h j-btn-small");
		return bt;
	}

	/**
	 * Add and action wrapped in a AnchorLinkControl
	 * @param action
	 * @return
	 */
	public AnchorLink addActionAnchorLink(IAction action){
		AnchorLink anchorLinkControl = new ActionAnchorLink(this, action);
		anchorLinkControl.setCssClass("j-toolbar-anchor");
		return anchorLinkControl;
	}
	
	/**
	 * Create a button and initialize it with the default styles.
	 * @return
	 */
	public Button addButton() {
		
		Button button = new Button(this);
		button.setCssClass("j-button-h j-btn-small");
		return button;
		
	}
	
	/**
	 * Add a label to the toolbar.
	 * @param label
	 * @return
	 */
	public Label addLabel(String label) {
		Label lbl= new Label(this);
		lbl.setText(label);
		lbl.setCssClass("j-toolbar-label");
		return lbl;
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
