/*
 * de.jwic.controls.RadioGroupControl
 * Created on 03.11.2004
 * $Id: RadioGroupControl.java,v 1.2 2010/07/15 11:59:10 lordsam Exp $
 */
package de.jwic.controls;

import de.jwic.base.IControlContainer;

/**
 * Displays a list of radio buttons. 
 * @author Florian Lippisch
 * @version $Revision: 1.2 $
 */
public class RadioGroupControl extends ListControl {

	private static final long serialVersionUID = 2L;

	private int columns = 0;
	
	/**
	 * @param container
	 */
	public RadioGroupControl(IControlContainer container) {
		super(container, null);
		setCssClass("j-radiobutton");
	}
	/**
	 * @param container
	 * @param name
	 */
	public RadioGroupControl(IControlContainer container, String name) {
		super(container, name);
		setCssClass("j-radiobutton");
	}
	
	/**
	 * Used by the velocity template to determine if a new line is required.
	 * @param count
	 * @return
	 */
	public boolean isDoBreak(int count) {
		return columns != 0 && count % columns == 0;
	}
	
	/**
	 * @return Returns the columns.
	 */
	public int getColumns() {
		return columns;
	}
	/**
	 * @param columns The columns to set.
	 */
	public void setColumns(int columns) {
		this.columns = columns;
		requireRedraw();
	}
}
