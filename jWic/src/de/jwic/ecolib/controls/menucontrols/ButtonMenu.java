/*
 * Copyright 2005-2007 jWic group (http://www.jwic.de)
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * de.jwic.ecolib.controls.menubutton.ButtonMenu
 * Created on 10.11.2012
 * $Id:$
 */
package de.jwic.ecolib.controls.menucontrols;

import de.jwic.base.Control;
import de.jwic.base.IControlContainer;
import de.jwic.base.JWicException;
import de.jwic.base.JavaScriptSupport;
import de.jwic.controls.AnchorLinkControl;
import de.jwic.controls.Button;
import de.jwic.controls.HTMLElementContainer;

/**
 * A button that shows a menu on click.
 * 
 * @author Andrei Pat
 */
@JavaScriptSupport
public class ButtonMenu extends HTMLElementContainer {
	private static final long serialVersionUID = 1L;
	private Button button;
	private final PopupMenuContainer menu = new PopupMenuContainer();

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.jwic.base.ControlContainer#registerControl(de.jwic.base.Control,
	 * java.lang.String)
	 */
	@Override
	public void registerControl(Control control, String name) throws JWicException {
		super.registerControl(control, name);
		if (control instanceof AnchorLinkControl) {
			// override the AnchorLinkControl's template, to avoid problems with
			// jQuery's menu widget.
			control.setTemplateName(this.getClass().getPackage().getName() + ".AnchorLinkControlMenu");
		}
	}

	public ButtonMenu(IControlContainer container, String name) {
		super(container, name);
	}

	/**
	 * Create the button that displays the menu.
	 * 
	 * @param name
	 * @return
	 */
	public Button createButton(String name) {
		if (button == null) {
			button = new Button(this, name);
			button.setSubmitButton(false);
		}
		return button;
	}

	/**
	 * @return the menu
	 */
	public PopupMenuContainer getMenu() {
		return menu;
	}

	/**
	 * @return the button
	 */
	public Button getButton() {
		return button;
	}

}