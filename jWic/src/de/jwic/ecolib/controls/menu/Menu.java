package de.jwic.ecolib.controls.menu;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Base structure used for use in conjuntion with the MenuControl element.
 * 
 * 
 * @see de.jwic.ecolib.controls.menu.MenuControl
 * 
 * @author Mark Frewin
 *
 */
public class Menu implements Serializable {
	private static final long serialVersionUID = -8515447745128263587L;
	
	public final static int STYLE_BAR = 0; //menu bar
	public final static int STYLE_DROP_DOWN = 1; //cascading menu
	public final static int STYLE_POP_UP = 2;  //context menu
	
	private int style = 0;	
	private List<MenuItem> menuItems = new ArrayList<MenuItem>();
	
	private int idCount = 0; //used to identify relative position of MenuItems
	private String idBase = ""; //relavtive position of menu in MenuControl
	private MenuControl parent = null;

	/**
	 * 
	 * @param parent
	 * @param style
	 */
	public Menu(MenuControl parent, int style) {
		if (style == STYLE_BAR)
			parent.setMenu(this);
		this.parent = parent;
		this.style = style;
	}

	/**
	 * Add direct child MenuItems of this menu.
	 * @param item
	 */
	public void addMenuItem(MenuItem item) {
		parent.registerMenuItem(item);
		menuItems.add(item);
		item.setId(idBase + "/" + idCount++);
	}
	
	/**
	 * Remove a direct child MenuItem of this menu.
	 * @param item
	 */
	public void removeMenuItem(MenuItem item) {
		parent.unregisterMenuItem(item);
		menuItems.remove(item);
		updateIds(idBase);
		parent.setRequireRedraw(true);
	}
	
	/**
	 * Getter for MenuItems list.
	 * @return
	 */
	public List<MenuItem> getMenuItems(){
		return menuItems;
	}

	/**
	 * Getter for style.
	 * @return
	 */
	public int getStyle() {
		return style;
	}

	/**
	 * Update the ids of all sub-items.
	 * @param id
	 */
	void updateIds(String id) {
		
		idBase = id;
		idCount = 0;
		for (Iterator<MenuItem> it = menuItems.iterator(); it.hasNext(); ) {
			MenuItem item = it.next();
			item.setId(idBase + "/" + idCount++);
		}
		
	}
	
	/**
	 * Getter for base id.
	 * @return
	 */
	public String getBaseId() {
		return idBase;
	}
	
	/**
	 * Determines if the icons will be displayed.
	 * 
	 * @return
	 */
	public boolean isShowIcons(){
		for (Iterator<MenuItem> it = menuItems.iterator(); it.hasNext(); ) {
			MenuItem item = it.next();
			if (item.getIconEnabled() != null)
				return true;
		}
		return false;
	}
	
}
