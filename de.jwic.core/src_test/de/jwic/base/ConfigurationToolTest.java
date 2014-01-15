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
 * de.jwic.base.ConfigurationToolTest
 * $Id: ConfigurationToolTest.java,v 1.1 2006/01/16 08:30:40 lordsam Exp $
 */
package de.jwic.base;

import java.util.Properties;

import junit.framework.TestCase;

/**
 * Tests the ConfigurationTool.
 * @author Florian Lippisch
 * @version $Revision: 1.1 $
 */
public class ConfigurationToolTest extends TestCase {

	public void testReplace() {
		ConfigurationTool.setRootPath("c:/test/");
		Properties prop = new Properties();
		prop.setProperty("path", "${rootPath}WEB-INF/jwic");
		ConfigurationTool.insertRootPath(prop);
		
		assertEquals(prop.getProperty("path"), "c:/test/WEB-INF/jwic");
	}
	
}
