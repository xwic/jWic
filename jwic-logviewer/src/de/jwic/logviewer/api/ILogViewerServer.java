/**
 * 
 */
package de.jwic.logviewer.api;

/**
 * @author vedad
 *
 */
public interface ILogViewerServer {
	
	/**
	 * Logon with the users name and password. Returns a ISession object
	 * for further operations or throws an AuthenticationFailedException
	 * if the authentication failed.
	 * @param username
	 * @param password
	 * @return
	 * @throws AuthenticationFailedException
	 */
	public ISession login(String username, String password) throws AuthenticationFailedException;


}
