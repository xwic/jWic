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
 * de.jwic.controls.DateInputBoxControlTest
 * Created on 11.09.2005
 * $Id: DateInputBoxControlTest.java,v 1.1 2006/09/15 08:38:46 lordsam Exp $
 */
package de.jwic.controls;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import de.jwic.base.Control;
import de.jwic.base.IControlContainer;
import de.jwic.test.ControlTestCase;

/**
 * @author Florian Lippisch
 */
public class DateInputBoxControlTest extends ControlTestCase {

	private DateInputBoxControl dinp = null;
	
	/* (non-Javadoc)
	 * @see de.jwic.test.ControlTestCase#createControl(de.jwic.base.IControlContainer)
	 */
	public Control createControl(IControlContainer container) {
		dinp = new DateInputBoxControl(container);
		return dinp;
	}
	
	/**
	 * Test method for {@link de.jwic.controls.DateInputBoxControl#getDate()}.
	 */
	public void testGetDate() {

		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, 2006);
		cal.set(Calendar.MONTH, Calendar.JUNE);
		cal.set(Calendar.DAY_OF_MONTH, 16);
		
		Date date = cal.getTime();
		dinp.setDate(date);
		
		Date returnDate = dinp.getDate();
		assertNotNull(returnDate);
		
		cal.setTime(returnDate);
		assertEquals(2006, cal.get(Calendar.YEAR));
		assertEquals(Calendar.JUNE, cal.get(Calendar.MONTH));
		assertEquals(16, cal.get(Calendar.DAY_OF_MONTH));
		
	}

	/**
	 * Test method for {@link de.jwic.controls.DateInputBoxControl#isValid()}.
	 */
	public void testIsValid() {

		// use german locale for test
		sc.setLocale(Locale.GERMAN);
		
		dinp.setText("");
		assertTrue(dinp.isValid());
		assertNull(dinp.getDate());
		
		dinp.setText("bla");
		assertFalse(dinp.isValid());
		assertNull(dinp.getDate());
		
		dinp.setText("16.06.2006");
		assertTrue(dinp.isValid());
		assertNotNull(dinp.getDate());
		
	}

}
