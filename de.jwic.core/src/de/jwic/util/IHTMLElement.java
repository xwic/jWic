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
package de.jwic.util;

import de.jwic.base.IControl;
import de.jwic.base.IHaveEnabled;

/**
 * Specifies common properties for controls that represent a basic HTML element.
 * 
 * @author Florian Lippisch
 * @version $Revision: 1.3 $
 */
public interface IHTMLElement extends IControl, IHaveEnabled {

	/**
	 * Returns the CSS class.
	 * @return Returns the cssClass.
	 */
	public abstract String getCssClass();

	/**
	 * Sets the CSS class to assign to the html element. If set to null, no class 
	 * will be assigned. 
	 * @param cssClass The cssClass to set.
	 */
	public abstract void setCssClass(String cssClass);

	/**
	 * Returns the height of the element. Returns 0 if the height is
	 * not specified.
	 * @return Returns the height.
	 */
	public abstract int getHeight();

	/**
	 * Sets the height of the element in 'px'. The height is set via the
	 * css height attribute. If set to 0, no height is assigned.
	 * @param height The height to set.
	 */
	public abstract void setHeight(int height);

	/**
	 * Returns the width of the element. Returns 0 if the width is
	 * not specified.
	 * @return Returns the width.
	 */
	public abstract int getWidth();

	/**
	 * Sets the width of the element in 'px'. The width is set via the
	 * css width attribute. If set to 0, no width is assigned.
	 * @param width The width to set.
	 */
	public abstract void setWidth(int width);

	/**
	 * Indicates if the width of the control is 100% to fill the available space.
	 * @return Returns the fillWidth.
	 */
	public boolean isFillWidth();

	/**
	 * Sets the width of the control to 100% to fill the available space.
	 * @param fillWidth The fillWidth to set.
	 */
	public void setFillWidth(boolean fillWidth);
	
	/**
	 * Forces focus for this control. Returns <code>true</code> if the 
	 * control could have been set.
	 */
	public boolean forceFocus();
}