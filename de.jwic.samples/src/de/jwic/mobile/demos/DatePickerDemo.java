package de.jwic.mobile.demos;

import de.jwic.base.Control;
import de.jwic.base.ControlContainer;
import de.jwic.base.IControlContainer;
import de.jwic.controls.Label;
import de.jwic.events.SelectionEvent;
import de.jwic.events.SelectionListener;
import de.jwic.mobile.MobileDemoModule;
import de.jwic.common.properties.PropertyChangedListener;
import de.jwic.mobile.controls.MButton;
import de.jwic.mobile.controls.MDatePicker;

import java.util.Date;

/**
 * Created by boogie on 10/31/14.
 */
public class DatePickerDemo extends MobileDemoModule{

	public DatePickerDemo() {
		super("Date Picker Demo");
	}

	@Override
	public Control createPage(IControlContainer controlContainer) {
		final ControlContainer content = new ControlContainer(controlContainer, "datePickerDemo");

		final Label dateLabel = new Label(content, "dateLabel");
		dateLabel.setText("Select Date");

		final MDatePicker MDatePicker = new MDatePicker(content, "datePicker");

		final MButton getDateMButton = new MButton(content, "getDateButton");
		getDateMButton.setText("Get Date From Above DatePicker");
		getDateMButton.addSelectionListener(new SelectionListener() {

			@Override
			public void objectSelected(SelectionEvent event) {
				System.out.println(MDatePicker.getDate());
			}
		});

		MDatePicker.setDate(new Date());

		final Label blurUpdate = new Label(content, "blurUpdate");
		blurUpdate.setText("Date picker with update on blur:");
		final MDatePicker updateOnBlur = new MDatePicker(content, "updateOnBlur");
		updateOnBlur.setUpdateOnBlur(true);


		final PropertyChangedListener listener = new PropertyChangedListener() {

			@Override
			public void onPropertyChanged(Control source, String propertyName, Object oldValue, Object newValue) {
				if (!"date".equals(propertyName)) {
					return;
				}
				if(source == updateOnBlur) {
					blurUpdate.setText("Date Changed to " + updateOnBlur.getDate());
				}
				if(source == MDatePicker){
					dateLabel.setText("Selected Date is "+String.valueOf(newValue));
				}
			}
		};

		updateOnBlur.addPropertyChangedListener(listener);
		MDatePicker.addPropertyChangedListener(listener);



		return content;
	}
}
