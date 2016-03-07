/**
 * 
 */
package de.jwic.controls.pojoedit;

/**
 * Since the editor works a lot with reflection, it uses this generic PojoEditorException to notify
 * users that something went wront...
 * 
 * @author lippisch
 */
public class PojoEditorException extends Exception {

	/**
	 * 
	 */
	public PojoEditorException() {
		super();
	}

	/**
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public PojoEditorException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public PojoEditorException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message
	 */
	public PojoEditorException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public PojoEditorException(Throwable cause) {
		super(cause);
	}

}
