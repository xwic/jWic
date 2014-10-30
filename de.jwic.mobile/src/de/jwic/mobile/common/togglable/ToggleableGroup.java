package de.jwic.mobile.common.togglable;

import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * The Toggleable Group Utility class
 * This class has the purpose of binding 2 or more toggleable elements so that only one can be active at a time
 *
 * Created by boogie on 10/30/14.
 */
public final class ToggleableGroup {
	private final Set<Togglable> togglables;
	private final Set<Togglable> activeTogglables;
	private final ToggleableListener listener;

	private ToggleableGroup(Collection<Togglable> togglables) {
		this.togglables = new LinkedHashSet<Togglable>();
		this.activeTogglables = new LinkedHashSet<Togglable>();
		this.listener = new ToggleableGroupListener(this);

		//add the listener to the toggleables
		for(Togglable t : togglables){
			this.addTogglable(t);
		}

	}

	public void addTogglable(Togglable togglable){
		if(this.togglables.contains(togglable)){
			return;
		}
		this.togglables.add(togglable);
		togglable.addToggleableListener(listener);
	}

	public void removeToggable(Togglable togglable){
		this.togglables.remove(togglable);
		togglable.removeToggleableListener(listener);
	}

	public Togglable getActiveTogglable(){
		for(Togglable t : this.togglables){
			if(t.isToggled()){
				return t;
			}
		}
		return null;
	}

	private void addActive(Togglable togglable){
		this.activeTogglables.add(togglable);

	}

	private void removeActive(Togglable togglable){
		this.activeTogglables.remove(togglable);
	}

	private static final class ToggleableGroupListener implements ToggleableListener{

		private final ToggleableGroup group;

		private ToggleableGroupListener(ToggleableGroup group) {
			this.group = group;
		}

		@Override
		public void onToggle(Togglable togglable) {
			for(Togglable t : group.togglables){
				if(t == togglable){
					continue;
				}
				if(togglable.isToggled()){
					t.setToggled(false);
				}
			}
		}
	}

	public static ToggleableGroup of(Togglable...togglables){
		return of( Arrays.asList(togglables));
	}

	public static ToggleableGroup of(Collection<Togglable> togglables){
		return new ToggleableGroup(togglables);
	}


}
