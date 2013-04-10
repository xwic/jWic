package de.jwic.samples.controls.propeditor;

import java.util.HashMap;
import java.util.Map;

import de.jwic.base.IControlContainer;
import de.jwic.controls.ListBoxControl;

/**
 * Small helper for selection of enums.
 * @author dotto
 *
 * @param <T>
 */
public class EnumListBoxControl<T extends Enum<?>> extends ListBoxControl {

	private Map<String, T> enumMap = new HashMap<String, T>();
	public EnumListBoxControl(IControlContainer container, String name, Class<T> enumClass) {
		super(container, name);
		setTemplateName(ListBoxControl.class.getName());
		for (T enumObj : enumClass.getEnumConstants()) {
			addElement(enumObj.name());
			enumMap.put(enumObj.name(), enumObj);
		}
	}
	
	/**
	 * 
	 * @return
	 */
	public T getEnumObject(){
		if(getSelectedKey() == null || getSelectedKey().length() == 0)
			return null;
		return enumMap.get(getSelectedKey());
	}

}
