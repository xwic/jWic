package de.jwic.ecolib.controls;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import de.jwic.base.Control;
import de.jwic.base.IControlContainer;
import de.jwic.base.JavaScriptSupport;

/**
 * This control renders a warning bar with stacktrace option view.
 * 
 * Usage:
 * 
 * errCtrl = new ErrorWarningControl(container, "error");
 * errCtrl.setVisible(false);
 * 
 * in handleException(...) method set exception previouse thrown by you:
 * 
 * 	public void handleException(Exception e)
 *	{
 *		errCtrl.setException(e);
 *		errCtrl.setVisible(true);
 *	}
 * 
 * Added methode ShowStackTrace...
 * If you want to hide the StackTrace in your errormessage, set it
 * false.
 * 
 * @author Buzas Adrian
 */
@JavaScriptSupport
public class ErrorWarningControl extends Control
{
	private static final long serialVersionUID = 1L;

	private Exception exception = null;
	// this is control's message 
	private java.lang.String strText = "";
	//	this is stacktrace where error occured
	private java.lang.String strStack = "";
	
	//sets, if the stacktrace will be shown, or not
	//default setting is true...
	private boolean showStackTrace = true;
	
	private boolean closable = false;
	private boolean autoClose = true;

	private boolean warning = false;
	
	private String cssClass = null;
	
	private int autoCloseDelay = 10000; // 10 sec. by default.
	
	
	/**
	 * @param container
	 */
	public ErrorWarningControl(IControlContainer container) {
		this(container, null);
	}
	/**
	 * @param container
	 * @param name
	 */
	public ErrorWarningControl(IControlContainer container, String name) {
		super(container, name);
		setVisible(false); // be invisible by default.
	}

	/**
	 * Displays the specified exception.
	 * @param e
	 */
	public void showError(Exception e, String cssClass) {
		setException(e);
		setVisible(true);
		setWarning(false);
		
        if (cssClass != null && cssClass.length() > 0)
            setCssClass(cssClass);
        else
        	setCssClass("errorMessage");

	}

	/**
	 * Displays the specified exception.
	 * @param e
	 */
	public void showError(Exception e) {
		showError(e, null);
	}

	/**
	 * Displays the specified text.
	 * @param e
	 */
	public void showError(String text) {
		showError(text, null);
	}

	
	/**
	 * Displays the specified text.
	 * @param e
	 */
	public void showError(String text, String cssClass) {
		setText(text);
		setVisible(true);
		setWarning(false);
		
        if (cssClass != null && cssClass.length() > 0)
            setCssClass(cssClass);
        else
        	setCssClass("errorMessage");
        
	}

	/**
	 * Displays the specified text as warning.
	 * @param e
	 */
	public void showWarning(String text) {
		showWarning(text, null);
	}

	
	/**
	 * Displays the specified text as warning.
	 * @param e
	 */
	public void showWarning(String text, String cssClass) {
		setText(text);
		setVisible(true);
		setWarning(true);
		
        if (cssClass != null && cssClass.length() > 0)
            setCssClass(cssClass);
        else
        	setCssClass("warningMessage");
        
	}

	
	
	/**
	 * Returns the text displayed as the error.
	 * @return java.lang.String
	 */
	public String getText()
	{
		return strText;
	}
	/**
	 * Sets the text displayed as the error.
	 * @param newText
	 */
	public void setText(String newText) {
		strText = newText;
	}
	/**
	 * The user produced an action event at the UI.
	 */
	public void actionPerformed(String actionId, String param) {
		
		if (actionId.equals("closeframe")) {
			setVisible(false);		
		}
	}

	/**
	 *
	 * @return java.lang.String
	 */
	public String getStack()
	{
		return strStack;
	}

	/**
	 * @param e represents current thorown exception
	 */
	public void setException(Exception e)
	{
		exception = e;
		strText = e.getMessage();
		try
		{
			// create some writersStringWriter 
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			// print stack trace to printer writer i.e. string writer
			e.printStackTrace(pw);
			// close writers
			pw.close();
			sw.close();
			// get stack trace as string
			strStack = sw.toString();

		}
		catch (IOException e1)
		{
			// empty catch
		}
	}

	/**
	 * 
	 * Shows the Stacktrace. <p>
	 * Default setting is true. If you want to hide the
	 * StackTrace in your ErrorMessage, set it false.
	 *
	 * (Added 2003-11-21) 
	 * @author Ronny Pfretzschner
	 * @param boolean show
	 */
	public void setShowStackTrace(boolean show)
	{
		showStackTrace = show;	
	}

	/**
	 * 
	 * Returns the "show" status of the stacktrace in your errormessage. <p>
	 * 
	 * (Added 2003-11-21) 
	 * @author Ronny Pfretzschner
	 * @return true or false
	 */
	public boolean isShowStackTrace()
	{
		return showStackTrace;	
	}

	/**
	 * @param b
	 */
	public void setClosable(boolean b) {
		closable = b;
	}

	/**
	 * @return Returns the closable.
	 */
	public boolean isClosable() {
		return closable;
	}
	/**
	 * @return Returns the exception.
	 */
	public Exception getException() {
		return exception;
	}
	
	/**
	 * If true the control is set to hidden in the end to the rendering process.
	 * @return Returns the autoClose.
	 */
	public boolean isAutoClose() {
		return autoClose;
	}
	/**
	 * If true the control is set to hidden in the end to the rendering process.
	 * @param autoClose The autoClose to set.
	 */
	public void setAutoClose(boolean autoHide) {
		this.autoClose = autoHide;
	}
	
    public void doAutoClose() {
    	// hide the control without forcing it to be re-rendered as it
    	// will be removed automatically after a specified period of time.
    	this.bolVisible = false;
    }
	/**
	 * @return the warning
	 */
	public boolean isWarning() {
		return warning;
	}
	/**
	 * @param warning the warning to set
	 */
	public void setWarning(boolean warning) {
		this.warning = warning;
	}
	/**
	 * @return the cssClass
	 */
	public String getCssClass() {
		return cssClass;
	}
	/**
	 * @param cssClass the cssClass to set
	 */
	public void setCssClass(String cssClass) {
		this.cssClass = cssClass;
	}
	/**
	 * @return the autoCloseDelay
	 */
	public int getAutoCloseDelay() {
		return autoCloseDelay;
	}
	/**
	 * Defines the number of milliseconds after the control is
	 * automatically hidden. Only works if autoClose is set to true!
	 * @param autoCloseDelay the autoCloseDelay to set
	 */
	public void setAutoCloseDelay(int autoCloseDelay) {
		this.autoCloseDelay = autoCloseDelay;
	}
}
