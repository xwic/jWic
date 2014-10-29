package de.jwic.mobile.common.togglable;

/**
 * Created by boogie on 10/28/14.
 */
public interface Togglable {

	void toggle();

	void setState(boolean on);

	boolean isState();
}
