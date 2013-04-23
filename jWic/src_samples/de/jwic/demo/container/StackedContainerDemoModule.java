/**
 * 
 */
package de.jwic.demo.container;

import de.jwic.base.IControlContainer;
import de.jwic.demo.DemoModule;

/**
 * @author lippisch
 *
 */
public class StackedContainerDemoModule extends DemoModule {

	/**
	 * 
	 */
	public StackedContainerDemoModule() {
		setTitle("StackedContainer");
		setDescription("A container with a single content area that displays one child at a time that is controlled programmatically only");
		setGroup("Container");
		
	}
	
	/* (non-Javadoc)
	 * @see de.jwic.demo.DemoModule#createModule(de.jwic.base.IControlContainer)
	 */
	@Override
	public void createModule(IControlContainer container) {

		
		new StackedContainerDemo(container);
		

	}

}
