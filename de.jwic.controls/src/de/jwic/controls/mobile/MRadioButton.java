package de.jwic.controls.mobile;

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
	 * @param container container of the radio button
	 * @param name name of the radio button
	 */
	public MRadioButton(IControlContainer container, String name) {
		super(container, name);
	}

	/**
	 * @param container container of the radio button
	 * @param name name of the radio button
	 * @param linkedButton radio button linked with existing one
	 */
	public MRadioButton(IControlContainer container, String name, MRadioButton linkedButton) {
		super(container, name, linkedButton);
	}

	/**
	 * If set to true, this will display a more compact version of the checkboxradio that uses less vertical height by applying the ui-mini class to the outermost element of the checkboxradio widget.
	 */
	@IncludeJsOption
	public boolean isMini() {
		return mini;
	}

	/**
	 * If set to true, this will display a more compact version of the checkboxradio that uses less vertical height by applying the ui-mini class to the outermost element of the checkboxradio widget.
	 */
	public void setMini(boolean mini) {
		if (mini != this.mini)
			requireRedraw();
		this.mini = mini;
	}

	/**
	 * Allows you to specify on which side of the checkbox or radio button the checkmark/radio icon will appear.
	 */
	@IncludeJsOption
	public IconPos getIconpos() {
		return iconpos;
	}

	/**
	 * Allows you to specify on which side of the checkbox or radio button the checkmark/radio icon will appear.
	 */
	public void setIconpos(IconPos iconpos) {
		if (!iconpos.equals(this.iconpos))
			requireRedraw();
		this.iconpos = iconpos;
	}

	/**
	 * This option allows you to specify one or more space-separated class names to be added to the wrapper div element by the framework.
	 */
	@IncludeJsOption
	public String getWrapperClass() {
		return wrapperClass;
	}

	/**
	 * This option allows you to specify one or more space-separated class names to be added to the wrapper div element by the framework.
	 */
	public void setWrapperClass(String wrapperClass) {
		if (!wrapperClass.equals(this.wrapperClass))
			requireRedraw();
		this.wrapperClass = wrapperClass;
	}
}
