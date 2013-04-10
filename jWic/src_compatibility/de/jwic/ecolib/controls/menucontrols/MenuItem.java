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
 * de.jwic.ecolib.controls.menubutton.MenuItem
 * Created on 10.11.2012
 * $Id:$
 */
package de.jwic.ecolib.controls.menucontrols;

import de.jwic.base.IControl;
import de.jwic.base.ImageRef;

/**
 * An item in a menu. It can contain other items representing a submenu.
 * 
 * @author Andrei
 */
public class MenuItem extends PopupMenuContainer {
	private static final long serialVersionUID = 1L;
	private ImageRef icon;
	private IControl content;

	protected MenuItem() {
	}

	/**
	 * Set the content of the menu item.
	 * 
	 * @param content
	 */
	public void setContent(IControl content) {
		this.content = content;
	}

	/**
	 * @return the content
	 */
	public IControl getContent() {
		return content;
	}

	/**
	 * @return the icon
	 */
	public ImageRef getIcon() {
		return icon;
	}

	/**
	 * @param icon the icon to set
	 */
	public void setIcon(ImageRef icon) {
		this.icon = icon;
	}
	
	/**
	 * @return true if the item contains other items
	 */
	public boolean isSubmenu() {
		return getMenuItems().size() > 0;
	}
	
	public boolean hasIcon() {
		return icon != null;
	}



}