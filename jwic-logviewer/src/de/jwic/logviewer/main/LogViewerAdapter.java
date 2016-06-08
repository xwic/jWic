/**
 * 
 */
package de.jwic.logviewer.main;

/**
 * 
 * Adapter for the ILogViewerListener.
 * @author vedad
 *
 */
public abstract class LogViewerAdapter implements ILogViewerListener {

	private static final long serialVersionUID = 5808573507816882072L;

	/**
	 * 
	 */
	public LogViewerAdapter() {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see de.jwic.logviewer.main.ILogViewerListener#logonSuccess(de.jwic.logviewer.main.LogViewerEvent)
	 */
	@Override
	public void logonSuccess(LogViewerEvent event) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see de.jwic.logviewer.main.ILogViewerListener#logoutSuccess(de.jwic.logviewer.main.LogViewerEvent)
	 */
	@Override
	public void logoutSuccess(LogViewerEvent event) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see de.jwic.logviewer.main.ILogViewerListener#logSelected(de.jwic.logviewer.main.LogViewerEvent)
	 */
	@Override
	public void logSelected(LogViewerEvent event) {
		// TODO Auto-generated method stub

	}

}
