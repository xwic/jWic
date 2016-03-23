/**
 * 
 */
package de.jwic.controls.pojoedit.handler;

import de.jwic.base.IControlContainer;
import de.jwic.controls.InputBox;
import de.jwic.controls.pojoedit.PojoField;

/**
 * Handles String properties with an InputBox field.
 * 
 * @author lippisch
 */
public class TextFieldHandler extends AbstractFieldHandler<InputBox> {


	/* (non-Javadoc)
	 * @see de.jwic.controls.pojoedit.IFieldHandler#accepts(de.jwic.controls.pojoedit.PojoField)
	 */
	@Override
	public boolean accepts(PojoField field) {
		Class<?> type = field.getPropertyDescriptor().getPropertyType();
		return type.isAssignableFrom(String.class);
	}

	/*
	 * (non-Javadoc)
	 * @see de.jwic.controls.pojoedit.IFieldHandler#createControl(de.jwic.base.IControlContainer, de.jwic.controls.pojoedit.PojoField)
	 */
	@Override
	public InputBox createControl(IControlContainer container, PojoField field) {

		InputBox inp = new InputBox(container);
		inp.setFillWidth(true);
		
		if (field.getAnnotation() != null) {
			if (field.getAnnotation().multiline()) {
				inp.setMultiLine(true);
				inp.setHeight(80);
			}
		}
		
		return inp;
	}

	/*
	 * (non-Javadoc)
	 * @see de.jwic.controls.pojoedit.IFieldHandler#loadValue(de.jwic.controls.pojoedit.PojoField, de.jwic.base.IControl, java.lang.Object)
	 */
	@Override
	public void loadValue(PojoField field, InputBox control, Object value) {

		if (value != null) {
			control.setText(value.toString());
		} else {
			control.setText("");
		}

	}

	/*
	 * (non-Javadoc)
	 * @see de.jwic.controls.pojoedit.IFieldHandler#readValue(de.jwic.controls.pojoedit.PojoField, de.jwic.base.IControl)
	 */
	@Override
	public Object readValue(PojoField field, InputBox control) {
		return control.getText();
	}

}
