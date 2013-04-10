/*
 * de.jwic.controls.TabControl
 * $Id: TabControl.java,v 1.2 2006/01/19 14:57:23 lordsam Exp $
 */
package de.jwic.controls;

import de.jwic.base.ControlContainer;
import de.jwic.base.IControlContainer;

/**
 * A Tab element is displayed in a TabStrip by modules.
 *
 * @author Florian Lippisch
 */
public class TabControl extends ControlContainer {

	private static final long serialVersionUID = 1L;
	private String strTitle = null;
	
	/**
	 * @param container
	 */
	public TabControl(IControlContainer container) {
		super(container);
	}
	/**
	 * @param container
	 * @param name
	 */
	public TabControl(IControlContainer container, String name) {
		super(container, name);
	}
	
	/**
	 * Returns the title of the tab.
	 * @return java.lang.String
	 */
	public String getTitle() {
		return strTitle;
	}
	/**
	 * Sets the title of the tab.
	 * @param newTitle java.lang.String
	 */
	public void setTitle(String newTitle) {
		strTitle = newTitle;
		getContainer().setRequireRedraw(true);
	}
	
	/* (non-Javadoc)
	 * @see de.jwic.base.Control#setVisible(boolean)
	 */
	public void setVisible(boolean newVisible) {
		super.setVisible(newVisible);
		// must notify the TabStripControl...
		TabStripControl tsc = (TabStripControl)getContainer();
		tsc.setRequireRedraw(true);
	}
}
