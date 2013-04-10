/*
 * Copyright 2005 jWic group (http://www.jwic.de)
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
 * de.jwic.base.ControlMovementTest
 * Created on 26.09.2006
 * $Id: ControlMovementTest.java,v 1.1 2006/09/26 14:26:56 lordsam Exp $
 */
package de.jwic.base;

import de.jwic.controls.InputBoxControl;
import de.jwic.test.ControlTestCase;

/**
 * @author Florian Lippisch
 * @version $Revision: 1.1 $
 */
public class ControlMovementTest extends ControlTestCase {

	private ControlContainer cont1;
	private ControlContainer cont2;
	private ControlContainer childContainer;
	private InputBoxControl child;
	
	/* (non-Javadoc)
	 * @see de.jwic.test.ControlTestCase#createControl(de.jwic.base.IControlContainer)
	 */
	public Control createControl(IControlContainer container) {
		
		cont1 = new ControlContainer(container, "cont1");
		cont2 = new ControlContainer(container, "cont2");
		childContainer = new ControlContainer(cont1, "childCont");
		child = new InputBoxControl(childContainer, "inp");
		return cont1;
	}

	public void testMove() {
		
		assertSame(childContainer.getContainer(), cont1);
		
		cont2.adopt(childContainer, "childCont");
		
		assertSame(childContainer.getContainer(), cont2);
		assertNull(cont1.getControl("childCont"));
		
	}
	
	public void testIDs() {
		
		assertEquals("root.cont1.childCont", childContainer.getControlID());
		assertEquals("root.cont1.childCont.inp", child.getControlID());
		
		cont2.adopt(childContainer, "childCont");
		
		assertEquals("root.cont2.childCont", childContainer.getControlID());
		
		// child IDs
		assertEquals("root.cont2.childCont.inp", child.getControlID());
		// field IDS
		assertEquals("fld_root.cont2.childCont.inp.0", child.getField().getId());
		
	}
	
}
