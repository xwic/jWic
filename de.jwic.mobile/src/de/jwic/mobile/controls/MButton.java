package de.jwic.mobile.controls;

import de.jwic.base.Control;
import de.jwic.base.IControlContainer;
import de.jwic.base.IncludeJsOption;
import de.jwic.base.JavaScriptSupport;
import de.jwic.common.enableable.Enableable;
import de.jwic.common.enableable.EnableableHandler;
import de.jwic.common.properties.WithTextProperty;
import de.jwic.common.selectable.Selectable;
import de.jwic.common.selectable.SelectionHandler;
import de.jwic.common.visible.Visible;
import de.jwic.events.SelectionListener;

/**
 * Created by boogie on 10/27/14.
 */
@JavaScriptSupport
public class MButton extends Control implements Selectable, Visible, WithTextProperty, Enableable{
	private final SelectionHandler handler;
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
	public MButton(IControlContainer container, String name) {
		super(container, name);
		this.handler = new SelectionHandler<MButton>(this);
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
			select();
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
	public final void select() {
		handler.select();
	}

	@Override
	public final void addSelectionListener(SelectionListener listener) {
		handler.addSelectionListener(listener);
	}

	@Override
	public final void removeSelectionListener(SelectionListener listener) {
		handler.removeSelectionListener(listener);
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
