package de.jwic.common.selectable;

import de.jwic.events.SelectionListener;

/**
 * Created by boogie on 10/28/14.
 */
public interface Selectable {
	void select();
	void addSelectionListener(SelectionListener listener);
	void removeSelectionListener(SelectionListener listener);
}
