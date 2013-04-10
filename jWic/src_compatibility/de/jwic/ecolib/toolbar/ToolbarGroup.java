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
 * de.jwic.ecolib.toolbar.ToolbarGroup
 * Created on 13.06.2011
 * $Id: ToolbarGroup.java,v 1.3 2011/06/14 13:08:05 lordsam Exp $
 */
package de.jwic.ecolib.toolbar;

import de.jwic.base.ControlContainer;
import de.jwic.base.IControlContainer;
import de.jwic.controls.AnchorLinkControl;
import de.jwic.controls.Button;
import de.jwic.controls.LabelControl;
import de.jwic.ecolib.actions.IAction;
import de.jwic.ecolib.controls.actions.ActionAnchorLink;
import de.jwic.ecolib.controls.actions.ActionButton;

/**
 * Groups controls displayed in a toolbar.
 * @author lippisch
 */
public class ToolbarGroup extends ControlContainer {
	private static final long serialVersionUID = 1L;
	private String cssClass = "j-toolbar-grp";
	
	/**
	 * @param container
	 */
	public ToolbarGroup(IControlContainer container) {
		super(container);
	}

	/**
	 * @param container
	 * @param name
	 */
	public ToolbarGroup(IControlContainer container, String name) {
		super(container, name);
	}
	
	/**
	 * Add a spacer into this group.
	 * @return
	 */
	public ToolbarSpacer addSpacer() {
		ToolbarSpacer spacer = new ToolbarSpacer(this);
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
	public AnchorLinkControl addActionAnchorLink(IAction action){
		AnchorLinkControl anchorLinkControl = new ActionAnchorLink(this, action);
		
		return anchorLinkControl;
	}
	
	/**
	 * Create a button and initialize it with the default styles.
	 * @return
	 */
	public Button addButton() {
		
		Button button = new Button(this);
		button.setCssClass("j-button-h j-btn-small");
		button.setHeight(26);
		return button;
		
	}
	
	/**
	 * Add a label to the toolbar.
	 * @param label
	 * @return
	 */
	public LabelControl addLabel(String label) {
		LabelControl lbl= new LabelControl(this);
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
