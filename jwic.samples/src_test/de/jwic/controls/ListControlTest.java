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
 * de.jwic.controls.ListControlTest
 * Created on 11.09.2005
 * $Id: ListControlTest.java,v 1.1 2006/09/14 20:23:19 lordsam Exp $
 */
package de.jwic.controls;

import de.jwic.base.Control;
import de.jwic.base.IControlContainer;
import de.jwic.events.ElementSelectedEvent;
import de.jwic.events.ElementSelectedListener;
import de.jwic.test.ControlTestCase;

/**
 * @author Florian Lippisch
 */
public class ListControlTest extends ControlTestCase {

	protected boolean eventTriggered = false;
	protected ListControl list = null;
	
	private class ListControlDummy extends ListControl {
		
		public ListControlDummy(IControlContainer container, String name) {
			super(container, name);
		}
	}
	
	/* (non-Javadoc)
	 * @see de.jwic.test.ControlTestCase#createControl(de.jwic.base.IControlContainer)
	 */
	public Control createControl(IControlContainer container) {
		list = new ListControlDummy(container, null);
		return list;
	}
	
	/**
	 * Test method for {@link de.jwic.controls.ListControl#addElement(java.lang.String, java.lang.String)}.
	 */
	public void testAddElement() {
		list.addElement("Title 1", "key1");
		list.addElement("Title 2", "key2");
		list.addElement("Title 3", "key3");
		
		assertEquals(3, list.getElements().size());
				
	}

	/**
	 * Test method for {@link de.jwic.controls.ListControl#clear()}.
	 */
	public void testClear() {

		list.addElement("Title 1", "key1");
		list.addElement("Title 2", "key2");
		list.addElement("Title 3", "key3");
		
		assertEquals(3, list.getElements().size());
		list.setSelectedKey("key1");
		
		list.clear();	
		
		assertEquals(0, list.getElements().size());
		// selected key must have been "cleared"
		assertEquals("", list.getSelectedKey());
		
	}

	/**
	 * Test method for {@link de.jwic.controls.ListControl#getSelectedKey()}.
	 */
	public void testGetSelectedKey() {

		list.addElement("Title 1", "key1");
		list.addElement("Title 2", "key2");
		list.addElement("Title 3", "key3");
		
		// method 1 -> direct
		list.setSelectedKey("key2");
		assertEquals("key2", list.getSelectedKey());
		
		// method 2 -> UI
		updateField(list, list.getField().getName(), "key3");
		updateDone();
		
		assertEquals("key3", list.getSelectedKey());
		
	}

	/**
	 * Test method for {@link de.jwic.controls.ListControl#getSelectedKeys()}.
	 */
	public void testGetSelectedKeys() {
		list.addElement("Title 1", "key1");
		list.addElement("Title 2", "key2");
		list.addElement("Title 3", "key3");
		
		// method 2 -> UI
		updateField(list, list.getField().getName(), new String[] {"key1", "key3"});
		updateDone();
		
		assertEquals(2, list.getSelectedKeys().length);
		assertEquals("key1", list.getSelectedKeys()[0]);
		assertEquals("key3", list.getSelectedKeys()[1]);
		
	}

	/**
	 * Test method for {@link de.jwic.controls.ListControl#isKeySelected(java.lang.String)}.
	 */
	public void testIsKeySelected() {
		list.addElement("Title 1", "key1");
		list.addElement("Title 2", "key2");
		list.addElement("Title 3", "key3");
		
		// method 2 -> UI
		updateField(list, list.getField().getName(), new String[] {"key1", "key3"});
		updateDone();
		
		assertTrue(list.isKeySelected("key1"));
		assertFalse(list.isKeySelected("key2"));
		assertTrue(list.isKeySelected("key3"));

	}

	/**
	 * Test method for {@link de.jwic.controls.ListControl#removeElement(java.lang.String)}.
	 */
	public void testRemoveElement() {
		list.addElement("Title 1", "key1");
		list.addElement("Title 2", "key2");
		list.addElement("Title 3", "key3");
		
		assertEquals(3, list.getElements().size());
		
		list.setSelectedKey("key2");
		list.removeElement("key1");
		list.removeElement("key2");
		
		assertEquals(1, list.getElements().size());
		assertEquals("", list.getSelectedKey());
		
	}

	 /**
	  * Test if the event is triggered correctly.
	  */
	public void testEvent() {
		
		list.addElement("Test 1", "1");
		list.addElement("Test 2", "2");
		
		list.addElementSelectedListener(new ElementSelectedListener() {
			public void elementSelected(ElementSelectedEvent event) {
				eventTriggered = true;
				assertEquals(list.getSelectedKey(), "2");
				assertEquals(event.getElement(), "2");
			}
		});
		
		updateField(list.getField().getName(), "2");
		updateDone();
		
		assertEquals("2", list.getSelectedKey());
		assertTrue(eventTriggered);
		
	}

	
}


