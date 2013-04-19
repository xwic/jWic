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
public class CheckBoxDemoModule extends DemoModule {

	/**
	 * 
	 */
	public CheckBoxDemoModule() {
		setTitle("CheckBox");
		setDescription("A single checkbox that can be selected/deslected with an optional label.");
		
	}
	
	/* (non-Javadoc)
	 * @see de.jwic.demo.DemoModule#createModule(de.jwic.base.IControlContainer)
	 */
	@Override
	public void createModule(IControlContainer container) {

		
		new CheckBoxDemo(container);
		

	}

}
