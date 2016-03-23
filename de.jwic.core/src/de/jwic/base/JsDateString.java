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
		
		return "JWic.controls.DatePicker.convertDate('" + currentTime + "')";
	}
}
