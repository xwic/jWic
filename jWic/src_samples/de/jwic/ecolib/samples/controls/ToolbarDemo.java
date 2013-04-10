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
 * de.jwic.ecolib.samples.controls.ToolbarDemo
 * Created on 13.06.2011
 * $Id: ToolbarDemo.java,v 1.1 2011/06/13 20:29:12 lordsam Exp $
 */
package de.jwic.ecolib.samples.controls;

import de.jwic.base.ControlContainer;
import de.jwic.base.IControlContainer;
import de.jwic.base.ImageRef;
import de.jwic.controls.AnchorLinkControl;
import de.jwic.controls.Button;
import de.jwic.controls.LabelControl;
import de.jwic.controls.combo.DropDown;
import de.jwic.ecolib.actions.Action;
import de.jwic.ecolib.controls.menucontrols.ButtonMenu;
import de.jwic.ecolib.controls.menucontrols.MenuItem;
import de.jwic.ecolib.toolbar.Toolbar;
import de.jwic.ecolib.toolbar.ToolbarGroup;
import de.jwic.ecolib.toolbar.ToolbarSpacer;

/**
 * Demo for the Toolbar Control.
 * 
 * @author lippisch
 */
public class ToolbarDemo extends ControlContainer {

	/**
	 * @param container
	 * @param name
	 */
	public ToolbarDemo(IControlContainer container, String name) {
		super(container, name);

		Toolbar tb = new Toolbar(this, "toolbar");

		createControls(tb.addGroup());
		createControls2(tb.addRightGroup());
		createControls3(tb.addGroup());

	}

	private void createControls2(ToolbarGroup grp) {

		new ToolbarSpacer(grp);

		Button button = grp.addButton();
		button.setTitle("Button 1");
		button.setConfirmMsg("I am a button");
		button.setIconEnabled(new ImageRef("icons/flag_blue.png"));

		DropDown dd = new DropDown(grp, "list");
		dd.addElement("A brown bag", "brown");
		dd.addElement("A green tea", "green");
		dd.addElement("Something else", "o");

		dd.setEmptyInfoText("Choose some..");

	}

	/**
	 * @param grp1
	 */
	private void createControls(ToolbarGroup grp) {

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
		
		Action a = new Action(){

			@Override
			public void run() {
				System.out.println("I am a cool action!");
				
			}};
			
			a.setTitle("Link With Action");
			a.setVisible(true);
			a.setEnabled(true);
			
		
		AnchorLinkControl control = grp.addActionAnchorLink(a);
		
		

	}

	private void createControls3(ToolbarGroup grp) {

		grp.addSpacer();

		ButtonMenu menu = new ButtonMenu(grp, "menuBtn");
		menu.setWidth(150);

		Button btn = menu.createButton("Open");
		btn.setTitle("Open Sesame!");
		btn.setCssClass("j-button-h j-btn-small");
		btn.setHeight(26);
		
		MenuItem item = menu.getMenu().addMenuItem();
		LabelControl l = new LabelControl(menu);
		l.setText("Option 1");
		item.setContent(l);

		MenuItem item2 = item.addMenuItem();
		l = new LabelControl(menu);
		l.setText("Option 1-1");
		item2.setContent(l);

		item = menu.getMenu().addMenuItem();
		final Button b = new Button(menu);
		b.setTitle("Option 2");
		item.setContent(b);

		item2 = item.addMenuItem();
		final Button b1 = new Button(menu);
		b1.setTitle("Option 2-1");
		item2.setContent(b1);

		item = menu.getMenu().addMenuItem();
		final AnchorLinkControl link = new AnchorLinkControl(menu);
		link.setTitle("Option 3");

		item.setContent(link);

		item2 = item.addMenuItem();
		final AnchorLinkControl link1 = new AnchorLinkControl(menu);
		link1.setTitle("Option 3-1");

		item2.setContent(link1);
		
	}

}
