/**
 * 
 */
package de.jwic.demo.basics;

import java.util.regex.Pattern;

import de.jwic.base.IControlContainer;
import de.jwic.controls.Button;
import de.jwic.controls.DatePicker;
import de.jwic.controls.InputBox;
import de.jwic.controls.Label;
import de.jwic.controls.LabelControl;
import de.jwic.controls.NumericInputBox;
import de.jwic.controls.ValidatedInputBox;
import de.jwic.controls.layout.TableLayoutContainer;
import de.jwic.demo.DemoModule;
import de.jwic.events.SelectionEvent;
import de.jwic.events.SelectionListener;

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
		
		new LabelControl(tlc).setText("Basic Input Field");
		new InputBox(tlc);
		
		new LabelControl(tlc).setText("With content");
		InputBox txt = new InputBox(tlc);
		txt.setWidth(500);
		txt.setText("This is text in a InputBox. It can be modified.");

		new LabelControl(tlc).setText("I am disabled");
		InputBox txtDis = new InputBox(tlc);
		txtDis.setWidth(500);
		txtDis.setEnabled(false);
		txtDis.setText("This is text in a InputBox. It can be modified.");

		new LabelControl(tlc).setText("TextArea");
		InputBox txtArea = new InputBox(tlc);
		txtArea.setWidth(500);
		txtArea.setMultiLine(true);
		txtArea.setHeight(100);
		txtArea.setText("This is a multiline InputBox with specific dimensions.");

		
		new LabelControl(tlc).setText("Empty with EmptyInfoText");
		InputBox txt2 = new InputBox(tlc);
		txt2.setWidth(500);
		txt2.setEmptyInfoText("Enter something smart here..");

		new LabelControl(tlc).setText("In Error State");
		InputBox txt3 = new InputBox(tlc);
		txt3.setWidth(500);
		txt3.setFlagAsError(true);
		txt3.setText("This is not correct.");


		new LabelControl(tlc).setText("NumericInputBox");
		NumericInputBox numInp = new NumericInputBox(tlc);
		numInp.setWidth(120);
		numInp.setNumber(120750.50d);
		
		new LabelControl(tlc).setText("DatePicker");
		DatePicker datePicker = new DatePicker(tlc);
		datePicker.setWidth(500);
		datePicker.setEmptyInfoText("Select a date..");
		
		
		new Label(tlc).setText("Validated Input (Here its an email address) :");
		final ValidatedInputBox vib = new ValidatedInputBox(tlc);
		vib.setRegExp(Pattern.compile("^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}$")); // validates an email address
		
		
	}

}
