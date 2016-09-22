/**
 * 
 */
package de.jwic.controls.mobile;

import de.jwic.base.IControlContainer;
import de.jwic.base.IncludeJsOption;
import de.jwic.controls.Window;

/**
 * @author vedad
 *
 */
public class MPopup extends Window {

	private static final long serialVersionUID = -537117679543144945L;

	private boolean corners = true;
	private boolean disabled = false;
	private boolean defaults = false;
	private boolean dismissible = true;
	private boolean history = true;
	private boolean shadow = true;
	private String positionTo = "origin";
	private String tolerance = "30,15,30,15";
	private String transition = null;
	private String btnTitle = "Open Popup";
	private Theme theme = null;
	private Theme overlayTheme = null;

	/**
	 * @param container
	 */
	public MPopup(IControlContainer container) {
		this(container, null);
	}

	/**
	 * @param container
	 * @param name
	 */
	public MPopup(IControlContainer container, String name) {
		super(container, name);
		setTemplateName(MPopup.class.getName());
		setPopup(true);
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
	 * @return the dismissible
	 */
	@IncludeJsOption
	public boolean isDismissible() {
		return dismissible;
	}

	/**
	 * @param dismissible
	 *            the dismissible to set
	 */
	public void setDismissible(boolean dismissible) {
		if (dismissible != this.dismissible)
			requireRedraw();
		this.dismissible = dismissible;
	}

	/**
	 * @return the history
	 */
	@IncludeJsOption
	public boolean isHistory() {
		return history;
	}

	/**
	 * @param history
	 *            the history to set
	 */
	public void setHistory(boolean history) {
		if (history != this.history)
			requireRedraw();
		this.history = history;
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
	 * @return the positionTo
	 */
	@IncludeJsOption
	public String getPositionTo() {
		return positionTo;
	}

	/**
	 * @param positionTo
	 *            the positionTo to set
	 */
	public void setPositionTo(String positionTo) {
		if (!this.positionTo.equals(positionTo))
			requireRedraw();
		this.positionTo = positionTo;
	}

	/**
	 * @return the tolerance
	 */
	@IncludeJsOption
	public String getTolerance() {
		return tolerance;
	}

	/**
	 * @param tolerance
	 *            the tolerance to set
	 */
	public void setTolerance(String tolerance) {
		if (!this.tolerance.equals(tolerance))
			requireRedraw();
		this.tolerance = tolerance;
	}

	/**
	 * @return the transition
	 */
	@IncludeJsOption
	public String getTransition() {
		return transition;
	}

	/**
	 * @param transition
	 *            the transition to set
	 */
	public void setTransition(String transition) {
		if (!this.transition.equals(transition))
			requireRedraw();
		this.transition = transition;
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
		if (!this.theme.equals(theme))
			requireRedraw();
		this.theme = theme;
	}

	/**
	 * @return the overlayTheme
	 */
	@IncludeJsOption
	public Theme getOverlayTheme() {
		return overlayTheme;
	}

	/**
	 * @param overlayTheme
	 *            the overlayTheme to set
	 */
	public void setOverlayTheme(Theme overlayTheme) {
		if (!this.overlayTheme.equals(overlayTheme))
			requireRedraw();
		this.overlayTheme = overlayTheme;
	}

	/**
	 * @return the disabled
	 */
	@IncludeJsOption
	public boolean isDisabled() {
		return disabled;
	}

	/**
	 * @param disabled the disabled to set
	 */
	public void setDisabled(boolean disabled) {
		if(this.disabled != disabled)
			requireRedraw();
		this.disabled = disabled;
	}

	/**
	 * @return the btnTitle
	 */
	@IncludeJsOption
	public String getBtnTitle() {
		return btnTitle;
	}

	/**
	 * @param btnTitle the btnTitle to set
	 */
	public void setBtnTitle(String btnTitle) {
		if(!this.btnTitle.equals(btnTitle))
			requireRedraw();
		this.btnTitle = btnTitle;
	}

}
