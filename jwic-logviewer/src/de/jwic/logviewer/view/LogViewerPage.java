/**
 * 
 */
package de.jwic.logviewer.view;

import de.jwic.base.Dimension;
import de.jwic.base.IControlContainer;
import de.jwic.base.Page;
import de.jwic.controls.Button;
import de.jwic.events.SelectionEvent;
import de.jwic.events.SelectionListener;
import de.jwic.logviewer.main.LogViewerModel;

/**
 * The page for main application
 * @author vedad
 *
 */
public class LogViewerPage extends Page {
	
	private LogViewerModel model;
	private LogViewerControl logViewerControl;

	/**
	 * @param container
	 */
	public LogViewerPage(IControlContainer container, LogViewerModel model) {
		super(container);
		this.model = model;
		
		init();
	}
	
	/**
	 *  Initialize controls
	 *  
	 */
	private void init() {
		
		setTitle("jWic Log Viewer for: " + model.getSession().getUsername());
		Button btnLogout = new Button(this, "btnLogout");
		btnLogout.setTitle("Logout");
		btnLogout.addSelectionListener(new SelectionListener() {
			
			@Override
			public void objectSelected(SelectionEvent event) {
				model.logout();
			}
		});
		
		logViewerControl = new LogViewerControl(this, "logViewerControl", model);
	}
	
	@Override
	public void setPageSize(Dimension pageSize) {
		super.setPageSize(pageSize);
		logViewerControl.setHeight(pageSize.height - 120);
		logViewerControl.setWidth(pageSize.width- 80);
	}

}
