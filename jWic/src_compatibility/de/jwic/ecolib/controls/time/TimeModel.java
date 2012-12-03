package de.jwic.ecolib.controls.time;

import java.io.Serializable;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import de.jwic.util.SerObservable;

/**
 * This is a model that maintains a given time. The time
 * can be manipulated using a date object or using the models
 * hour, minute and second variables.
 *  
 *  Created on 21.09.2006
 * @author Mark Frewin
 */
public class TimeModel extends SerObservable implements Serializable{
	private static final long serialVersionUID = 1L;
	public final static int DF_STYLE_SHORT = DateFormat.SHORT;
	public final static int DF_STYLE_MEDIUM = DateFormat.MEDIUM;
	private Locale locale;
	private Calendar time;
	private int dateFormatStyle = DateFormat.MEDIUM;
	
	/**
	 * 
	 *
	 */
	public TimeModel() {
		locale = Locale.getDefault();
		time = Calendar.getInstance(locale);
	}

	/**
	 * Returns the current locale.
	 * 
	 * @return
	 */
	public Locale getLocale() {
		return locale;
	}

	/**
	 * Sets the current locale.
	 * 
	 * @param locale
	 */
	public void setLocale(Locale locale) {
		this.locale = locale;
	}
	
	/**
	 * Change the TimeZone.
	 * @param timeZone
	 */
	public void setTimeZone(TimeZone timeZone) {
		time.setTimeZone(timeZone);
	}
	
	/**
	 * Sets the hour value.
	 * 
	 * @param hour
	 */
	public void setHour(int hour) {
		if (time.get(Calendar.HOUR_OF_DAY) != hour) {
			time.set(Calendar.HOUR_OF_DAY, hour);
			setChanged();
			notifyObservers(new Integer(hour));
		}
	}
	
	/**
	 * Sets the Minute value.
	 * 
	 * @param minute
	 */
	public void setMinute(int minute) {
		if (time.get(Calendar.MINUTE) != minute) {
			time.set(Calendar.MINUTE, minute);
			setChanged();
			notifyObservers(new Integer(minute));
		}
	}
	
	/**
	 * Sets the second value.
	 * 
	 * @param second
	 */
	public void setSecond(int second) {
		if (time.get(Calendar.SECOND) != second) {
			time.set(Calendar.SECOND, second);
			setChanged();
			notifyObservers(new Integer(second));
		}
	}
	
	/**
	 * Returns the time value.
	 * 
	 * @return
	 */
	public Date getTime() {
		return time.getTime();
	}
	
	/**
	 * Returns the time in a format corresponding 
	 * to the current date format style and locale.
	 *  
	 * @return
	 */
	public String getFormattedTime() {
		DateFormat formatter = DateFormat.getTimeInstance(dateFormatStyle, locale);
		return formatter.format(time.getTime());
	}

	/**
	 * Returns the hour value.
	 * 
	 * @return
	 */
	public int getHour() {		
		return time.get(Calendar.HOUR_OF_DAY);
	}

	/**
	 * Returns the Minute value.
	 * 
	 * @return
	 */
	public int getMinute() {		
		return time.get(Calendar.MINUTE);
	}
	
	/**
	 * Returns the second value.
	 * 
	 * @return
	 */
	public int getSecond() {
		return time.get(Calendar.SECOND);
	}

	/**
	 * Returns the date format style.
	 * These correspond to the static DF_STYLES.
	 * 
	 * @return
	 */
	public int getDateFormatStyle() {
		return dateFormatStyle;
	}

	/**
	 * Returns the date format style value.
	 * 
	 * @param style
	 */
	public void setDateFormatStyle(int style) {
		this.dateFormatStyle = style;
	}
	
	/**
	 * Sets the time value.
	 * 
	 * @param time
	 */
	public void setTime(Date time) {
		if (!this.time.getTime().equals(time)) {
			this.time.setTime(time);
			setChanged();
			notifyObservers();
		}
	}
	
	/**
	 * Returns a date formatter corresponding to
	 * the current DateFormatStyle and locale.
	 * 
	 * @return
	 */
	public DateFormat getDateFormatter() {
		return DateFormat.getTimeInstance(dateFormatStyle, locale);
	}
}
