/**
 * 
 */
package de.jwic.mobile.controls;

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
	 * @param container
	 */
	public MInputBox(IControlContainer container) {
		super(container);
	}

	/**
	 * @param container
	 * @param name
	 */
	public MInputBox(IControlContainer container, String name) {
		super(container, name);
	}

	
	/**
	 * @return the autogrow
	 */
	@IncludeJsOption
	public boolean isAutogrow() {
		return autogrow;
	}

	
	/**
	 * @param autogrow the autogrow to set
	 */
	public void setAutogrow(boolean autogrow) {
		if (autogrow != this.autogrow)
			requireRedraw();
		this.autogrow = autogrow;
	}

	
	/**
	 * @return the clearBtn
	 */
	@IncludeJsOption
	public boolean isClearBtn() {
		return clearBtn;
	}

	
	/**
	 * @param clearBtn the clearBtn to set
	 */
	public void setClearBtn(boolean clearBtn) {
		if (clearBtn != this.clearBtn)
			requireRedraw();
		this.clearBtn = clearBtn;
	}

	
	/**
	 * @return the corners
	 */
	@IncludeJsOption
	public boolean isCorners() {
		return corners;
	}

	
	/**
	 * @param corners the corners to set
	 */
	public void setCorners(boolean corners) {
		if (corners != this.corners)
			requireRedraw();
		this.corners = corners;
	}

	
	/**
	 * @return the mini
	 */
	@IncludeJsOption
	public boolean isMini() {
		return mini;
	}

	
	/**
	 * @param mini the mini to set
	 */
	public void setMini(boolean mini) {
		if (mini != this.mini)
			requireRedraw();
		this.mini = mini;
	}

	
	/**
	 * @return the preventFocusZoom
	 */
	@IncludeJsOption
	public boolean isPreventFocusZoom() {
		return preventFocusZoom;
	}

	
	/**
	 * @param preventFocusZoom the preventFocusZoom to set
	 */
	public void setPreventFocusZoom(boolean preventFocusZoom) {
		if (preventFocusZoom != this.preventFocusZoom)
			requireRedraw();
		this.preventFocusZoom = preventFocusZoom;
	}

	
	/**
	 * @return the clearBtnText
	 */
	@IncludeJsOption
	public String getClearBtnText() {
		return clearBtnText;
	}

	
	/**
	 * @param clearBtnText the clearBtnText to set
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
	 * @return the keyupTimeoutBuffer
	 */
	@IncludeJsOption
	public int getKeyupTimeoutBuffer() {
		return keyupTimeoutBuffer;
	}

	
	/**
	 * @param keyupTimeoutBuffer the keyupTimeoutBuffer to set
	 */
	public void setKeyupTimeoutBuffer(int keyupTimeoutBuffer) {
		if (keyupTimeoutBuffer != this.keyupTimeoutBuffer)
			requireRedraw();
		this.keyupTimeoutBuffer = keyupTimeoutBuffer;
	}

	
	/**
	 * @return the theme
	 */
	@IncludeJsOption
	public Theme getTheme() {
		return theme;
	}

	
	/**
	 * @param theme the theme to set
	 */
	public void setTheme(Theme theme) {
		if (!theme.equals(this.theme))
			requireRedraw();
		this.theme = theme;
	}

}
