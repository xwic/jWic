package de.jwic.ecolib.samples.controls;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.Locale;

import de.jwic.base.ControlContainer;
import de.jwic.base.IControlContainer;
import de.jwic.controls.Button;
import de.jwic.controls.InputBoxControl;
import de.jwic.controls.ListBoxControl;
import de.jwic.ecolib.controls.ErrorWarningControl;
import de.jwic.ecolib.controls.time.TimePickerControl;
import de.jwic.events.ElementSelectedEvent;
import de.jwic.events.ElementSelectedListener;
import de.jwic.events.SelectionEvent;
import de.jwic.events.SelectionListener;
import de.jwic.events.ValueChangedEvent;
import de.jwic.events.ValueChangedListener;

/**
 * Demonstrates the use of the TimePickerControl.
 * Features include:
 * <ul><li>Setting time using control which updates an input field</li>
 * <li>Toggling display of seconds</li>
 * <li>Setting time using an input box which updates the control</li></ul>
 * 
 *  Created on 21.09.2006
 * @author Mark Frewin
 */
public class TimePickerDemo extends ControlContainer{

	private static final long serialVersionUID = -1454341107449793071L;
	private InputBoxControl ibcDisplay;
	private TimePickerControl timePicker;
	private ErrorWarningControl errCtrl;
	private InputBoxControl ibcHint;
	private ListBoxControl lbcEvents;
	
	/**
	 * 
	 * @param container
	 */
	public TimePickerDemo(IControlContainer container) {
		this(container, null);
	}

	/**
	 * 
	 * @param container
	 * @param name
	 */
	public TimePickerDemo(IControlContainer container, String name) {
		super(container, name);
		initialise();
	}
	
	/**
	 * Setup the controls.
	 *
	 */
	private void initialise() {
		
		timePicker = new TimePickerControl(this, "timePicker");
		timePicker.addValueChangedListener(new ValueChangedListener () {
			private static final long serialVersionUID = 1L;
			public void valueChanged(ValueChangedEvent event) {
				String tpTime = timePicker.getFormattedTime();
				if (!ibcDisplay.getText().equals(tpTime)) {
					ibcDisplay.setText(tpTime);
				}
				lbcEvents.addElement(new Date() + ": ValueChangedEvent.");
			}			
		});
		
		errCtrl = new ErrorWarningControl(this, "errCtrl");
		errCtrl.setVisible(false);
		errCtrl.setAutoClose(true);
		
		ibcDisplay = new InputBoxControl(this, "ibcDisplay");		
		ibcDisplay.setText(timePicker.getFormattedTime());
		
		Button btSetTime = new Button(this, "btSetTime");
		btSetTime.setTitle("Set Time");		
		btSetTime.addSelectionListener(new SelectionListener() {
			private static final long serialVersionUID = 2825601330586301276L;
			public void objectSelected(SelectionEvent event) {		
				doSetTime();
			}			
		});
		
		ListBoxControl lbcDateFormat = new ListBoxControl(this, "lbcDateFormat");
		lbcDateFormat.addElement("SHORT", "" + DateFormat.SHORT);
		lbcDateFormat.addElement("MEDIUM", "" + DateFormat.MEDIUM);
		lbcDateFormat.setSelectedKey("" + DateFormat.MEDIUM);
		lbcDateFormat.setChangeNotification(true);
		lbcDateFormat.addElementSelectedListener(new ElementSelectedListener() {
			private static final long serialVersionUID = -6702060651576623911L;
			public void elementSelected(ElementSelectedEvent event) {
				ListBoxControl lbc = (ListBoxControl)event.getEventSource();
				timePicker.setDateFormatStyle(Integer.parseInt(lbc.getSelectedKey()));				
			}			
		});
		
		ListBoxControl lbcLocale = new ListBoxControl(this, "lbcLocale");		
		lbcLocale.addElement("GERMANY", "de");
		lbcLocale.addElement("AUSTRALIA", "au");
		lbcLocale.addElement("US", "us");
		lbcLocale.addElement("CANADA", "ca");
		lbcLocale.setSelectedKey("de");
		lbcLocale.setChangeNotification(true);
		lbcLocale.addElementSelectedListener(new ElementSelectedListener() {
			private static final long serialVersionUID = 8527808530365307746L;
			public void elementSelected(ElementSelectedEvent event) {
				ListBoxControl lbc = (ListBoxControl)event.getEventSource();
				Locale locale = new Locale(lbc.getSelectedKey(),"","");
				timePicker.setLocale(locale);				
			}
			
		});
		
		ibcHint = new InputBoxControl(this, "ibcHint");
		ibcHint.setText("hh:mm:ss");
		
		Button btSetHint = new Button(this, "btSetHint");
		btSetHint.setTitle("Set Hint");
		btSetHint.addSelectionListener(new SelectionListener() {
			private static final long serialVersionUID = 2111554429176400688L;
			public void objectSelected(SelectionEvent event) {
				timePicker.setHint(ibcHint.getText());
			}
			
		});
		
		
		lbcEvents = new ListBoxControl(this, "events");
		lbcEvents.setSize(10);
		lbcEvents.setWidth(500);
		
	}
	
	/**
	 * Allows user to manually input a time that will update
	 * the TimePickerControl.  
	 *
	 */
	private void doSetTime() {
		String input = ibcDisplay.getText();		
		Date date = null;
		//Parse
	    try {
	        date = timePicker.getDateFormatter().parse(input);
	        Date tpTime = timePicker.getTime();
	        if (!tpTime.equals(date)) {
		        timePicker.setTime(date);		        
	        }
	    } catch (ParseException e) {	    		    	
	    	errCtrl.setText("Unable to parse input as date.");	    	
	    	errCtrl.setVisible(true);	    	
	    }	   
	}	

}
