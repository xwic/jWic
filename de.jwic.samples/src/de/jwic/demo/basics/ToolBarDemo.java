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
package de.jwic.demo.basics;

import de.jwic.base.ControlContainer;
import de.jwic.base.IControlContainer;
import de.jwic.base.ImageRef;
import de.jwic.controls.Button;
import de.jwic.controls.ToolBar;
import de.jwic.controls.ToolBarGroup;
import de.jwic.controls.ToolBarSpacer;
import de.jwic.controls.actions.Action;
import de.jwic.controls.combo.DropDown;
import de.jwic.controls.menu.Menu;
import de.jwic.demo.ImageLibrary;

/**
 * Demo for the Toolbar Control.
 * 
 * @author lippisch
 */
public class ToolBarDemo extends ControlContainer {

	private ToolBar toolBar;

	/**
	 * @param container
	 * @param name
	 */
	public ToolBarDemo(IControlContainer container, String name) {
		super(container, name);

		toolBar = new ToolBar(this, "toolbar1");
		ToolBarGroup tbGroup1 = toolBar.addGroup();
		Button btSave = tbGroup1.addButton();
		btSave.setIconEnabled(ImageLibrary.IMG_DISK);
		btSave.setTitle("Save");

		Button btClose = tbGroup1.addButton();
		btClose.setIconEnabled(ImageLibrary.IMG_CROSS);
		btClose.setTitle("Close");
		
		tbGroup1.addSpacer();
		Button btCreate = tbGroup1.addButton();
		btCreate.setIconEnabled(ImageLibrary.IMG_ADD);
		btCreate.setTitle("Create Task");
		btCreate.setTooltip("Adding a <b>task</b> to a project defines its work-breakdown-structure and allows you to assign resources.");

		Button btHours = tbGroup1.addButton();
		btHours.setIconEnabled(ImageLibrary.IMG_LIST_VIEW);
		btHours.setTitle("View Hours");

		// Add action without image
		Button btHelp = tbGroup1.addButton();
		btHelp.setTitle("Help");

		
		ToolBar tb2 = new ToolBar(this, "toolbar2");

		createControls(tb2.addGroup());
		createControls2(tb2.addRightGroup());
		createControls3(tb2.addGroup());

	}

	private void createControls2(ToolBarGroup grp) {

		new ToolBarSpacer(grp);

		Action a = new Action(){
		@Override
		public void run() {
			System.out.println("I am a cool action!");
			
		}};
		a.setTitle("Link With Action");
		a.setVisible(true);
		a.setEnabled(true);
		
		grp.addActionAnchorLink(a);

		DropDown dd = new DropDown(grp, "list");
		
		dd.addElement("A brown bag", "brown");
		dd.addElement("A green tea", "green");
		dd.addElement("Something else", "o");

		dd.setEmptyInfoText("Choose some..");

	}

	/**
	 * @param grp1
	 */
	private void createControls(ToolBarGroup grp) {

		grp.addLabel("I am a label:");

		Button button = grp.addButton();
		button.setTitle("Button 1");
		button.setConfirmMsg("I am a button");
		button.setIconEnabled(new ImageRef("icons/flag_blue.png"));

		button = grp.addButton();
		button.setTitle("");
		button.setIconEnabled(new ImageRef("icons/flag_red.png"));

		button = grp.addButton();
		button.setTitle("I am disabled");
		button.setIconEnabled(new ImageRef("icons/flag_green.png"));
		button.setEnabled(false);
		
		
		

	}

	private void createControls3(ToolBarGroup grp) {

		grp.addSpacer();

		Button btMenu = grp.addButton();
		btMenu.setTitle("Open Report");
		
		Menu menu = new Menu(toolBar);
		menu.setWidth(180);
		menu.addMenuItem("Order Report");
		menu.addMenuItem("Pending Approvals");
		menu.addMenuItem("And Something Else");
		
		btMenu.setMenu(menu);
		
	}

}
