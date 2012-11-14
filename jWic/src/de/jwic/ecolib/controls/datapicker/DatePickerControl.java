/*
 * Copyright 2005-2007 jWic group (http://www.jwic.de)
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * de.jwic.ecolib.controls.datapicker.DatePicker
 * Created on 12.11.2012
 * $Id:$
 */
package de.jwic.ecolib.controls.datapicker;

import java.sql.Date;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import de.jwic.base.IControlContainer;
import de.jwic.controls.InputBoxControl;

/**
 * 
 * @author bogdan
 */
public class DatePickerControl extends InputBoxControl {
	private static final long serialVersionUID = 1L;
	private Locale locale;
	private Date date;
	private final List<DateChangedListener> listeners = new ArrayList<DateChangedListener>();

	/**
	 * @param container
	 */
	public DatePickerControl(IControlContainer container) {
		super(container);
		init();
	}

	/**
	 * @param container
	 * @param name
	 */
	public DatePickerControl(IControlContainer container, String name) {
		super(container, name);
		init();
	}

	private void init() {
		locale = getSessionContext().getLocale();
	}

	/**
	 * @return the locale
	 */
	public Locale getLocale() {
		return locale;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.jwic.controls.InputBoxControl#actionPerformed(java.lang.String,
	 * java.lang.String)
	 */
	@Override
	public void actionPerformed(String actionId, String parameter) {
		if ("datechanged".equals(actionId)) {
			Date oldDate = this.getDate();
			this.date = new Date(Long.valueOf(parameter));
			this.notifyListeners(oldDate, date);
		}

	}

	private void notifyListeners(Date oldDate, Date newDate) {
		for (DateChangedListener d : listeners) {
			d.onDateChanged(oldDate, newDate);
		}
	}

	/**
	 * @param locale
	 *            the locale to set
	 */
	public void setLocale(Locale locale) {
		this.locale = locale;
		this.setDate(this.getDate());
	}

	/**
	 * @param date
	 *            the date to set
	 */
	public void setDate(Date date) {
		Date oldDate = this.getDate();
		this.date = date;
		DateFormat formatter = DateFormat.getDateTimeInstance(DateFormat.SHORT,
				DateFormat.SHORT, locale);
		this.setText(formatter.format(date));
		this.notifyListeners(oldDate, date);
	}

	/**
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}
	
	public void addDateChangedListener(DateChangedListener d){
		this.listeners.add(d);
	}
	public void removeDateChangedListener(DateChangedListener d){
		this.listeners.remove(d);
	}

}
