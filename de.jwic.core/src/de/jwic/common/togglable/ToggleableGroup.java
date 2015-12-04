package de.jwic.common.togglable;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * The Toggleable Group Utility class
 * This class has the purpose of binding 2 or more toggleable elements so that only one can be active at a time
 *
 * Created by boogie on 10/30/14.
 */
public abstract class ToggleableGroup implements Togglable{
	private final Set<ToggleableListener> toggleableListeners;


	ToggleableGroup() {
		this.toggleableListeners = new HashSet<ToggleableListener>();
	}

	public abstract Set<Togglable> getActiveTogglables();

	@Override
	public void addToggleableListener(ToggleableListener listener) {
		this.toggleableListeners.add(listener);
	}

	@Override
	public void removeToggleableListener(ToggleableListener listener) {
		this.toggleableListeners.remove(listener);
	}

	protected final void triggerListeners(){
		for(ToggleableListener t : new HashSet<ToggleableListener>(this.toggleableListeners)){
			t.onToggle(this);
		}
	}

	/**
	 * Creates a toggleable group that allows at most 1 element to be active and automatically de-toggles the rest of the elements
	 * Toggling the group will cycle through the elements always activating the next element and deactivating the previous.
	 * @param togglables
	 * @return
	 */
	public static ToggleableGroup unique(Togglable...togglables){
		return unique(Arrays.asList(togglables));
	}

	/**
	 *
	 * @param togglables
	 * @return
	 */
	public static ToggleableGroup unique(Collection<Togglable> togglables){
		return new UniqueToggleableGroup(togglables);
	}

	/**
	 * Creates a toggleable group that permits multiple elements to active at the same time.
	 * Toggling the group will cause the elements to flip state
	 * @param togglables1
	 * @return
	 */
	public static ToggleableGroup multiple(Togglable...togglables1){
		return multiple(Arrays.asList(togglables1));
	}

	public static ToggleableGroup multiple(Collection<Togglable> togglables1){
		return new MultiToggleableGroup(togglables1);
	}

	/**
	 * Creates a toggleable group of two elements that alternate states i.e. if first is toggled second won't be.
	 * Toggling the group will cause the two elements to flip state.
	 * The group is considered toggled if the first element is toggled.
	 * @param first
	 * @param second
	 * @return
	 */
	public static ToggleableGroup alternating(Togglable first, Togglable second){
		return new AlternatingToggleableGroup(first, second);
	}

}
