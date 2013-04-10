/**
 * 
 */
package de.jwic.demo.basics;

import de.jwic.base.IControlContainer;
import de.jwic.controls.InputBoxControl;
import de.jwic.controls.basics.Label;
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
		new InputBoxControl(tlc);
		
		new Label(tlc).setText("With content");
		InputBoxControl txt = new InputBoxControl(tlc);
		txt.setWidth(500);
		txt.setText("This is a textbox. It can be modified.");
		
		new Label(tlc).setText("Empty with EmptyInfoText");
		InputBoxControl txt2 = new InputBoxControl(tlc);
		txt2.setWidth(500);
		txt2.setEmptyInfoText("Enter something smart here..");

		new Label(tlc).setText("In Error State");
		InputBoxControl txt3 = new InputBoxControl(tlc);
		txt3.setWidth(500);
		txt3.setFlagAsError(true);
		txt3.setText("This is not correct.");

		
	}

}
