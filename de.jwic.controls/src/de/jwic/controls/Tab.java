/*******************************************************************************
 * Copyright 2015 xWic group (http://www.xwic.de)
 *
 * Licensed under the Apache License, Version 2.0 (the "License").
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * 		http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software 
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and 
 * limitations under the License.
 *  
 *******************************************************************************/
/**
 * 
 */
package de.jwic.controls;

import de.jwic.base.ControlContainer;

/**
 * A tab within a TabStrip acts as a container for other controls.
 * 
 * @author lippisch
 */
public class Tab extends ControlContainer {

	private String title;
	
	/**
	 * @param container
	 * @param name
	 * @param tabStrip
	 * @param title
	 */
	public Tab(TabStrip tabStrip, String name, String title) {
		super(tabStrip, name);
		this.title = title;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	
	/* (non-Javadoc)
	 * @see de.jwic.base.Control#setVisible(boolean)
	 */
	@Override
	public void setVisible(boolean newVisible) {
		if (this.bolVisible != newVisible) {
			super.setVisible(newVisible);
			// The parent requires re-rendering if a tab's visibility is changed
			if (getContainer() instanceof TabStrip) {
				TabStrip ts = (TabStrip)getContainer();
				ts._internalTabVisibilityChange(this, newVisible);
			}
		}
	}
	
}
