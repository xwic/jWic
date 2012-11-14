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
 * de.jwic.base.SessionStorageTest
 * Created on 08.11.2005
 * $Id: SessionStorageTest.java,v 1.2 2008/06/12 13:39:18 lordsam Exp $
 */
package de.jwic.base;

import java.util.Locale;
import java.util.TimeZone;

import de.jwic.test.TestEnvironment;
import junit.framework.TestCase;

/**
 * @author Florian Lippisch
 * @version $Revision: 1.2 $
 */
public class SessionStorageTest extends TestCase {
	
	private SessionManager manager = null; 
	
	/* (non-Javadoc)
	 * @see junit.framework.TestCase#setUp()
	 */
	protected void setUp() throws Exception {
		super.setUp();
		
		TestEnvironment env = TestEnvironment.getTestEnvironment();
		manager = new SessionManager(env.getProperty("session.store.dir"));
	}
	
	/**
	 * Simply create a storage.
	 *
	 */
	public void testCreateRead() {
		
		
		String clientID = "client1";
		String appID = "testapp";
		
		SessionContainer store = manager.create(clientID, appID);
		assertNotNull(store);
		System.out.println("Storage created: " + store.getId());
		
		String id = store.getId();
		store = manager.get(clientID, id);
		assertNotNull(store);
		assertEquals(id, store.getId());
		
		
	}

	/**
	 * Simply create a storage.
	 *
	 */
	public void testGetByApp() {
		
		String clientID = "client1";
		String appID = "testapp";
		
		SessionContainer store = manager.create(clientID, appID);
		
		assertNotNull(store);
		
		System.out.println("Storage created: " + store.getId());
		
		store = manager.getByAppID(clientID, appID);
		assertNotNull(store);
		System.out.println("Found.");
		
	}
	
	/**
	 * Create & Remove it.
	 *
	 */
	public void testRemove() {

		String clientID = "client1";
		String appID = "testapp";
		
		SessionContainer store = manager.create(clientID, appID);
		assertNotNull(store);
		System.out.println("Storage created: " + store.getId());
		
		String id = store.getId();
		
		manager.remove(store);
		
		store = manager.get(clientID, id);
		assertNull(store);
		
	}
	
	/**
	 * Create & Remove it.
	 *
	 */
	public void testSerialization() {

		String clientID = "client1";
		String appID = "testapp";
		
		SessionContainer store = manager.create(clientID, appID);
		assertNotNull(store);
		System.out.println("Storage created: " + store.getId());

		ApplicationSetupBean appsetup = new ApplicationSetupBean();
		appsetup.setName("test");
		
		SessionContext sc = new SessionContext(appsetup, Locale.getDefault(), TimeZone.getDefault());
		store.setSessionContext(sc);
		assertEquals(SessionContainer.STATE_NORMAL, store.getState());
		
		manager.serialize(store);
		assertEquals(SessionContainer.STATE_STORED, store.getState());
		
		assertNull(store.getSessionContext());
		
		manager.deserialize(store);
		
		assertEquals(SessionContainer.STATE_NORMAL, store.getState());
		assertNotNull(store.getSessionContext());
		
	}
	
}
