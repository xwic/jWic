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
 * de.jwic.controls.ISelectElement
 * Created on Jan 8, 2010
 * $Id: ISelectElement.java,v 1.1 2010/04/22 16:00:10 lordsam Exp $
 */
package de.jwic.data;

import de.jwic.base.ImageRef;


/**
 * Defines an element that can be selected in basic controls such as Combos, DropDown, ListBoxes, etc..
 * 
 * @author lippisch
 */
public interface ISelectElement {
	
	/**
	 * The custom key of the element.
	 * @return
	 */
	public String getKey();
	
	/**
	 * The title of the element.
	 * @return
	 */
	public String getTitle();
	
	/**
	 * The custom image.
	 * @return
	 */
	public ImageRef getImage();
	
	/**
	 * Returns true if the element can be selected.
	 * @return
	 */
	public boolean isSelectable();

}
