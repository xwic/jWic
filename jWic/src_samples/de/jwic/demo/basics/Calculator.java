/**
 * 
 */
package de.jwic.demo.basics;

import de.jwic.base.ControlContainer;
import de.jwic.base.IControlContainer;
import de.jwic.controls.ListBoxControl;
import de.jwic.controls.NumericInputControl;
import de.jwic.controls.basics.Button;
import de.jwic.events.SelectionEvent;
import de.jwic.events.SelectionListener;

/**
 * A simple calculator control that is used as an embedded control in various demos.
 * 
 * @author lippisch
 */
public class Calculator extends ControlContainer {

	private NumericInputControl inpValue1;
	private NumericInputControl inpValue2;
	private NumericInputControl inpResult;
	private ListBoxControl lbOp;

	/**
	 * @param container
	 * @param name
	 */
	public Calculator(IControlContainer container, String name) {
		super(container, name);

		inpValue1 = new NumericInputControl(this, "inpValue1");
		inpValue1.setWidth(100);
		inpValue1.setNumber(21.0);
		
		inpValue2 = new NumericInputControl(this, "inpValue2");
		inpValue2.setWidth(100);
		inpValue2.setNumber(2.0);
		
		lbOp = new ListBoxControl(this, "lbOp");
		lbOp.setWidth(40);
		lbOp.addElement("+");
		lbOp.addElement("-");
		lbOp.addElement("*");
		lbOp.addElement("/");
		lbOp.setSelectedKey("*");
		
		inpResult = new NumericInputControl(this, "inpResult");
		inpResult.setWidth(100);
		inpResult.setReadonly(true);
	
		Button btCalculate = new Button(this, "btCalculate");
		btCalculate.setTitle("Calculate");
		btCalculate.addSelectionListener(new SelectionListener() {
			@Override
			public void objectSelected(SelectionEvent event) {
				calculate();
			}
		});
		
	}

	/**
	 * 
	 */
	protected void calculate() {
		
		Double v1 = inpValue1.getNumber();
		Double v2 = inpValue2.getNumber();
		
		if (v1 != null && v2 != null) {
		
			Double result = 0.0;
			if (lbOp.getSelectedKey().equals("+")) {
				result = v1 + v2;
			} else if (lbOp.getSelectedKey().equals("-")) {
				result = v1 - v2;
			} else if (lbOp.getSelectedKey().equals("*")) {
				result = v1 * v2;
			} else if (lbOp.getSelectedKey().equals("/")) {
				if (v2 != 0.0) {
					result = v1 / v2;
				}
			}
			inpResult.setNumber(result);
			
		}
		
	}

	
	
}
