/*
 * de.jwic.events.SessionAdapter
 * Created on 20.10.2004
 * $Id: SessionAdapter.java,v 1.1 2006/01/16 08:30:40 lordsam Exp $
 */
package de.jwic.events;

/**
 * Adapter for the SessionListener. 
 * @author Florian Lippisch
 * @version $Revision: 1.1 $
 */
public abstract class SessionAdapter implements	SessionListener {

	/* (non-Javadoc)
	 * @see de.jwic.events.SessionListener#afterDeserialization(de.jwic.events.SessionEvent)
	 */
	public void afterDeserialization(SessionEvent event) {

	}
	
	/* (non-Javadoc)
	 * @see de.jwic.events.SessionListener#beforeSerialization(de.jwic.events.SessionEvent)
	 */
	public void beforeSerialization(SessionEvent event) {

	}
	
	/* (non-Javadoc)
	 * @see de.jwic.events.SessionListener#sessionReused(de.jwic.events.SessionEvent)
	 */
	public void sessionReused(SessionEvent event) {

	}

	/* (non-Javadoc)
	 * @see de.jwic.events.SessionListener#sessionStarted(de.jwic.events.SessionEvent)
	 */
	public void sessionStarted(SessionEvent event) {
		// nothing to do

	}
	
	/* (non-Javadoc)
	 * @see de.jwic.events.SessionListener#sessionStopped(de.jwic.events.SessionEvent)
	 */
	public void sessionStopped(SessionEvent event) {
		// nothing to do

	}
	
}
