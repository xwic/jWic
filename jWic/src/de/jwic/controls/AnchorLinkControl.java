/*
 * de.jwic.controls.AnchorLinkControl
 * $Id: AnchorLinkControl.java,v 1.2 2006/08/09 14:52:40 lordsam Exp $
 */
package de.jwic.controls;

import de.jwic.base.IControlContainer;


/**
 * Displays an anchor (&lt;a href="..."&gt;) link.
 * @author Florian Lippisch
 * @version $Revision: 1.2 $
 */
public class AnchorLinkControl extends SelectableControl {

	private static final long serialVersionUID = 1L;

	private String title = null;
	private String infoMessage = null;

	
	/**
	 * @param container
	 */
	public AnchorLinkControl(IControlContainer container) {
		super(container);
		title = getName();
	}
	/**
	 * @param container
	 * @param name
	 */
	public AnchorLinkControl(IControlContainer container, String name) {
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
}
