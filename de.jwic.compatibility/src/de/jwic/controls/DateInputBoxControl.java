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
package de.jwic.controls;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import de.jwic.base.IControlContainer;

/**
 * DateInputBoxControl displays a InputBoxContol with an date picker link.
 * 
 * $Id: DateInputBoxControl.java,v 1.7 2011/07/12 16:07:12 adrianionescu12 Exp $
 * @version $Revision: 1.7 $
 * @author JBornemann
 */
public class DateInputBoxControl extends InputBox {

	private static final long serialVersionUID = 1L;

	protected String cssClassBorder = null;
	protected String datePattern = null;
	protected boolean textInputEnabled = true;
	
	private TimeZone timeZone; 
	
	/**
	 * @param container
	 */
	public DateInputBoxControl(IControlContainer container) {
		super(container);
		initialize();
	}
	/**
	 * @param container
	 * @param name
	 */
	public DateInputBoxControl(IControlContainer container, String name) {
		super(container, name);
		initialize();
	}

	/**
	 * DateInputBoxControl constructor.
	 */
	private void initialize() {
		// new default width
		width = 80;
		// new default maxLength
		maxLength = 10;
		String lang_id = getSessionContext().getLocale().getLanguage().toUpperCase();
		if (lang_id.equals("DE")) {
			datePattern = "dd.MM.yyyy";
		} else {
			datePattern = "MM/dd/yyyy";
		}
		
		timeZone = getSessionContext().getTimeZone();

	}

	/**
	 * @return Returns the cssClassBorder.
	 */
	public String getCssClassBorder() {
		return cssClassBorder;
	}
	/**
	 * @param cssClassBorder The cssClassBorder to set.
	 */
	public void setCssClassBorder(String cssClassBorder) {
		this.cssClassBorder = cssClassBorder;
	}
	/**
	 * @return Returns the DateFormat used by this control for formating and parsing.
	 */
	protected DateFormat getDateFormat() {
		String pattern = getDatePattern();
		SimpleDateFormat format = pattern != null ? 
					new SimpleDateFormat(pattern) : 
					new SimpleDateFormat();
		format.setTimeZone(timeZone);
		return format;
	}
	/**
	 * Returns the pattern used for the DateFormat.
	 * The pattern is "MM/dd/yyyy" by default or "dd.MM.yyyy" if locale is DE.
	 * @return
	 */
	public String getDatePattern() {
		return datePattern; 
	}
	/**
	 * Set the pattern used for the date format.
	 * @param datePattern
	 */
	public void setDatePattern(String datePattern) {
		// before changing the pattern, save and restore the old date.
		Date date = getDate();
		this.datePattern = datePattern;
		setDate(date);
		requireRedraw();
	}
	/**
	 * @return Returns the date.
	 */
	public Date getDate() {
		DateFormat format = getDateFormat();
		try {
			return format.parse(getText());
		} catch (ParseException pe) {
			return null;
		}
	}
	
	/**
	 * Returns true if the value entered by the user is a  
	 * valid date or if the field is empty.
	 * @return
	 */
	public boolean isValid() {
		
		// an empty field is valid! 
		if (getText().length() == 0) {
			return true;
		}
		
		DateFormat format = getDateFormat();
		try {
			format.parse(getText());
		} catch (ParseException pe) {
			return false;
		}
		return true;
	}
	
	/**
	 * @param date The date to set.
	 */
	public void setDate(Date date) {
		DateFormat format = getDateFormat();
		setText(date == null ? "" : format.format(date));
	}
	/**
	 * @return the textInputEnabled
	 */
	public boolean isTextInputEnabled() {
		return textInputEnabled;
	}
	/**
	 * Used to enable or disable the text-input in the field. If set to false,
	 * the date can only be selected using the date pop-up window.
	 * @param textInputEnabled the textInputEnabled to set
	 */
	public void setTextInputEnabled(boolean textInputEnabled) {
		this.textInputEnabled = textInputEnabled;
	}
	/**
	 * @return the timeZone
	 */
	public TimeZone getTimeZone() {
		return timeZone;
	}
	/**
	 * @param timeZone the timeZone to set
	 */
	public void setTimeZone(TimeZone timeZone) {
		this.timeZone = timeZone;
	}
	
}
