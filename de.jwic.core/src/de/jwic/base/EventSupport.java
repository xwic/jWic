/**
 * 
 */
package de.jwic.base;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Helper class to handle events in models and controls.
 * 
 * @author lippisch
 */
public class EventSupport<E extends Event> {

	private final static Log log = LogFactory.getLog(EventSupport.class);
	private String eventName;

	private List<IEventListener<E>> listeners = new ArrayList<IEventListener<E>>();
	
	/**
	 * 
	 */
	public EventSupport() {
		super();
		eventName = "Unnamed";
	}

	/**
	 * @param eventName
	 */
	public EventSupport(String eventName) {
		super();
		this.eventName = eventName;
	}
	
	
	/**
	 * Add a listener to this event.
	 * @param listener
	 */
	public void addListener(IEventListener<E> listener) {
		if (log.isDebugEnabled()) {
			log.debug("Attaching listener to event " + eventName);
		}
		listeners.add(listener);
	}
	
	/**
	 * Remove a listener from this event.
	 * @param listener
	 * @return true if the listener was successfully removed
	 */
	public boolean removeListener(IEventListener<E> listener) {
		boolean removed = listeners.remove(listener);
		if (log.isDebugEnabled()) {
			if (removed) {
				log.debug("Successfully detached listener from event " + eventName);
			} else {
				log.warn("The listener was not detached from the event " + eventName + ". It may not have been registered at first.");
			}
		}
		return removed;
	}
	
	/**
	 * Remove all the listeners from this event.
	 */
	public void removeAllListeners() {
		listeners.clear();
		log.debug("Removed all listeners from event " + eventName);
	}
	
	/**
	 * Notify all listeners to this event.
	 * @param e
	 */
	@SuppressWarnings("unchecked")
	public void fireEvent(E e) {
		// We copy the list of listeners into an array to not get "disturbed"
		// by events that add or remove listeners
		Object[] all = listeners.toArray();
		for (Object o : all) {
			IEventListener<E> l = (IEventListener<E>)o;
			l.onEvent(e);
		}
	}
	
}
