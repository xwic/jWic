/*******************************************************************************
 * Copyright 2015 xWic group (http://www.xwic.de)
 *
 * Licensed under the Apache License, Version 2.0 (the "License").
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * 		http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software 
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and 
 * limitations under the License.
 *  
 *******************************************************************************/
package de.jwic.test;

import java.util.Locale;
import java.util.TimeZone;

import junit.framework.TestCase;
import de.jwic.base.ApplicationSetupBean;
import de.jwic.base.Control;
import de.jwic.base.IActionController;
import de.jwic.base.IControlContainer;
import de.jwic.base.JWicRuntime;
import de.jwic.base.Page;
import de.jwic.base.SessionContext;
import de.jwic.base.ValueChangedQueue;

/**
 * Tool class that is usefull to test controls. The control's test-class must
 * be created in the createControl(..) method. The test methods can then
 * access the control using the protected 'control' field. The control can
 * be modified like this: 
 * 
 *  MyControl myControl = (MyControl)control;
 *  updateField("field1", "value");
 *  updateField("text", "abc");
 *  updateDone();
 *  sendAction(control, "click", "1");
 *  
 *  assertEquals(myControl.getText(), "abc");
 * 
 * @author Florian Lippisch
 */
public abstract class ControlTestCase extends TestCase {

	protected SessionContext sc = null;
	protected IActionController ac = null;
	protected ValueChangedQueue queue = null;

	protected Control control = null;

	/**
	 * Must return the class that should be tested.
	 * @return
	 */
	public abstract Control createControl(IControlContainer container);
	
	/*
	 * (non-Javadoc)
	 * @see junit.framework.TestCase#setUp()
	 */
	protected void setUp() throws Exception {
		super.setUp();
		
		ApplicationSetupBean appSetup = new ApplicationSetupBean();
		appSetup.setName("test");
		appSetup.setRootControlName("root");
		appSetup.setRootControlClassName(Page.class.getName());
		
		JWicRuntime rt = TestJWicRuntimeProvider.getJWicRuntime();
		
		sc = rt.createSessionContext(appSetup, new Locale("en", "EN"), TimeZone.getDefault(), null);
		control = createControl((Page)sc.getTopControl());
		ac = sc.getActionController();
	}

	/* (non-Javadoc)
	 * @see junit.framework.TestCase#tearDown()
	 */
	protected void tearDown() throws Exception {
		super.tearDown();
		sc.destroy(); // destroy the session.
	}
	
	/**
	 * Simulate the update of a field. You must call updateDone() after all fields of
	 * the controls have been set to invoke pending listeners.
	 * @param control
	 * @param fieldName
	 * @param value
	 */
	protected void updateField(String fieldName, String value) {
		updateField(control, fieldName, value);
	}
	
	/**
	 * Simulate the update of a field. You must call updateDone() after all fields of
	 * the controls have been set to invoke pending listeners.
	 * @param control
	 * @param fieldName
	 * @param value
	 */
	protected void updateField(Control control, String fieldName, String value) {
		updateField(control, fieldName, new String[] { value });
	}
	
	/**
	 * Simulate the update of a field. You must call updateDone() after all fields of
	 * the controls have been set to invoke pending listeners.
	 * @param control
	 * @param fieldName
	 * @param value
	 */
	protected void updateField(Control control, String fieldName, String value[]) {
		
		if (queue == null) {
			queue = new ValueChangedQueue();
		}
		ac.handleField(sc, queue, "fld_" + control.getControlID() + "." + fieldName, value);
		
	}

	/**
	 * Processes the updateQueue and invokes pending valueChanged listeners.
	 *
	 */
	protected void updateDone() {
		if (queue != null) {
			queue.processQueue();
			queue = null;
		}
	}
	
	/**
	 * Simulates a fireAction on the specified control.
	 * @param control
	 * @param action
	 * @param param
	 */
	protected void sendAction(Control control, String action, String param) {
		if (queue != null) {
			throw new IllegalStateException("A queue is still open! Must call updateDone() first");
		}
		ac.handleAction(sc, control.getControlID(), action, param);
	}
}
