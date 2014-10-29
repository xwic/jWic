package de.jwic.mobile.controls;

import de.jwic.base.Control;
import de.jwic.base.IControlContainer;
import de.jwic.base.IncludeJsOption;
import de.jwic.base.JavaScriptSupport;
import de.jwic.mobile.common.Visible;
import de.jwic.mobile.common.clickable.ClickHandler;
import de.jwic.mobile.common.clickable.ClickListener;
import de.jwic.mobile.common.clickable.Clickable;
import de.jwic.mobile.common.enableable.Enableable;
import de.jwic.mobile.common.enableable.EnableableHandler;
import de.jwic.mobile.common.properties.PropertyObservable;
import de.jwic.mobile.common.properties.PropertiesHandler;
import de.jwic.mobile.common.properties.PropertyChangedListener;
import de.jwic.mobile.common.togglable.Togglable;

/**
 * Created by boogie on 10/28/14.
 */
@JavaScriptSupport
public class FlipSwitch extends Control implements Visible, Clickable, PropertyObservable, Togglable, Enableable {

	private String onText = "Yes";
	private String offText = "No";
	private final PropertiesHandler propertiesHandler;
	private final ClickHandler clickHandler;
	private final EnableableHandler enableableHandler;

	/**
	 * Constructs a new control instance and adds it to the specified
	 * container with the specified name. If the name is <code>null</code>,
	 * a unique name will be choosen by the container.
	 *
	 * @param container
	 * @param name
	 */
	public FlipSwitch(IControlContainer container, String name) {
		super(container, name);
		this.propertiesHandler = new PropertiesHandler(this);
		this.propertiesHandler.setProperty("state", false);

		this.clickHandler = new ClickHandler(this);
		this.enableableHandler = new EnableableHandler(this);
	}

	@Override
	public final void actionPerformed(String actionId, String parameter) {
		if ("click".equals(actionId)) {
			this.click();
		}
	}

	@Override
	public final void click() {
		this.toggle();
		this.clickHandler.click();
	}

	@Override
	public final void addClickListener(ClickListener listener) {
		this.clickHandler.addClickListener(listener);
	}

	@Override
	public final void removeClickListener(ClickListener listener) {
		this.clickHandler.removeClickListener(listener);
	}

	@Override
	public final void show() {
		this.setVisible(true);
	}

	@Override
	public final void hide() {
		this.setVisible(false);
	}

	@Override
	public final void addPropertyChangedListener(PropertyChangedListener listener) {
		this.propertiesHandler.addPropertyChangedListener(listener);
	}

	@Override
	public final void removePropertyChangedListener(PropertyChangedListener listener) {
		this.propertiesHandler.removePropertyChangedListener(listener);
	}

	@Override
	public final void toggle() {
		this.setState(!this.isState());
	}

	@Override
	public final void setState(boolean on) {
		this.propertiesHandler.setProperty("state", on);
	}

	@Override
	@IncludeJsOption
	public final boolean isState() {
		return this.propertiesHandler.getProperty("state", Boolean.class);
	}

	public final void setOnText(String title) {
		this.onText = title;
		this.requireRedraw();
	}

	@IncludeJsOption
	public final String getOnText() {
		return this.onText;
	}

	public final void setOffText(String text) {
		this.offText = text;
		this.requireRedraw();
	}
	@IncludeJsOption
	public final String getOffText() {
		return this.offText;
	}

	@Override
	public void enable() {
		enableableHandler.enable();
	}

	@Override
	public void disable() {
		enableableHandler.disable();
	}

	@Override
	public boolean isEnabled() {
		return enableableHandler.isEnabled();
	}

	@Override
	public void setEnabled(boolean enabled) {
		enableableHandler.setEnabled(enabled);
	}
}
