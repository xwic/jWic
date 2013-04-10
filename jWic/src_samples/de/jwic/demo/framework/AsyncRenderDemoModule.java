/**
 * 
 */
package de.jwic.demo.framework;

import de.jwic.base.IControlContainer;
import de.jwic.demo.DemoModule;

/**
 * @author lippisch
 *
 */
public class AsyncRenderDemoModule extends DemoModule {

	/**
	 * 
	 */
	public AsyncRenderDemoModule() {
		setTitle("Async Rendering");
		setDescription("Asynchronous initialization and/or rendering of controls.");
		
		setGroup("Framework");
		
	}

	/* (non-Javadoc)
	 * @see de.jwic.demo.DemoModule#createModule(de.jwic.base.IControlContainer)
	 */
	@Override
	public void createModule(IControlContainer container) {
		new AsyncRenderContainerDemo(container);

	}

}
