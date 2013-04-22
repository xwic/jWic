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
public class ProgressBarDemoModule extends DemoModule {

	/**
	 * 
	 */
	public ProgressBarDemoModule() {
		setTitle("ProgressBar");
		setDescription("Display status of a determinate or indeterminate process.");
		
	}
	
	/* (non-Javadoc)
	 * @see de.jwic.demo.DemoModule#createModule(de.jwic.base.IControlContainer)
	 */
	@Override
	public void createModule(IControlContainer container) {

		
		new ProgressBarDemo(container);
		

	}

}
