/**
 * 
 */
package de.jwic.demo.advanced;

import de.jwic.base.IControlContainer;
import de.jwic.demo.DemoModule;

/**
 * @author lippisch
 *
 */
public class ProcessInfoDemoModule extends DemoModule {

	/**
	 * 
	 */
	public ProcessInfoDemoModule() {
		setTitle("ProcessInfo");
		setDescription("Display status of background process");
		setGroup("Advanced");
	}
	
	/* (non-Javadoc)
	 * @see de.jwic.demo.DemoModule#createModule(de.jwic.base.IControlContainer)
	 */
	@Override
	public void createModule(IControlContainer container) {

		
		new ProcessInfoDemo(container);
		

	}

}
