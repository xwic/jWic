package de.jwic.mobile.common.enableable;

/**
 * Created by boogie on 10/29/14.
 */
public interface Enableable {
	void enable();
	void disable();
	boolean isEnabled();
	void setEnabled(boolean enabled);
}
