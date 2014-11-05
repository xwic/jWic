package de.jwic.common.visible;

import de.jwic.common.togglable.Togglable;
import de.jwic.common.togglable.ToggleableListener;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Created by boogie on 11/4/14.
 */
public class VisibilityToggleable implements Togglable{

	private final Visible visible;
	private final Set<ToggleableListener> toggleableListeners;

	public VisibilityToggleable(Visible visible) {
		this.visible = visible;
		this.toggleableListeners = new LinkedHashSet<ToggleableListener>();
	}

	@Override
	public void toggle() {
		this.setToggled(!this.isToggled());
	}

	@Override
	public void setToggled(boolean on) {
		if(this.isToggled() == on){
			return;
		}
		this.visible.setVisible(on);

		for(ToggleableListener t : new LinkedHashSet<ToggleableListener>(this.toggleableListeners)){
			t.onToggle(this);
		}
	}

	@Override
	public boolean isToggled() {
		return this.visible.isVisible();
	}

	@Override
	public void addToggleableListener(ToggleableListener listener) {
		this.toggleableListeners.add(listener);
	}

	@Override
	public void removeToggleableListener(ToggleableListener listener) {
		this.toggleableListeners.remove(listener);
	}
}
