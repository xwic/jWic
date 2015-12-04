package de.jwic.common.selectable;

import de.jwic.base.Control;
import de.jwic.events.SelectionEvent;
import de.jwic.events.SelectionListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by boogie on 10/28/14.
 */
public final class SelectionHandler <E extends Control & Selectable> implements Selectable {
	private final List<SelectionListener> clickListeners;
	private final E control;

	public SelectionHandler(E control) {
		this.control = control;
		this.clickListeners = new ArrayList<SelectionListener>();
	}

	@Override
	public void select() {
		final SelectionEvent event = new SelectionEvent(this.control);
		for(SelectionListener listener : new ArrayList<SelectionListener>(this.clickListeners)){
			listener.objectSelected(event);
		}
	}

	@Override
	public void addSelectionListener(SelectionListener listener) {
		this.clickListeners.add(listener);
	}

	@Override
	public void removeSelectionListener(SelectionListener listener) {
		this.clickListeners.remove(listener);
	}
}
