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
public class RadioGroupDemoModule extends DemoModule {

	/**
	 * 
	 */
	public RadioGroupDemoModule() {
		setTitle("RadioGroup");
		setDescription("A list of ISelectElement's represented by radio elements.");
		
	}
	
	/* (non-Javadoc)
	 * @see de.jwic.demo.DemoModule#createModule(de.jwic.base.IControlContainer)
	 */
	@Override
	public void createModule(IControlContainer container) {

		
		new RadioGroupDemo(container);
		

	}

}
