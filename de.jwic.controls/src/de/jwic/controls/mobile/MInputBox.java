/**
 * 
 */
package de.jwic.controls.mobile;

import de.jwic.base.IControlContainer;
import de.jwic.base.IncludeJsOption;
import de.jwic.base.JavaScriptSupport;
import de.jwic.controls.InputBox;

/**
 * A textinput widget that is represented as a jQuery Mobile control. Extends
 * the standard jWic InputBox for compatibility and ease of use. It
 * 
 * @author lippisch
 */
@JavaScriptSupport
public class MInputBox extends InputBox {

	private static final long serialVersionUID = 1L;

	private boolean autogrow = true;
	private boolean clearBtn = false;
	private boolean corners = true;
	private boolean mini = false;
	private boolean preventFocusZoom = true;
	private String clearBtnText = "Clear Text";
	private String wrapperClass = null;
	private int keyupTimeoutBuffer = 100;
	private Theme theme = null;

	/**
	 * Constructs a new control instance and adds it to the specified container.
	 * 
	 * @param container container of the input box
	 */
	public MInputBox(IControlContainer container) {
		super(container);
	}

	/**
	 * Constructs a new control instance and adds it to the specified container with the specified name. If the name is <code>null</code>, a
	 * unique name will be chosen by the container.
	 * 
	 * @param container container of the input box
	 * @param name name of the input box
	 */
	public MInputBox(IControlContainer container, String name) {
		super(container, name);
	}

	
	/**
	 * This option is provided by the autogrow extension. 
	 * Whether to update the size of the textarea element upon first appearance, as well as upon a change in the content of the element. 
	 * This option applies only to textinput widgets based on textarea elements.
	 */
	@IncludeJsOption
	public boolean isAutogrow() {
		return autogrow;
	}

	
	/**
	 * This option is provided by the autogrow extension. 
	 * Whether to update the size of the textarea element upon first appearance, as well as upon a change in the content of the element. 
	 * This option applies only to textinput widgets based on textarea elements.
	 */
	public void setAutogrow(boolean autogrow) {
		if (autogrow != this.autogrow)
			requireRedraw();
		this.autogrow = autogrow;
	}

	
	/**
	 * This option is provided by the clearButton extension. Adds a clear button to the input when set to true. 
	 * This option applies only to textinput widgets based on input elements.
	 */
	@IncludeJsOption
	public boolean isClearBtn() {
		return clearBtn;
	}

	
	/**
	 * This option is provided by the clearButton extension. Adds a clear button to the input when set to true. 
	 * This option applies only to textinput widgets based on input elements.
	 */
	public void setClearBtn(boolean clearBtn) {
		if (clearBtn != this.clearBtn)
			requireRedraw();
		this.clearBtn = clearBtn;
	}

	
	/**
	 * Applies the theme border radius if set to true by adding the class ui-corner-all to the textinput widget's outermost element.
	 */
	@IncludeJsOption
	public boolean isCorners() {
		return corners;
	}

	
	/**
	 * Applies the theme border radius if set to true by adding the class ui-corner-all to the textinput widget's outermost element.
	 */
	public void setCorners(boolean corners) {
		if (corners != this.corners)
			requireRedraw();
		this.corners = corners;
	}

	
	/**
	 * If set to true, this will display a more compact version of the textinput that uses less vertical height by applying the ui-mini class to the outermost element of the textinput widget.
	 */
	@IncludeJsOption
	public boolean isMini() {
		return mini;
	}

	
	/**
	 * If set to true, this will display a more compact version of the textinput that uses less vertical height by applying the ui-mini class to the outermost element of the textinput widget.
	 */
	public void setMini(boolean mini) {
		if (mini != this.mini)
			requireRedraw();
		this.mini = mini;
	}

	
	/**
	 * Attempts to prevent the device from focusing in on the input element when the element receives the focus.
	 */
	@IncludeJsOption
	public boolean isPreventFocusZoom() {
		return preventFocusZoom;
	}

	
	/**
	 * Attempts to prevent the device from focusing in on the input element when the element receives the focus.
	 */
	public void setPreventFocusZoom(boolean preventFocusZoom) {
		if (preventFocusZoom != this.preventFocusZoom)
			requireRedraw();
		this.preventFocusZoom = preventFocusZoom;
	}

	
	/**
	 * This option is provided by the clearButton extension. The text description for the optional clear button, useful for assistive technologies like screen readers. 
	 * This option applies only to textinput widgets based on input elements.
	 */
	@IncludeJsOption
	public String getClearBtnText() {
		return clearBtnText;
	}

	
	/**
	 * This option is provided by the clearButton extension. The text description for the optional clear button, useful for assistive technologies like screen readers. 
	 * This option applies only to textinput widgets based on input elements.
	 */
	public void setClearBtnText(String clearBtnText) {
		if (!clearBtnText.equals(this.clearBtnText))
			requireRedraw();
		this.clearBtnText = clearBtnText;
	}

	
	/**
	 * @return the wrapperClass
	 */
	@IncludeJsOption
	public String getWrapperClass() {
		return wrapperClass;
	}

	
	/**
	 * @param wrapperClass the wrapperClass to set
	 */
	public void setWrapperClass(String wrapperClass) {
		if (!wrapperClass.equals(this.wrapperClass))
			requireRedraw();
		this.wrapperClass = wrapperClass;
	}

	
	/**
	 * This option is provided by the autogrow extension. The amount of time (in milliseconds) to wait between the occurence of a keystroke and the resizing of the textarea element. 
	 * If another keystroke occurs within this time, the resizing is postponed by another period of time of the same length. This option applies only to textinput widgets based on textarea elements.
	 */
	@IncludeJsOption
	public int getKeyupTimeoutBuffer() {
		return keyupTimeoutBuffer;
	}

	
	/**
	 * This option is provided by the autogrow extension. The amount of time (in milliseconds) to wait between the occurence of a keystroke and the resizing of the textarea element. 
	 * If another keystroke occurs within this time, the resizing is postponed by another period of time of the same length. This option applies only to textinput widgets based on textarea elements.
	 */
	public void setKeyupTimeoutBuffer(int keyupTimeoutBuffer) {
		if (keyupTimeoutBuffer != this.keyupTimeoutBuffer)
			requireRedraw();
		this.keyupTimeoutBuffer = keyupTimeoutBuffer;
	}

	
	/**
	 * The value of this option is a string containing a space-separated list of classes to be applied to the outermost element of the textinput widget.
	 */
	@IncludeJsOption
	public Theme getTheme() {
		return theme;
	}

	
	/**
	 * The value of this option is a string containing a space-separated list of classes to be applied to the outermost element of the textinput widget.
	 */
	public void setTheme(Theme theme) {
		if (!theme.equals(this.theme))
			requireRedraw();
		this.theme = theme;
	}

}
