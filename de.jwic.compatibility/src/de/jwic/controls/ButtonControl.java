/*
 * de.jwic.controls.ButtonControl
 * $Id: ButtonControl.java,v 1.2 2010/02/08 21:09:50 lordsam Exp $
 */
package de.jwic.controls;

import de.jwic.base.IControlContainer;
import de.jwic.base.ImageRef;


/**
 * Represents the &lt;button&gt; html element. A button displays text and/or
 * an image. 
 *  
 * @author Florian Lippisch
 * @version $Revision: 1.2 $
 */
public class ButtonControl extends SelectableControl {

	private static final long serialVersionUID = 1L;

	private String title = null;
	private ImageRef iconEnabled = null;
	private ImageRef iconDisabled = null;
	private boolean submitButton = false;
	private String iconInfo = null;
	private String confirmMsg = "";
	
	/**
	 * @param container
	 */
	public ButtonControl(IControlContainer container) {
		super(container);
		init();
	}
	/**
	 * @param container
	 * @param name
	 */
	public ButtonControl(IControlContainer container, String name) {
		super(container, name);
		init();
	}
	/* (non-Javadoc)
	 * @see de.jwic.base.Control#init()
	 */
	private void init() {
		setCssClass("default_button");
		if (title == null) {
			title = getName();
		}
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
	 * Returns the icon for the button depending on the state.
	 * @return
	 */
	public ImageRef getIcon() {
		if (!isEnabled()) {
			return iconDisabled != null ? iconDisabled : iconEnabled;
		} else {
			return iconEnabled;
		}
	}

	/**
	 * Returns true if an icon is specified.
	 * @return
	 */
	public boolean hasIcon(){
		return iconEnabled != null; 
	}

	/**
	 * Returns true if the button should behave like a submit button.
	 * @return boolean
	 */
	public boolean isSubmitButton() {
		return submitButton;
	}
	/**
	 * Set to true if the button should be of the type submit.
	 * @param submitButton The submitButton to set.
	 */
	public void setSubmitButton(boolean submitButton) {
		this.submitButton = submitButton;
	}
	/**
	 * @return Returns the iconInfo.
	 */
	public String getIconInfo() {
		return iconInfo;
	}
	/**
	 * @param iconInfo The iconInfo to set.
	 */
	public void setIconInfo(String iconInfo) {
		this.iconInfo = iconInfo;
	}
	/**
	 * @return Returns the confirmMsg.
	 */
	public String getConfirmMsg() {
		return confirmMsg;
	}
	/**
	 * @param confirmMsg The confirmMsg to set.
	 */
	public void setConfirmMsg(String confirmMsg) {
		this.confirmMsg = confirmMsg;
	}
	/**
	 * @return the iconDisabled
	 */
	public ImageRef getIconDisabled() {
		return iconDisabled;
	}
	/**
	 * @param iconDisabled the iconDisabled to set
	 */
	public void setIconDisabled(ImageRef iconDisabled) {
		this.iconDisabled = iconDisabled;
	}
	/**
	 * @return the iconEnabled
	 */
	public ImageRef getIconEnabled() {
		return iconEnabled;
	}
	/**
	 * @param iconEnabled the iconEnabled to set
	 */
	public void setIconEnabled(ImageRef iconEnabled) {
		this.iconEnabled = iconEnabled;
	}
}
