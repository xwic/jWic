package de.jwic.ecolib.controls.stackpanel;

import de.jwic.base.ControlContainer;
import de.jwic.base.IControlContainer;

public class Panel extends ControlContainer {
	private static final long serialVersionUID = 1L;

	private String title;

	protected Panel(IControlContainer container, String name) {
		super(container, name);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param container
	 */
	protected Panel(IControlContainer container) {
		super(container);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 * @param title
	 *            to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * 
	 * @return the title of the this panel
	 */
	public String getTitle() {
		return title;
	}

}
