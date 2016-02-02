package de.jwic.controls.mobile;

import de.jwic.base.ControlContainer;
import de.jwic.base.IControlContainer;
import de.jwic.base.IOuterLayout;
import de.jwic.base.IncludeJsOption;
import de.jwic.base.JavaScriptSupport;

/**
 * Flexible by design, panels can be used for navigation, forms, inspectors and more.
 * 
 * Representation of the jQuery Mobile Panel control (http://demos.jquerymobile.com/1.4.5/panel/)
 * 
 */
@JavaScriptSupport(jsTemplate = "de.jwic.controls.mobile.MPanel")
public class MPanel extends ControlContainer implements IOuterLayout{

	/**
	 * Position of the panel on the page.
	 * @author lippisch
	 *
	 */
	public enum Position {
		LEFT,
		RIGHT;
		public String getCode() {
			return name().toLowerCase();
		}
	}
	
	/**
	 * The mode in which the panel opens on the page.
	 * @author lippisch
	 *
	 */
	public enum Display {
		OVERLAY,
		REVEAL,
		PUSH;
		public String getCode() {
			return name().toLowerCase();
		}
	}
	
	private boolean animate;
	private String title;
	private boolean open;
	private boolean enabled;
	private Position position;
	private Display display;

	/**
	 * Create a new panel with an automatically generated name.
	 * @param container
	 */
	public MPanel(IControlContainer container) {
		this(container, null);
	}

	/**
	 * Create a new panel with a specified name. If <code>name</code> is null, the
	 * container will assign a unique name.
	 * @param container
	 * @param name
	 */
	public MPanel(IControlContainer container, String name) {
		super(container, name);
		this.setRendererId(DEFAULT_OUTER_RENDERER);
		this.setTemplateName(ControlContainer.class.getName());

		this.title = name;
		this.open = true;
		this.enabled = true;
		this.position = Position.LEFT;
		this.display = Display.REVEAL;
		this.animate = true;

	}

	/**
	 * Handle the action's triggered by this control.
	 */
	@Override
	public void actionPerformed(String actionId, String parameter) {

		// those actions do only notify the server of the state change. This means that on
		// open, the panel is already open on the client side and vice versa.
		if("open".equals(actionId)){
			open = true;
			
		} else if("close".equals(actionId)){
			open = false;
		}
	}


	/**
	 * Returns true if the panel is currently open.
	 * @return
	 */
	public boolean isOpen() {
		return open;
	}
	
	/**
	 * Open the panel.
	 */
	public void open() {
		if (!open) {
			open = true;
		}
	}
	
	/**
	 * Close the panel.
	 */
	public void close() {
		if (open) {
			open = false;
			requireRedraw();
		}
	}
	
	/**
	 * Set the title of the panel.
	 * @param title
	 */
	public void setTitle(String title) {
		this.title = title;
		this.requireRedraw();
	}

	/**
	 * Returns the title of the panel.
	 * @return
	 */
	@IncludeJsOption
	public String getTitle() {
		return this.title;
	}


	/**
	 * Returns true if the panel is enabled. 
	 * @return
	 */
	@IncludeJsOption
	public boolean isEnabled() {
		return this.enabled;
	}

	/**
	 * Change the enabled state of the panel.
	 * @param enabled
	 */
	public void setEnabled(boolean enabled) {
		if (this.enabled != enabled) {
			this.enabled = enabled;
			requireRedraw();
		}
	}

	/**
	 * Returns MPanel's class to always render with this outer layout.
	 * (non-Javadoc)
	 * @see de.jwic.base.IOuterLayout#getOuterTemplateName()
	 */
	@Override
	public String getOuterTemplateName() {
		return MPanel.class.getName();
	}

	/**
	 * Returns the position on the page.
	 * @return the position
	 */
	@IncludeJsOption
	public Position getPosition() {
		return position;
	}

	/**
	 * Set the position of the Panel on the page.
	 * @param position the position to set
	 */
	public void setPosition(Position position) {
		if (this.position != position) {
			this.position = position;
			requireRedraw();
		}
	}

	/**
	 * @return the display
	 */
	@IncludeJsOption
	public Display getDisplay() {
		return display;
	}

	/**
	 * @param display the display to set
	 */
	public void setDisplay(Display display) {
		if (this.display != display) {
			this.display = display;
			requireRedraw();
		}
	}

	/**
	 * @return the animated
	 */
	@IncludeJsOption
	public boolean isAnimate() {
		return animate;
	}

	/**
	 * @param animated the animated to set
	 */
	public void setAnimate(boolean animated) {
		if (this.animate != animated) {
			this.animate = animated;
			requireRedraw();
		}
	}
}
