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
package de.jwic.controls.menu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.jwic.base.Control;
import de.jwic.base.IControlContainer;
import de.jwic.base.ImageRef;
import de.jwic.base.JavaScriptSupport;
import de.jwic.controls.actions.IAction;

/**
 * Definition of a menu that can be used stand alone or in combination with many controls.
 * 
 * @author lippisch
 */
@JavaScriptSupport
public class Menu extends Control {

	private List<MenuSelectionListener> menuSelectionListeners = new ArrayList<MenuSelectionListener>();
	
	private int nextId = 0;
	private boolean hidden = true;
	
	private int width;
	
	private Map<Integer, MenuItem> allItems = new HashMap<Integer, MenuItem>();
	private List<MenuItem> menuItems = new ArrayList<MenuItem>();
	
	/**
	 * Constructor. 
	 */
	public Menu(IControlContainer container) {
		super (container, null);
	}

	/**
	 * Constructor. 
	 */
	public Menu(IControlContainer container, String name) {
		super (container, name);
	}

	/**
	 * Add a MenuSelectionListener.
	 * @param listener
	 */
	public void addMenuSelectionListener(MenuSelectionListener listener) {
		menuSelectionListeners.add(listener);
	}
	
	/**
	 * Remove the MenuSelectionListener.
	 * @param listener
	 */
	public void removeMenuSelectionListener(MenuSelectionListener listener) {
		menuSelectionListeners.remove(listener);
	}
	
	/**
	 * Handle click action.
	 * @param menuId
	 */
	public void actionClick(String menuId) {
		if (menuId != null && !menuId.isEmpty()) {
			Integer id = Integer.parseInt(menuId);
			MenuItem item = allItems.get(id);
			if (item != null) {
				MenuEvent event = new MenuEvent(item);
				// first notify the Menu attached listener(s)
				
				MenuSelectionListener[] l = new MenuSelectionListener[menuSelectionListeners.size()];
				l = menuSelectionListeners.toArray(l);
				for (MenuSelectionListener listener : l) {
					listener.menuItemSelected(event);
				}
				// then notify the MenuItem registered events
				item.clicked(); // first notify the item
			}
		}
	}

	/**
	 * Create a new MenuItem and register it in the all-items table.
	 * @return
	 */
	protected MenuItem createMenuItem() {
		MenuItem itm = new MenuItem(this, nextId++);
		allItems.put(itm.getId(), itm);
		return itm;
	}
	
	/**
	 * Add a MenuItem to the menu.
	 * @return
	 */
	public MenuItem addMenuItem() {
		MenuItem item = createMenuItem();
		menuItems.add(item);
		return item;
	}
	
	/**
	 * Add an item with the title as specified.
	 * @param title
	 * @return
	 */
	public MenuItem addMenuItem(IAction action) {
		MenuItem item = addMenuItem();
		item.setAction(action);
		return item;
	}

	/**
	 * Add an item with the title as specified.
	 * @param title
	 * @return
	 */
	public MenuItem addMenuItem(String title) {
		MenuItem item = addMenuItem();
		item.setTitle(title);
		return item;
	}
	
	/**
	 * Add an item which will open an URL using target='_blank'.
	 * <br/>
	 * Note: if the urlToOpen is provided, no action event will be fired when the item is clicked. 
	 * 
	 * @param title
	 * @param icon
	 * @param urlToOpen
	 * @return
	 */
	public MenuItem addMenuItem(String title, ImageRef icon, String urlToOpen) {
		MenuItem item = addMenuItem(title, icon);
		item.setUrlToOpen(urlToOpen);
		return item;
	}

	/**
	 * Add an item with the title as specified.
	 * @param title
	 * @return
	 */
	public MenuItem addMenuItem(String title, ImageRef icon) {
		MenuItem item = addMenuItem();
		item.setTitle(title);
		item.setIconEnabled(icon);
		return item;
	}

	/**
	 * Returns true if at least one MenuItem has icons. This is used
	 * during rendering to identify if a spacer has to be put in front of the
	 * menu items that do not have an icon.
	 * @return
	 */
	public boolean hasIcons() {
		if (menuItems != null) {
			for (MenuItem item : menuItems) {
				if (item.getIconEnabled() != null) {
					return true;
				}
			}
		}
		return false;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((menuItems == null) ? 0 : menuItems.hashCode());
		result = prime * result + nextId;
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Menu other = (Menu) obj;
		if (menuItems == null) {
			if (other.menuItems != null)
				return false;
		} else if (!menuItems.equals(other.menuItems))
			return false;
		if (nextId != other.nextId)
			return false;
		return true;
	}

	/**
	 * @return the hidden
	 */
	public boolean isHidden() {
		return hidden;
	}

	/**
	 * @param hidden the hidden to set
	 */
	public void setHidden(boolean hidden) {
		this.hidden = hidden;
	}

	/**
	 * @return the menuItems
	 */
	public List<MenuItem> getMenuItems() {
		return menuItems;
	}

	/**
	 * @return the width
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * Set the width of the menu displaying the child items.
	 * @param width the width to set
	 */
	public void setWidth(int width) {
		this.width = width;
	}
	
	
	
}
