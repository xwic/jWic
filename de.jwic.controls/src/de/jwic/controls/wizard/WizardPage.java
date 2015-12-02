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
package de.jwic.controls.wizard;

import java.io.Serializable;

import de.jwic.base.IControlContainer;

/**
 * Represents a page of a wizard.
 * 
 * @author Florian Lippisch
 * @version $Revision: 1.2 $
 */
public abstract class WizardPage implements Serializable {

	private String title = "";
	private String subTitle = "";
	
	/**
	 * Creates the content of this page.
	 * @param container
	 * @return
	 */
	public abstract void createControls(IControlContainer container);

	/**
	 * This method is invoked when this page is activated. This happens
	 * when the user navigates to the page.
	 */
	public void activated() {
		// nothing to do.
	}
	
	/**
	 * Returns true if the page is valid and the wizard can proced to the
	 * next page. (If there is any)
	 * @return
	 */
	public boolean validate() throws ValidationException {
		return true;
	}
	
	/**
	 * @return Returns the subTitle.
	 */
	public String getSubTitle() {
		return subTitle;
	}

	/**
	 * @param subTitle The subTitle to set.
	 */
	public void setSubTitle(String subTitle) {
		this.subTitle = subTitle;
	}

	/**
	 * @return Returns the title.
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title The title to set.
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	
}
