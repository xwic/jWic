package de.jwic.common.enableable;

/**
 * Any object (control or otherwise) that can be activated and deactivated.
 * Created by boogie on 10/29/14.
 */
public interface Enableable {

	/**
	 * Enables this instance
	 */
	void enable();

	/**
	 * Disables this instance
	 */
	void disable();

	/**
	 *
	 * @return true if this instance is enabled, false otherwise
	 */
	boolean isEnabled();

	/**
	 *
	 * @param enabled - if true set the state of this to enabled, else set to disabled
	 */
	void setEnabled(boolean enabled);
}
