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
import de.jwic.controls.AnchorLink;
import de.jwic.controls.Button;
import de.jwic.controls.menu.Menu;
import de.jwic.controls.menu.MenuEvent;
import de.jwic.controls.menu.MenuItem;
import de.jwic.controls.menu.MenuSelectionListener;
import de.jwic.demo.ImageLibrary;

/**
 * 
 * Demonstrates the usage of the LabelControl.
 * 
 * @author Florian Lippisch
 * @version $Revision: 1.2 $
 */
public class MenuDemo extends ControlContainer {

	
	private MenuSelectionListener msListener = new MenuSelectionListener() {
		
		@Override
		public void menuItemSelected(MenuEvent event) {
			getSessionContext().notifyMessage("Selected Menu Item: " + event.getMenuItem().getTitle());
		}
	};
	
	public MenuDemo(IControlContainer container) {
		super(container);

		
		Menu menu = new Menu(this, "plainMenu");
		addDemoMenu(menu);
		
		menu.setHidden(true); // do not display by default
		menu.addMenuSelectionListener(msListener);
		
		
		Button btMenu = new Button(this, "buttonMenu");
		btMenu.setTitle("Click Me");

		Menu subMenu = new Menu(this, "subMenu");
		subMenu.addMenuSelectionListener(msListener);
		addDemoMenu(subMenu);
		btMenu.setMenu(subMenu);
		
		// And as AnchorLink
		Menu menu3 = new Menu(this, "menu3");
		addDemoMenu(menu3);
		menu3.addMenuSelectionListener(msListener);
		
		AnchorLink link = new AnchorLink(this, "link");
		link.setTitle("Click me");
		link.setMenu(menu3);
		
		
	}

	/**
	 * @param menu
	 */
	private void addDemoMenu(Menu menu) {

		menu.addMenuItem("New...", ImageLibrary.IMG_ADD);
		menu.addMenuItem("Open...");
		
		MenuItem importMenu = menu.addMenuItem("Import");
		importMenu.addMenuItem("From Database");
		importMenu.addMenuItem("From Disk")
			.setEnabled(false);
		
		menu.addMenuItem("Save", ImageLibrary.IMG_DISK);

		menu.addMenuItem("Open URL", null, "http://www.google.com");
	}
	
}
