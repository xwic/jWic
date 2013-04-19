/*
 * Copyright 2005 jWic Group (http://www.jwic.de)
 * $Id: SubFrameControl.java,v 1.1 2006/01/16 08:31:12 lordsam Exp $
 */
package de.jwic.controls;

import de.jwic.base.ControlContainer;
import de.jwic.base.IControlContainer;
import de.jwic.base.IOuterLayout;

/**
 * Displays controls in a window style subframe.
 * @author Florian Lippisch
 */
public class SubFrameControl extends ControlContainer implements IOuterLayout {

	private static final long serialVersionUID = 1L;

	private String width = "100%";
	private int height = 0;
	private String title = "";
	private boolean borderLeft = false;
	private boolean closeable = true;

	
	/**
	 * @param container
	 */
	public SubFrameControl(IControlContainer container) {
		super(container);
		init();
	}
	/**
	 * @param container
	 * @param name
	 */
	public SubFrameControl(IControlContainer container, String name) {
		super(container, name);
		init();
	}
	/**
	 * Initialize the control. 
	 */
	private void init() {
		setRendererId(DEFAULT_OUTER_RENDERER);
	}
	
	/**
	 * Returns the total height of the sub frame. 
	 * @return int - height in pixel
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * Returns the title of the sub frame.
	 * @return
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Returns the width of the sub frame. 
	 * @return
	 */
	public String getWidth() {
		return width;
	}

	/**
	 * Set the height of the frame. Set to 0 if the size should be dynamic.
	 * @param int - height in pixel
	 */
	public void setHeight(int newHeight) {
		height = newHeight;
	}

	/**
	 * Set the title of the subframe.
	 * @param string
	 */
	public void setTitle(String string) {
		title = string;
	}

	/**
	 * Set the width of the subframe.
	 * @param string
	 */
	public void setWidth(String string) {
		width = string;
	}

	/**
	 * @param event
	 */
	public void actionPerformed(String actionId, String param) {
		
		if (actionId.equals("closeframe")) {
			closeFrame();
		}
	}

	/**
	 * Close/Hide the sub frame.
	 */
	public void closeFrame() {
		setVisible(false);
	}

	/**
	 * @return
	 */
	public boolean isBorderLeft() {
		return borderLeft;
	}

	/**
	 * @param b
	 */
	public void setBorderLeft(boolean b) {
		borderLeft = b;
	}
	
	/**
	 * Returns <code>null</code> if the class has not been extended and
	 * no template name has been set.
	 * @see de.jwic.base.Control#getTemplateName()
	 */
	public String getTemplateName() {
		if (super.getTemplateName().equals(SubFrameControl.class.getName())) {
			return null;
		}
		return super.getTemplateName();
	}

	/* (non-Javadoc)
	 * @see de.jwic.base.IOuterLayout#getOuterTemplateName()
	 */
	public String getOuterTemplateName() {
		return SubFrameControl.class.getName();
	}

	/**
	 * @return Returns true if closeable.
	 */
	public boolean isCloseable() {
		return closeable;
	}
	/**
	 * @param closeable Set to true if closeable.
	 */
	public void setCloseable(boolean closeable) {
		this.closeable = closeable;
	}
}
