/*
 * Copyright 2005 jWic Group (http://www.jwic.de)
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
 * de.jwic.base.XmlApplicationSetupTest
 * Created on 24.03.2005
 * $Id: XmlApplicationSetupTest.java,v 1.2 2008/06/12 13:39:18 lordsam Exp $
 */
package de.jwic.base;

import java.io.IOException;
import java.io.StringReader;
import java.util.Locale;
import java.util.TimeZone;

import junit.framework.TestCase;

import org.xml.sax.InputSource;

/**
 * @author Florian Lippisch
 * @version $Revision: 1.2 $
 */
public class XmlApplicationSetupTest extends TestCase {

	public void testRead() throws IOException {
		
		String testString = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" + 
			"<application>\n" +
			" <name>TestModule</name>\n" +
			" <rootcontrol name=\"app\" classname=\"de.jwic.controls.ButtonControl\">\n" +
			" </rootcontrol>\n" +
			" <serializable>true</serializable>\n" +
			" <singlesession>true</singlesession>\n" +
			" <property name=\"any\">someValue</property>\n" +
			"</application>";
		
		StringReader reader = new StringReader(testString);
		XmlApplicationSetup setup = new XmlApplicationSetup(new InputSource(reader));
		
		SessionContext container = new SessionContext(setup, Locale.getDefault(), TimeZone.getDefault());
		
		assertEquals("TestModule", setup.getName());
		assertEquals("app", setup.getRootControlName());
		assertEquals("de.jwic.controls.ButtonControl", setup.createApplication().createRootControl(container).getClass().getName());
		assertEquals(true, setup.isSerializable());
		assertEquals(true, setup.isSingleSession());
		assertEquals("someValue", setup.getProperty("any"));
		
	}
	
}
