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
package de.jwic.ecolib.controls.datepicker;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.apache.log4j.Logger;

import de.jwic.base.IControlContainer;
import de.jwic.controls.InputBoxControl;

/**
 * 
 * @author bogdan
 */
public class DatePickerControl extends InputBoxControl {
	private static final long serialVersionUID = 1L;

	public static final String NO_FORMAT = "noformat";
	private static final String CSS_CLASS = "datePickerControl";

	private Locale locale;
	private Date date;
	private final List<DateChangedListener> listeners = new ArrayList<DateChangedListener>();
	private boolean showMonth = true;
	private boolean showYear = true;
	private String dateFormat = NO_FORMAT;
	private int numberOfMonths = 1;
	private boolean showWeek = false;

	private boolean iconTriggered = false;

	private static final Logger log = Logger.getLogger(DatePickerControl.class);

	/**
	 * @param container
	 */
	public DatePickerControl(IControlContainer container) {
		super(container);
		init();
		this.setDateFormat(this.getSessionContext().getDateFormat());
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
		locale = this.getSessionContext().getLocale();
		setCssClass(CSS_CLASS);
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
			this.setDate(new Date(Long.valueOf(parameter)));
		}
		if ("localeNotFound".equals(actionId)) {
			this.setLocale(Locale.ENGLISH);
			log.info("The selected local was not found. Defaulting back to Locale.ENGLISH");
		}
		if ("dateisempty".equals(actionId)) {
			this.setDate(null);
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
	 * 
	 * <br/>
	 *            If the locale is not supported the control defaults to
	 *            Locale.ENGLISH
	 */

	public void setLocale(Locale locale) {
		this.locale = locale;
		this.setDate(this.getDate());
	}

	/**
	 * @param date
	 *            the date to set
	 * 
	 */
	public void setDate(Date date) {
		Date oldDate = this.getDate();
		this.date = date;
		this.requireRedraw();
		this.notifyListeners(oldDate, this.date);

	}

	/**
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * 
	 * @param dcl
	 *            Add a DateChangeListener to this DatePickerControl that fires
	 *            when a new date for this control is selected or set form code
	 * 
	 * 
	 */
	public void addDateChangedListener(DateChangedListener dcl) {
		this.listeners.add(dcl);
	}

	public void removeDateChangedListener(DateChangedListener dcl) {
		this.listeners.remove(dcl);
	}

	/**
	 * @param showMonth
	 *            set true to show month drop down list <br/>
	 *            Default is true
	 */
	public void setShowMonth(boolean showMonth) {

		this.showMonth = showMonth;
		this.requireRedraw();
	}

	/**
	 * @param showYear
	 *            set true to show year drop down list <br/>
	 *            Default is true
	 */
	public void setShowYear(boolean showYear) {
		this.showYear = showYear;
		this.requireRedraw();
	}

	/**
	 * @return true if this control is set to show the month selection dropdown
	 *         list
	 */
	public boolean isShowMonth() {
		return showMonth;
	}

	/**
	 * @return true if this control is set to show the year selection dropdown
	 *         list
	 */
	public boolean isShowYear() {
		return showYear;
	}

	/**
	 * 
	 * @param dateFormat
	 *            the format of the date <br/>
	 *            example: dd-mm-yy (30/01/2012) <br/>
	 *            The format can be combinations of the following:<br/>
	 *            d - day of month (no leading zero)<br/>
	 *            dd - day of month (two digit)<br/>
	 *            o - day of the year (no leading zeros)<br/>
	 *            oo - day of the year (three digit)<br/>
	 *            D - day name short<br/>
	 *            DD - day name long<br/>
	 *            m - month of year (no leading zero)<br/>
	 *            mm - month of year (two digit)<br/>
	 *            M - month name short<br/>
	 *            MM - month name long<br/>
	 *            y - year (two digit)<br/>
	 *            yy - year (four digit)<br/>
	 *            @ - Unix timestamp (ms since 01/01/1970)<br/>
	 *            the String representing the format can be null or empty in
	 *            wich case it defaults to DatePickerControl.NO_FORMAT
	 * 
	 */
	public void setDateFormat(String dateFormat) {
		if (dateFormat != null && !dateFormat.isEmpty()) {
			this.dateFormat = dateFormat;
		} else {
			this.dateFormat = NO_FORMAT;
		}
		this.requireRedraw();

	}

	/**
	 * 
	 * @return the date format if it was set to a non-null/non-empty value or
	 *         DatePickerControl.NO_FORMAT
	 * 
	 */
	public String getDateFormat() {
		return dateFormat;
	}

	/**
	 * 
	 * @return the number of months to be displayed
	 */
	public int getNumberOfMonths() {
		return numberOfMonths;
	}

	/**
	 * 
	 * @param the
	 *            number of months to be displayed
	 */
	public void setNumberOfMonths(int numberOfMonths) {
		this.numberOfMonths = numberOfMonths;
		this.requireRedraw();
	}

	/**
	 * 
	 * @return
	 */
	public boolean isShowWeek() {
		return showWeek;
	}

	/**
	 * 
	 * @param showWeek
	 */
	public void setShowWeek(boolean showWeek) {
		this.showWeek = showWeek;
		this.requireRedraw();
	}

	/**
	 * 
	 * @return
	 */
	public boolean isIconTriggered() {
		return iconTriggered;
	}

	/**
	 * 
	 * @param iconTriggered
	 */
	public void setIconTriggered(boolean iconTriggered) {
		this.iconTriggered = iconTriggered;
		this.requireRedraw();
	}

}