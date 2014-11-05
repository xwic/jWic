package de.jwic.common.togglable;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Created by boogie on 11/3/14.
 */
class MultiToggleableGroup extends ToggleableGroup{

	private final MultipleToggleListener multipleToggleListener;
	private final Set<Togglable> togglables;

	MultiToggleableGroup(Collection<Togglable> togglables) {
		this.togglables = new LinkedHashSet<Togglable>(togglables);
		multipleToggleListener = new MultipleToggleListener(this);
		for(Togglable t : togglables){
			t.addToggleableListener(multipleToggleListener);
		}
	}

	@Override
	public Set<Togglable> getActiveTogglables() {
		Set<Togglable> active = new LinkedHashSet<Togglable>();
		for(Togglable t : this.togglables){
			if(t.isToggled()){
				active.add(t);
			}
		}
		return active;
	}

	@Override
	public void toggle() {
		for(Togglable t  : this.togglables){
			t.toggle();
		}
		triggerListeners();
	}

	@Override
	public void setToggled(boolean on) {
		for(Togglable t : this.togglables){
			t.setToggled(on);
		}
		triggerListeners();
	}

	@Override
	public boolean isToggled() {
		for(Togglable t : this.togglables){
			if(!t.isToggled()){
				return false;
			}
		}
		return true;
	}

	private static class MultipleToggleListener implements ToggleableListener {

		private final MultiToggleableGroup group;

		private MultipleToggleListener(MultiToggleableGroup group) {
			this.group = group;
		}

		@Override
		public void onToggle(Togglable togglable) {
			group.triggerListeners();
		}
	}
}
