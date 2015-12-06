package de.jwic.mobile.controls;

import de.jwic.base.IControlContainer;
import de.jwic.base.JavaScriptSupport;
import de.jwic.controls.Button;

/**
 * The MButton offers the same functionality as the base Button, but 
 * renders as a basic HTML button that inherits the default jQuery Mobile css styles. 
 */
@JavaScriptSupport
public class MButton extends Button {
	
	/**
	 * Constructs a new control instance and adds it to the specified
	 * container with the specified name. If the name is <code>null</code>,
	 * a unique name will be choosen by the container.
	 *
	 * @param container
	 * @param name
	 */
	public MButton(IControlContainer container, String name) {
		super(container, name);
	}

}
