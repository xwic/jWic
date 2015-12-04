package de.jwic.common.togglable;

import java.util.Collections;
import java.util.Set;

/**
 * Created by boogie on 11/4/14.
 */
class AlternatingToggleableGroup extends ToggleableGroup{

	private final AlternatingToggleListener alternatingToggleListener;
	private final Togglable first;
	private final Togglable second;

	AlternatingToggleableGroup(Togglable first, Togglable second) {
		super();
		alternatingToggleListener = new AlternatingToggleListener(this);

		this.first = first;
		this.second = second;
		this.first.addToggleableListener(alternatingToggleListener);
		this.second.setToggled(!first.isToggled());
		this.second.addToggleableListener(alternatingToggleListener);

	}


	@Override
	public Set<Togglable> getActiveTogglables() {
		final Togglable active = this.first.isToggled() ? this.first : this.second;
		return Collections.singleton(active);
	}

	@Override
	public void toggle() {
		first.toggle();
		triggerListeners();
	}

	@Override
	public void setToggled(boolean on) {
		first.setToggled(on);
		triggerListeners();
	}

	@Override
	public boolean isToggled() {
		return first.isToggled();
	}


	private static class AlternatingToggleListener implements ToggleableListener{

		private final AlternatingToggleableGroup group;

		private AlternatingToggleListener(AlternatingToggleableGroup group) {
			this.group = group;
		}

		@Override
		public void onToggle(Togglable togglable) {
			if(group.first == togglable){
				group.second.setToggled(!togglable.isToggled());
			}
			if(group.second == togglable){
				group.first.setToggled(!togglable.isToggled());
			}
		}
	}

}
