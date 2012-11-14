/*
 * de.jwic.events.ElementSelectedListener
 * $Id: ElementSelectedListener.java,v 1.1 2006/01/16 08:30:40 lordsam Exp $
 */
package de.jwic.events;

import java.io.Serializable;

/**
 * This listener listens to the selection of an element.
 * @author Florian Lippisch
 * @version $Revision: 1.1 $
 */
public interface ElementSelectedListener extends Serializable {
	
	public abstract void elementSelected(ElementSelectedEvent event);
	
}
