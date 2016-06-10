/**
 * 
 */
package de.jwic.logviewer.main;

import de.jwic.base.Application;
import de.jwic.base.Control;
import de.jwic.base.IControlContainer;

/**
 * @author vedad
 *
 */
public class LogViewerApp extends Application {

	private static final long serialVersionUID = 4860732768394398271L;

	private LogViewerModel model = null;

	/**
	 * 
	 */
	public LogViewerApp() {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.jwic.base.Application#createRootControl(de.jwic.base.
	 * IControlContainer)
	 */
	@Override
	public Control createRootControl(IControlContainer container) {
		getSessionContext().setExitURL("exitpage.html");

		model = new LogViewerModel();

		model.addLogViewerModelListener(new LogViewerAdapter() {

			public void loginSuccess(LogViewerEvent event) {
				login();
			}

			public void logoutSuccess(LogViewerEvent event) {
				logout();
			}
		});
		
		return null;
	}
	
	/**
	 * 
	 */
	protected void logout() {
		
		getSessionContext().exit();
	}

	/**
	 * 
	 */
	protected void login() {
//		MailClientPage mcPage = new MailClientPage(getSessionContext(), model);
//		getSessionContext().pushTopControl(mcPage);
	}

}
