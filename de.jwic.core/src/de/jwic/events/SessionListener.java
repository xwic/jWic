/*
 * de.jwic.events.SessionListener
 * Created on 20.10.2004
 * $Id: SessionListener.java,v 1.2 2006/09/25 13:29:39 lordsam Exp $
 */
package de.jwic.events;

import java.io.Serializable;

/**
 * Event listener for the SessionEvent. This event is fired
 * by the SessionContext before the session is serialized, after the 
 * session has been deserialized and when the session has been re-activated.
 * 
 * @author Florian Lippisch
 * @version $Revision: 1.2 $
 */
public interface SessionListener extends Serializable {

	/**
	 * Invoked when a SessionContext that is configured as a SingleSession
	 * application is reused.
	 */
	public void sessionReused(SessionEvent event);
	
	/**
	 * Invoked before the session is serialized.
	 */
	public void beforeSerialization(SessionEvent event);
	
	/**
	 * Invoked after the whole session has been deserialized.
	 */
	public void afterDeserialization(SessionEvent event);
	
	/**
	 * Invoked when the session starts. This happens after the root control has been added.
	 * @since 3.0.0
	 * @param event
	 */
	public void sessionStarted(SessionEvent event);
	
	/**
	 * Invoked when the session is stopped. This is the case when the session either
	 * times out or the exit() method is called. 
	 * @since 3.0.0
	 * @param event
	 */
	public void sessionStopped(SessionEvent event);
	
	
}
