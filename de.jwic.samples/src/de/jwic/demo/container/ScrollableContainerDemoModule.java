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
public class ScrollableContainerDemoModule extends DemoModule {

	/**
	 * 
	 */
	public ScrollableContainerDemoModule() {
		setTitle("ScrollableContainer");
		setDescription("A container with a fixed width and height that is scrollable");
		setGroup("Container");
		
	}
	
	/* (non-Javadoc)
	 * @see de.jwic.demo.DemoModule#createModule(de.jwic.base.IControlContainer)
	 */
	@Override
	public void createModule(IControlContainer container) {

		
		new ScrollableContainerDemo(container);
		

	}

}
