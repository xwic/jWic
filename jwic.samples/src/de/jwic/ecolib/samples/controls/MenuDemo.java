/*
 * Copyright 2006 jWic group (http://www.jwic.de)
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
 * de.jwic.ecolib.samples.controls.MenuDemo.java
 * Created on Feb 9, 2006
 * $Id: MenuDemo.java,v 1.9 2011/06/09 09:53:04 adrianionescu12 Exp $
 * @author jbornema
 */
package de.jwic.ecolib.samples.controls;

import de.jwic.base.ControlContainer;
import de.jwic.base.IControlContainer;
import de.jwic.base.ImageRef;
import de.jwic.ecolib.actions.Action;
import de.jwic.ecolib.controls.menu.Menu;
import de.jwic.ecolib.controls.menu.MenuControl;
import de.jwic.ecolib.controls.menu.MenuItem;

/* Created on Feb 9, 2006
 * @author jbornema
 */
public class MenuDemo extends ControlContainer {

	private static final long serialVersionUID = 3782042858778812921L;

	public MenuDemo(IControlContainer container) {
		super(container);
		
		// create sample menu
		MenuControl menuCtrl = new MenuControl(this, "horizMenu");		
		menuCtrl.setWidth("");		
		
		Menu menuBar = new Menu(menuCtrl, Menu.STYLE_BAR);		
		TestAction testAction = new TestAction();
		
		// top elements
		{
			MenuItem americaItem = new MenuItem(menuBar, MenuItem.STYLE_CASCADE);			
			americaItem.setTitle("America");			
			
			MenuItem europeItem = new MenuItem(menuBar, MenuItem.STYLE_CASCADE);
			europeItem.setTitle("Europe");					
			
			MenuItem apacItem = new MenuItem(menuBar, MenuItem.STYLE_CASCADE);
			apacItem.setTitle("Apac");
			
			Menu usMenu = new Menu(menuCtrl, Menu.STYLE_DROP_DOWN);
			americaItem.setMenu(usMenu);
			
			final Menu europeMenu = new Menu(menuCtrl, Menu.STYLE_DROP_DOWN);
			europeItem.setMenu(europeMenu);
			
			//us menu
			{
				MenuItem nyItem = new MenuItem(usMenu, testAction, "New&nbsp;York");				
				nyItem.setEnabled(false);

			}
			
			//europe menu
			{
				MenuItem enItem = new MenuItem(europeMenu, MenuItem.STYLE_CASCADE);
				enItem.setTitle("England");	
				
				//England Menu
				{
					Menu enMenu = new Menu(menuCtrl, Menu.STYLE_DROP_DOWN);
					enItem.setMenu(enMenu);
					
					new MenuItem(enMenu, testAction, "London");			
				}
				
				MenuItem deItem = new MenuItem(europeMenu, MenuItem.STYLE_CASCADE);
				deItem.setTitle("Germany");				
				
				//German menu
				{
					
					Menu deMenu = new Menu(menuCtrl, Menu.STYLE_DROP_DOWN);
					deItem.setMenu(deMenu);
					
					new MenuItem(deMenu, testAction, "Berlin");			
					
					new MenuItem(deMenu, testAction, "Hamburg");								
					
					MenuItem mucItem = new MenuItem(deMenu, MenuItem.STYLE_CASCADE);
					mucItem.setTitle("Munich");															
					
					//Muc Menu
					{
						Menu mucMenu = new Menu(menuCtrl, Menu.STYLE_DROP_DOWN);
						mucItem.setMenu(mucMenu);
						
						new MenuItem(mucMenu, testAction, "Grasbrunn");									
						
					}
					//end MucMenu					
				}
				//end German menu
				
				final MenuItem frItem = new MenuItem(europeMenu, testAction, "France");				

				Action action = new Action() {
					public void run() {
						europeMenu.removeMenuItem(frItem);
						this.setEnabled(false);
					}
				};
				action.setTitle("Remove&nbsp;France");
				new MenuItem(usMenu, action);

				
			}
			//end Europe
			
		}
		
		MenuControl vertMenu = new MenuControl(this, "vertMenu");
		vertMenu.setWidth("100px");
		vertMenu.setOrientation(MenuControl.VERTICAL_ORIENTATION);
		
		Menu vMenubar = new Menu(vertMenu, Menu.STYLE_BAR);
		
		MenuItem nswItem = new MenuItem(vMenubar, MenuItem.STYLE_CASCADE);
		nswItem.setTitle("NSW");
		nswItem.setIconEnabled(new ImageRef("img/nswicon.jpg"));

		{
			Menu nswMenu = new Menu(vertMenu, Menu.STYLE_DROP_DOWN);
			nswItem.setMenu(nswMenu);
			
			
			MenuItem sydItem = new MenuItem(nswMenu, MenuItem.STYLE_CASCADE);
			sydItem.setTitle("Sydney");
			{
				Menu sydMenu = new Menu(vertMenu, Menu.STYLE_DROP_DOWN);
				sydItem.setMenu(sydMenu);
				
				MenuItem tm = new MenuItem(sydMenu, testAction);
				tm.setTitle("Turramurra");
				
				MenuItem cm = new MenuItem(sydMenu, testAction);
				cm.setTitle("Cronulla");
				
			}
		}
		
		new MenuItem(vMenubar, MenuItem.STYLE_SEPARATOR);
		
		MenuItem qldItem = new MenuItem(vMenubar, MenuItem.STYLE_CASCADE);
		qldItem.setTitle("QLD");
		qldItem.setIconEnabled(new ImageRef("img/qldicon.jpg"));
		
		new MenuItem(vMenubar, MenuItem.STYLE_SEPARATOR);
		
		MenuItem vicItem = new MenuItem(vMenubar, MenuItem.STYLE_CASCADE);
		vicItem.setTitle("VIC");
		vicItem.setIconEnabled(new ImageRef("img/vicicon.jpg"));
		
		
		
		
	}

}
