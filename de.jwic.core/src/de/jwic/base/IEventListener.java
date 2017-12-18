/**
 * 
 */
package de.jwic.base;

/**
 * Generic EventListener interface to use in various components and models. Designed for
 * usage with Lambda expressions in Java 8 to delegate the invocation of an event to
 * a subsequent call.
 * 
 * @author lippisch
 */
public interface IEventListener<E extends Event> {

	public void onEvent(E event);
	
}
