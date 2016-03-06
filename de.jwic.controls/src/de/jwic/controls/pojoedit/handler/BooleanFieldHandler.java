/**
 * 
 */
package de.jwic.controls.pojoedit.handler;

import de.jwic.base.IControlContainer;
import de.jwic.controls.CheckBox;
import de.jwic.controls.pojoedit.PojoField;

/**
 * Handles Boolean properties with a CheckBox field.
 * 
 * @author lippisch
 */
public class BooleanFieldHandler extends AbstractFieldHandler<CheckBox> {

	/* (non-Javadoc)
	 * @see de.jwic.controls.pojoedit.IFieldHandler#accepts(de.jwic.controls.pojoedit.PojoField)
	 */
	@Override
	public boolean accepts(PojoField field) {
		Class<?> type = field.getPropertyDescriptor().getPropertyType();
		return type.isAssignableFrom(Boolean.class) || type.isAssignableFrom(boolean.class);
	}

	/*
	 * (non-Javadoc)
	 * @see de.jwic.controls.pojoedit.IFieldHandler#createControl(de.jwic.base.IControlContainer, de.jwic.controls.pojoedit.PojoField)
	 */
	@Override
	public CheckBox createControl(IControlContainer container, PojoField field) {

		CheckBox inp = new CheckBox(container, null);
		return inp;
	}

	/*
	 * (non-Javadoc)
	 * @see de.jwic.controls.pojoedit.IFieldHandler#loadValue(de.jwic.controls.pojoedit.PojoField, de.jwic.base.IControl, java.lang.Object)
	 */
	@Override
	public void loadValue(PojoField field, CheckBox control, Object value) {

		if (value != null) {
			control.setChecked(Boolean.TRUE.equals(value));
		} else {
			control.setChecked(false);
		}

	}

	/*
	 * (non-Javadoc)
	 * @see de.jwic.controls.pojoedit.IFieldHandler#readValue(de.jwic.controls.pojoedit.PojoField, de.jwic.base.IControl)
	 */
	@Override
	public Object readValue(PojoField field, CheckBox control) {
		return control.isChecked();
	}

}
