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
package de.jwic.base;

import java.io.Serializable;

/**
 * An application is responsible for the lifecycle of an jWic application. It
 * creates the root control(s) and recieves events from the framework about
 * starting and stopping.
 * 
 * @author Florian Lippisch
 * @version $Revision: 1.1 $
 * @since 3.0.0
 */
public interface IApplication extends Serializable {
	
	/**
	 * Create the root control(s). This method must return the
	 * root control.
	 * @param container
	 */
	public Control createRootControl(IControlContainer container);

	/**
	 * Initialize the application.
	 * @param context
	 */
	public void initialize(SessionContext context);
	
	/**
	 * Invoked after the SessionContext and all controls have been destroyed.
	 *
	 */
	public void postDestroy();
	
	/**
	 * Invoked before the SessionContext and all controls are destroyed.
	 *
	 */
	public void preDestroy();
	
}
