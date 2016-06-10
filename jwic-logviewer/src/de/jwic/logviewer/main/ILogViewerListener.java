/**
 * 
 */
package de.jwic.logviewer.main;

import java.io.Serializable;

/**
 * @author vedad
 *
 */
public interface ILogViewerListener extends Serializable {
	
	/**
	 * Fired when the model logged on successfully.
	 * @param event
	 */
	public void loginSuccess(LogViewerEvent event);
	
	/**
	 * Fired when the user logged out.
	 * @param event
	 */
	public void logoutSuccess(LogViewerEvent event);
	
	/**
	 * Fired when the user selected a log.
	 * @param event
	 */
	public void logSelected(LogViewerEvent event);

}
