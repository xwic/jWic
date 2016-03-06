/**
 * 
 */
package de.jwic.controls.pojoedit.handler;

import de.jwic.base.IControlContainer;
import de.jwic.controls.ListBox;
import de.jwic.controls.pojoedit.PojoField;

/**
 * Handles String properties with an InputBox field.
 * 
 * @author lippisch
 */
public class EnumFieldHandler extends AbstractFieldHandler<ListBox> {

	/* (non-Javadoc)
	 * @see de.jwic.controls.pojoedit.IFieldHandler#accepts(de.jwic.controls.pojoedit.PojoField)
	 */
	@Override
	public boolean accepts(PojoField field) {
		Class<?> type = field.getPropertyDescriptor().getPropertyType();
		return type.isEnum();
	}

	/*
	 * (non-Javadoc)
	 * @see de.jwic.controls.pojoedit.IFieldHandler#createControl(de.jwic.base.IControlContainer, de.jwic.controls.pojoedit.PojoField)
	 */
	@Override
	public ListBox createControl(IControlContainer container, PojoField field) {

		ListBox listBox = new ListBox(container, null);

		listBox.addElement("", "-");
		Class<?> type = field.getPropertyDescriptor().getPropertyType();
		if (type.isEnum()) {
			Object[] enumConstants = type.getEnumConstants();
			for (Object constant : enumConstants) {
				listBox.addElement(constant.toString(), constant.toString());
			}
			
		}
		return listBox;
	}

	/*
	 * (non-Javadoc)
	 * @see de.jwic.controls.pojoedit.IFieldHandler#loadValue(de.jwic.controls.pojoedit.PojoField, de.jwic.base.IControl, java.lang.Object)
	 */
	@Override
	public void loadValue(PojoField field, ListBox control, Object value) {
		
		if (value == null) {
			control.setSelectedKey("");
		} else {
			control.setSelectedKey(value.toString());
		}
			
	}

	/*
	 * (non-Javadoc)
	 * @see de.jwic.controls.pojoedit.IFieldHandler#readValue(de.jwic.controls.pojoedit.PojoField, de.jwic.base.IControl)
	 */
	@Override
	public Object readValue(PojoField field, ListBox control) {
		String key = control.getSelectedKey();
		if (key == null || key.isEmpty() || key.equals("-")) {
			return null;
		} else {
			Class<?> type = field.getPropertyDescriptor().getPropertyType();
			for (Object ec : type.getEnumConstants()) {
				if (ec.toString().equals(key)) {
					return ec;
				}
			}
		}
		return null;
	}

}
