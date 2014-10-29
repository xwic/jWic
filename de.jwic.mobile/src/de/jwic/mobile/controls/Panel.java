package de.jwic.mobile.controls;

import de.jwic.base.ControlContainer;
import de.jwic.base.IControlContainer;
import de.jwic.base.IOuterLayout;
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

/**
 * Created by boogie on 10/29/14.
 */
@JavaScriptSupport
public class Panel extends ControlContainer implements IOuterLayout, Clickable, WithTextProperty, Visible, Togglable, PropertyObservable, Enableable{

	private final ClickHandler clickHandler;
	private final PropertiesHandler propertiesHandler;
	private final EnableableHandler enableableHandler;

	private String text;

	public Panel(IControlContainer container) {
		this(container, null);
	}

	public Panel(IControlContainer container, String name) {
		super(container, name);
		this.setRendererId(DEFAULT_OUTER_RENDERER);
		this.setTemplateName(ControlContainer.class.getName());

		this.clickHandler = new ClickHandler(this);
		this.propertiesHandler = new PropertiesHandler(this);
		this.enableableHandler = new EnableableHandler(this);
		this.text = name;

		this.propertiesHandler.setProperty("state", true);
	}

	@Override
	public void click() {
		this.toggle();
		this.clickHandler.click();
	}

	@Override
	public void actionPerformed(String actionId, String parameter) {
		if("open".equals(actionId)){
			this.propertiesHandler.setPropertyNoRedraw("state", true);
			return;
		}

		if("close".equals(actionId)){
			this.propertiesHandler.setPropertyNoRedraw("state", false);
		}
	}

	@Override
	public void addClickListener(ClickListener listener) {
		this.clickHandler.addClickListener(listener);
	}

	@Override
	public void removeClickListener(ClickListener listener) {
		this.clickHandler.removeClickListener(listener);
	}

	@Override
	public void setText(String text) {
		this.text = text;
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
		this.setState(!this.isState());
	}

	@Override
	public void setState(boolean on) {
		this.propertiesHandler.setProperty("state", on);
	}

	@Override
	@IncludeJsOption
	public boolean isState() {
		return this.propertiesHandler.getProperty("state", Boolean.class);
	}

	@Override
	public void addPropertyChangedListener(PropertyChangedListener listener) {
		this.propertiesHandler.addPropertyChangedListener(listener);
	}

	@Override
	public void removePropertyChangedListener(PropertyChangedListener listener) {
		this.propertiesHandler.addPropertyChangedListener(listener);
	}

	@Override
	public void enable() {
		this.enableableHandler.enable();
	}

	@Override
	public void disable() {
		this.enableableHandler.disable();
	}

	@Override
	@IncludeJsOption
	public boolean isEnabled() {
		return this.enableableHandler.isEnabled();
	}

	@Override
	public void setEnabled(boolean enabled) {
		this.enableableHandler.setEnabled(enabled);
	}

	@Override
	public String getOuterTemplateName() {
		return Panel.class.getName();
	}
}
