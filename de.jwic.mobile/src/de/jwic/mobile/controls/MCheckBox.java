package de.jwic.mobile.controls;

import de.jwic.base.Control;
import de.jwic.base.IControlContainer;
import de.jwic.base.IncludeJsOption;
import de.jwic.base.JavaScriptSupport;
import de.jwic.events.SelectionListener;
import de.jwic.common.selectable.Selectable;
import de.jwic.common.selectable.SelectionHandler;
import de.jwic.common.visible.Visible;
import de.jwic.common.enableable.Enableable;
import de.jwic.common.enableable.EnableableHandler;
import de.jwic.common.properties.PropertiesHandler;
import de.jwic.common.properties.PropertyChangedListener;
import de.jwic.common.properties.PropertyObservable;
import de.jwic.common.properties.WithTextProperty;
import de.jwic.common.togglable.Togglable;
import de.jwic.common.togglable.ToggleableHandler;
import de.jwic.common.togglable.ToggleableListener;

/**
 * Created by boogie on 10/30/14.
 */
@JavaScriptSupport
public class MCheckBox extends Control implements WithTextProperty, Selectable, Togglable, Visible, Enableable, PropertyObservable{

	private String text;
	private final SelectionHandler clickHandler;
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
	public MCheckBox(IControlContainer container, String name) {
		super(container, name);
		this.clickHandler = new SelectionHandler<MCheckBox>(this);
		this.enableableHandler = new EnableableHandler(this);
		this.propertiesHandler = new PropertiesHandler(this);
		this.toggleableHandler = new ToggleableHandler<MCheckBox>(this);

		this.propertiesHandler.setProperty("state", false);
		this.toggleableHandler.setToggled(false);
		this.text = "";


	}

	@Override
	public void actionPerformed(String actionId, String parameter) {
		if("select".equals(actionId)){
			toggleableHandler.toggle();
			this.propertiesHandler.setPropertyNoRedraw("state", !this.propertiesHandler.getProperty("state", Boolean.class));
			clickHandler.select();
		}

	}

	@Override
	public void select() {
		this.toggle();
	}

	@Override
	public void addSelectionListener(SelectionListener listener) {
		clickHandler.addSelectionListener(listener);
	}

	@Override
	public void removeSelectionListener(SelectionListener listener) {
		clickHandler.removeSelectionListener(listener);
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
		this.propertiesHandler.setProperty("state", !this.propertiesHandler.getProperty("state", Boolean.class));
		clickHandler.select();
	}

	@Override
	public void setToggled(boolean on) {
		this.toggleableHandler.setToggled(on);
		this.propertiesHandler.setProperty("state", on);
		this.clickHandler.select();
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
