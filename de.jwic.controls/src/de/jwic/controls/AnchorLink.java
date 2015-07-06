/*******************************************************************************
 * Copyright 2015 xWic group (http://www.xwic.de)
 *
 * Licensed under the Apache License, Version 2.0 (the "License").
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * 		http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software 
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and 
 * limitations under the License.
 *  
 *******************************************************************************/
/*
 * de.jwic.controls.AnchorLinkControl
 * $Id: AnchorLinkControl.java,v 1.2 2006/08/09 14:52:40 lordsam Exp $
 */
package de.jwic.controls;

import de.jwic.base.IControlContainer;
import de.jwic.base.JavaScriptSupport;
import de.jwic.controls.menu.Menu;


/**
 * Displays an anchor (&lt;a href="..."&gt;) link.
 * @author Florian Lippisch
 * @version $Revision: 1.2 $
 */
@JavaScriptSupport
public class AnchorLink extends SelectableControl {

	private static final long serialVersionUID = 1L;

	private String title = "";
	private String infoMessage = "";
	private String cssClass = "j-anchor";
	private String tooltip = null;

	private Menu menu = null;
	
	/**
	 * @param container
	 */
	public AnchorLink(IControlContainer container) {
		super(container);
		title = getName();
	}
	/**
	 * @param container
	 * @param name
	 */
	public AnchorLink(IControlContainer container, String name) {
		super(container, name);
		title = name;
	}
	/* (non-Javadoc)
	 * @see de.jwic.base.Control#actionPerformed(java.lang.String, java.lang.String)
	 */
	public void actionPerformed(String actionId, String parameter) {
		
		click();
		
	}
	
	/**
	 * @return Returns the title.
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * @param title The title to set.
	 */
	public void setTitle(String title) {
		this.title = title;
		setRequireRedraw(true);
	}
	
	/**
	 * @return Returns the infoMessage.
	 */
	public String getInfoMessage() {
		return infoMessage;
	}
	/**
	 * This text is displayed in the infobar of the browser during mouseover and as the
	 * title of the anchor tag, wich results in a little popup info.
	 * @param infoMessage The infoMessage to set.
	 */
	public void setInfoMessage(String infoMessage) {
		this.infoMessage = infoMessage;
		setRequireRedraw(true);
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
	 * @return the menu
	 */
	public Menu getMenu() {
		return menu;
	}
	/**
	 * @param menu the menu to set
	 */
	public void setMenu(Menu menu) {
		this.menu = menu;
	}
	/**
	 * @return the tooltip
	 */
	public String getTooltip() {
		return tooltip;
	}
	/**
	 * @param tooltip the tooltip to set
	 */
	public void setTooltip(String tooltip) {
		this.tooltip = tooltip;
	}
}
