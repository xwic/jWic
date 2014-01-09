/*
 * de.jwic.controls.TabStripControl
 * $Id: TabStripControl.java,v 1.7 2008/09/17 15:19:45 lordsam Exp $
 */
package de.jwic.controls;

import java.util.ArrayList;
import java.util.List;

import de.jwic.base.Control;
import de.jwic.base.ControlContainer;
import de.jwic.base.IControlContainer;
import de.jwic.base.JWicException;
/**
 * A TabStrip acts like the dividers in a notebook or the labels on a group of file folders. 
 * By using a TabStrip control, you can define multiple pages for the same area of a panel.
 *
 * @author Florian Lippisch
 */
public class TabStripControl extends ControlContainer {

	private static final long serialVersionUID = 1L;
	
	public static final String LOCATION_TOP = "top";
	public static final String LOCATION_BOTTOM = "bottom";
	public static final String LOCATION_LEFT = "left";
	public static final String LOCATION_RIGHT = "right";
	
	public final static String ACTION_OPENTAB = "opentab";
	private String strActiveTabName = null;
	private String location = LOCATION_TOP;
	
	private List<Control> tabs = new ArrayList<Control>();
	
	private String cssClass = null;
	
	
	/**
	 * @param container
	 */
	public TabStripControl(IControlContainer container) {
		this(container, null);
	}
	/**
	 * @param container
	 * @param name
	 */
	public TabStripControl(IControlContainer container, String name) {
		super(container, name);
		cssClass = "default_tabstrip";
	}

	/* (non-Javadoc)
	 * @see de.jwic.base.ControlContainer#registerControl(de.jwic.base.Control, java.lang.String)
	 */
	public void registerControl(Control control, String name)
			throws JWicException {
		if (!(control instanceof TabControl)) {
			throw new IllegalArgumentException("Only TabControls may be added to a TabStripControl.");
		}
		super.registerControl(control, name);
		tabs.add(control);
		
	}
	
	/* (non-Javadoc)
	 * @see de.jwic.base.ControlContainer#unregisterControl(de.jwic.base.Control)
	 */
	public void unregisterControl(Control control) {
		if(control != null) {
			tabs.remove(control);
			if (control.getName().equals(getActiveTabName())) {
				if (tabs.size() > 0) {
					setActiveTabName(tabs.get(0).getName());
				} else {
					setActiveTabName(null);
				}
			}
		}
		super.unregisterControl(control);
	}
	
	/**
	 * Add a new tab with a default name.
	 * @param titleID java.lang.String
	 * @param name java.lang.String
	 */
	public TabControl addTab(String titleID) {
		return addTab(titleID, null);
	}

	/**
	 * Add a new tab with the specified title and name.
	 * @param titleID java.lang.String
	 * @param name java.lang.String
	 */
	public TabControl addTab(String titleID, String name) {
		TabControl tc = new TabControl(this, name);
		tc.setTitle(titleID);
	
		if (strActiveTabName == null)
			setActiveTabName(tc.getName());
		
		return tc;
	}
	
	/* (non-Javadoc)
	 * @see de.jwic.base.ControlContainer#actionPerformed(java.lang.String, java.lang.String)
	 */
	public void actionPerformed(String actionId, String parameter) {
		
		if (actionId.equals(ACTION_OPENTAB)) {
			setActiveTabName(parameter);
		}
		
	}
	/**
	 * Returns the name of the active tab.
	 * @return java.lang.String
	 */
	public String getActiveTabName() {
		return strActiveTabName;
	}
	/**
	 * Creation date: (03.02.2003 12:26:12)
	 * @param newActiveTabName java.lang.String
	 */
	public void setActiveTabName(java.lang.String newActiveTabName) {
		strActiveTabName = newActiveTabName;
		setRequireRedraw(true);
	}
	/**
	 * Returns the list of tabs.
	 * @return List of TabControl objects.
	 */
	public List<Control> getTabs() {
		return tabs;
	}

	/* (non-Javadoc)
	 * @see de.jwic.base.ControlContainer#isRenderingRelevant(de.jwic.base.Control)
	 */
	public boolean isRenderingRelevant(Control childControl) {
		return childControl.getName().equals(strActiveTabName);
	}
	
	/**
	 * @return the location of the tabs
	 */
	public String getLocation() {
		return location;
	}
	
	/**
	 * Set the location of the tabs
	 * @param newLocation
	 */
	public void setLocation(String newLocation) {
		if (! this.location.equals(newLocation)) {
			setRequireRedraw(true);
		}
		
		this.location = newLocation;
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
