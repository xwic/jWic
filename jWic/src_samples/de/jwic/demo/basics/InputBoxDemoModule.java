/**
 * 
 */
package de.jwic.demo.basics;

import de.jwic.base.IControlContainer;
import de.jwic.controls.DatePicker;
import de.jwic.controls.InputBox;
import de.jwic.controls.Label;
import de.jwic.controls.NumericInputBox;
import de.jwic.controls.layout.TableLayoutContainer;
import de.jwic.demo.DemoModule;

/**
 * @author lippisch
 *
 */
public class InputBoxDemoModule extends DemoModule {

	/**
	 * 
	 */
	public InputBoxDemoModule() {
		setTitle("Input Box");
		setDescription("A control that allows the user to enter text");
		
	}
	
	/* (non-Javadoc)
	 * @see de.jwic.demo.DemoModule#createModule(de.jwic.base.IControlContainer)
	 */
	@Override
	public void createModule(IControlContainer container) {

		TableLayoutContainer tlc = new TableLayoutContainer(container);
		tlc.setColumnCount(2);
		
		new Label(tlc).setText("Basic Input Field");
		new InputBox(tlc);
		
		new Label(tlc).setText("With content");
		InputBox txt = new InputBox(tlc);
		txt.setWidth(500);
		txt.setText("This is a textbox. It can be modified.");
		
		new Label(tlc).setText("Empty with EmptyInfoText");
		InputBox txt2 = new InputBox(tlc);
		txt2.setWidth(500);
		txt2.setEmptyInfoText("Enter something smart here..");

		new Label(tlc).setText("In Error State");
		InputBox txt3 = new InputBox(tlc);
		txt3.setWidth(500);
		txt3.setFlagAsError(true);
		txt3.setText("This is not correct.");


		new Label(tlc).setText("NumericInputBox");
		NumericInputBox numInp = new NumericInputBox(tlc);
		numInp.setWidth(120);
		numInp.setNumber(120750.50d);
		
		new Label(tlc).setText("DatePicker");
		DatePicker datePicker = new DatePicker(tlc);
		datePicker.setWidth(500);
		datePicker.setEmptyInfoText("Select a date..");
	}

}
