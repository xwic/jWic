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
 * de.jwic.samples.sample3.MemoryTest
 * Created on 18.05.2005
 * $Id: MemoryTest.java,v 1.3 2008/09/17 15:20:03 lordsam Exp $
 */
package de.jwic.samples.sample3;

import java.util.ArrayList;
import java.util.Locale;
import java.util.TimeZone;

import junit.framework.TestCase;
import de.jwic.base.ApplicationSetupBean;
import de.jwic.base.JWicRuntime;
import de.jwic.base.SessionContext;
import de.jwic.test.TestJWicRuntimeProvider;

/**
 * @author Florian Lippisch
 * @version $Revision: 1.3 $
 */
public class MemoryTest extends TestCase {

	private JWicRuntime rt = null;
	ApplicationSetupBean appSetup = new ApplicationSetupBean();
	
	/*
	 * @see TestCase#setUp()
	 */
	protected void setUp() throws Exception {

		appSetup.setName("test");
		appSetup.setRootControlName("test");
		appSetup.setRootControlClassName(TemperatureDemo.class.getName());
		
		rt = TestJWicRuntimeProvider.getJWicRuntime();
		
	}
	
	/**
	 * Tries to create as many instances of the TemperatureDemo as possible, testing how
	 * many instances can be loaded at one time.
	 *
	 */
	public void testLoad() throws Throwable{

		Runtime jrt = Runtime.getRuntime();
		System.out.println("total: " + jrt.totalMemory());
		System.out.println("free: " + jrt.freeMemory());
		System.out.println("used: " + (jrt.totalMemory() - jrt.freeMemory()));
		
		int max = 20000;
		ArrayList<SessionContext> scs = new ArrayList<SessionContext>();
		try {
			for (int i = 0; i < max; i++) {
	
				SessionContext sc = rt.createSessionContext(appSetup, new Locale("en", "EN"), TimeZone.getDefault(), null);
				scs.add(sc);
				
			}
		} catch (Throwable t) {
			System.out.println("scs size:" + scs.size());
			throw t;
		}

		System.out.println("total: " + jrt.totalMemory());
		System.out.println("free: " + jrt.freeMemory());
		System.out.println("used: " + (jrt.totalMemory() - jrt.freeMemory()));
		
		System.out.println("created " + scs.size());
		

	}

}
