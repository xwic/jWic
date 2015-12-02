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

import java.util.regex.Pattern;

import de.jwic.base.IControlContainer;
import de.jwic.base.IncludeJsOption;
import de.jwic.base.JavaScriptSupport;

/**
 * 
 * ValidatedInputBox
 * 
 * This control is like a regular inputBox except in is validated on the front end via a regular expression set in java. 
 * 
 * @author bogdan
 *
 */
@JavaScriptSupport(jsTemplate="de.jwic.controls.ValidatedInputBox")
public class ValidatedInputBox extends InputBox {

	
	private String regExp;
	private Pattern regExpPattern;
	
	//the following patterns were taken and adapted from http://net.tutsplus.com/tutorials/other/8-regular-expressions-you-should-know/
	//i did not include the html tag pattern as i don't think its useful 
	/**
	 * Pattern for 1 email
	 */
	public static final Pattern EMAIL_PATTERN = Pattern.compile("^[\\w+-.%]+@[\\w-.]+\\.[A-Za-z]{2,4}$");
	/**
	 * Pattern for multiple email separated with ';'
	 */
	public static final Pattern EMAILS_PATTERN_SEMICOLON_SEPARATED = Pattern.compile("^([\\w+-.%]+@[\\w-.]+\\.[A-Za-z]{2,4};?)+$");
	
	/**
	 * Pattern for multiple email separated with ','
	 */
	public static final Pattern EMAILS_PATTERN_COMMA_SEPARATED = Pattern.compile("^([\\w+-.%]+@[\\w-.]+\\.[A-Za-z]{2,4},?)+$");
	
	/**
	 * Pattern for usernames (letters, numbers, _ and -) with max length of 16 chars and min length of 3
	 */
	public static final Pattern USERNAME_PATTERN = Pattern.compile("^[a-z0-9_-]{3,16}$");
	
	/**
	 * Pattern for passwords (letters number _ and -) with max length of 18 and min length of 6
	 */
	public static final Pattern PASSWORD_PATTERN = Pattern.compile("^[a-z0-9_-]{6,18}$");
	
	/**
	 * Pattern for hex values (ex: #00ff00 the color green)
	 */
	public static final Pattern HEX_VALUE_PATTERN = Pattern.compile("^#?([a-f0-9]{6}|[a-f0-9]{3})$");
	
	/**
	 * Pattern for a http/https url
	 */
	public static final Pattern URL_PATTERN = Pattern.compile("^(https?:\\/\\/)?([\\da-z\\.-]+)\\.([a-z\\.]{2,6})([\\/\\w \\.-]*)*\\/?$");
	
	/**
	 * Pattern for matching ip addresses
	 */
	public static final Pattern IP_ADDRESS_PATTERN = Pattern.compile("^(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$");
	
	/**
	 * @param container
	 */
	public ValidatedInputBox(IControlContainer container) {
		this(container,null);
	}

	/**
	 * @param container
	 * @param name
	 */
	public ValidatedInputBox(IControlContainer container, String name) {
		super(container, name);
		this.setTemplateName(InputBox.class.getName());
		
		this.regExp = "";
		this.regExpPattern = Pattern.compile(this.regExp);
	}

	/**
	 * Sets the regular expression pattern to be used on front end validation.<br>
	 * !!Omit the leading and trailing '/' that are regularly used in regEx patterns as they are problematic on the front end and will cause your RegExp to break!!
	 * @param regExp
	 */
	public void setRegExp(String regExp) {
		this.setRegExp(regExp != null ? Pattern.compile(regExp) : null);	
	}
	/**
	 * sets the regex pattern as a java.util.regex.Pattern
	 * @param regExpPattern
	 */
	public void setRegExp(Pattern regExpPattern) {
		this.regExpPattern = regExpPattern;
		this.regExp = regExpPattern != null ? regExpPattern.pattern() : ""; 
		this.requireRedraw();
	}
	/**
	 * 
	 * @return the RegExp pattern currently used in this control
	 */
	@IncludeJsOption
	public String getRegExp() {
		return regExp;
	}
	
	/**
	 * 
	 * @return the current regex pattern as a java.util.regex.Pattern
	 */
	public Pattern getRegExpPattern(){
		return this.regExpPattern;
	}
	
	/**
	 * Matches the current pattern o the value of the input field.<br>
	 * can be used to validate on the backend.<br>
	 * This method is independent of the ui validation.<br>
	 * 
	 * it returns true if no pattern was specified
	 * 
	 * @return true if text is valid with pattern ( or if pattern is null or the string "" ), false otherwise
	 */
	public boolean isValid(){
		return regExpPattern != null ? regExpPattern.matcher(this.getText()).matches() : true;
	}
	
	
}
