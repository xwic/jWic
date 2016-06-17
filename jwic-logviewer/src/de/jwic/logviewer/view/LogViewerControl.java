/**
 * 
 */
package de.jwic.logviewer.view;

import de.jwic.base.ControlContainer;
import de.jwic.base.IControlContainer;
import de.jwic.controls.StackedContainer;
import de.jwic.logviewer.main.LogViewerModel;
import de.jwic.logviewer.viewer.LogViewerViewer;

/**
 * @author vedad
 *
 */
public class LogViewerControl extends ControlContainer {
	
	private static final long serialVersionUID = 3450369077323940034L;
	
	private LogViewerModel model = null;
	private int height = 100;
	private int width = 100;
	
	private StackedContainer mainContainer = null;
	
	private LogViewerViewer logViewer = null;

	/**
	 * @param container
	 * @param name
	 */
	public LogViewerControl(IControlContainer container, String name, LogViewerModel model) {
		super(container, name);
		this.model = model;
		
		init();
	}
	
	/**
	 * 
	 */
	private void init() {
		
	}

	/**
	 * @return the height
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * @param height the height to set
	 */
	public void setHeight(int height) {
		this.height = height;
		requireRedraw();
	}

	/**
	 * @return the width
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * @param width the width to set
	 */
	public void setWidth(int width) {
		this.width = width;
		requireRedraw();
	}

}
