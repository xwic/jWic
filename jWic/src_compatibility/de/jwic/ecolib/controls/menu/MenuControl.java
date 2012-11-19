package de.jwic.ecolib.controls.menu;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

import de.jwic.base.Control;
import de.jwic.base.IControlContainer;

/**
 * A Menu control to display menu items in lists with mouseover functionality.
 * The menu can be organised horizontally or vertically.
 *  
 * @author Mark Frewin
 *
 */
public class MenuControl extends Control {
	private static final long serialVersionUID = 2077302542938926941L;
	
	public final static int HORIZONTAL_ORIENTATION = 0;
	public final static int VERTICAL_ORIENTATION = 1;
	
	private int orientation = 0;
	private Menu menu = null;
	private String width = "";
	private int iconWidth = 16;
	private int iconHeight = 16;
	
	private Map<Long, MenuItem> menuItems = new HashMap<Long, MenuItem>();
	private Map<Long, MenuItemListener> miListeners = new HashMap<Long, MenuItemListener>();
	

	/**
	 * 
	 * @param container
	 * @param name
	 */
	public MenuControl(IControlContainer container, String name) {
		super(container, name);
	}

	/**
	 * Getter for orientation.
	 * @return
	 */
	public int getOrientation() {
		return orientation;
	}

	/**
	 * Set horizontal or vertical orientation of control.
	 * 
	 * @param orientation
	 */
	public void setOrientation(int orientation) {
		this.orientation = orientation;
	}

	/**
	 * Getter for Menu.
	 * @return
	 */
	public Menu getMenu() {
		return menu;
	}

	/**
	 * Set top level menu for control.
	 * 
	 * @param menu
	 */
	public void setMenu(Menu menu) {
		this.menu = menu;
	}

	/*
	 *  (non-Javadoc)
	 * @see de.jwic.base.IControl#actionPerformed(java.lang.String, java.lang.String)
	 */
	public void actionPerformed(String actionId, String parameter) {
		if (actionId.equals("click")) {						

			// find menu item
			Menu currMenu = menu;
			MenuItem item = null;
			StringTokenizer stk = new StringTokenizer(parameter, "/");
			while (stk.hasMoreTokens() && currMenu != null) {
				String id = stk.nextToken();
				if (id.length() != 0) {
					int idx = Integer.parseInt(id);
					item = currMenu.getMenuItems().get(idx);
					currMenu = item.getMenu();
				}
			}
			if (!stk.hasMoreTokens() && item != null) {
				item.run();
			}
			
		}
	}
	
	/**
	 * Getter for Width.
	 * @return
	 */
	public String getWidth() {
		return width;
	}

	/**
	 * Set width of control. 
	 * This is equivalent to the width property of a html table.
	 * The value needs to contain a unit value.
	 * eg. "170px" or "100%"
	 * 
	 * @param width
	 */
	public void setWidth(String width) {
		this.width = width;
	}
	
	/**
	 * Allows menu items related to this control to be registered.
	 *  
	 * @param item
	 */
	void registerMenuItem(MenuItem item){
		Long uniqueId = new Long(item.getUniqueId());
		
		MenuItemListener listener = new MenuItemListener();
		miListeners.put(uniqueId, listener);
		
		item.addPropertyChangeListener(listener);
		menuItems.put(uniqueId, item);
		
	}
	
	/**
	 * Allows menu items related to this control to be unregistered.
	 * 
	 * @param item
	 */
	void unregisterMenuItem(MenuItem item){
		Long uniqueId = new Long(item.getUniqueId());
		
		item.removePropertyChangeListener(miListeners.get(uniqueId));
		miListeners.remove(uniqueId);
		menuItems.remove(uniqueId);			
	}

	/**
	 * Setter for icon width.
	 * 
	 * @param width
	 */
	public void setIconWidth(int width){
		iconWidth = width;
	}
	
	/**
	 * Getter for icon width.
	 * 
	 * @return
	 */
	public int getIconWidth(){
		return iconWidth;
	}
	
	/**
	 * Setter for icon height.
	 * 
	 * @param height
	 */
	public void setIconHeight(int height){
		iconHeight = height;
	}
	
	/**
	 * Getter for icon height.
	 * 
	 * @return
	 */
	public int getIconHeight(){
		return iconHeight;
	}
	
	/**
	 * Allows a redraw of the control when a property of a related
	 * MenuItem has changed.
	 * eg. When visible or enabled property changes,
	 * then the control is redrawn to reflect this.
	 *
	 */
	class MenuItemListener implements PropertyChangeListener, Serializable {

		public void propertyChange(PropertyChangeEvent evt) {
			MenuControl.this.setRequireRedraw(true);
		}		
	}
	
}
