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
 * Describes the properties of an jwic application used to manage the lifecycle.
 *   
 * @author Florian Lippisch
 * @version $Revision: 1.1 $
 */
public interface IApplicationSetup extends Serializable {

	/**
	 * Returns the application class.
	 * @return
	 */
	public IApplication createApplication();
	
	/**
	 * Returns the name of the application.
	 */
	public String getName();

	/**
	 * Returns true if the application should be rendered using ajax
	 * (Asynchronous JavaScript and XML) and DTHML. The default value
	 * is true.
	 * @return
	 */
	public boolean isUseAjaxRendering();

	/**
	 * Returns if the application is serializable. Applications should be serializable by
	 * default to allow the runtime to "swap out" sessions. If an application is marked
	 * not serializable, the session will remain loaded until the application is terminated
	 * or the session times out.  
	 */
	public boolean isSerializable();
	
	/**
	 * Returns if the application should be loaded only once per client(-session). If
	 * set to true, each try to launch another instance of the application will only
	 * reactivate an existing session if exists.  
	 * @return
	 */
	public boolean isSingleSession();
	
	/**
	 * Returns if the application can be accessed only if the user is authenticated.
	 * To use this feature, an <code>IAuthenticator</code> must be specified for the
	 * invoker (i.e. DispatcherServlet) 
	 * @return
	 */
	public boolean isRequireAuthentication();
	
	/**
	 * Returns an application specific property.
	 * @param key
	 * @return
	 */
	public String getProperty(String key);
}
