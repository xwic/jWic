package de.jwic.mobile.controls;

import de.jwic.base.IControlContainer;
import de.jwic.base.JavaScriptSupport;
import de.jwic.controls.CheckBox;

/**
 * Created by boogie on 10/30/14.
 */
@JavaScriptSupport
public class MCheckBox extends CheckBox {

	private String text;

	/**
	 * Constructs a new control instance and adds it to the specified
	 * container with the specified name. If the name is <code>null</code>,
	 * a unique name will be choosen by the container.
	 *
	 * @param container
	 * @param name
	 */
	public MCheckBox(IControlContainer container, String name) {
		super(container, name);
	}

}
