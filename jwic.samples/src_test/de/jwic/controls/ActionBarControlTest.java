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
 * de.jwic.controls.ActionBarControlTest
 * Created on 11.09.2005
 * $Id: ActionBarControlTest.java,v 1.3 2010/01/26 11:25:18 lordsam Exp $
 */
package de.jwic.controls;

import java.util.Iterator;

import de.jwic.base.Control;
import de.jwic.base.IControlContainer;
import de.jwic.test.ControlTestCase;

/**
 * @author Florian Lippisch
 */
public class ActionBarControlTest extends ControlTestCase {

	private ActionBarControl actionBar = null;
	
	/* (non-Javadoc)
	 * @see de.jwic.test.ControlTestCase#createControl(de.jwic.base.IControlContainer)
	 */
	public Control createControl(IControlContainer container) {
		actionBar = new ActionBarControl(container);
		return actionBar;
	}
	
	/**
	 * Adding controls in the right order.
	 */
	public void testAdd() {
		
		Button btOpen = new Button(actionBar);
		Button btSave = new Button(actionBar);
		Button btExit = new Button(actionBar);
		
		assertEquals(3, actionBar.getElements().size());
		Iterator<?> it = actionBar.getElements().iterator();
		
		assertSame(btOpen, it.next());
		assertSame(btSave, it.next());
		assertSame(btExit, it.next());
		
	}

	/**
	 * Removing controls from the ActionBar.
	 */
	public void testRemove() {
		
		Button btOpen = new Button(actionBar);
		Button btSave = new Button(actionBar);
		Button btExit = new Button(actionBar);
		
		assertEquals(3, actionBar.getElements().size());

		btSave.destroy(); // remove
		assertEquals(2, actionBar.getElements().size());
		
		Iterator<?> it = actionBar.getElements().iterator();
		
		assertSame(btOpen, it.next());
		assertSame(btExit, it.next());
		
	}

}

