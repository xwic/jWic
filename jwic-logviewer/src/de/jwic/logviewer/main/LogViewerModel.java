/**
 * 
 */
package de.jwic.logviewer.main;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import de.jwic.logviewer.api.AuthenticationFailedException;
import de.jwic.logviewer.api.ISession;


/**
 * @author vedad
 *
 */
public class LogViewerModel implements Serializable {

	private static final long serialVersionUID = 6861031499199340884L;
	
	private ISession session = null;

	private List listeners = Collections.synchronizedList(new ArrayList());

	private final static int EVENT_LOGON = 1;
	private final static int EVENT_LOGOUT = 2;

	/**
	 * 
	 */
	public LogViewerModel() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Add a MailModelListener.
	 * @param listener
	 */
	public void addLogViewerModelListener(ILogViewerListener listener) {
		listeners.add(listener);
	}
	
	/**
	 * Remove a MailModelListener.
	 * @param listener
	 */
	public void removeLogViewerModelListener(ILogViewerListener listener) {
		listeners.remove(listener);
	}
	
	/**
	 * Fire an event.
	 * @param eventType
	 * @param event
	 */
	private void fireEvent(int eventType, LogViewerEvent event) {
		
		Object[] lst = listeners.toArray();
		for (int i = 0; i < lst.length; i++) {
			ILogViewerListener listener = (ILogViewerListener)lst[i];
			switch (eventType) {
			case EVENT_LOGON:
				listener.logonSuccess(event);
				break;
			case EVENT_LOGOUT:
				listener.logoutSuccess(event);
				break;
			}
			
		}
		
	}
	
	/**
	 * Logon to the server.
	 * @param username
	 * @param password
	 * @return
	 * @throws AuthenticationFailedException
	 */
	public ISession logon(String username, String password) throws AuthenticationFailedException {
		//session = mailServer.logon(username, password);
		fireEvent(EVENT_LOGON, new LogViewerEvent(this));
		return session;
	}
	
	/**
	 * Logout the current user and close the session.
	 */
	public void logout() {
		session.close();
		fireEvent(EVENT_LOGOUT, new LogViewerEvent(this));
	}

}
