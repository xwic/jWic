/**
 * 
 */
package de.jwic.logviewer.main;

import de.jwic.base.Event;
import de.jwic.logviewer.api.ILogViewer;

/**
 * @author vedad
 *
 */
public class LogViewerEvent extends Event {

	private static final long serialVersionUID = -5700557035499278937L;
	
	private ILogViewer log = null;

	/**
	 * @param eventSource
	 */
	public LogViewerEvent(Object eventSource) {
		super(eventSource);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * @param eventSource
	 * @param selectedMail
	 */
	public LogViewerEvent(Object eventSource, ILogViewer log) {
		super(eventSource);
		this.log = log;
	}

	/**
	 * @return the log
	 */
	public ILogViewer getLog() {
		return log;
	}

	/**
	 * @param log the log to set
	 */
	public void setLog(ILogViewer log) {
		this.log = log;
	}

}
