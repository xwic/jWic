package de.jwic.mobile.controls;

import de.jwic.base.Control;
import de.jwic.base.IControlContainer;
import de.jwic.base.IncludeJsOption;
import de.jwic.base.JavaScriptSupport;
import de.jwic.mobile.common.Visible;
import de.jwic.mobile.common.clickable.ClickHandler;
import de.jwic.mobile.common.enableable.Enableable;
import de.jwic.mobile.common.enableable.EnableableHandler;
import de.jwic.mobile.common.properties.WithTextProperty;
import de.jwic.mobile.common.clickable.ClickListener;
import de.jwic.mobile.common.clickable.Clickable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by boogie on 10/27/14.
 */
@JavaScriptSupport
public class Button extends Control implements Clickable, Visible, WithTextProperty, Enableable{
	private final ClickHandler handler;
	private final EnableableHandler enableableHandler;
	private String value = "";

	/**
	 * Constructs a new control instance and adds it to the specified
	 * container with the specified name. If the name is <code>null</code>,
	 * a unique name will be choosen by the container.
	 *
	 * @param container
	 * @param name
	 */
	public Button(IControlContainer container, String name) {
		super(container, name);
		this.handler = new ClickHandler(this);
		this.enableableHandler = new EnableableHandler(this);
	}

	@Override
	public final void setText(String title) {
		this.value = title;
		this.requireRedraw();
	}

	@Override
	@IncludeJsOption
	public final String getText() {
		return this.value;
	}

	@Override
	public final void actionPerformed(String actionId, String parameter) {
		if("click".equals(actionId)) {
			click();
		}
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
	public final void click() {
		handler.click();
	}

	@Override
	public final void addClickListener(ClickListener listener) {
		handler.addClickListener(listener);
	}

	@Override
	public final void removeClickListener(ClickListener listener) {
		handler.removeClickListener(listener);
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
		return enableableHandler.isEnabled();
	}

	@Override
	public void setEnabled(boolean enabled) {
		enableableHandler.setEnabled(enabled);
	}
}
