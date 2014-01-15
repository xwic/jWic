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
public class FileTreeDemoModule extends DemoModule {

	/**
	 * 
	 */
	public FileTreeDemoModule() {
		setTitle("TableViewer Tree");
		setDescription("Display the TableViewer data in form of a tree like UI");
		setGroup("Table Viewer");
	}
	
	/* (non-Javadoc)
	 * @see de.jwic.demo.DemoModule#createModule(de.jwic.base.IControlContainer)
	 */
	@Override
	public void createModule(IControlContainer container) {

		
		new FileTreeDemo(container);
		

	}

}
