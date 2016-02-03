package de.jwic.controls.mobile;

import de.jwic.base.IControlContainer;
import de.jwic.base.IncludeJsOption;
import de.jwic.base.JavaScriptSupport;
import de.jwic.controls.TabStrip;
import de.jwic.controls.accordion.Accordion.HeightStyle;

/**
 * 
 * The MTabs offers the same functionality as the base TabStrip, but renders as
 * a basic HTML tabbar that inherits the default jQuery Mobile
 * 
 * @author vedad
 *
 */
@JavaScriptSupport
public class MTabStrip extends TabStrip {

	private static final long serialVersionUID = 1L;

	private boolean collapsible = false;
	private boolean hide = false;
	private boolean show = false;
	private HeightStyle heightStyle = HeightStyle.CONTENT;
	private int counter = 97;

	/**
	 * Constructs a new control instance and adds it to the specified container
	 * with the specified name. If the name is <code>null</code>, a unique name
	 * will be chosen by the container.
	 * 
	 * @param container
	 * @param name
	 */
	public MTabStrip(IControlContainer container, String name) {
		super(container, name);
	}

	/**
	 * Constructs a new control instance and adds it to the specified container.
	 * 
	 * @param container
	 */
	public MTabStrip(IControlContainer container) {
		super(container);
	}

	/**
	 * @return the collapsible
	 */
	@IncludeJsOption
	public boolean isCollapsible() {
		return collapsible;
	}

	/**
	 * @param collapsible
	 *            the collapsible to set
	 */
	public void setCollapsible(boolean collapsible) {
		if (collapsible != this.collapsible)
			requireRedraw();
		this.collapsible = collapsible;
	}

	/**
	 * @return the hide
	 */
	@IncludeJsOption
	public boolean isHide() {
		return hide;
	}

	/**
	 * @param hide
	 *            the hide to set
	 */
	public void setHide(boolean hide) {
		if (hide != this.hide)
			requireRedraw();
		this.hide = hide;
	}

	/**
	 * @return the show
	 */
	@IncludeJsOption
	public boolean isShow() {
		return show;
	}

	/**
	 * @param show
	 *            the show to set
	 */
	public void setShow(boolean show) {
		if (show != this.show)
			requireRedraw();
		this.show = show;
	}

	/**
	 * @return the heightStyle
	 */
	@IncludeJsOption
	public HeightStyle getHeightStyle() {
		return heightStyle;
	}

	/**
	 * @param heightStyle
	 *            the heightStyle to set
	 */
	public void setHeightStyle(HeightStyle heightStyle) {
		if (heightStyle != this.heightStyle)
			requireRedraw();
		this.heightStyle = heightStyle;
	}

	/**
	 * returns counter incremented for the position in foreach loop in the vtl
	 * file
	 * 
	 * @return the counter
	 */
	@IncludeJsOption
	public char getCounter(int increment) {
		return (char) (counter + increment);
	}

	/**
	 * @return the counter
	 */
	@IncludeJsOption
	public int getCounter() {
		return counter;
	}

}
