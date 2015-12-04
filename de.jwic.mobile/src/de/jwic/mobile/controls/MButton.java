package de.jwic.mobile.controls;

import de.jwic.base.IControlContainer;
import de.jwic.base.IncludeJsOption;
import de.jwic.base.JavaScriptSupport;
import de.jwic.controls.SelectableControl;

/**
 * Created by boogie on 10/27/14.
 */
@JavaScriptSupport
public class MButton extends SelectableControl {
	
	private String text = "";

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

	public final void setText(String text) {
		this.text = text;
		this.requireRedraw();
	}

	@IncludeJsOption
	public final String getText() {
		return this.text;
	}

	@Override
	public final void actionPerformed(String actionId, String parameter) {
		if("click".equals(actionId)) {
			click();
		}
	}

}
