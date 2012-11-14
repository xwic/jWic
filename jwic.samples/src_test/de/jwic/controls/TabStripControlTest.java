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
 * de.jwic.controls.TabStripControlTest
 * Created on 11.09.2005
 * $Id: TabStripControlTest.java,v 1.1 2006/09/15 08:38:46 lordsam Exp $
 */
package de.jwic.controls;

import de.jwic.base.Control;
import de.jwic.base.IControlContainer;
import de.jwic.test.ControlTestCase;

/**
 * @author Florian Lippisch
 */
public class TabStripControlTest extends ControlTestCase {

	private TabStripControl tabStrip;
	
	/* (non-Javadoc)
	 * @see de.jwic.test.ControlTestCase#createControl(de.jwic.base.IControlContainer)
	 */
	public Control createControl(IControlContainer container) {
		tabStrip = new TabStripControl(container);
		return tabStrip;
	}
	
	/**
	 * Test method for {@link de.jwic.controls.TabStripControl#removeControl(java.lang.String)}.
	 */
	public void testRemoveControl() {

		TabControl tab1 = tabStrip.addTab("Tab 1");
		TabControl tab2 = tabStrip.addTab("Tab 2");
		tabStrip.addTab("Tab 3");
		
		assertEquals(3, tabStrip.getTabs().size());
		assertEquals(tab1.getName(), tabStrip.getActiveTabName());
		
		tab1.destroy(); // remove 1 tab

		assertEquals(2, tabStrip.getTabs().size());
		assertEquals(tab2.getName(), tabStrip.getActiveTabName());
		
		
	}

	/**
	 * Test method for {@link de.jwic.controls.TabStripControl#addTab(java.lang.String)}.
	 */
	public void testAddTabString() {
		
		tabStrip.addTab("Tab 1");
		tabStrip.addTab("Tab 2");
		tabStrip.addTab("Tab 3");
		
		assertEquals(3, tabStrip.getTabs().size());
		
		
	}

	/**
	 * Test method for {@link de.jwic.controls.TabStripControl#getActiveTabName()}.
	 */
	public void testGetActiveTabName() {

		TabControl tab1 = tabStrip.addTab("Tab 1");
		TabControl tab2 = tabStrip.addTab("Tab 2");
		TabControl tab3 = tabStrip.addTab("Tab 3");
		
		assertEquals(tab1.getName(), tabStrip.getActiveTabName());
		
		tabStrip.setActiveTabName(tab2.getName());
		assertEquals(tab2.getName(), tabStrip.getActiveTabName());
		
		sendAction(tabStrip, TabStripControl.ACTION_OPENTAB, tab3.getName());
		assertEquals(tab3.getName(), tabStrip.getActiveTabName());

	}

}
