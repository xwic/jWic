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
public class RadioGroup extends AbstractSelectListControl {

	private static final long serialVersionUID = 2L;

	private int columns = 0;
	protected String cssClass = "ui-widget j-radiogroup";
	protected boolean fillWidth = false;
	protected int width = 0;	// 0 = not set
	protected int height = 0;	// 0 = not set
	
	/**
	 * @param container
	 */
	public RadioGroup(IControlContainer container) {
		super(container, null);
	}
	/**
	 * @param container
	 * @param name
	 */
	public RadioGroup(IControlContainer container, String name) {
		super(container, name);
	}
	
	/**
	 * Invoked when the value has been changed.
	 *
	 */
	public void actionValuechanged() {
		// nothing to do, as the valueChanged is triggered directly by the field.
		// but we must leave this method as it is invoked by the onChanged event.
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
		requireRedraw();
	}

	/**
	 * @return the fillWidth
	 */
	public boolean isFillWidth() {
		return fillWidth;
	}

	/**
	 * @param fillWidth the fillWidth to set
	 */
	public void setFillWidth(boolean fillWidth) {
		this.fillWidth = fillWidth;
		requireRedraw();
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
		requireRedraw();
	}

	/**
	 * @return the height
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * @param height the height to set
	 */
	public void setHeight(int height) {
		this.height = height;
		requireRedraw();
	}

}
