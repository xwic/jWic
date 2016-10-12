/**
 * 
 */
package de.jwic.controls.mobile;

import de.jwic.base.IControlContainer;
import de.jwic.base.IncludeJsOption;
import de.jwic.controls.CheckBoxGroup;
import de.jwic.util.Util;

/**
 * @author vedad
 *
 */
public class MCheckBoxGroup extends CheckBoxGroup {

	private static final long serialVersionUID = -7839611328199530859L;

	private boolean corners = true;
	private boolean defaults = false;
	private boolean excludeInvisible = true;
	private boolean mini = false;
	private boolean shadow = true;
	private Theme theme = null;
	private String type = "vertical";

	/**
	 * @param container
	 */
	public MCheckBoxGroup(IControlContainer container) {
		this(container, null);
	}

	/**
	 * @param container
	 * @param name
	 */
	public MCheckBoxGroup(IControlContainer container, String name) {
		super(container, name);
		setTemplateName(MCheckBoxGroup.class.getName());
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
		if(this.corners != corners)
			requireRedraw();
		this.corners = corners;
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
		if(this.defaults != defaults)
			requireRedraw();
		this.defaults = defaults;
	}

	/**
	 * @return the excludeInvisible
	 */
	@IncludeJsOption
	public boolean isExcludeInvisible() {
		return excludeInvisible;
	}

	/**
	 * @param excludeInvisible
	 *            the excludeInvisible to set
	 */
	public void setExcludeInvisible(boolean excludeInvisible) {
		if(this.excludeInvisible != excludeInvisible)
			requireRedraw();
		this.excludeInvisible = excludeInvisible;
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
		if(this.mini != mini)
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
		if(this.shadow != shadow)
			requireRedraw();
		this.shadow = shadow;
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
		if(Util.equals(theme, this.theme))
			requireRedraw();
		this.theme = theme;
	}

	/**
	 * @return the type
	 */
	@IncludeJsOption
	public String getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(String type) {
		if(Util.equals(type, this.type))
			requireRedraw();
		this.type = type;
	}

}
