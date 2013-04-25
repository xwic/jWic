package de.jwic.demo.basics;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import de.jwic.base.ControlContainer;
import de.jwic.base.IControlContainer;
import de.jwic.controls.Button;
import de.jwic.controls.DateChangedListener;
import de.jwic.controls.DatePicker;
import de.jwic.controls.DateTimePicker;
import de.jwic.controls.LabelControl;
import de.jwic.events.SelectionEvent;
import de.jwic.events.SelectionListener;
import de.jwic.events.ValueChangedEvent;
import de.jwic.events.ValueChangedListener;
import de.jwic.samples.controls.propeditor.PropertyEditorView;

public class DatePickerDemo extends ControlContainer {

	private DatePicker datePicker;
	private DateTimePicker dateTimePicker;
	private LabelControl lblInfo;

	/**
	 * Constructor.
	 * @param container
	 */
	public DatePickerDemo(IControlContainer container) {
		super(container);
		
		// create the button instance
		datePicker = new DatePicker(this, "datePicker");
		dateTimePicker = new DateTimePicker(this, "dateTimePicker");
		
		Button btn = new Button(this, "btn");
		btn.setTitle("Switch locale");

		btn.addSelectionListener(new SelectionListener() {
			private boolean DE = false;

			public void objectSelected(SelectionEvent event) {
				if (DE) {
					datePicker.setLocale(Locale.ENGLISH);
					dateTimePicker.setLocale(Locale.ENGLISH);
					datePicker.getLocale();
					DE = false;
				} else {
					datePicker.setLocale(Locale.GERMAN);
					dateTimePicker.setLocale(Locale.GERMAN);
					DE = true;
				}
			}
		});
		
		DateChangedListener listener = new DateChangedListener() {

			public void onDateChanged(Date oldDate, Date newDate) {
				DateFormat dateFormatter = DateFormat.getDateTimeInstance(DateFormat.LONG,
						DateFormat.LONG, datePicker.getLocale());
				if (newDate != null) {
					lblInfo.setText("Selected Date is: "
							+ dateFormatter.format(newDate));
				} else {
					lblInfo.setText("Selected Date is: null");
				}
			}
		};
		
		datePicker.addDateChangedListener(listener);
		dateTimePicker.addDateChangedListener(listener);
				
		lblInfo = new LabelControl(this, "lblInfo");
		
		final PropertyEditorView propEditor = new PropertyEditorView(this, "propEditor");
		propEditor.setBean(datePicker);
		
		datePicker.addValueChangedListener(new ValueChangedListener() {
			/* (non-Javadoc)
			 * @see de.jwic.events.ValueChangedListener#valueChanged(de.jwic.events.ValueChangedEvent)
			 */
			public void valueChanged(ValueChangedEvent event) {
				propEditor.loadValues();	 // reload values.			
			}
		});
				
		propEditor.loadValues(); // refresh values.
		
	}
	

}
