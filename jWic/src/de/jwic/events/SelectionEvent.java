/*
 * de.jwic.events.ObjectSelectedEvent
 * $Id: SelectionEvent.java,v 1.3 2010/02/07 14:24:23 lordsam Exp $
 */
package de.jwic.events;

import de.jwic.base.Control;
import de.jwic.base.Event;

/**
 * 
 * @author Florian Lippisch
 * @version $Revision: 1.3 $
 */
public class SelectionEvent extends Event {

	private static final long serialVersionUID = 1L;
	
	private boolean dblClick = false;
	
	/**
	 * @param sourceControl
	 */
	public SelectionEvent(Control sourceControl) {
		super(sourceControl);
	}
	
	/**
	 * SelectionEvent constructor
	 * @param eventSource
	 * @param dblClick
	 */
	public SelectionEvent(Object eventSource, boolean dblClick) {
		super(eventSource);
		this.dblClick = dblClick;
	}

	/**
	 * @return the dblClick
	 */
	public boolean isDblClick() {
		return dblClick;
	}

	
}
