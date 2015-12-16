package de.jwic.common.togglable;

/**
 * Created by boogie on 11/4/14.
 */
public class NegativeToggleable implements Togglable{

	private final Togglable togglable;

	public NegativeToggleable(Togglable togglable) {
		this.togglable = togglable;
	}

	@Override
	public void toggle() {
		togglable.toggle();
	}

	@Override
	public void setToggled(boolean on) {
		this.togglable.setToggled(!on);
	}

	@Override
	public boolean isToggled() {
		return !this.togglable.isToggled();
	}

	@Override
	public void addToggleableListener(ToggleableListener listener) {
		togglable.addToggleableListener(listener);
	}

	@Override
	public void removeToggleableListener(ToggleableListener listener) {
		togglable.removeToggleableListener(listener);
	}
}
