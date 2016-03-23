/**
 * 
 */
package de.jwic.controls.pojoedit.handler;

import de.jwic.base.IControlContainer;
import de.jwic.controls.NumericInputBox;
import de.jwic.controls.pojoedit.PojoField;

/**
 * Handles Numeric properties with an InputBox field.
 * 
 * @author lippisch
 */
public class NumberFieldHandler extends AbstractFieldHandler<NumericInputBox> {


	/* (non-Javadoc)
	 * @see de.jwic.controls.pojoedit.IFieldHandler#accepts(de.jwic.controls.pojoedit.PojoField)
	 */
	@Override
	public boolean accepts(PojoField field) {
		Class<?> type = field.getPropertyDescriptor().getPropertyType();
		return type.isAssignableFrom(Integer.class) 
				|| type.isAssignableFrom(int.class)
				|| type.isAssignableFrom(Long.class)
				|| type.isAssignableFrom(long.class)
				|| type.isAssignableFrom(Double.class)
				|| type.isAssignableFrom(double.class)
				;
	}

	/*
	 * (non-Javadoc)
	 * @see de.jwic.controls.pojoedit.IFieldHandler#createControl(de.jwic.base.IControlContainer, de.jwic.controls.pojoedit.PojoField)
	 */
	@Override
	public NumericInputBox createControl(IControlContainer container, PojoField field) {

		NumericInputBox inp = new NumericInputBox(container);
		inp.setFillWidth(true);
		Class<?> type = field.getPropertyDescriptor().getPropertyType();
		if (type.isAssignableFrom(Integer.class) 
				|| type.isAssignableFrom(int.class)
				|| type.isAssignableFrom(Long.class)
				|| type.isAssignableFrom(long.class)) {
			inp.setDecimalPlaces(0);
		}
				

		return inp;
	}

	/*
	 * (non-Javadoc)
	 * @see de.jwic.controls.pojoedit.IFieldHandler#loadValue(de.jwic.controls.pojoedit.PojoField, de.jwic.base.IControl, java.lang.Object)
	 */
	@Override
	public void loadValue(PojoField field, NumericInputBox control, Object value) {

		if (value != null) {
			if (value instanceof Number) {
				control.setNumber(((Number)value).doubleValue());
			}
		} else {
			control.setNumber(0d);
		}

	}

	/*
	 * (non-Javadoc)
	 * @see de.jwic.controls.pojoedit.IFieldHandler#readValue(de.jwic.controls.pojoedit.PojoField, de.jwic.base.IControl)
	 */
	@Override
	public Object readValue(PojoField field, NumericInputBox control) {
		// find out target type to cast to the right number
		double value = control.getNumber();
		Class<?> type = field.getPropertyDescriptor().getPropertyType(); 
		if (type.isAssignableFrom(int.class) || type.isAssignableFrom(Integer.class)) {
			return new Double(value).intValue();
		} else if (type.isAssignableFrom(long.class) || type.isAssignableFrom(Long.class)) {
				return new Double(value).longValue();
		}
		
		return new Double(value);
	}

}
