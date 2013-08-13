/*
 * Copyright 2005-2007 jWic group (http://www.jwic.de)
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * de.jwic.controls.GroupControl
 * Created on Mar 30, 2007
 * $Id: GroupControl.java,v 1.5 2010/01/10 19:54:49 lordsam Exp $
 */
package de.jwic.controls;

import de.jwic.base.ControlContainer;
import de.jwic.base.IControlContainer;
import de.jwic.base.IOuterLayout;
import de.jwic.util.IHTMLElement;

/**
 * This class defines the Group Control.
 * 
 * @author Aron Cotrau
 */
public class GroupControl extends ControlContainer implements IHTMLElement, IOuterLayout {

	private static final long serialVersionUID = 2265482725775557103L;
	
	public enum GroupControlLayout {
		DEFAULT ( GroupControl.class.getName()),
		GROUP ( GroupControl.class.getName() + "_group");
		
		private String outerTemplateName;
		GroupControlLayout(String tpl) {
			outerTemplateName = tpl;
		}
	}
	
	private String outerTemplateName = GroupControl.class.getName();
	private String title = null;
	private String cssClass = null;
	private boolean enabled = true;
	private boolean fillWidth = false;
	private int width = 0; // 0 = not set
	private int height = 0; // 0 = not set
	private boolean closeable = false;

	/**
	 * @param container
	 */
	public GroupControl(IControlContainer container) {
		super(container);
		init();
	}

	/**
	 * @param container
	 * @param name
	 */
	public GroupControl(IControlContainer container, String name) {
		super(container, name);
		init();
	}
	
	/**
	 * Set the layout of the group.
	 * @param layout
	 */
	public void setLayout(GroupControlLayout layout) {
		setOuterTemplateName(layout.outerTemplateName);
		requireRedraw();
	}
	
	/**
	 * Initialize the control.
	 */
	private void init() {
		setRendererId(DEFAULT_OUTER_RENDERER);
		title = getName();
		setCssClass("grpDefault");
	}
	
	/**
	 * Returns <code>null</code> if the class has not been extended and no
	 * template name has been set.
	 * 
	 * @see de.jwic.base.Control#getTemplateName()
	 */
	public String getTemplateName() {
		String tmpl = super.getTemplateName();
		if (tmpl.equals(outerTemplateName) || tmpl.equals(GroupControl.class.getName())) {
			return null;
		}
		return super.getTemplateName();
	}
	
	
	/**
	 * @param param
	 */
	public void actionCloseframe(String param){
		setVisible(false);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.jwic.controls.IHTMLElement#forceFocus()
	 */
	public boolean forceFocus() {
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.jwic.controls.IHTMLElement#getCssClass()
	 */
	public String getCssClass() {
		return cssClass;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.jwic.controls.IHTMLElement#getHeight()
	 */
	public int getHeight() {
		return height;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.jwic.controls.IHTMLElement#getWidth()
	 */
	public int getWidth() {
		return width;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.jwic.controls.IHTMLElement#isEnabled()
	 */
	public boolean isEnabled() {
		return enabled;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.jwic.controls.IHTMLElement#isFillWidth()
	 */
	public boolean isFillWidth() {
		return fillWidth;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.jwic.controls.IHTMLElement#setCssClass(java.lang.String)
	 */
	public void setCssClass(String cssClass) {
		this.cssClass = cssClass;
		requireRedraw();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.jwic.controls.IHTMLElement#setEnabled(boolean)
	 */
	public void setEnabled(boolean enabled) {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.jwic.controls.IHTMLElement#setFillWidth(boolean)
	 */
	public void setFillWidth(boolean fillWidth) {
		if (this.fillWidth != fillWidth) {
			requireRedraw();
		}
		this.fillWidth = fillWidth;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.jwic.controls.IHTMLElement#setHeight(int)
	 */
	public void setHeight(int height) {
		if (this.height != height) {
			requireRedraw();
		}
		this.height = height;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.jwic.controls.IHTMLElement#setWidth(int)
	 */
	public void setWidth(int width) {
		if (this.width != width) {
			requireRedraw();
		}
		this.width = width;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title
	 *            the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
		requireRedraw();
	}

	public String getOuterTemplateName() {
		return outerTemplateName;
	}

	/**
	 * The outerTemplateName to set.
	 * 
	 * @param outerTemplateName
	 */
	public void setOuterTemplateName(String outerTemplateName) {
		this.outerTemplateName = outerTemplateName;
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
