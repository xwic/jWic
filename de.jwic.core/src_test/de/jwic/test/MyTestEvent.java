/**
 * 
 */
package de.jwic.test;

import de.jwic.base.Event;

/**
 * @author lippisch
 *
 */
public class MyTestEvent extends Event {

	private int myEventId = 0;
	
	/**
	 * @param eventSource
	 */
	public MyTestEvent(Object eventSource) {
		super(eventSource);
	}
	
	/**
	 * @param eventSource
	 */
	public MyTestEvent(Object eventSource, int id) {
		super(eventSource);
		this.myEventId = id;
	}
	
	/**
	 * @return the myEventId
	 */
	public int getMyEventId() {
		return myEventId;
	}

	/**
	 * @param myEventId the myEventId to set
	 */
	public void setMyEventId(int myEventId) {
		this.myEventId = myEventId;
	}

}
