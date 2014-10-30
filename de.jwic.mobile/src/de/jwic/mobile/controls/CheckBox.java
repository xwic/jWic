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
import de.jwic.mobile.common.properties.PropertiesHandler;
import de.jwic.mobile.common.properties.PropertyChangedListener;
import de.jwic.mobile.common.properties.PropertyObservable;
import de.jwic.mobile.common.properties.WithTextProperty;
import de.jwic.mobile.common.togglable.Togglable;
import de.jwic.mobile.common.togglable.ToggleableHandler;
import de.jwic.mobile.common.togglable.ToggleableListener;

/**
 * Created by boogie on 10/30/14.
 */
@JavaScriptSupport
public class CheckBox extends Control implements WithTextProperty, Clickable, Togglable, Visible, Enableable, PropertyObservable{

	private String text;
	private final ClickHandler clickHandler;
	private final EnableableHandler enableableHandler;
	private final PropertiesHandler propertiesHandler;
	private final ToggleableHandler toggleableHandler;

	/**
	 * Constructs a new control instance and adds it to the specified
	 * container with the specified name. If the name is <code>null</code>,
	 * a unique name will be choosen by the container.
	 *
	 * @param container
	 * @param name
	 */
	public CheckBox(IControlContainer container, String name) {
		super(container, name);
		this.clickHandler = new ClickHandler(this);
		this.enableableHandler = new EnableableHandler(this);
		this.propertiesHandler = new PropertiesHandler(this);
		this.toggleableHandler = new ToggleableHandler<CheckBox>(this);

		this.propertiesHandler.setProperty("state", false);
		this.text = "";


	}

	@Override
	public void actionPerformed(String actionId, String parameter) {
		if("click".equals(actionId)){
			toggleableHandler.toggle();
			this.propertiesHandler.setPropertyNoRedraw("state", !this.isToggled());
			clickHandler.click();
		}

	}

	@Override
	public void click() {
		this.toggle();
	}

	@Override
	public void addClickListener(ClickListener listener) {
		clickHandler.addClickListener(listener);
	}

	@Override
	public void removeClickListener(ClickListener listener) {
		clickHandler.removeClickListener(listener);
	}

	@Override
	public void addPropertyChangedListener(PropertyChangedListener listener) {
		propertiesHandler.addPropertyChangedListener(listener);
	}

	@Override
	public void removePropertyChangedListener(PropertyChangedListener listener) {
		propertiesHandler.removePropertyChangedListener(listener);
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
	@IncludeJsOption
	public boolean isEnabled() {
		return enableableHandler.isEnabled();
	}

	@Override
	public void setEnabled(boolean enabled) {
		enableableHandler.setEnabled(enabled);
	}

	@Override
	public void setText(String title) {
		this.text = title;
		this.requireRedraw();
	}

	@Override
	@IncludeJsOption
	public String getText() {
		return this.text;
	}

	@Override
	public void show() {
		this.setVisible(true);
	}

	@Override
	public void hide() {
		this.setVisible(false);
	}

	@Override
	public void toggle() {
		toggleableHandler.toggle();
		this.propertiesHandler.setProperty("state", isToggled());
		clickHandler.click();
	}

	@Override
	public void setToggled(boolean on) {
		this.propertiesHandler.setProperty("state", on);
		this.toggleableHandler.setToggled(on);
	}

	@Override
	@IncludeJsOption
	public boolean isToggled() {
		return toggleableHandler.isToggled();
	}

	@Override
	public void addToggleableListener(ToggleableListener listener) {
		toggleableHandler.addToggleableListener(listener);
	}

	@Override
	public void removeToggleableListener(ToggleableListener listener) {
		toggleableHandler.removeToggleableListener(listener);
	}
}
