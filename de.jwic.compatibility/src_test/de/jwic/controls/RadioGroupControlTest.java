/*
 * Copyright 2005-2006 jWic group (http://www.jwic.de)
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
 * de.jwic.controls.RadioGroupControlTest
 * Created on 11.09.2005
 * $Id: RadioGroupControlTest.java,v 1.1 2006/09/15 08:38:46 lordsam Exp $
 */
package de.jwic.controls;

import de.jwic.base.Control;
import de.jwic.base.IControlContainer;

/**
 * This test just applies the tests for ListControl upon the RadioGroupControl.
 * @author Florian Lippisch
 */
public class RadioGroupControlTest extends ListControlTest {

	/* (non-Javadoc)
	 * @see de.jwic.controls.ListControlTest#createControl(de.jwic.base.IControlContainer)
	 */
	public Control createControl(IControlContainer container) {
		list = new RadioGroupControl(container);
		return list;
	}
	
}
