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
	 * When set to true, the active panel can be closed.
	 */
	@IncludeJsOption
	public boolean isCollapsible() {
		return collapsible;
	}

	/**
	 * When set to true, the active panel can be closed.
	 */
	public void setCollapsible(boolean collapsible) {
		if (collapsible != this.collapsible)
			requireRedraw();
		this.collapsible = collapsible;
	}

	/**
	 * When set to false, no animation will be used and the panel will be hidden immediately. When set to true, the panel will fade out with the default duration and the default easing.
	 */
	@IncludeJsOption
	public boolean isHide() {
		return hide;
	}

	/**
	 * When set to false, no animation will be used and the panel will be hidden immediately. When set to true, the panel will fade out with the default duration and the default easing.
	 */
	public void setHide(boolean hide) {
		if (hide != this.hide)
			requireRedraw();
		this.hide = hide;
	}

	/**
	 * When set to false, no animation will be used and the panel will be shown immediately. When set to true, the panel will fade in with the default duration and the default easing.
	 */
	@IncludeJsOption
	public boolean isShow() {
		return show;
	}

	/**
	 * When set to false, no animation will be used and the panel will be shown immediately. When set to true, the panel will fade in with the default duration and the default easing.
	 */
	public void setShow(boolean show) {
		if (show != this.show)
			requireRedraw();
		this.show = show;
	}

	/**
	 * Controls the height of the tabs widget and each panel. Possible values:
	 * "auto": All panels will be set to the height of the tallest panel.
	 * "fill": Expand to the available height based on the tabs' parent height. 
	 * "content": Each panel will be only as tall as its content.
	 */
	@IncludeJsOption
	public HeightStyle getHeightStyle() {
		return heightStyle;
	}

	/**
	 * Controls the height of the tabs widget and each panel. Possible values:
	 * "auto": All panels will be set to the height of the tallest panel.
	 * "fill": Expand to the available height based on the tabs' parent height. 
	 * "content": Each panel will be only as tall as its content.
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
	 * default get method for counter
	 * 
	 * @return the counter
	 */
	@IncludeJsOption
	public int getCounter() {
		return counter;
	}

}
