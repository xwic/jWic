package de.jwic.mobile.controls;

import de.jwic.base.IControlContainer;
import de.jwic.base.JavaScriptSupport;

/**
 * Created by boogie on 10/30/14.
 */
@JavaScriptSupport(jsTemplate = "de.jwic.mobile.controls.MCheckBox")
public class MRadioButton extends MCheckBox {

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
}
