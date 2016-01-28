package de.jwic.mobile.controls;

import de.jwic.base.IControlContainer;
import de.jwic.base.IncludeJsOption;
import de.jwic.base.JavaScriptSupport;
import de.jwic.controls.RadioButton;

/**
 * A single radio button that can be linked with other radio buttons on a page in a mobile design.
 */
@JavaScriptSupport
public class MRadioButton extends RadioButton {

	private static final long serialVersionUID = 1L;

	private boolean mini = false;
	private IconPos iconpos = IconPos.LEFT;
	private String wrapperClass = null;

	/**
	 * Constructs a new control instance and adds it to the specified container with the specified name. If the name is <code>null</code>, a
	 * unique name will be choosen by the container.
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

	/**
	 * @return the mini
	 */
	@IncludeJsOption
	public boolean isMini() {
		return mini;
	}

	/**
	 * @param mini
	 *            the mini to set
	 */
	public void setMini(boolean mini) {
		if (mini != this.mini)
			requireRedraw();
		this.mini = mini;
	}

	/**
	 * @return the iconpos
	 */
	@IncludeJsOption
	public IconPos getIconpos() {
		return iconpos;
	}

	/**
	 * @param iconpos
	 *            the iconpos to set
	 */
	public void setIconpos(IconPos iconpos) {
		if (!iconpos.equals(this.iconpos))
			requireRedraw();
		this.iconpos = iconpos;
	}

	/**
	 * @return the wrapperClass
	 */
	@IncludeJsOption
	public String getWrapperClass() {
		return wrapperClass;
	}

	/**
	 * @param wrapperClass
	 *            the wrapperClass to set
	 */
	public void setWrapperClass(String wrapperClass) {
		if (!wrapperClass.equals(this.wrapperClass))
			requireRedraw();
		this.wrapperClass = wrapperClass;
	}
}
