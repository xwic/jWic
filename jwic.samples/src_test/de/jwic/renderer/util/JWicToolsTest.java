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
 * de.jwic.renderer.util.JWicToolsTest
 * Created on 06.10.2006
 * $Id: JWicToolsTest.java,v 1.1 2006/10/06 08:52:01 lordsam Exp $
 */
package de.jwic.renderer.util;

import java.util.Locale;

import junit.framework.TestCase;

/**
 * @author Florian Lippisch
 * @version $Revision: 1.1 $
 */
public class JWicToolsTest extends TestCase {

	private JWicTools jwic = null;
	
	/* (non-Javadoc)
	 * @see junit.framework.TestCase#setUp()
	 */
	protected void setUp() throws Exception {
		super.setUp();
		jwic = new JWicTools(Locale.getDefault());
	}
	
	/*
	 * Test method for 'de.jwic.renderer.util.JWicTools.formatInp(String)'
	 */
	public void testFormatInp() {
		assertEquals("", jwic.formatInp(null)); // should not fail
	}

	/*
	 * Test method for 'de.jwic.renderer.util.JWicTools.formatHtml(String)'
	 */
	public void testFormatHtml() {
		assertEquals("", jwic.formatHtml(null)); // should not fail
	}

	/*
	 * Test method for 'de.jwic.renderer.util.JWicTools.formatTime(Date)'
	 */
	public void testFormatTime() {
		assertEquals("", jwic.formatTime(null)); // should not fail
	}

	/*
	 * Test method for 'de.jwic.renderer.util.JWicTools.formatDate(Date)'
	 */
	public void testFormatDate() {
		assertEquals("", jwic.formatDate(null)); // should not fail
	}

	/*
	 * Test method for 'de.jwic.renderer.util.JWicTools.formatDateTime(Date)'
	 */
	public void testFormatDateTime() {
		assertEquals("", jwic.formatDateTime(null)); // should not fail
	}

}
