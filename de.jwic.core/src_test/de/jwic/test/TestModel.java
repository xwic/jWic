/**
 * 
 */
package de.jwic.test;

import de.jwic.base.Event;
import de.jwic.base.EventSupport;
import de.jwic.base.IEventListener;

/**
 * @author lippisch
 */
public class TestModel  {

	private final EventSupport<Event> eventUpdated = new EventSupport<>("Updated"); // naming the event is optional
	private final EventSupport<MyTestEvent> eventGeneric = new EventSupport<>("Generic");
	
	/**
	 * Add an Event.
	 * @param listener
	 */
	public void addUpdateListener(IEventListener<Event> listener) {
		eventUpdated.addListener(listener);
	}
	
	/**
	 * Return the listener.
	 * @param listener
	 * @return
	 */
	public boolean removeUpdateListener(IEventListener<Event> listener) {
		return eventUpdated.removeListener(listener);
	}
	
	/**
	 * Fire the event.
	 */
	public void doSomething() {
		eventUpdated.fireEvent(new Event(this));
		eventGeneric.fireEvent(new MyTestEvent(this, 123));
	}

	/**
	 * @param listener
	 * @see de.jwic.base.EventSupport#addListener(de.jwic.base.IEventListener)
	 */
	public void addGenericListener(IEventListener<MyTestEvent> listener) {
		eventGeneric.addListener(listener);
	}

	/**
	 * @param listener
	 * @return
	 * @see de.jwic.base.EventSupport#removeListener(de.jwic.base.IEventListener)
	 */
	public boolean removeGenericListener(IEventListener<MyTestEvent> listener) {
		return eventGeneric.removeListener(listener);
	}
	
}
