package de.jwic.demo.basics;

import de.jwic.base.IControlContainer;
import de.jwic.demo.DemoModule;

/**
 * 
 * @author dotto
 *
 */
public class DatePickerDemoModule extends DemoModule {

	/**
	 * 
	 */
	public DatePickerDemoModule() {
		setTitle("DatePicker");
		setDescription("Input Box which provides localized date selection.");
		
	}
	
	/* (non-Javadoc)
	 * @see de.jwic.demo.DemoModule#createModule(de.jwic.base.IControlContainer)
	 */
	@Override
	public void createModule(IControlContainer container) {
		new DatePickerDemo(container);
	}

}

