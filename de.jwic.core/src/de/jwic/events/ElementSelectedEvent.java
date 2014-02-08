/*
 * de.jwic.events.ElementSelectedEvent
 * $Id: ElementSelectedEvent.java,v 1.4 2010/02/07 14:24:23 lordsam Exp $
 */
package de.jwic.events;

import de.jwic.base.Event;

/**
 * This event is fired when an element of an control has been selected.
 * An element can be something like an entry in a list or a node in a tree.
 * 
 * @author Florian Lippisch
 * @version $Revision: 1.4 $
 */
public class ElementSelectedEvent extends Event {

	private static final long serialVersionUID = 1L;
	private Object element = null;
	private boolean dblClick = false;
	
	/**
	 * @param sourceControl
	 */
	public ElementSelectedEvent(Object source, Object selectedElement) {
		super(source);
		this.element = selectedElement;
	}

	/**
	 * @param sourceControl
	 */
	public ElementSelectedEvent(Object source, Object selectedElement, boolean dblClick) {
		super(source);
		this.element = selectedElement;
		this.dblClick = dblClick;
	}

	/**
	 * @return Returns the element.
	 */
	public Object getElement() {
		return element;
	}

	/**
	 * @return the dblClick
	 */
	public boolean isDblClick() {
		return dblClick;
	}
}
