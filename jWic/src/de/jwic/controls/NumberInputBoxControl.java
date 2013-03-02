package de.jwic.controls;

import de.jwic.base.Field;
import de.jwic.base.IControlContainer;
import de.jwic.base.JavaScriptSupport;
import de.jwic.events.ValueChangedListener;

@JavaScriptSupport
public class NumberInputBoxControl extends InputBoxControl {


	private Field fieldNumber;
	private static final long serialVersionUID = 1L;
	private double number = 0.0;


	private String thousandsSeparator = ",";
	private String decimalSeparator = ".";

	public NumberInputBoxControl(IControlContainer container) {
		this(container,"");
		
	}

	public NumberInputBoxControl(IControlContainer container, String name) {
		super(container, name);
		this.fieldNumber = new Field(this);
		this.fieldNumber.setValue(String.valueOf(number));
	}
	
	public Field getFieldNumber() {
		return fieldNumber;
	}
	
	
	public double getNumber(){
		return Double.parseDouble(this.fieldNumber.getValue());
	}
	
	public void setNumber(double d){
		
		this.fieldNumber.setValue(String.valueOf(d));
		this.number = d;
		this.requireRedraw();		
	}
	
	@Override
	public void removeValueChangedListener(ValueChangedListener listener) {
		this.fieldNumber.removeValueChangedListener(listener);
	}
	
	@Override
	public void addValueChangedListener(ValueChangedListener listener) {
		super.addValueChangedListener(listener);
		this.fieldNumber.addValueChangedListener(listener);
	}
	
	
	/**
	 * 
	 * @return the separator for thounsends 
	 */
	public String getThousandsSeparator () {
		return thousandsSeparator ;
	}

	/**
	 * @param thounsendsSeparator to be set
	 * Default is ',' (comma)
	 */
	public void setThousandsSeparator (char thounsendsSeparator) {
		this.thousandsSeparator  = String.valueOf(thounsendsSeparator);
	}

	/**
	 * @return
	 */
	public String getDecimalSeparator() {
		return decimalSeparator;
	}

	/**
	 * @param decimalSeparator to be set
	 * Default is '.' (dot)
	 */
	public void setDecimalSeparator(char decimalSeparator) {
		this.decimalSeparator = String.valueOf(decimalSeparator);
	}

}
