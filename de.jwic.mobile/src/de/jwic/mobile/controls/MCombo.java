/**
 * 
 */
package de.jwic.mobile.controls;

import static de.jwic.mobile.controls.Icon.CARATR;

import de.jwic.base.IControlContainer;
import de.jwic.base.IncludeJsOption;
import de.jwic.base.JavaScriptSupport;
import de.jwic.controls.combo.Combo;

/**
 * 
 * The MCombo offers the same functionality as the base Combo, but renders as a basic HTML combo that inherits the default jQuery Mobile
 * 
 * @author vedad
 *
 */
@JavaScriptSupport
public class MCombo extends Combo<Object> {

	private static final long serialVersionUID = 1L;

	private boolean autodividers = false;
	private boolean defaults = false;
	private boolean filter = false;
	private boolean hideDividers = false;
	private boolean inset = false;
	private Icon iconClass = CARATR;
	private Icon splitIcon = Icon.CARATR;
	private Theme dividerTheme = null;
	private Theme splitTheme = null;
	private Theme theme = null;

	/**
	 * Constructs a new control instance and adds it to the specified container with the specified name. If the name is <code>null</code>, a
	 * unique name will be chosen by the container.
	 * 
	 * @param container
	 * @param name
	 */
	public MCombo(IControlContainer container, String name) {
		super(container, name);
	}

	/**
	 * @return the autodividers
	 */
	@IncludeJsOption
	public boolean isAutodividers() {
		return autodividers;
	}

	/**
	 * @param autodividers
	 *            the autodividers to set
	 */
	public void setAutodividers(boolean autodividers) {
		if (autodividers != this.autodividers)
			requireRedraw();
		this.autodividers = autodividers;
	}

	/**
	 * @return the defaults
	 */
	@IncludeJsOption
	public boolean isDefaults() {
		return defaults;
	}

	/**
	 * @param defaults
	 *            the defaults to set
	 */
	public void setDefaults(boolean defaults) {
		if (defaults != this.defaults)
			requireRedraw();
		this.defaults = defaults;
	}

	/**
	 * @return the filter
	 */
	@IncludeJsOption
	public boolean isFilter() {
		return filter;
	}

	/**
	 * @param filter
	 *            the filter to set
	 */
	public void setFilter(boolean filter) {
		if (filter != this.filter)
			requireRedraw();
		this.filter = filter;
	}

	/**
	 * @return the hideDividers
	 */
	@IncludeJsOption
	public boolean isHideDividers() {
		return hideDividers;
	}

	/**
	 * @param hideDividers
	 *            the hideDividers to set
	 */
	public void setHideDividers(boolean hideDividers) {
		if (hideDividers != this.hideDividers)
			requireRedraw();
		this.hideDividers = hideDividers;
	}

	/**
	 * @return the inset
	 */
	@IncludeJsOption
	public boolean isInset() {
		return inset;
	}

	/**
	 * @param inset
	 *            the inset to set
	 */
	public void setInset(boolean inset) {
		if (inset != this.inset)
			requireRedraw();
		this.inset = inset;
	}

	/**
	 * @return the iconClass
	 */
	@IncludeJsOption
	public Icon getIconClass() {
		return iconClass;
	}

	/**
	 * @param iconClass
	 *            the iconClass to set
	 */
	public void setIconClass(Icon iconClass) {
		if (iconClass != this.iconClass)
			requireRedraw();
		this.iconClass = iconClass;
	}

	/**
	 * @return the splitIcon
	 */
	@IncludeJsOption
	public Icon getSplitIcon() {
		return splitIcon;
	}

	/**
	 * @param splitIcon
	 *            the splitIcon to set
	 */
	public void setSplitIcon(Icon splitIcon) {
		if (splitIcon != this.splitIcon)
			requireRedraw();
		this.splitIcon = splitIcon;
	}

	/**
	 * @return the dividerTheme
	 */
	@IncludeJsOption
	public Theme getDividerTheme() {
		return dividerTheme;
	}

	/**
	 * @param dividerTheme
	 *            the dividerTheme to set
	 */
	public void setDividerTheme(Theme dividerTheme) {
		if (dividerTheme != this.dividerTheme)
			requireRedraw();
		this.dividerTheme = dividerTheme;
	}

	/**
	 * @return the splitTheme
	 */
	@IncludeJsOption
	public Theme getSplitTheme() {
		return splitTheme;
	}

	/**
	 * @param splitTheme
	 *            the splitTheme to set
	 */
	public void setSplitTheme(Theme splitTheme) {
		if (splitTheme != this.splitTheme)
			requireRedraw();
		this.splitTheme = splitTheme;
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
		if (theme != this.theme)
			requireRedraw();
		this.theme = theme;
	}

}
