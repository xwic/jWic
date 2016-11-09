/**
 * 
 */
package de.jwic.controls;

import de.jwic.base.IControlContainer;

/**
 * @author bogdan
 * 
 */
public class NumericIntegerInputBox extends NumericInputBox {

	/**
	 * @param container
	 */
	public NumericIntegerInputBox(IControlContainer container) {
		this(container, null);
	}

	/**
	 * @param container
	 * @param name
	 */
	public NumericIntegerInputBox(IControlContainer container, String name) {
		super(container, name);
		this.setTemplateName(NumericInputBox.class.getName());
		this.setDecimalPlaces(0);
	}

	/**
	 * @return
	 */
	public Integer getNumberAsInt() {
		final Double number = getNumber();
		if (number == null) {
			return null;
		}
		return number.intValue();
	}

}
