package de.jwic.common.enableable;

import de.jwic.common.togglable.Togglable;
import de.jwic.common.togglable.ToggleableListener;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Created by boogie on 11/4/14.
 */
public class EnabledToggleable implements Togglable{

	private final Set<Enableable> togglables;
	private final Set<ToggleableListener> listeners;


	public EnabledToggleable(Enableable togglable, Enableable...others) {
		this.togglables = new LinkedHashSet<Enableable>();
		this.listeners = new LinkedHashSet<ToggleableListener>();
		togglables.add(togglable);
		togglables.addAll(Arrays.asList(others));
	}

	@Override
	public void toggle() {
		this.setToggled(!this.isToggled());
	}

	@Override
	public void setToggled(boolean on) {
		for(Enableable e : this.togglables){
			e.setEnabled(on);
		}
		for(ToggleableListener l : new LinkedHashSet<ToggleableListener>(this.listeners)){
			l.onToggle(this);
		}
	}

	@Override
	public boolean isToggled() {
		for(Enableable e : this.togglables){
			if(!e.isEnabled()){
				return false;
			}
		}
		return true;
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
