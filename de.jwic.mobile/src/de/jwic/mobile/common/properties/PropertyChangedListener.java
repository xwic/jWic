package de.jwic.mobile.common.properties;

import de.jwic.base.Control;

/**
 * Created by boogie on 10/28/14.
 */
public interface PropertyChangedListener {
	void onPropertyChanged(Control source, String propertyName, Object oldValue, Object newValue);
}
