package de.jwic.controls.mobile;

import de.jwic.base.IControlContainer;
import de.jwic.base.JavaScriptSupport;
import de.jwic.controls.CheckBox;

/**
 * Created by boogie on 10/30/14.
 */
@JavaScriptSupport
public class MFlipSwitch extends CheckBox {

	protected String onText = "On";
	protected String offText = "Off";
	
	/**
	 * Constructs a new control instance and adds it to the specified
	 * container with the specified name. If the name is <code>null</code>,
	 * a unique name will be choosen by the container.
	 *
	 * @param container
	 * @param name
	 */
	public MFlipSwitch(IControlContainer container, String name) {
		super(container, name);
	}

	/**
	 * @return the onText
	 */
	public String getOnText() {
		return onText;
	}

	/**
	 * @param onText the onText to set
	 */
	public void setOnText(String onText) {
		this.onText = onText;
	}

	/**
	 * @return the offText
	 */
	public String getOffText() {
		return offText;
	}

	/**
	 * @param offText the offText to set
	 */
	public void setOffText(String offText) {
		this.offText = offText;
	}

	
	
}
