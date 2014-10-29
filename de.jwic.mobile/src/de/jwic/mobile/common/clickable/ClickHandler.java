package de.jwic.mobile.common.clickable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by boogie on 10/28/14.
 */
public final class ClickHandler implements Clickable{
	private final List<ClickListener> clickListeners;
	private final Clickable control;

	public ClickHandler(Clickable control) {
		this.control = control;
		this.clickListeners = new ArrayList<ClickListener>();
	}

	@Override
	public void click() {
		for(ClickListener listener : new ArrayList<ClickListener>(this.clickListeners)){
			listener.onClick(control);
		}
	}

	@Override
	public void addClickListener(ClickListener listener) {
		this.clickListeners.add(listener);
	}

	@Override
	public void removeClickListener(ClickListener listener) {
		this.clickListeners.remove(listener);
	}
}
