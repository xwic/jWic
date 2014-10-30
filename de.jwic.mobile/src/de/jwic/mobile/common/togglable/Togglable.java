package de.jwic.mobile.common.togglable;

/**
 * Any object (control or otherwise) that can exist in multiple states
 * and notifies interested parties when its state changes.
 *
 * Created by boogie on 10/28/14.
 */
public interface Togglable {

	/**
	 * Cycles the states of the togglable instance
	 */
	void toggle();

	/**
	 * Sets the state of this togglable to the "on" state.
	 * The Toggleable instance can have any number of state of only one should be considered "On"
	 *
	 * @param on - if true set the state to the "on" state. otherwise set this instance to an "off" state
	 */
	void setToggled(boolean on);

	/**
	 *
	 * @return true if this toggleable is in the "On" state, false otherwise
	 */
	boolean isToggled();

	/**
	 *
	 * @param listener - to be added
	 */
	void addToggleableListener(ToggleableListener listener);

	/**
	 *
	 * @param listener - to be removed
	 */
	void removeToggleableListener(ToggleableListener listener);
}
