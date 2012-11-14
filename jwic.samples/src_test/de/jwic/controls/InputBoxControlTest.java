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
 * de.jwic.controls.InputBoxControlTest
 * Created on 11.09.2005
 * $Id: InputBoxControlTest.java,v 1.1 2006/09/15 08:38:46 lordsam Exp $
 */
package de.jwic.controls;

import de.jwic.base.Control;
import de.jwic.base.IControlContainer;
import de.jwic.base.Page;
import de.jwic.events.ValueChangedEvent;
import de.jwic.events.ValueChangedListener;
import de.jwic.test.ControlTestCase;

/**
 * @author Florian Lippisch
 */
public class InputBoxControlTest extends ControlTestCase {

	private InputBoxControl inp = null;
	private boolean triggered = false;
	
	/* (non-Javadoc)
	 * @see de.jwic.test.ControlTestCase#createControl(de.jwic.base.IControlContainer)
	 */
	public Control createControl(IControlContainer container) {
		inp = new InputBoxControl(container);
		return inp;
	}
	
	/**
	 * Test method for {@link de.jwic.controls.InputBoxControl#forceFocus()}.
	 */
	public void testForceFocus() {

		inp.forceFocus();
		assertEquals(inp.getField().getId(), ((Page)sc.getTopControl()).getForceFocusElement());
		
	}

	/**
	 * Test method for {@link de.jwic.controls.InputBoxControl#addValueChangedListener(de.jwic.events.ValueChangedListener)}.
	 */
	public void testAddValueChangedListener() {

		ValueChangedListener listener = new ValueChangedListener() {
			public void valueChanged(ValueChangedEvent event) {
				triggered = true;
			}
		};
		inp.addValueChangedListener(listener);
		
		triggered = false;
		
		updateField(inp.getField().getName(), "123");
		updateDone();
		
		assertTrue(triggered);
		triggered = false;
		
		updateField(inp.getField().getName(), "123");	// same value
		updateDone();
		
		assertFalse(triggered); // not changed!
		
		triggered = false;
		inp.removeValueChangedListener(listener);
		
		updateField(inp.getField().getName(), "new value");	// same value
		updateDone();
		
		assertFalse(triggered);
		
	}

}
