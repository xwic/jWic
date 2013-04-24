package de.jwic.controls;

import org.apache.log4j.Logger;

import de.jwic.base.IControlContainer;

/**
 * Date Time Picker Control
 * @author dotto
 *
 */
public class DateTimePicker extends DatePicker {

	private static final Logger log = Logger.getLogger(DateTimePicker.class);
	
	public DateTimePicker(IControlContainer container) {
		super(container);
	}
	
	
	public DateTimePicker(IControlContainer container, String name) {
		super(container, name);
	}

}
