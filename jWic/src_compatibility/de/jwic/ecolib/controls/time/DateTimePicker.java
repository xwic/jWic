package de.jwic.ecolib.controls.time;

import java.util.Locale;

import de.jwic.base.ControlContainer;
import de.jwic.base.IControlContainer;
import de.jwic.controls.Button;
import de.jwic.controls.DateInputBoxControl;
import de.jwic.controls.InputBoxControl;

public class DateTimePicker extends ControlContainer {

	private static final long serialVersionUID = -7081580246667940883L;
	private DateTimeModel dtModel;
	
	public DateTimePicker(IControlContainer container) {
		this(container, null);
	}

	public DateTimePicker(IControlContainer container, String name) {
		super(container, name);
		initialise();
	}
	
	private void initialise(){		
		Locale locale = getSessionContext().getLocale();
		dtModel = new DateTimeModel();
		dtModel.setLocale(locale);
		
		InputBoxControl ibcDisplay = new  InputBoxControl(this, "ibcDisplay");
		ibcDisplay.setText(dtModel.getFormattedDateTime());
		
		Button btToogle = new Button(this, "btToogle");
		btToogle.setTitle(">");
		
		DateInputBoxControl dibDate = new DateInputBoxControl(this, "dibDate");
		dibDate.setDate(dtModel.getDateTime());
		
		
		
	}
	
	

}
