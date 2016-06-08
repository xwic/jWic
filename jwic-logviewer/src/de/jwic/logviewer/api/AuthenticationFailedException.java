/**
 * 
 */
package de.jwic.logviewer.api;

/**
 * @author vedad
 *
 */
public class AuthenticationFailedException extends Exception {

	private static final long serialVersionUID = -7109972199859681953L;

	/**
	 * 
	 */
	public AuthenticationFailedException() {
		super();
	}

	/**
	 * @param message
	 * @param cause
	 */
	public AuthenticationFailedException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message
	 */
	public AuthenticationFailedException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public AuthenticationFailedException(Throwable cause) {
		super(cause);
	}

}
