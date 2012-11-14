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
 * de.jwic.controls.CheckboxControlTest
 * Created on 11.09.2005
 * $Id: CheckboxControlTest.java,v 1.1 2006/09/14 20:23:19 lordsam Exp $
 */
package de.jwic.controls;

import de.jwic.base.Control;
import de.jwic.base.Field;
import de.jwic.base.IControlContainer;
import de.jwic.events.ElementSelectedEvent;
import de.jwic.events.ElementSelectedListener;

/**
 * @author Florian Lippisch
 */
public class CheckboxControlTest extends ListControlTest {

	private CheckboxControl chkBox = null;
	
	/* (non-Javadoc)
	 * @see de.jwic.controls.ListControlTest#createControl(de.jwic.base.IControlContainer)
	 */
	public Control createControl(IControlContainer container) {
		chkBox = new CheckboxControl(container);
		list = chkBox; // the basic tests use the list field...
		return list;
	}
	
	/**
	 * Test method for {@link de.jwic.controls.CheckboxControl#getFieldByKey(java.lang.String)}.
	 */
	public void testGetFieldByKey() {
		
		chkBox.addElement("title1", "key1");
		chkBox.addElement("title2", "key2");
		chkBox.addElement("title3", "key3");
		
		Field field = chkBox.getFieldByKey("key2");
		
		assertEquals("", field.getValue());
		
		chkBox.setSelectedKey("key2");
		
		assertEquals("key2", field.getValue());
		
		
	}

	/**
	 * This test must override the default ListControl test because the
	 * checkbox handles the fields different.
	 * 
	 * Test method for {@link de.jwic.controls.ListControl#getSelectedKey()}.
	 */
	public void testGetSelectedKey() {

		chkBox.addElement("Title 1", "key1");
		chkBox.addElement("Title 2", "key2");
		chkBox.addElement("Title 3", "key3");
		
		// method 1 -> direct
		chkBox.setSelectedKey("key2");
		assertEquals("key2", chkBox.getSelectedKey());
		
		chkBox.setSelectedKey(""); // clear selection
		
		// method 2 -> UI
		updateField(chkBox, chkBox.getFieldByKey("key3").getName(), "key3");
		updateDone();
		
		assertEquals("key3", chkBox.getSelectedKey());
		
	}

	/**
	 * Test method for {@link de.jwic.controls.ListControl#getSelectedKeys()}.
	 */
	public void testGetSelectedKeys() {
		chkBox.addElement("Title 1", "key1");
		chkBox.addElement("Title 2", "key2");
		chkBox.addElement("Title 3", "key3");
		
		// method 2 -> UI
		updateField(chkBox, chkBox.getFieldByKey("key1").getName(), "key1");
		updateField(chkBox, chkBox.getFieldByKey("key3").getName(), "key3");
		updateDone();
		
		assertEquals(2, chkBox.getSelectedKeys().length);
		assertEquals("key1", chkBox.getSelectedKeys()[0]);
		assertEquals("key3", chkBox.getSelectedKeys()[1]);
		
	}
	
	/**
	 * Test method for {@link de.jwic.controls.ListControl#isKeySelected(java.lang.String)}.
	 */
	public void testIsKeySelected() {
		chkBox.addElement("Title 1", "key1");
		chkBox.addElement("Title 2", "key2");
		chkBox.addElement("Title 3", "key3");
		
		// method 2 -> UI
		updateField(chkBox, chkBox.getFieldByKey("key1").getName(), "key1");
		updateField(chkBox, chkBox.getFieldByKey("key3").getName(), "key3");
		updateDone();
		
		assertTrue(chkBox.isKeySelected("key1"));
		assertFalse(chkBox.isKeySelected("key2"));
		assertTrue(chkBox.isKeySelected("key3"));

	}


	
	 /**
	  * Test if the event is triggered correctly.
	  */
	public void testEvent() {
		
		chkBox.addElement("Test 1", "1");
		chkBox.addElement("Test 2", "2");
		
		chkBox.addElementSelectedListener(new ElementSelectedListener() {
			public void elementSelected(ElementSelectedEvent event) {
				eventTriggered = true;
				assertEquals(chkBox.getSelectedKey(), "2");
				assertEquals(event.getElement(), "2");
			}
		});
		
		updateField(chkBox.getFieldByKey("2").getName(), "2");
		updateDone();
		
		assertEquals("2", chkBox.getSelectedKey());
		assertTrue(eventTriggered);
		
	}

	
	
	/**
	 * Test method for {@link de.jwic.controls.CheckboxControl#isDoBreak(int)}.
	 */
	public void testIsDoBreak() {
		chkBox.setColumns(2);
		assertFalse(chkBox.isDoBreak(1)); // velocity starts counting with 1
		assertTrue(chkBox.isDoBreak(2));
		assertFalse(chkBox.isDoBreak(3));
	}

}
