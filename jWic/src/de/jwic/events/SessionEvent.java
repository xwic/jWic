/*
 * Copyright 2005 jWic Group (http://www.jwic.de)
 * $Id: SessionEvent.java,v 1.3 2010/08/25 14:23:57 cosote Exp $
 */
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
