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

import java.lang.reflect.Constructor;

import de.jwic.base.Control;
import de.jwic.base.ControlContainer;
import de.jwic.base.IApplication;
import de.jwic.base.IControlContainer;
import de.jwic.base.JWicException;
import de.jwic.base.Page;
import de.jwic.controls.Label;

/**
 * Launches a jwic application from the URL. The TestLauncher is just a plain
 * container that adds one child control specified in the starting URL. This is 
 * helpfull if you want to quickly test a control without creating an 
 * ApplicationSetup for it.
 * 
 * @author Florian Lippisch
 * @version $Revision: 1.3 $
 */
public class TestLauncher extends Page {

	private static final long serialVersionUID = 1L;

	/**
	 * @param container
	 */
	public TestLauncher(IControlContainer container) {
		super(container);
		init();
	}
	/**
	 * @param container
	 * @param name
	 */
	public TestLauncher(IControlContainer container, String name) {
		super(container, name);
		init();
	}

	/* (non-Javadoc)
	 * @see de.jwic.base.ControlContainer#actionPerformed(java.lang.String, java.lang.String)
	 */
	public void actionPerformed(String actionId, String parameter) {
		
		if (actionId.equals("exit")) {
			getSessionContext().exit();
		}
		
	}
	
	/* (non-Javadoc)
	 * @see de.jwic.base.Page#init()
	 */
	private void init() {
		
		String classname = getSessionContext().getInitParameter("class");
		if (classname == null) {
			throw new JWicException("parameter class missing.");
		}
		
		try {
			Class<?> clazz = Class.forName(classname);
			if (IApplication.class.isAssignableFrom(clazz)) {
				// create application
				ControlContainer container = new ControlContainer(this, "app");
				IApplication app = (IApplication)clazz.newInstance();
				app.initialize(getSessionContext());
				app.createRootControl(container);
				
			} else if (Control.class.isAssignableFrom(clazz)) {
				Constructor<?> cstr = clazz.getConstructor(new Class[] { IControlContainer.class, String.class} );
				cstr.newInstance(new Object[] { this, "app" });
				
			} else {
				throw new IllegalArgumentException("Specified class is not an application nor a control.");
			}
		} catch (Exception e) {
			removeControl("app");
			Label lbl = new Label(this, "app");
			lbl.setText("Error loading control '" + classname + "':" + e);
		}
		
	}
	
}
