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
 * de.jwic.base.TestLifecycle
 * Created on 08.11.2005
 * $Id: TestLifecycle.java,v 1.2 2008/06/12 13:39:18 lordsam Exp $
 */
package de.jwic.base;

import java.util.Locale;
import java.util.TimeZone;

import junit.framework.TestCase;
import de.jwic.test.TestJWicRuntimeProvider;

/**
 * @author Florian Lippisch
 * @version $Revision: 1.2 $
 */
public class TestLifecycle extends TestCase {

	private JWicRuntime rt = null;
	
	/* (non-Javadoc)
	 * @see junit.framework.TestCase#setUp()
	 */
	protected void setUp() throws Exception {
		super.setUp();
		
		rt = TestJWicRuntimeProvider.getJWicRuntime();
		
	}
	
	/**
	 * 
	 *
	 */
	public void testLifeCycle() {
		
		
		ApplicationSetupBean appSetup = new ApplicationSetupBean();
		appSetup.setClassname(TestApplication.class.getName());
		appSetup.setName("Testapp");
		
		SessionContext context = rt.createSessionContext(appSetup, Locale.getDefault(), TimeZone.getDefault(), null);
		
		assertNotNull(context.getTopControl());
		
		String sessionID = context.getSessionId();
		String clientID = context.getClientId();
		
		context.destroy(); // end the application
		
		context = rt.getSessionContext(clientID, sessionID);
		assertNull(context);
		
	}
	
}
