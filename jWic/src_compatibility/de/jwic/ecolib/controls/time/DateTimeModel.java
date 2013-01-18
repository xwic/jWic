package de.jwic.ecolib.controls.time;

import java.io.Serializable;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

public class DateTimeModel implements Serializable {
	private static final long serialVersionUID = 1L;
	Locale locale;
	Date dateTime;
	
	
	public DateTimeModel() {
		locale = Locale.getDefault();
		dateTime = new Date();
	}


	public Date getDateTime() {
		return dateTime;
	}


	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}


	public Locale getLocale() {
		return locale;
	}


	public void setLocale(Locale locale) {
		this.locale = locale;
	}
	
	public String getFormattedDateTime() {
		DateFormat formatter = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT, locale);
		return formatter.format(dateTime);
	}
	
}
