/*******************************************************************************
 * Copyright 2015 xWic group (http://www.xwic.de)
 *
 * Licensed under the Apache License, Version 2.0 (the "License").
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * 		http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software 
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and 
 * limitations under the License.
 *  
 *******************************************************************************/
package de.jwic.demo.basics;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import de.jwic.base.ControlContainer;
import de.jwic.base.IControlContainer;
import de.jwic.controls.*;
import de.jwic.events.*;
import de.jwic.samples.controls.propeditor.PropertyEditorView;

public class DatePickerDemo extends ControlContainer {
	private DateTimePicker dtInstallationDate;
	private TimeZoneSelectionCombo cmbTimeZone;

	/**
	 * Constructor.
	 * @param container
	 */
	public DatePickerDemo(IControlContainer container) {
		super(container);
		this.cmbTimeZone = new TimeZoneSelectionCombo(this, "cmbTimeZone");
		cmbTimeZone.setChangeNotification(true);
		cmbTimeZone.addElementSelectedListener(new ElementSelectedListener() {

			@Override
			public void elementSelected(ElementSelectedEvent event) {
				onTimeZoneChanged();
			}
		});

		this.dtInstallationDate = new DateTimePicker(this, "dtInstallationDate");
		dtInstallationDate.setShowSecond(false);
		dtInstallationDate.setStepMinute(15);
		dtInstallationDate.setWidth(120);
		dtInstallationDate.setUpdateOnChange(true);
		dtInstallationDate.addValueChangedListener(new ValueChangedListener() {

			@Override
			public void valueChanged(ValueChangedEvent valueChangedEvent) {
				System.out.println("Something");
			}
		});


		cmbTimeZone.selectedByKey("PST");
	}



	/**
	 *
	 */
	private void onTimeZoneChanged() {
		TimeZone tz = cmbTimeZone.getSelectedTimeZone(true);

		dtInstallationDate.setTimeZone(tz);

		// needed to refresh the date for the TZ to kick in
		dtInstallationDate.setDate(dtInstallationDate.getDate());
	}
}
