/*
 * Copyright 2005-2013 jWic group (http://www.jwic.de)
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
 * de.jwic.menu.MenuSelectionListener
 */
package de.jwic.controls.menu;

import java.io.Serializable;

/**
 * @author lippisch
 */
public interface MenuSelectionListener extends Serializable {

	/**
	 * Invoked when a menu item was selected.
	 * @param event
	 */
	public void menuItemSelected(MenuEvent event);
	
}
