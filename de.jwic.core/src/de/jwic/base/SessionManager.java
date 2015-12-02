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

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import de.jwic.events.SessionEvent;

/**
 * Manages the SessionContainer objects. It is used by the JWicRuntime
 * to create, retrieve, serialize, deserialize and destroy applications. 
 * 
 * @author Florian Lippisch
 * @version $Revision: 1.5 $
 */
public class SessionManager {

	protected final Log log = LogFactory.getLog(getClass());
	
	private final static long DELAY = 1000 * 60 * 5; // wait 5 minutes before the first run.
	private final static long INTERVALL = 1000 * 60 * 5; // run every 5 minutes.
	
	// client map
	private Map<String, Map<String, SessionContainer>> clientSessions = new HashMap<String, Map<String, SessionContainer>>();
	private File serDir = null; 
	
	private long idCount = 10000;
	private int storeTime = 0;

	private Timer timer = null;
	
	private class RefreshDeamon extends TimerTask {
		/* (non-Javadoc)
		 * @see java.util.TimerTask#run()
		 */
		public void run() {
			storeOldSessions();
		}
	}
	
	/**
	 * Default Constructor.
	 */
	public SessionManager(String tempDir) {
		serDir = new File(tempDir);
		if (!serDir.exists()) {
			serDir.mkdirs();
		}
		
		timer = new Timer(true);
		timer.schedule(new RefreshDeamon(), DELAY, INTERVALL);
	}
	
	/**
	 * Cancles the RefreshDeamon task and destroys.
	 * all open sessions.
	 */
	public void destroy() {
		
		// stop timer.
		timer.cancel();

		String[] clients = new String[clientSessions.size()];
		getClientIDs().toArray(clients);
		// destroy all clients.
		for (String clientID : clients) {
			destroyClient(clientID);
		}
		
	}
	
	/**
	 * 
	 */
	public void storeOldSessions() {
		
		if (storeTime > 0) { // serialization is not disabled
			log.debug("Searching for out-timed sessions to store...");
			long maxAge = System.currentTimeMillis() - (storeTime * 1000 * 60);
			for (Iterator<String> it = getClientIDs().iterator(); it.hasNext(); ) {
				String clientID = it.next();
				Collection<SessionContainer> c = getSessions(clientID);
				if (c != null) {
					for (Iterator<SessionContainer> itS = c.iterator(); itS.hasNext(); ) {
						SessionContainer container = itS.next();
						if (container.getState() == SessionContainer.STATE_NORMAL && 
								container.getLastAccess() < maxAge) {
							if (container.getSessionContext().getApplicationSetup().isSerializable()) {
								try {
									log.debug("Auto-Serializing container " + container);
									serialize(container);
								} catch (Exception e) {
									log.error("Error serializing container " + container, e);
								}
							}
						}
					}
				}
			}
			
		}
		
	}

	/**
	 * Create a new SessionStore object. 
	 * @param clientID - String
	 * @param applicationID - String
	 * @param singleSession - boolean
	 * @return
	 */
	public SessionContainer create(String clientID, String applicationID) {
		
		Map<String, SessionContainer> sessionMap = getSessionMap(clientID);
		
		
		long idNum = idCount++;
		String id = idNum + "-" + System.currentTimeMillis();
		
		SessionContainer store = new SessionContainer(id, clientID);
		store.setApplicationId(applicationID);
		
		sessionMap.put(id, store);
		
		return store;
	}
	
	/**
	 * @param clientID
	 * @return
	 */
	private Map<String, SessionContainer> getSessionMap(String clientID) {

		Map<String, SessionContainer> map = clientSessions.get(clientID);
		if (map == null) {
			synchronized (clientSessions) {
				map = clientSessions.get(clientID);
				if (map == null) {
					map = new HashMap<String, SessionContainer>();
					clientSessions.put(clientID, map);
				}
			}
		}
		return map;
	}

	/**
	 * Returns the SessionStore with the specified id. Returns <code>null</code>
	 * if no SessionStore was found.
	 * @param clientID
	 * @param id
	 * @return
	 */
	public SessionContainer get(String clientID, String id) {
		Map<String, SessionContainer> sessionMap = getSessionMap(clientID);
		return sessionMap.get(id);
	}
	
	/**
	 * Returns the SessionStore for the specified applicationId 
	 * @param clientID
	 * @param applicationID
	 * @return
	 */
	public SessionContainer getByAppID(String clientID, String applicationID) {
		Map<String, SessionContainer> sessionMap = getSessionMap(clientID);
		
		// iterate through the sessions to find one with the specified appId
		for (Iterator<SessionContainer> it = sessionMap.values().iterator(); it.hasNext(); ) {
			SessionContainer store = it.next();
			if (applicationID.equals(store.getApplicationId())) {
				return store;
			}
		}
		
		return null;
	}
	
	/**
	 * Removes the SessionStore.
	 * @param store
	 */
	public void remove(SessionContainer store) {
		
		Map<String, SessionContainer> sessionMap = getSessionMap(store.getClientId());
		sessionMap.remove(store.getId());
		synchronized (clientSessions) {
			if (sessionMap.size() == 0) {
				clientSessions.remove(store.getClientId());
			}
		}
	}
	
	/**
	 * Write the session to a temporary directory. 
	 * @param container
	 */
	public void serialize(SessionContainer container) {
		
		log.debug("Serializing container " + container);
		
		String filename = container.getClientId() + "_" + container.getId() + ".ser"; 
		
		try {
			SessionContext sc = container.getSessionContext();
			sc.fireEvent(new SessionEvent(null), SessionContext.BEFORE_SERIALIZATION);
			FileOutputStream fos = new FileOutputStream(new File(serDir, filename));
			ObjectOutputStream oos = new ObjectOutputStream(fos);
	
			oos.writeObject(sc);
			oos.close();
			
			container.setState(SessionContainer.STATE_STORED);
			container.setSessionContext(null); // release SessionContext
			
		} catch (Exception e) {
			log.error("Error storing session", e);
			throw new JWicException("Serialization failed. Can not serialize session.", e);
		}

	}

	/**
	 * Reactivate the session.
	 * @param container
	 */
	public void deserialize(SessionContainer container) {
		
		log.debug("Deserializing container " + container);

		try {
			String filename = container.getClientId() + "_" + container.getId() + ".ser"; 
			File file = new File(serDir, filename);
			if (file.exists()) {
				FileInputStream fis = new FileInputStream(file);
				ObjectInputStream ois = new ObjectInputStream(fis);
		
				SessionContext sc = (SessionContext) ois.readObject();
				ois.close();
				sc.fireEvent(new SessionEvent(null), SessionContext.AFTER_DESERIALIZATION);

				container.setSessionContext(sc);
				container.setState(SessionContainer.STATE_NORMAL);
				
				fis.close();
				if (!file.delete()) {	// remove the serialized file.
					log.warn("Can't delete serialization file " + file.getName());
				}
				
			} else {
				throw new JWicException("The session can not be desrialized because the data can not be found.");
			}
			
		} catch (Exception e) {
			log.error("Error deserializing session", e);
			throw new JWicException("Deserialization failed. Can not deserialize session.", e);
		}

	}

	/**
	 * Destroys all sessions store for the specified clientID.
	 * @param clientID
	 */
	public void destroyClient(String clientID) {

		Map<String, SessionContainer> map = clientSessions.get(clientID);
		if (map != null) {
			
			// Iterate through all sessions and destroy them.
			// 2005-12-06 JBO: replaced usage of Iterator to solve ConcurrentModificationException bug
			while (map.size() > 0) {
				Map.Entry<String, SessionContainer> entry = map.entrySet().iterator().next();
				SessionContainer container = entry.getValue();
				try {
					switch (container.getState()) {
						// destroy the session
						case SessionContainer.STATE_NORMAL: {
							container.getSessionContext().destroy();
							container.setState(SessionContainer.STATE_DESTROYED);
							break;
						}
						// desrialize and destroy the session.
						case SessionContainer.STATE_STORED: {
							deserialize(container);
							container.getSessionContext().destroy();
							container.setState(SessionContainer.STATE_DESTROYED);
							break;
						}
						default: // ignore others, just remove from map.
							break;
					}
				} catch (Exception e) {
					log.error("Exception while destroying session " + container, e);
				}
				map.remove(entry.getKey());
				clientSessions.remove(clientID);
			}
			
		}
		
	}

	/**
	 * Returns the clientIDs that have one or more sessions.
	 * @return
	 */
	public Collection<String> getClientIDs() {
		return clientSessions.keySet();
	}

	/**
	 * Returns the sessions assigned to the specified client ID.
	 * @param clientID
	 * @return
	 */
	public Collection<SessionContainer> getSessions(String clientID) {
		Map<String, SessionContainer> map = clientSessions.get(clientID);
		if (map != null) {
			return map.values();
		}
		return null;
	}

	/**
	 * Returns the time after a session is serialized.
	 * @return
	 */
	public int getStoreTime() {
		return storeTime;
	}
	
	/**
	 * @param sessionStoreTime
	 */
	public void setStoreTime(int sessionStoreTime) {
		this.storeTime = sessionStoreTime;
	}
}
