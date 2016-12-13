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

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import de.jwic.base.IHaveEnabled;
import de.jwic.base.ImageRef;
import de.jwic.controls.actions.IAction;
import de.jwic.util.Util;

/**
 * @author lippisch
 *
 */
public class MenuItem implements Serializable, IHaveEnabled {

	private List<MenuSelectionListener> menuSelectionListeners = new ArrayList<MenuSelectionListener>();

	private IAction action = null;
	
	private int id = 0;
	private String title = null;
	private ImageRef iconEnabled = null;
	private ImageRef iconDisabled = null;

	private Object data = null;
	
	private boolean enabled = true;
	private boolean visible = true;
	private String tooltip = null;
	
	private int width = 150;
	
	private List<MenuItem> menuItems = null;
	private Menu menu;
	
	private String urlToOpen;
	
	private PropertyChangeListener actionListener = new ActionListener();
	
	private class ActionListener implements Serializable, PropertyChangeListener {
		public void propertyChange(PropertyChangeEvent evt) {
			copyActionProperties();
		}
	}
	
	/**
	 * 
	 */
	MenuItem(Menu menu, int id) {
		this.menu = menu;
		this.id = id;
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
	 * Invoke the clicked event.
	 */
	public void clicked() {
		MenuEvent event = new MenuEvent(this);
		MenuSelectionListener[] l = new MenuSelectionListener[menuSelectionListeners.size()];
		l = menuSelectionListeners.toArray(l);
		for (MenuSelectionListener listener : l) {
			listener.menuItemSelected(event);
		}
		
		// now notify the action
		if (action != null) {
			action.run();
		}
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
		
	/**
	 * Add a MenuItem to the menu.
	 * @return
	 */
	public MenuItem addMenuItem() {
		MenuItem item = menu.createMenuItem();
		if (menuItems == null) {
			menuItems = new ArrayList<MenuItem>();
		}
		menuItems.add(item);
		return item;
	}
	
	/**
	 * Add an item based on the specified action element. When the action is updated, the MenuItem 
	 * automatically updates itself too.
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
	 * Associate this MenuItem with the specified action.
	 * @param action
	 */
	public void setAction(IAction action) {
		if (this.action != null) {
			this.action.removePropertyChangeListener(actionListener); // remove listener from old action..
		}
		this.action = action;
		
		if (action != null) {
			
			action.addPropertyChangeListener(actionListener);
			copyActionProperties();
		}
	}
	
	private void copyActionProperties() {
		setTitle(action.getTitle());
		setIconEnabled(action.getIconEnabled());
		setIconDisabled(action.getIconDisabled());
		setEnabled(action.isEnabled());
		setVisible(action.isVisible());
		setTooltip(action.getTooltip());
	}
	
	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		if (!Util.equals(this.title, title)) {
			this.title = title;
			menu.requireRedraw();
		}
	}

	/**
	 * @return the icon
	 */
	public ImageRef getIconEnabled() {
		return iconEnabled;
	}

	/**
	 * @param icon the icon to set
	 */
	public void setIconEnabled(ImageRef icon) {
		if (!Util.equals(this.iconEnabled, icon)) {
			this.iconEnabled = icon;
			menu.requireRedraw();
		}
	}

	/**
	 * @return the iconDisabled
	 */
	public ImageRef getIconDisabled() {
		return iconDisabled;
	}

	/**
	 * @param iconDisabled the iconDisabled to set
	 */
	public void setIconDisabled(ImageRef iconDisabled) {
		if (!Util.equals(this.iconDisabled, iconDisabled)) {
			this.iconDisabled = iconDisabled;
			menu.requireRedraw();
		}
	}

	/**
	 * Returns the icon to use.
	 * @return
	 */
	public ImageRef getIcon() {
		if (!enabled && iconDisabled != null) {
			return iconDisabled;
		}
		return iconEnabled;
	}
	
	/* (non-Javadoc)
	 * @see de.jwic.base.IHaveEnabled#isEnabled()
	 */
	@Override
	public boolean isEnabled() {
		return enabled;
	}

	/* (non-Javadoc)
	 * @see de.jwic.base.IHaveEnabled#setEnabled(boolean)
	 */
	@Override
	public void setEnabled(boolean enabled) {
		if (!Util.equals(this.enabled, enabled)) {
			this.enabled = enabled;
			menu.requireRedraw();
		}
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @return the data
	 */
	public Object getData() {
		return data;
	}

	/**
	 * @param data the data to set
	 */
	public void setData(Object data) {
		this.data = data;
	}

	/**
	 * @return the menuItems
	 */
	public List<MenuItem> getMenuItems() {
		return menuItems;
	}

	/**
	 * @return the visible
	 */
	public boolean isVisible() {
		return visible;
	}

	/**
	 * @param visible the visible to set
	 */
	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	/**
	 * @return the tooltip
	 */
	public String getTooltip() {
		return tooltip;
	}

	/**
	 * @param tooltip the tooltip to set
	 */
	public void setTooltip(String tooltip) {
		this.tooltip = tooltip;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((data == null) ? 0 : data.hashCode());
		result = prime * result + (enabled ? 1231 : 1237);
		result = prime * result
				+ ((iconDisabled == null) ? 0 : iconDisabled.hashCode());
		result = prime * result
				+ ((iconEnabled == null) ? 0 : iconEnabled.hashCode());
		result = prime * result + id;
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		result = prime * result + ((tooltip == null) ? 0 : tooltip.hashCode());
		result = prime * result + (visible ? 1231 : 1237);
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
		MenuItem other = (MenuItem) obj;
		if (data == null) {
			if (other.data != null)
				return false;
		} else if (!data.equals(other.data))
			return false;
		if (enabled != other.enabled)
			return false;
		if (iconDisabled == null) {
			if (other.iconDisabled != null)
				return false;
		} else if (!iconDisabled.equals(other.iconDisabled))
			return false;
		if (iconEnabled == null) {
			if (other.iconEnabled != null)
				return false;
		} else if (!iconEnabled.equals(other.iconEnabled))
			return false;
		if (id != other.id)
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		if (tooltip == null) {
			if (other.tooltip != null)
				return false;
		} else if (!tooltip.equals(other.tooltip))
			return false;
		if (visible != other.visible)
			return false;
		return true;
	}

	/**
	 * @return the width
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * @param width the width to set
	 */
	public void setWidth(int width) {
		this.width = width;
	}

	/**
	 * @return the urlToOpen
	 */
	public String getUrlToOpen() {
		return urlToOpen;
	}

	/**
	 * Sets the URL to be opened when clicking this MenuItem.
	 * <br/>
	 * If urlToOpen is provided, then no action event will be fired when the MenuItem is clicked. Instead, 
	 * the given URL will be opened using target='_blank' 
	 * 
	 * @param urlToOpen the urlToOpen to set
	 */
	public void setUrlToOpen(String urlToOpen) {
		this.urlToOpen = urlToOpen;
	}
}
