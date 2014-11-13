package de.jwic.mobile.controls;

import de.jwic.base.ControlContainer;
import de.jwic.base.IControlContainer;
import de.jwic.base.IOuterLayout;
import de.jwic.base.IncludeJsOption;
import de.jwic.base.JavaScriptSupport;
import de.jwic.common.enableable.Enableable;
import de.jwic.common.enableable.EnableableHandler;
import de.jwic.common.properties.PropertiesHandler;
import de.jwic.common.properties.PropertyChangedListener;
import de.jwic.common.properties.PropertyObservable;
import de.jwic.common.properties.WithTextProperty;
import de.jwic.common.selectable.Selectable;
import de.jwic.common.selectable.SelectionHandler;
import de.jwic.common.togglable.Togglable;
import de.jwic.common.togglable.ToggleableHandler;
import de.jwic.common.togglable.ToggleableListener;
import de.jwic.common.visible.Visible;
import de.jwic.events.SelectionListener;

/**
 * Created by boogie on 10/29/14.
 */
@JavaScriptSupport(jsTemplate = "de.jwic.mobile.controls.MPanel")
public class MPanel extends ControlContainer implements IOuterLayout, Selectable, WithTextProperty, Visible, Togglable, PropertyObservable, Enableable{

	private final SelectionHandler clickHandler;
	private final PropertiesHandler propertiesHandler;
	private final EnableableHandler enableableHandler;
	private final ToggleableHandler toggleableHandler;

	private String text;

	public MPanel(IControlContainer container) {
		this(container, null);
	}

	public MPanel(IControlContainer container, String name) {
		super(container, name);
		this.setRendererId(DEFAULT_OUTER_RENDERER);
		this.setTemplateName(ControlContainer.class.getName());

		this.clickHandler = new SelectionHandler<MPanel>(this);
		this.propertiesHandler = new PropertiesHandler(this);
		this.enableableHandler = new EnableableHandler(this);
		this.toggleableHandler = new ToggleableHandler<MPanel>(this);
		this.text = name;

		this.propertiesHandler.setProperty("state", true);

	}

	@Override
	public void select() {
		this.toggle();
		this.clickHandler.select();
	}

	@Override
	public void actionPerformed(String actionId, String parameter) {
		if("open".equals(actionId)){
			System.out.println("open");
			toggleableHandler.setToggledNoRedraw(true); ;
			this.propertiesHandler.setPropertyNoRedraw("state", true);
			return;
		}

		if("close".equals(actionId)){
			System.out.println("clone");
			toggleableHandler.setToggledNoRedraw(false);
			this.propertiesHandler.setPropertyNoRedraw("state", false);
		}
	}

	@Override
	public void addSelectionListener(SelectionListener listener) {
		this.clickHandler.addSelectionListener(listener);
	}

	@Override
	public void removeSelectionListener(SelectionListener listener) {
		this.clickHandler.removeSelectionListener(listener);
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
		this.toggleableHandler.toggle();
		this.propertiesHandler.setProperty("state", !this.propertiesHandler.getProperty("state", Boolean.class));
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
		this.toggleableHandler.addToggleableListener(listener);
	}

	@Override
	public void removeToggleableListener(ToggleableListener listener) {
		this.toggleableHandler.removeToggleableListener(listener);
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
		return MPanel.class.getName();
	}
}
