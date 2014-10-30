package de.jwic.mobile.controls;

import de.jwic.base.Control;
import de.jwic.base.IControlContainer;
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
@JavaScriptSupport(jsTemplate = "de.jwic.mobile.controls.CheckBox")
public class RadioButton extends CheckBox{

	/**
	 * Constructs a new control instance and adds it to the specified
	 * container with the specified name. If the name is <code>null</code>,
	 * a unique name will be choosen by the container.
	 *
	 * @param container
	 * @param name
	 */
	public RadioButton(IControlContainer container, String name) {
		super(container, name);
	}
}
