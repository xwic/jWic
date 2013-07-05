package de.jwic.base;

import java.util.Date;
import java.util.TimeZone;

import org.json.JSONString;

/**
 * Wrapper class for js output of date objects.
 * @author dotto
 *
 */
public class JsDateString implements JSONString { 
	
	 private Date date;
	 private TimeZone timeZone;
	 /**
	 * @param date
	 */
	public JsDateString(Date date, TimeZone timeZone){
		 this.date = date;
		 this.timeZone = timeZone;
	 }
	
	/* (non-Javadoc)
	 * @see org.json.JSONString#toJSONString()
	 */
	@Override
	public String toJSONString() {
		if(date == null)
			return null;
		
		long offset = timeZone != null ? timeZone.getOffset(date.getTime()) : 0;
		long currentTime = offset + date.getTime();
		
		return "JWic.controls.DateTimePicker.convertDate('" + currentTime + "')";
	}
}
