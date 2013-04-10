/**
 * 
 */
package de.jwic.controls.basics;

import de.jwic.base.ControlContainer;

/**
 * A tab within a TabStrip acts as a container for other controls.
 * 
 * @author lippisch
 */
public class Tab extends ControlContainer {

	private String title;
	
	/**
	 * @param container
	 * @param name
	 * @param tabStrip
	 * @param title
	 */
	public Tab(TabStrip tabStrip, String name, String title) {
		super(tabStrip, name);
		this.title = title;
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
		this.title = title;
	}
	
}
