/**
 * 
 */
package de.jwic.test;

import org.junit.Test;

import de.jwic.base.Event;
import de.jwic.base.EventSupport;
import de.jwic.base.IEventListener;
import static org.junit.Assert.*;

/**
 * @author lippisch
 *
 */
public class EventSupportTest {

	private class TestListener implements IEventListener<Event> {
		int count = 0;
		
		/* (non-Javadoc)
		 * @see de.jwic.base.IEventListener#onEvent(de.jwic.base.Event)
		 */
		@Override
		public void onEvent(Event event) {
			count++;
		}
	}
	
	/**
	 * Test method for {@link de.jwic.base.EventSupport#fireEvent(de.jwic.base.Event)}.
	 * Invoke the event and check if the listener was notified.
	 */
	@Test
	public void testFireEvent() {
		
		EventSupport<Event> es = new EventSupport<>();
		TestListener tl = new TestListener();
		
		es.addListener(tl);
		
		es.fireEvent(new Event(this));
		assertEquals(1, tl.count);
		
		es.fireEvent(new Event(this));
		assertEquals(2, tl.count);
		
		
	}

	/**
	 * Add a listener, invoke an event, remove it and check if it is gone.
	 * 
	 */
	@Test
	public void testRemoveListener() {
		
		EventSupport<Event> es = new EventSupport<>();
		TestListener tl1 = new TestListener();
		TestListener tl2 = new TestListener();
		
		es.addListener(tl1);
		es.addListener(tl2);
		
		es.fireEvent(new Event(this));
		assertEquals(1, tl1.count);
		assertEquals(1, tl2.count);
		
		es.removeListener(tl1);
		
		es.fireEvent(new Event(this));
		assertEquals(1, tl1.count);
		assertEquals(2, tl2.count);
		
		
	}

	
	/**
	 * Test if the event support is properly "ignoring" modifications (adding/removing) 
	 * during the event.
	 */
	@Test
	public void testConcurrentModification() {
		
		TestListener tl1 = new TestListener();
		TestListener tl2 = new TestListener();
		TestListener tl3 = new TestListener();
		
		final EventSupport<Event> es = new EventSupport<>();
		
		
		es.addListener(tl1);
		es.addListener(e -> es.addListener(tl2));
		es.addListener(tl3);
		
		
		es.fireEvent(new Event(this));
		
		assertEquals(1, tl1.count);
		assertEquals(0, tl2.count);
		assertEquals(1, tl3.count);
		
		es.fireEvent(new Event(this));
		
		assertEquals(2, tl1.count);
		assertEquals(1, tl2.count);
		assertEquals(2, tl3.count);
		
	}

	
	
	
}
