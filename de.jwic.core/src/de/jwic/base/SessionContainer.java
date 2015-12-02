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

/**
 * Contains the SessionContext assigned to a specified session. It manages the
 * state of the application.
 * 
 * @author Florian Lippisch
 * @version $Revision: 1.2 $
 */
public class SessionContainer {

	public final static int STATE_NORMAL = 0;
	public final static int STATE_DESTROYED = 1;
	public final static int STATE_STORED = 2;
	
	private String clientId = null;
	private String id = null;
	private String applicationId = null;
	private SessionContext sessionContext = null;
	private int state = STATE_NORMAL;
	private long lastAccess = 0;

	/**
	 * Constructs a new SessionStore with the given ID.
	 * @param id
	 */
	public SessionContainer(String id, String clientId) {
		this.id = id;
		this.clientId = clientId;
		lastAccess = System.currentTimeMillis();
	}
	
	/**
	 * Update the access timestamp.
	 */
	public void access() {
		lastAccess = System.currentTimeMillis();
	}
	
	/**
	 * @return Returns the id.
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id The id to set.
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @return Returns the sessionContext.
	 */
	public SessionContext getSessionContext() {
		return sessionContext;
	}
	/**
	 * @param sessionContext The sessionContext to set.
	 */
	public void setSessionContext(SessionContext sessionContext) {
		this.sessionContext = sessionContext;
	}
	/**
	 * @return Returns the state.
	 */
	public int getState() {
		return state;
	}
	/**
	 * @param state The state to set.
	 */
	public void setState(int state) {
		this.state = state;
	}
	/**
	 * @return Returns the lastAccess.
	 */
	public long getLastAccess() {
		return lastAccess;
	}

	/**
	 * @return Returns the applicationId.
	 */
	public String getApplicationId() {
		return applicationId;
	}

	/**
	 * @param applicationId The applicationId to set.
	 */
	public void setApplicationId(String applicationId) {
		this.applicationId = applicationId;
	}

	/**
	 * @return Returns the clientId.
	 */
	public String getClientId() {
		return clientId;
	}

	/**
	 * @param clientId The clientId to set.
	 */
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return "SessionContainer ID '" + id + "', AppID: '" + applicationId + "', Client: " + clientId;
	}
	/**
	 * Returns the age in minutes and seconds.
	 * @param container
	 */
	public String getAge() {

		long age = System.currentTimeMillis() - getLastAccess();
		
		long seconds = (age / 1000) % 60;
		long minutes = (age / 1000 / 60);
		
		return minutes + "m " + seconds + "s";
	}


}
