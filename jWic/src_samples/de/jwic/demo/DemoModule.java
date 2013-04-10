/**
 * 
 */
package de.jwic.demo;

import java.io.Serializable;

import de.jwic.base.IControlContainer;

/**
 * This object describes the meta data of a demo module and is able to
 * create the controls that demonstrate a feature/module.
 * 
 * @author lippisch
 */
public abstract class DemoModule implements Serializable, Comparable<DemoModule> {

	protected String title = "Untitled";
	protected String description = null;
	protected String group = "Basics";
	
	
	/**
	 * Create the demo.
	 * 
	 * @param container
	 */
	public abstract void createModule(IControlContainer container);



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



	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}



	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}



	/**
	 * @return the group
	 */
	public String getGroup() {
		return group;
	}



	/**
	 * @param group the group to set
	 */
	public void setGroup(String group) {
		this.group = group;
	}

	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(DemoModule o) {
		int n = group.compareTo(o.group);
		if (n == 0) {
			return title.compareTo(o.title);
		}
		return n;
	}
	
}
