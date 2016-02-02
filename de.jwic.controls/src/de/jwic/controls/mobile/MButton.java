package de.jwic.controls.mobile;

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
	 * @param container container of the button
	 * @param name name of the button
	 */
	public MButton(IControlContainer container, String name) {
		super(container, name);
	}

	/**
	 * Applies the theme button border-radius if set to true
	 */
	@IncludeJsOption
	public boolean isCorners() {
		return corners;
	}

	/**
	 * Applies the theme button border-radius if set to true
	 */
	public void setCorners(boolean corners) {
		if (corners != this.corners)
			requireRedraw();
		this.corners = corners;
	}

	/**
	 * If set to true, this will make the button act like an inline button so the width is determined by the button's text. 
	 * By default, this is null (false) so the button is full width, regardless of the feedback content.
	 */
	@IncludeJsOption
	public boolean isInline() {
		return inline;
	}

	/**
	 * If set to true, this will make the button act like an inline button so the width is determined by the button's text. 
	 * By default, this is null (false) so the button is full width, regardless of the feedback content.
	 */
	public void setInline(boolean inline) {
		if (inline != this.inline)
			requireRedraw();
		this.inline = inline;
	}

	/**
	 * If set to true, this will display a more compact version of the button that uses less vertical h
	 * eight by applying the ui-mini class to the outermost element of the button widget
	 */
	@IncludeJsOption
	public boolean isMini() {
		return mini;
	}

	/**
	 * If set to true, this will display a more compact version of the button that uses less vertical h
	 * eight by applying the ui-mini class to the outermost element of the button widget
	 */
	public void setMini(boolean mini) {
		if (mini != this.mini)
			requireRedraw();
		this.mini = mini;
	}

	/**
	 * Applies the drop shadow style to the button if set to true.
	 */
	@IncludeJsOption
	public boolean isShadow() {
		return shadow;
	}

	/**
	 * Applies the drop shadow style to the button if set to true.
	 */
	public void setShadow(boolean shadow) {
		if (shadow != this.shadow)
			requireRedraw();
		this.shadow = shadow;
	}
	
	
	/**
	 * Applies an icon from the icon set. 
	 * The <a href="http://api.jquerymobile.com/buttonMarkup/">.buttonMarkup()</a> documentation contains a reference of all the icons available in the default theme.
	 */
	@IncludeJsOption
	public Icon getIconClass() {
		return iconClass;
	}

	
	/**
	 * Applies an icon from the icon set. 
	 * The <a href="http://api.jquerymobile.com/buttonMarkup/">.buttonMarkup()</a> documentation contains a reference of all the icons available in the default theme.
	 */
	public void setIconClass(Icon iconClass) {
		if (iconClass != this.iconClass)
			requireRedraw();
		this.iconClass = iconClass;
	}

	/**
	 * Positions the icon in the button. Possible values: left, right, top, bottom, none, notext. 
	 * The notext value will display an icon-only button with no text feedback.
	 */
	@IncludeJsOption
	public IconPos getIconpos() {
		return iconpos;
	}

	/**
	 * Positions the icon in the button. Possible values: left, right, top, bottom, none, notext. 
	 * The notext value will display an icon-only button with no text feedback.
	 */
	public void setIconpos(IconPos iconpos) {
		if (!iconpos.equals(this.iconpos))
			requireRedraw();
		this.iconpos = iconpos;
	}

	/**
	 * Sets the color scheme (swatch) for the button widget. 
	 * It accepts a single letter from a-z that maps to the swatches included in your theme. Possible values: swatch letter (a-z).
	 */
	@IncludeJsOption
	public Theme getTheme() {
		return theme;
	}

	/**
	 * Sets the color scheme (swatch) for the button widget. 
	 * It accepts a single letter from a-z that maps to the swatches included in your theme. Possible values: swatch letter (a-z).
	 */
	public void setTheme(Theme theme) {
		if (!theme.equals(this.theme))
			requireRedraw();
		this.theme = theme;
	}

	/**
	 * Allows you to specify CSS classes to be set on the button's wrapper element.
	 */
	@IncludeJsOption
	public String getWrapperClass() {
		return wrapperClass;
	}

	/**
	 * Allows you to specify CSS classes to be set on the button's wrapper element.
	 */
	public void setWrapperClass(String wrapperClass) {
		if (!wrapperClass.equals(this.wrapperClass))
			requireRedraw();
		this.wrapperClass = wrapperClass;
	}
}
