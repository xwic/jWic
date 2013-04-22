/*
 * Created on 30.11.2004
 */
package de.jwic.controls;

import de.jwic.base.IControlContainer;


/**
 * CheckboxControl creates one or more checkboxes that can be multi selected.
 * 
 * $Id: CheckboxControl.java,v 1.9 2008/09/17 15:19:46 lordsam Exp $
 * @version $Revision: 1.9 $
 * @author JBornemann
 */
public class CheckBoxGroup extends AbstractSelectListControl {
	
	private static final long serialVersionUID = 2L;
	
	private int columns = 0;

	protected String cssClass = "ui-widget j-checkboxgroup";
	protected boolean fillWidth = false;
	protected int width = 0;	// 0 = not set
	protected int height = 0;	// 0 = not set
	
	/**
	 * @param container
	 */
	public CheckBoxGroup(IControlContainer container) {
		super(container, null);
		init();
	}
	/**
	 * @param container
	 * @param name
	 */
	public CheckBoxGroup(IControlContainer container, String name) {
		super(container, name);
		init();
	}
	/* (non-Javadoc)
	 * @see de.jwic.controls.ListControl#init()
	 */
	private void init() {
		
		setMultiSelect(true);
		
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
	}
	

}
