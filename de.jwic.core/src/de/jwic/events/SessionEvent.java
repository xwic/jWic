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
package de.jwic.events;

import java.util.Map;

import de.jwic.base.SessionContext;


/**
 * Contains informations about the parameters used to reuse a SessionContext object.
 * @version $Revision: 1.3 $
 * @author Florian Lippisch
 */
public class SessionEvent {

	private SessionContext sessionContext = null;
	private Map<String, String[]> parameters = null;
	
	public SessionEvent(Map<String, String[]> parameter) {
		this.parameters = parameter;
	}
	
	/**
	 * Returns the value of the specified parameter.
	 * @param key
	 * @return
	 */
	public String getParameter(String key) {
		String[] values = parameters.get(key);
		if (values != null) {
			return values[0];
		}
		return null;
	}

	/**
	 * @return Returns the parameters.
	 */
	public Map<String, String[]> getParameters() {
		return parameters;
	}
	/**
	 * @param parameters The parameters to set.
	 */
	public void setParameters(Map<String, String[]> parameters) {
		this.parameters = parameters;
	}
	
	/**
	 * @return Returns the SessionContext for this event.
	 */
	public SessionContext getSessionContext() {
		return sessionContext;
	}
	
	/**
	 * @param sessionContext The SessionContext for this event.
	 */
	public void setSessionContext(SessionContext sessionContext) {
		this.sessionContext = sessionContext;
	}
}
