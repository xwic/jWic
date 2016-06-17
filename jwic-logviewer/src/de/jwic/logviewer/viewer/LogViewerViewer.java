/**
 * 
 */
package de.jwic.logviewer.viewer;

import de.jwic.base.ControlContainer;
import de.jwic.base.IControlContainer;
import de.jwic.logviewer.main.LogViewerModel;

/**
 * @author vedad
 *
 */
public class LogViewerViewer extends ControlContainer {
	
	private static final long serialVersionUID = -1443075278564195943L;
	
	private int height = 100;
	private int width = 100;
	
	private LogViewerModel model;

	/**
	 * 
	 */
	public LogViewerViewer(IControlContainer container, LogViewerModel model) {
		super(container);
		
		this.model = model;
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
	}

}
