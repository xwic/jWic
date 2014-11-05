package de.jwic.mobile.controls;

import de.jwic.base.Control;
import de.jwic.base.Field;
import de.jwic.base.IControlContainer;
import de.jwic.base.IncludeJsOption;
import de.jwic.base.JavaScriptSupport;
import de.jwic.events.ValueChangedEvent;
import de.jwic.events.ValueChangedListener;
import de.jwic.common.visible.Visible;
import de.jwic.common.blurable.BlurHandler;
import de.jwic.common.enableable.Enableable;
import de.jwic.common.enableable.EnableableHandler;
import de.jwic.common.properties.PropertyObservable;
import de.jwic.common.properties.PropertiesHandler;
import de.jwic.common.properties.PropertyChangedListener;
import de.jwic.common.properties.WithTextProperty;
import de.jwic.common.blurable.BlurListener;
import de.jwic.common.blurable.Blurable;

/**
 *
 * InputBox Control for Mobile.<br>
 *<br>
 * Has text property. On text property change it fires a property changed event with "text" as the property. <br>
 * It can be blurred and will trigger an onBlur event when blurring happens, this will be reflected in the UI. <br>
 * It can be shown or hidden.<br>
 *
 * Created by boogie on 10/28/14.
 */
@JavaScriptSupport
public class MInputBox extends Control implements WithTextProperty, PropertyObservable, Visible, Blurable, Enableable {
	private final Field field;
	private final BlurHandler blurHandler;
	private final PropertiesHandler propertiesHandler;
	private final EnableableHandler enableableHandler;

	/**
	 * Constructs a new control instance and adds it to the specified
	 * container with the specified name. If the name is <code>null</code>,
	 * a unique name will be chosen by the container.
	 *
	 * @param container
	 * @param name
	 */
	public MInputBox(IControlContainer container, String name) {
		super(container, name);
		this.blurHandler = new BlurHandler<MInputBox>(this);
		this.propertiesHandler = new PropertiesHandler(this);
		this.enableableHandler = new EnableableHandler(this);

		this.field = new Field(this, "textField");
		this.field.addValueChangedListener(new InputBoxFieldValueChangedListener());

	}

	@Override
	public void setText(String text) {
		this.field.setValue(text);
		this.propertiesHandler.setProperty("text", text);
		this.requireRedraw();
	}

	@Override
	public String getText() {
		return this.field.getValue();
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
	public void actionPerformed(String actionId, String parameter) {
		if("blur".equals(actionId)){
			blurHandler.blurNoRedraw();
		}
	}

	@Override
	public void blur() {
		blurHandler.blur();
	}


	@Override
	public void addBlurListener(BlurListener listener) {
		this.blurHandler.addBlurListener(listener);
	}

	@Override
	public void removeBlurListener(BlurListener listener) {
		this.blurHandler.removeBlurListener(listener);
	}

	@Override
	public void setUpdateOnBlur(boolean updateOnBlur) {
		this.blurHandler.setUpdateOnBlur(updateOnBlur);
	}

	@Override
	@IncludeJsOption
	public boolean isUpdateOnBlur() {
		return blurHandler.isUpdateOnBlur();
	}

	/**
	 * This property is not idempotent.<br>
	 * Calling this method will change the value to false<br>
	 * It should not be used in java.<br>
	 * It is only as a way to inform the ui the it should blur the control on first create.<br>
	 *
	 * @return
	 */
	@IncludeJsOption
	boolean isBlurred(){
		return this.blurHandler.isBlurred();
	}


	@Override
	public void addPropertyChangedListener(PropertyChangedListener listener) {
		this.propertiesHandler.addPropertyChangedListener(listener);
	}

	@Override
	public void removePropertyChangedListener(PropertyChangedListener listener) {
		this.propertiesHandler.removePropertyChangedListener(listener);
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

	/**
	 *
	 */
	private class InputBoxFieldValueChangedListener implements ValueChangedListener {

		@Override
		public void valueChanged(ValueChangedEvent event) {
			propertiesHandler.triggerPropertyChange("text", event.getOldValue(), event.getNewValue());
		}
	}
}
