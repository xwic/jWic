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
/*
 * de.jwic.renderer.velocity.JWicTools
 * $Id: JWicTools.java,v 1.10 2011/05/10 12:19:11 lordsam Exp $
 */
package de.jwic.renderer.util;

import java.text.DateFormat;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import org.apache.commons.lang.StringEscapeUtils;

import de.jwic.base.JWicRuntime;
import de.jwic.util.IHTMLElement;
import de.jwic.util.XMLTool;


/**
 * JWicTools are used as a velocity context object to provide usefull functions
 * like formatting within velocity templates. The JWicTools are placed as the
 * context object "jwic".
 *    
 * @author Florian Lippisch
 * @version $Revision: 1.10 $
 */
public class JWicTools {

	protected Locale locale = null;
	protected TimeZone timeZone = TimeZone.getDefault();
	/**
	 * JWicTools.
	 */
	public JWicTools(Locale locale) {
		super();
		setLocale(locale);
	}

	/**
	 * Instantiate with TimeZone informations.
	 * @param locale
	 * @param timeZone
	 */
	public JWicTools(Locale locale, TimeZone timeZone) {
		super();
		this.locale = locale;
		this.timeZone = timeZone;
	}



	/**
	 * @return Returns the locale.
	 */
	public Locale getLocale() {
		return locale;
	}
	/**
	 * @param locale The locale to set.
	 */
	public void setLocale(Locale locale) {
		this.locale = locale;
	}
	
	/**
	 * Transforms a text into UTF-8 format. Required by renderers who
	 * create XML files in UTF-8 format.
	 * @param text
	 * @return
	 */
	public String toUtf8(String text) {
		return XMLTool.toUtf8(text);
	}
	
	/**
	 * Transforms HTML code into code that can
	 * be placed into a TEXTAREA field. 
	 * i.e. transforming &lt; character into &amp;lt;
	 * <p>Returns an empty string if the argument is <code>null</code>.
	 * @return java.lang.String
	 * @param text java.lang.String
	 */
	public String formatInp(String text) {
		
		if (text == null) {
			return "";
		}

		StringBuffer sbHTML = new StringBuffer("");
		//boolean newline = true;
		
		for(int i=0; i < text.length(); i++)
		{
			char c = text.charAt(i);
			switch (c) {
				case 34 :
					sbHTML.append("&quot;");
					break;
				case 60 :
					sbHTML.append("&lt;");
					break;
				case 62 :
					sbHTML.append("&gt;");
					break;
				case 38 :
					sbHTML.append("&amp;");
					break;
				default :
					sbHTML.append(c);
				}

		}
		
		return sbHTML.toString();
	}

	/**
	 * Transforms text into HTML format. Used to transform
	 * user inputed text.
	 * <p>Returns an empty string if the argument is <code>null</code>.
	 * @return java.lang.String
	 * @param text java.lang.String
	 */
	public String formatHtml(String text) {
		
		if (text == null) {
			return "";
		}
		
		StringBuffer sbHTML = new StringBuffer("");
		//boolean newline = true;
		boolean newline = true;
		boolean wascr = false;
	
		for(int i=0; i < text.length(); i++)
		{
			char c = text.charAt(i);
			switch (c) {
				case 34 :
					sbHTML.append("&quot;");
					break;
				case 60 :
					sbHTML.append("&lt;");
					break;
				case 62 :
					sbHTML.append("&gt;");
					break;
				case 38 :
					sbHTML.append("&amp;");
					break;
				case 13 :
					// do some
					sbHTML.append("<BR>");
					wascr = true;
					break;

				case 32 :
					// space
					if (newline)
						sbHTML.append("&nbsp;");
					else
						sbHTML.append(c);
					wascr = false;
					break;

				case 10 :
					if (!wascr) {
						sbHTML.append("<BR>");
					}
					wascr = false;
					break;
					
				default :
					sbHTML.append(c);
					wascr = false;
					newline = false;

				}

		}
		
		return sbHTML.toString();
	}
	
	/**
	 * Returns a String MessageFormat(ed) with the pattern and the object in current Locale. 
	 * @param pattern
	 * @param object An Object or an array of objects
	 * @return
	 */
	public String format(String pattern, Object object) {
		if (object == null) {
			return null;
		}
		if (!(object instanceof Object[])) {
			object = new Object[] {object};
		}
		MessageFormat format = new MessageFormat(pattern, locale);
		return format.format(object);
	}
	/**
	 * Returns the time in short format. If time is 0:0:0.0 an empty String is returned.
	 * @param time
	 * @return
	 */
	public String formatTime(Date time) {
		if (time == null) {
			return ""; 
		}
		Calendar cal = Calendar.getInstance(locale);
		cal.setTime(time);
		if (cal.get(Calendar.HOUR_OF_DAY) == 0 && 
			cal.get(Calendar.MINUTE) == 0 &&
			cal.get(Calendar.SECOND) == 0 &&
			cal.get(Calendar.MILLISECOND) == 0) {
			// if time is exactly 0:0:0.0 return ""
			return "";
		}
		// by default return time like "12:16"
		DateFormat format = SimpleDateFormat.getTimeInstance(SimpleDateFormat.SHORT, locale);
		format.setTimeZone(timeZone);
		return format.format(time);
	}
	
	/**
	 * Returns the time in short format. If time is 0:0:0.0 an empty String is returned.
	 * @param time
	 * @return
	 */
	public String formatDate(Date date) {
		if (date == null) {
			return ""; 
		}
		DateFormat format = SimpleDateFormat.getDateInstance(SimpleDateFormat.SHORT, locale);
		format.setTimeZone(timeZone);
		return format.format(date);
	}

	/**
	 * Returns the time in short format. If time is 0:0:0.0 an empty String is returned.
	 * @param time
	 * @return
	 */
	public String formatDateTime(Date date) {
		if (date == null) {
			return ""; 
		}
		// by default return time like "12:16"
		DateFormat format = SimpleDateFormat.getDateTimeInstance(SimpleDateFormat.SHORT, SimpleDateFormat.SHORT, locale);
		format.setTimeZone(timeZone);
		return format.format(date);
	}
	
	/**
	 * Returns the context path of the webapp. Used to build links with an absolut path.
	 * @return
	 */
	public String getContextPath() {
		return JWicRuntime.getJWicRuntime().getContextPath();
	}
	
	/**
	 * Creates a URL to the specified resource. Works only if the ClasspathResourceServlet is
	 * activated.
	 * @param control
	 * @param resourceName
	 * @return
	 */
	public static String linkResource(Object control, String resourceName) {
		
		String packageName = control.getClass().getPackage().getName();
		
		return JWicRuntime.getJWicRuntime().getContextPath()  
				+ "/cp/"
				+ packageName.replace('.', '/')
				+ "/"
				+ resourceName;
		
	}

	/**
	 * Generates the class and style property based upon the IHTMLElement object.
	 * @param element
	 * @return
	 */
	public String generateCssProperty(IHTMLElement element) {
		return generateCssProperty(element, "");
	}
	
	/**
	 * Generates the class and style property based upon the IHTMLElement object. The extraStyles
	 * parameter is added to the style property.</p>
	 * 
	 * Sample Result: class="myClass" style="width: 98px;"
	 * 
	 * @param element
	 * @return
	 */
	public String generateCssProperty(IHTMLElement element, String extraStyles) {
		return generateCssProperty(element, extraStyles, element.getCssClass());
	}
	
	/**
	 * Generates the class and styles property based upon the IHTMLElement object. The extraStyles
	 * parameter is added to the style property.
	 * 
	 * @param element
	 * @param extraStyles
	 * @param customClass - Overrides the cssClass property of the HTMLelement.
	 * @return
	 */
	public String generateCssProperty(IHTMLElement element, String extraStyles, String customClass) {
		
		StringBuffer sb = new StringBuffer();
		if (element.getCssClass() != null && element.getCssClass().length() != 0) {
			sb.append("class=\"").append(customClass).append("\" ");
		}
		
		if (element.getWidth() != 0 || element.getHeight() != 0 || element.isFillWidth() || extraStyles.length() != 0) {
			
			sb.append("style=\"");
			if (element.isFillWidth()) {
				sb.append("width: 100%;");
			} else if (element.getWidth() != 0) {
				sb.append("width: ").append(element.getWidth()).append("px;");
			}
			if (element.getHeight() != 0) {
				sb.append("height: ").append(element.getHeight()).append("px;");
			}
			sb.append(extraStyles);
			sb.append("\"");
		}
		
		return sb.toString();
		
	}

	/**
	 * Returns the used TimeZone.
	 * @return
	 */
	public TimeZone getTimeZone() {
		return timeZone;
	}

	/**
	 * Set the TimeZone to be used for DateTime formatting.
	 * @param timeZone
	 */
	public void setTimeZone(TimeZone timeZone) {
		this.timeZone = timeZone;
	}

	
	/**
	 * Escapes a string to be used within a JavaScript function.
	 * @param jsString
	 * @return
	 */
	public String escapeJavaScript(String jsString) {
		return StringEscapeUtils.escapeJavaScript(jsString);
	}
	
}
