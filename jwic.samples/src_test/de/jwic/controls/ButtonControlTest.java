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
 * de.jwic.controls.ButtonControlTest
 * Created on 11.09.2005
 * $Id: ButtonControlTest.java,v 1.5 2011/09/09 15:09:05 adrianionescu12 Exp $
 */
package de.jwic.controls;

import de.jwic.base.Control;
import de.jwic.base.IControlContainer;
import de.jwic.base.ImageRef;
import de.jwic.events.SelectionEvent;
import de.jwic.events.SelectionListener;
import de.jwic.test.ControlTestCase;

/**
 * @author Florian Lippisch
 */
public class ButtonControlTest extends ControlTestCase {

	private Button ctrl = null;
	private boolean clicked = false;


	/* (non-Javadoc)
	 * @see de.jwic.test.ControlTestCase#createControl(de.jwic.base.IControlContainer)
	 */
	public Control createControl(IControlContainer container) {
		ctrl = new Button(container);
		return ctrl;
	}
	
	/**
	 * Test method for {@link de.jwic.controls.Button#setTitle(java.lang.String)}.
	 */
	public void testSetTitle() {
		ctrl.setTitle("MyTitle");
		assertEquals("MyTitle", ctrl.getTitle());
	}

	/**
	 * Test method for {@link de.jwic.controls.Button#getIconUrl()}.
	 */
	public void testGetIconUrl() {
		
		ctrl.setIconEnabled(new ImageRef("enabled"));
		ctrl.setIconDisabled(new ImageRef("disabled"));
		
		ctrl.setEnabled(true);
		assertEquals("enabled", ctrl.getIcon().getPath());
		ctrl.setEnabled(false);
		assertEquals("disabled", ctrl.getIcon().getPath());

	}

	/**
	 * Test method for {@link de.jwic.controls.Button#hasIcon()}.
	 */
	public void testHasIcon() {
		assertFalse(ctrl.hasIcon());
		ctrl.setIconEnabled(new ImageRef(""));
		assertTrue(ctrl.hasIcon());
	}

	/**
	 * Test method for {@link de.jwic.controls.Button#setSubmitButton(boolean)}.
	 */
	public void testSetSubmitButton() {
		ctrl.setSubmitButton(true);
		assertEquals(true, ctrl.isSubmitButton());
		ctrl.setSubmitButton(false);
		assertEquals(false, ctrl.isSubmitButton());
	}

	/**
	 * Test method for {@link de.jwic.controls.Button#setTooltip(java.lang.String)}.
	 */
	public void testSetTooltip() {
		ctrl.setTooltip("expected");
		assertEquals("expected", ctrl.getTooltip());
	}

	/**
	 * Test method for {@link de.jwic.controls.Button#setConfirmMsg(java.lang.String)}.
	 */
	public void testSetConfirmMsg() {
		ctrl.setConfirmMsg("expected");
		assertEquals("expected", ctrl.getConfirmMsg());
	}

	/**
	 * Test method for {@link de.jwic.controls.SelectableControl#click()}.
	 */
	public void testClick() {
		ctrl.addSelectionListener(new SelectionListener() {
			public void objectSelected(SelectionEvent event) {
				clicked = true;
			}
		});
		
		// method 1 
		ctrl.click();
		assertTrue(clicked);
		
		clicked = false;
		sendAction(control, "click", "");
		assertTrue(clicked);
	}

}
