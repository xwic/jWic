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
package de.jwic.web;

import java.util.HashMap;
import java.util.Map;

import de.jwic.base.SessionContext;

/**
 * Event object to carry additional details.
 * 
 * @author lippisch
 */
public class WebEngineEvent {

	private Map<String, Object> customAttributes = null;
	
	private SessionContext sessionContext = null;
	private String controlId = null;
	private String action = null;
	private String actionParameter = null;
	private boolean ajaxRendering = false;
	
	/**
	 * @param ajaxRendering
	 */
	public WebEngineEvent(SessionContext sc, boolean ajaxRendering) {
		super();
		this.sessionContext = sc;
		this.ajaxRendering = ajaxRendering;
	}
	
	
	/**
	 * @param ajaxRendering
	 */
	public WebEngineEvent(SessionContext sc, boolean ajaxRendering, String controlId) {
		super();
		this.sessionContext = sc;
		this.ajaxRendering = ajaxRendering;
		this.controlId = controlId;
	}
		

	/**
	 * @param sessionContext
	 * @param controlId
	 */
	public WebEngineEvent(SessionContext sessionContext, String controlId) {
		super();
		this.sessionContext = sessionContext;
		this.controlId = controlId;
	}



	/**
	 * @param controlId
	 * @param control
	 */
	public WebEngineEvent(SessionContext sc, String controlId, String action, String actionParameter) {
		super();
		this.sessionContext = sc;
		this.controlId = controlId;
		this.action = action;
		this.actionParameter = actionParameter;
	}

	/**
	 * @return the controlId
	 */
	public String getControlId() {
		return controlId;
	}

	/**
	 * @return the ajaxRendering
	 */
	public boolean isAjaxRendering() {
		return ajaxRendering;
	}


	
	/**
	 * Attach a custom object to this event.
	 * @param key
	 * @param object
	 */
	public void setAttribute(String key, Object object) {
		if (customAttributes == null) {
			customAttributes = new HashMap<String, Object>();
		}
		customAttributes.put(key,  object);
	}
	
	/**
	 * Return an attached custom attribute.
	 * @param key
	 * @return
	 */
	public Object getAttribute(String key) {
		if (customAttributes == null) {
			return null;
		}
		return customAttributes.get(key);
	}

	/**
	 * @return the sessionContext
	 */
	public SessionContext getSessionContext() {
		return sessionContext;
	}

	/**
	 * @return the action
	 */
	public String getAction() {
		return action;
	}

	/**
	 * @return the actionParameter
	 */
	public String getActionParameter() {
		return actionParameter;
	}
}
