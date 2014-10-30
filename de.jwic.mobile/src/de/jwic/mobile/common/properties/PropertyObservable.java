package de.jwic.mobile.common.properties;

/**
 * Any control that has properties that can change from the UI (via user interaction or otherwise)
 * should implement this interface in order to notify interested parties of UI state change
 * Created by boogie on 10/28/14.
 */
public interface PropertyObservable {
	public void addPropertyChangedListener(PropertyChangedListener listener);
	public void removePropertyChangedListener(PropertyChangedListener listener);
}
