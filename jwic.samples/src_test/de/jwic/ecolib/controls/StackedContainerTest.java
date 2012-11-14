/*
 * Copyright 2006 jWic group (http://www.jwic.de)
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
 * de.jwic.ecolib.controls.StackedContainerTest.java
 * Created on 15.09.2006
 * $Id: StackedContainerTest.java,v 1.2 2010/02/07 14:26:33 lordsam Exp $
 * @author Lord Sam
 */

package de.jwic.ecolib.controls;

import de.jwic.base.Control;
import de.jwic.base.IControlContainer;
import de.jwic.controls.Button;
import de.jwic.test.ControlTestCase;

/* Created on 15.09.2006
 * @author Lord Sam
 */

public class StackedContainerTest extends ControlTestCase {

	private StackedContainer cont = null;
	/* (non-Javadoc)
	 * @see de.jwic.test.ControlTestCase#createControl(de.jwic.base.IControlContainer)
	 */
	public Control createControl(IControlContainer container) {
		cont = new StackedContainer(container);
		return cont;
	}
	
	/**
	 * Test method for {@link de.jwic.ecolib.controls.StackedContainer#getCurrentControlName()}.
	 */
	public void testGetCurrentControlName() {
		
		Button bt1 = new Button(cont, "button1");
		Button bt2 = new Button(cont, "button2");
		
		assertEquals(bt1.getName(), cont.getCurrentControlName());
		
		cont.setCurrentControlName(bt2.getName());
		assertEquals(bt2.getName(), cont.getCurrentControlName());
		
	}
	
	public void testRemoveControl() {

		Button bt1 = new Button(cont, "button1");
		Button bt2 = new Button(cont, "button2");
		
		assertEquals(bt1.getName(), cont.getCurrentControlName());
		
		bt1.destroy();
		
		assertEquals(bt2.getName(), cont.getCurrentControlName());
		
	}
}
