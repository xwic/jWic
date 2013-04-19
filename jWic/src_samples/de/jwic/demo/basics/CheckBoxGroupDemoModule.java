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
public class CheckBoxGroupDemoModule extends DemoModule {

	/**
	 * 
	 */
	public CheckBoxGroupDemoModule() {
		setTitle("CheckBoxGroup");
		setDescription("Contains a list of elements that the user can select from.");
		
	}
	
	/* (non-Javadoc)
	 * @see de.jwic.demo.DemoModule#createModule(de.jwic.base.IControlContainer)
	 */
	@Override
	public void createModule(IControlContainer container) {

		
		new CheckBoxGroupDemo(container);
		

	}

}
