package de.jwic.common.togglable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by boogie on 11/3/14.
 */
class UniqueToggleableGroup extends ToggleableGroup {
	private final ToggleableListener listener;
	private int indexOfActiveToggleable = -1;
	private final Set<Togglable> togglables;

	UniqueToggleableGroup(Collection<Togglable> togglables) {
		this.togglables = new LinkedHashSet<Togglable>(togglables);
		this.listener = new ToggleableGroupListener(this);
		for(Togglable t : this.togglables){
			t.addToggleableListener(listener);
		}
	}

	@Override
	public Set<Togglable> getActiveTogglables() {
		for(Togglable t : this.togglables){
			if(t.isToggled()){
				return Collections.singleton(t);
			}
		}
		return Collections.emptySet();
	}


	/**
	 * Cycles the toggle state of the group down ( equivalent to called setToggled(true) on this group)
	 */
	@Override
	public void toggle() {
		this.setToggled(true);
	}

	/**
	 *
	 * @param direction - true if the group should cycle down, false if the group should cycle up
	 */
	@Override
	public void setToggled(boolean direction) {
		final List<Togglable> togglables = new ArrayList<Togglable>(this.togglables);
		if(direction) {
			cycleDown(togglables);
		}else {
			cycleUp(togglables);
		}
		this.triggerListeners();

	}

	private void cycleDown(List<Togglable> togglables) {
		if(indexOfActiveToggleable == -1 || indexOfActiveToggleable == togglables.size()){
			indexOfActiveToggleable = 0;
			togglables.get(indexOfActiveToggleable).toggle();
			return;
		}
		if(indexOfActiveToggleable>= 0 && indexOfActiveToggleable<togglables.size()){
			togglables.get(indexOfActiveToggleable).toggle();
			indexOfActiveToggleable+=1;
			if(indexOfActiveToggleable == togglables.size()){
				indexOfActiveToggleable = -1;
				return;
			}
			togglables.get(indexOfActiveToggleable).toggle();
		}
	}

	private void cycleUp(List<Togglable> togglables) {
		if(indexOfActiveToggleable == togglables.size() || indexOfActiveToggleable == -1){
			indexOfActiveToggleable = togglables.size()-1;
			togglables.get(indexOfActiveToggleable).toggle();
			return;
		}
		if(indexOfActiveToggleable>= 0 && indexOfActiveToggleable<togglables.size()){
			togglables.get(indexOfActiveToggleable).toggle();
			indexOfActiveToggleable-=1;
			if(indexOfActiveToggleable == -1){
				indexOfActiveToggleable = togglables.size();
				return;
			}
			togglables.get(indexOfActiveToggleable).toggle();
		}
	}

	@Override
	public boolean isToggled() {
		//the unique group is only considered toggled when at least one of the elements is toggled
		return indexOfActiveToggleable > -1 && indexOfActiveToggleable < togglables.size();
	}

	private static final class ToggleableGroupListener implements ToggleableListener{

		private final UniqueToggleableGroup group;

		private ToggleableGroupListener(UniqueToggleableGroup group) {
			this.group = group;
		}

		@Override
		public void onToggle(Togglable togglable) {
			int index = 0;//counter for the elements
			for (Iterator<Togglable> iterator = group.togglables.iterator(); iterator.hasNext(); index++) {
				Togglable t = iterator.next();
				if (t == togglable) {
					if(t.isToggled()) {
						//we need an active index on the group
						//so that we can cycle though the elements
						//and toggle them one by one
						group.indexOfActiveToggleable = index;
						group.triggerListeners();
					}
					continue;
				}
				if (togglable.isToggled()) {
					t.setToggled(false);
				}
			}

		}
	}

}
