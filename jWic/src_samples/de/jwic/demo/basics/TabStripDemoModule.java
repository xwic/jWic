/**
 * 
 */
package de.jwic.demo.basics;

import de.jwic.base.IControlContainer;
import de.jwic.demo.DemoModule;

/**
 * @author lippisch
 *
 */
public class TabStripDemoModule extends DemoModule {

	/**
	 * 
	 */
	public TabStripDemoModule() {
		setTitle("TabStrip");
		setDescription("A single content area with multiple panels, each associated with a header in a list");
		
	}
	
	/* (non-Javadoc)
	 * @see de.jwic.demo.DemoModule#createModule(de.jwic.base.IControlContainer)
	 */
	@Override
	public void createModule(IControlContainer container) {

		
		new TabStripDemo(container);
		

	}

}
