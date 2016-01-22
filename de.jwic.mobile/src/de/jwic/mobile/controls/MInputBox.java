/**
 * 
 */
package de.jwic.mobile.controls;

import de.jwic.base.IControlContainer;
import de.jwic.base.JavaScriptSupport;
import de.jwic.controls.InputBox;

/**
 * A textinput widget that is represented as a jQuery Mobile control. Extends the standard
 * jWic InputBox for compatibility and ease of use. It
 * @author lippisch
 */
@JavaScriptSupport
public class MInputBox extends InputBox {

	/**
	 * @param container
	 */
	public MInputBox(IControlContainer container) {
		super(container);
	}

	/**
	 * @param container
	 * @param name
	 */
	public MInputBox(IControlContainer container, String name) {
		super(container, name);
	}

}
