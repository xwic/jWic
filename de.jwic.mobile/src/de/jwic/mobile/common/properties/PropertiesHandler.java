package de.jwic.mobile.common.properties;

import de.jwic.base.Control;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by boogie on 10/28/14.
 */
public class PropertiesHandler implements PropertyObservable {
	private final Control control;
	private final List<PropertyChangedListener> propertyChangedListeners;
	private final Map<String, Object> properties;

	public PropertiesHandler(Control control) {
		this.control = control;
		this.propertyChangedListeners = new ArrayList<PropertyChangedListener>();
		this.properties = new HashMap<String, Object>();
	}

	public void triggerPropertyChange(String propertyName, Object oldValue, Object newValue){
		for (PropertyChangedListener pl : new ArrayList<PropertyChangedListener>(this.propertyChangedListeners)){
			pl.onPropertyChanged(this.control, propertyName, oldValue, newValue);
		}
	}

	@Override
	public void addPropertyChangedListener(PropertyChangedListener listener) {
		this.propertyChangedListeners.add(listener);
	}

	@Override
	public void removePropertyChangedListener(PropertyChangedListener listener) {
		this.propertyChangedListeners.remove(listener);
	}

	public void setProperty(String name, Object value){
		setPropertyNoRedraw(name, value);
		this.control.requireRedraw();
	}

	public void setPropertyNoRedraw(String name, Object value){
		Object oldValue = this.properties.get(name);
		if(equal(value, oldValue)){
			return;
		}
		this.properties.put(name, value);
		this.triggerPropertyChange(name, oldValue, value);
	}

	/**
	 *
	 * @param name
	 * @param defaultValue
	 * @param <T>
	 * @return
	 * @throws java.lang.ClassCastException - when trying to get something of a different class
	 */
	@SuppressWarnings("unchecked")
	public <T> T getProperty(String name, Class<T> type){
		return getPropertyOrElse(name, type, null);
	}

	/**
	 *
	 * @param name
	 * @param defaultValue
	 * @param <T>
	 * @return
	 * @throws java.lang.ClassCastException - when trying to get something of a different class that the object passed as the default param
	 */
	@SuppressWarnings("unchecked")
	public <T> T getPropertyOrElse(String name,Class<T> type, T defaultValue){
		if(!this.properties.containsKey(name)){
			return defaultValue;
		}
		return type.cast(this.properties.get(name));
	}

	private static boolean equal(Object value, Object value2){
		if(value == null && value2 == null){
			return true;
		}
		if(value != null && value.equals(value2)){
			return true;
		}
		if(value2 != null && value2.equals(value)){
			return true;
		}
		return false;
	}


}
