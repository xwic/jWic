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
public class WindowDemoModule extends DemoModule {

	/**
	 * 
	 */
	public WindowDemoModule() {
		setTitle("Window");
		setDescription("A container that display the content in a floating frame that behaves like a stand-alone window within the page");
		setGroup("Container");
		
	}
	
	/* (non-Javadoc)
	 * @see de.jwic.demo.DemoModule#createModule(de.jwic.base.IControlContainer)
	 */
	@Override
	public void createModule(IControlContainer container) {

		
		new WindowDemo(container);
		

	}

}
