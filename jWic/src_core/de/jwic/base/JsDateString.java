package de.jwic.base;

import java.util.Date;

import org.json.JSONString;

/**
 * Wrapper class for js output of date objects.
 * @author dotto
 *
 */
public class JsDateString implements JSONString { 
	
	 private Date date;
	
	 /**
	 * @param date
	 */
	public JsDateString(Date date){
		 this.date = date;
	 }
	
	/* (non-Javadoc)
	 * @see org.json.JSONString#toJSONString()
	 */
	@Override
	public String toJSONString() {
		if(date == null)
			return null;
		return "JWic.controls.DateTimePicker.convertDate('" + date.getTime() + "')";
	}
}
