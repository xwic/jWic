/**
 * 
 */
package de.jwic.demo.tbv;

import de.jwic.base.IControlContainer;
import de.jwic.demo.DemoModule;

/**
 * @author lippisch
 *
 */
public class TableViewerDemoModule extends DemoModule {

	/**
	 * 
	 */
	public TableViewerDemoModule() {
		setTitle("Basic TableViewer");
		setDescription("Renders data from a content provider in a table form");
		setGroup("Table Viewer");
	}
	
	/* (non-Javadoc)
	 * @see de.jwic.demo.DemoModule#createModule(de.jwic.base.IControlContainer)
	 */
	@Override
	public void createModule(IControlContainer container) {

		
		new TableViewerDemo(container);
		

	}

}
