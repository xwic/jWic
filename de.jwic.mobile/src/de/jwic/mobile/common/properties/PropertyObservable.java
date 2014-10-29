package de.jwic.mobile.common.properties;

/**
 * Created by boogie on 10/28/14.
 */
public interface PropertyObservable {
	public void addPropertyChangedListener(PropertyChangedListener listener);
	public void removePropertyChangedListener(PropertyChangedListener listener);
}
