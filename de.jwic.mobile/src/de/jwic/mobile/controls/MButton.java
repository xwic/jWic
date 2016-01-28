package de.jwic.mobile.controls;

import de.jwic.base.IControlContainer;
import de.jwic.base.IncludeJsOption;
import de.jwic.base.JavaScriptSupport;
import de.jwic.controls.Button;

/**
 * The MButton offers the same functionality as the base Button, but renders as a basic HTML button that inherits the default jQuery Mobile
 * css styles.
 */
@JavaScriptSupport
public class MButton extends Button {

	private static final long serialVersionUID = 1L;

	private boolean corners = true;
	private boolean inline = false;
	private boolean mini = false;
	private boolean shadow = true;
	private Icon iconClass = null;
	private IconPos iconpos = IconPos.LEFT;
	private Theme theme = null;
	private String wrapperClass = null;

	/**
	 * Constructs a new control instance and adds it to the specified container with the specified name. If the name is <code>null</code>, a
	 * unique name will be chosen by the container.
	 *
	 * @param container
	 * @param name
	 */
	public MButton(IControlContainer container, String name) {
		super(container, name);
	}

	/**
	 * @return the corners
	 */
	@IncludeJsOption
	public boolean isCorners() {
		return corners;
	}

	/**
	 * @param corners
	 *            the corners to set
	 */
	public void setCorners(boolean corners) {
		if (corners != this.corners)
			requireRedraw();
		this.corners = corners;
	}

	/**
	 * @return the inline
	 */
	@IncludeJsOption
	public boolean isInline() {
		return inline;
	}

	/**
	 * @param inline
	 *            the inline to set
	 */
	public void setInline(boolean inline) {
		if (inline != this.inline)
			requireRedraw();
		this.inline = inline;
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
	 * @return the shadow
	 */
	@IncludeJsOption
	public boolean isShadow() {
		return shadow;
	}

	/**
	 * @param shadow
	 *            the shadow to set
	 */
	public void setShadow(boolean shadow) {
		if (shadow != this.shadow)
			requireRedraw();
		this.shadow = shadow;
	}
	
	
	/**
	 * @return the iconClass
	 */
	@IncludeJsOption
	public Icon getIconClass() {
		return iconClass;
	}

	
	/**
	 * @param iconClass the iconClass to set
	 */
	public void setIconClass(Icon iconClass) {
		if (iconClass != this.iconClass)
			requireRedraw();
		this.iconClass = iconClass;
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
	 * @return the theme
	 */
	@IncludeJsOption
	public Theme getTheme() {
		return theme;
	}

	/**
	 * @param theme
	 *            the theme to set
	 */
	public void setTheme(Theme theme) {
		if (!theme.equals(this.theme))
			requireRedraw();
		this.theme = theme;
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
