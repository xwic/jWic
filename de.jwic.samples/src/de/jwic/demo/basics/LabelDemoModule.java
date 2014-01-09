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
public class LabelDemoModule extends DemoModule {

	/**
	 * 
	 */
	public LabelDemoModule() {
		setTitle("Label");
		setDescription("A label displays text on a page, that can be modified during runtime");
		
	}
	
	/* (non-Javadoc)
	 * @see de.jwic.demo.DemoModule#createModule(de.jwic.base.IControlContainer)
	 */
	@Override
	public void createModule(IControlContainer container) {

		
		new LabelDemo(container);
		

	}

}
