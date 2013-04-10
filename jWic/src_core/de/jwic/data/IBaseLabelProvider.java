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
 * de.jwic.controls.combo.IObjectTitleProvider
 * Created on Mar 13, 2010
 * $Id: IBaseLabelProvider.java,v 1.1 2010/04/22 16:00:10 lordsam Exp $
 */
package de.jwic.data;

import java.io.Serializable;

/**
 * This provider returns the title for an object used in a Combo. The title of the
 * object is required to properly populate the text-field displaying the selection. 
 * @author lippisch
 */
public interface IBaseLabelProvider<A> extends Serializable {

	/**
	 * Returns the label used to display the object details.
	 * @param object
	 * @return
	 */
	public DataLabel getBaseLabel(A object);
	
}
