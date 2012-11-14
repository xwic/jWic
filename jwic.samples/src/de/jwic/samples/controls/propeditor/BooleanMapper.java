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
 * de.jwic.samples.controls.propeditor.LabelControlMapper
 * Created on 16.09.2008
 * $Id: BooleanMapper.java,v 1.1 2008/09/16 21:55:44 lordsam Exp $
 */
package de.jwic.samples.controls.propeditor;

import de.jwic.base.Control;
import de.jwic.controls.ListBoxControl;

/**
 *
 * @author Developer
 */
public class BooleanMapper extends AbstractPropertyMapper implements IPropertyMapper {

	/* (non-Javadoc)
	 * @see de.jwic.samples.controls.propeditor.IPropertyMapper#getControlValue(de.jwic.base.Control)
	 */
	public Object getControlValue(Control control) {
		ListBoxControl lbc = (ListBoxControl)control;
		return new Boolean("true".equals(lbc.getSelectedKey()));
	}

	/* (non-Javadoc)
	 * @see de.jwic.samples.controls.propeditor.IPropertyMapper#updateControlValue(de.jwic.base.Control, java.lang.Object)
	 */
	public void updateControlValue(Control control, Object value) {
		ListBoxControl lbc = (ListBoxControl)control;
		lbc.setSelectedKey(value != null && ((Boolean)value).booleanValue() ? "true" : "false");
	}

}
