package de.jwic.mobile.common.togglable;

import de.jwic.base.Control;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by boogie on 10/30/14.
 */
public final class ToggleableHandler<E extends Control & Togglable> implements Togglable{
	private final E control;
	private final List<ToggleableListener> listeners;
	private boolean state;

	public ToggleableHandler(E control) {
		this.control = control;
		this.listeners = new ArrayList<ToggleableListener>();
		this.state = false;
	}

	@Override
	public void toggle() {
		this.setToggled(!this.isToggled());
	}

	@Override
	public void setToggled(boolean on) {
		if(this.state == on){
			return;
		}
		this.setToggledNoRedraw(on);
		control.requireRedraw();
	}

	public void setToggledNoRedraw(boolean toggled){
		if(state == toggled){
			return;
		}
		this.state = toggled;
		for(ToggleableListener t : new ArrayList<ToggleableListener>(this.listeners)){
			t.onToggle(control);
		}
	}


	@Override
	public boolean isToggled() {
		return this.state;
	}

	@Override
	public void addToggleableListener(ToggleableListener listener) {
		this.listeners.add(listener);
	}

	@Override
	public void removeToggleableListener(ToggleableListener listener) {
		this.listeners.remove(listener);
	}
}
