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
		this.setRegExp(Pattern.compile(regExp));	
	}
	/**
	 * sets the regex pattern as a java.util.regex.Pattern
	 * @param regExpPattern
	 */
	public void setRegExp(Pattern regExpPattern) {
		this.regExpPattern = regExpPattern;
		this.regExp = regExpPattern.pattern();
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
	 * @return true if text is valid with pattern, false otherwise
	 */
	public boolean isValid(){
		return regExpPattern.matcher(this.getText()).matches();
	}
	
	
}
