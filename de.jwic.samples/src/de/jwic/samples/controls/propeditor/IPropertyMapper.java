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
 * de.jwic.samples.controls.propeditor.IPropertyMapper
 * Created on 16.09.2008
 * $Id: IPropertyMapper.java,v 1.1 2008/09/16 21:55:43 lordsam Exp $
 */
package de.jwic.samples.controls.propeditor;

import de.jwic.base.Control;

/**
 *
 * @author Florian Lippisch
 */
public interface IPropertyMapper {

	/**
	 * Update the control with the beans property value. 
	 * @param bean
	 * @param control
	 */
	public void updateControlValue(Control control, Object value);
	
	/**
	 * Returns the control value.
	 * @param bean
	 * @param control
	 */
	public Object getControlValue(Control control);
	
}
