/*
 * de.jwic.events.SelectionListener
 * $Id: SelectionListener.java,v 1.1 2006/01/16 08:30:40 lordsam Exp $
 */
package de.jwic.events;

import java.io.Serializable;

/**
 * 
 * @author Florian Lippisch
 * @version $Revision: 1.1 $
 */
public interface SelectionListener extends Serializable {
	
	public abstract void objectSelected(SelectionEvent event);

}
