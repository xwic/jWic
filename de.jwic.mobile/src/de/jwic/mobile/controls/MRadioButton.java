package de.jwic.mobile.controls;

import de.jwic.base.IControlContainer;
import de.jwic.base.JavaScriptSupport;
import de.jwic.controls.RadioButton;

/**
 * A single radio button that can be linked with other radio buttons on a page in a mobile design.
 */
@JavaScriptSupport
public class MRadioButton extends RadioButton {

	/**
	 * Constructs a new control instance and adds it to the specified
	 * container with the specified name. If the name is <code>null</code>,
	 * a unique name will be choosen by the container.
	 *
	 * @param container
	 * @param name
	 */
	public MRadioButton(IControlContainer container, String name) {
		super(container, name);
	}

	/**
	 * @param container
	 * @param name
	 * @param linkedButton
	 */
	public MRadioButton(IControlContainer container, String name, MRadioButton linkedButton) {
		super(container, name, linkedButton);
	}
	
	
}
