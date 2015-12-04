package de.jwic.mobile.controls;

import de.jwic.base.Control;
import de.jwic.base.IControlContainer;
import de.jwic.base.IncludeJsOption;
import de.jwic.base.JavaScriptSupport;
import de.jwic.common.enableable.Enableable;
import de.jwic.common.enableable.EnableableHandler;
import de.jwic.common.properties.PropertiesHandler;
import de.jwic.common.properties.PropertyChangedListener;
import de.jwic.common.properties.PropertyObservable;
import de.jwic.common.selectable.Selectable;
import de.jwic.common.selectable.SelectionHandler;
import de.jwic.common.togglable.Togglable;
import de.jwic.common.togglable.ToggleableHandler;
import de.jwic.common.togglable.ToggleableListener;
import de.jwic.events.SelectionListener;

/**
 * Created by boogie on 10/28/14.
 */
@JavaScriptSupport
public class MFlipSwitch extends Control implements Selectable, PropertyObservable, Togglable, Enableable {

	private String onText = "Yes";
	private String offText = "No";
	private final PropertiesHandler propertiesHandler;
	private final SelectionHandler clickHandler;
	private final EnableableHandler enableableHandler;
	private final ToggleableHandler toggleableHandler;

	/**
	 * Constructs a new control instance and adds it to the specified
	 * container with the specified name. If the name is <code>null</code>,
	 * a unique name will be choosen by the container.
	 *
	 * @param container
	 * @param name
	 */
	public MFlipSwitch(IControlContainer container, String name) {
		super(container, name);
		this.propertiesHandler = new PropertiesHandler(this);
		this.propertiesHandler.setProperty("state", false);

		this.toggleableHandler = new ToggleableHandler<MFlipSwitch>(this);

		this.clickHandler = new SelectionHandler<MFlipSwitch>(this);
		this.enableableHandler = new EnableableHandler(this);
	}

	@Override
	public final void actionPerformed(String actionId, String parameter) {
		if ("click".equals(actionId)) {
			this.select();
		}
	}

	@Override
	public final void select() {
		this.toggle();
		this.clickHandler.select();
	}

	@Override
	public final void addSelectionListener(SelectionListener listener) {
		this.clickHandler.addSelectionListener(listener);
	}

	@Override
	public final void removeSelectionListener(SelectionListener listener) {
		this.clickHandler.removeSelectionListener(listener);
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
		this.propertiesHandler.setProperty("state", !this.propertiesHandler.getProperty("state", Boolean.class));
		this.toggleableHandler.toggle();
	}

	@Override
	public final void setToggled(boolean on) {
		this.propertiesHandler.setProperty("state", on);
		this.toggleableHandler.setToggled(on);
	}

	@Override
	@IncludeJsOption
	public final boolean isToggled() {
		return this.toggleableHandler.isToggled();
	}

	@Override
	public final void addToggleableListener(ToggleableListener listener) {
		this.toggleableHandler.addToggleableListener(listener);
	}

	@Override
	public final void removeToggleableListener(ToggleableListener listener) {
		this.toggleableHandler.removeToggleableListener(listener);
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
	@IncludeJsOption
	public boolean isEnabled() {
		return enableableHandler.isEnabled();
	}

	@Override
	public void setEnabled(boolean enabled) {
		enableableHandler.setEnabled(enabled);
	}
}
