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
 * de.jwic.base.ControlTest
 * Created on 11.09.2005
 * $Id: ControlTest.java,v 1.1 2006/09/14 20:23:19 lordsam Exp $
 */
package de.jwic.base;

import de.jwic.test.ControlTestCase;

/**
 * Tests the basic features of the Control object. 
 * @author Florian Lippisch
 */
public class ControlTest extends ControlTestCase {

	/* (non-Javadoc)
	 * @see de.jwic.test.ControlTestCase#createControl(de.jwic.base.IControlContainer)
	 */
	public Control createControl(IControlContainer container) {
		return new ControlContainer(container);
	}

	/**
	 * Test the automatic action invocation.
	 */
	public void testActionInvoke() {
		
		ControlContainer root = (ControlContainer)control;
		ActionTestControl atc = new ActionTestControl(root, "atc");
		
		sendAction(atc, "test", "");
		assertTrue(atc.invoked);
		
		atc.invoked = false;
		sendAction(atc, "Test", "");
		assertTrue(atc.invoked);

		try {
			atc.invoked = false;
			sendAction(atc, "TEST", "");
			fail("No error raised on call of unknown method.");
			
		} catch (IllegalArgumentException iae) {
			// that was expected.
		}
		
	}
	
	/**
	 * Test if a control is successfully removed when it is destroyed.
	 */
	public void testDestroy() {
		
		ControlContainer root = (ControlContainer)control;
		
		DummyControl dummy = new DummyControl(root, "dummy");
		
		assertNotNull(root.getControl("dummy"));
		
		dummy.destroy();
		
		assertNull(root.getControl("dummy"));
		
	}

	/**
	 * Test if a control is successfully destroyed when its removed from a container.
	 */
	public void testRemoveControl() {
		
		ControlContainer root = (ControlContainer)control;
		
		DummyControl dummy = new DummyControl(root, "dummy");
		
		assertNotNull(root.getControl("dummy"));
		
		root.removeControl(dummy.getName());
		
		assertNull(root.getControl("dummy"));
		assertTrue(dummy.destroyed);
		
	}
	

	/*
	 * Small controls that are used to test certain functions.
	 */

	/**
	 * This class is used to test the basic features of the Control object.
	 * @author Florian Lippisch
	 */
	private class ActionTestControl extends Control {
		public boolean invoked = false;
		/**
		 * Constructor. 
		 */
		public ActionTestControl(IControlContainer container, String name) {
			super(container, name);
		}
		
		public void actionTest(String param) {
			invoked = true;
		}
	}

	/**
	 * Simple dummy implementation.
	 * @author Florian Lippisch
	 */
	private class DummyControl extends Control {
		public boolean destroyed = false;
		/**
		 * @param container
		 * @param name
		 */
		public DummyControl(IControlContainer container, String name) {
			super(container, name);
		}
		/* (non-Javadoc)
		 * @see de.jwic.base.Control#destroy()
		 */
		public void destroy() {
			super.destroy();
			destroyed = true;
		}
	}
	
}
