/**
 * 
 */
package de.jwic.logviewer.api;


/**
 * @author vedad
 *
 */
public interface ISession {
	

	/**
	 * Close the session and logout the user from the mailserver.
	 */
	public void close();
	
	/**
	 * Returns the username of this session.
	 * @return
	 */
	public String getUsername();

}
