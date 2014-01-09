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
public class MenuDemoModule extends DemoModule {

	/**
	 * 
	 */
	public MenuDemoModule() {
		setTitle("Menu");
		setDescription("Themeable menu with mouse and keyboard interactions for navigation that can be linked with other controls");
		
	}
	
	/* (non-Javadoc)
	 * @see de.jwic.demo.DemoModule#createModule(de.jwic.base.IControlContainer)
	 */
	@Override
	public void createModule(IControlContainer container) {

		
		new MenuDemo(container);
		

	}

}
