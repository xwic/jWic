/*******************************************************************************
 * Copyright 2015 xWic group (http://www.xwic.de)
 *
 * Licensed under the Apache License, Version 2.0 (the "License").
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * 		http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software 
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and 
 * limitations under the License.
 *  
 *******************************************************************************/
package de.jwic.controls;

import de.jwic.base.Control;
import de.jwic.base.IControlContainer;
import de.jwic.events.SelectionEvent;
import de.jwic.events.SelectionListener;
import de.jwic.test.ControlTestCase;

/**
 * @author Florian Lippisch
 */
public class AnchorLinkControlTest extends ControlTestCase {

	private AnchorLink link = null;
	private boolean clicked = false;
	
	/* (non-Javadoc)
	 * @see de.jwic.test.ControlTestCase#createControl(de.jwic.base.IControlContainer)
	 */
	public Control createControl(IControlContainer container) {
		link = new AnchorLink(container);
		return link;
	}

/**
	 * Test method for {@link de.jwic.controls.AnchorLink#setTitle(java.lang.String)}.
	 */
	public void testSetTitle() {
		link.setTitle("MyTitle");
		assertEquals("MyTitle", link.getTitle());
	}

	/**
	 * Test method for {@link de.jwic.controls.AnchorLink#setInfoMessage(java.lang.String)}.
	 */
	public void testSetInfoMessage() {
		link.setInfoMessage("Info Message");
		assertEquals("Info Message", link.getInfoMessage());
	}

	/**
	 * Test method for {@link de.jwic.controls.SelectableControl#click()}.
	 */
	public void testClick() {
		link.addSelectionListener(new SelectionListener() {
			public void objectSelected(SelectionEvent event) {
				clicked = true;
			}
		});
		
		// method 1 
		link.click();
		assertTrue(clicked);
		
		clicked = false;
		sendAction(control, "click", "");
		assertTrue(clicked);
		
	}
}
