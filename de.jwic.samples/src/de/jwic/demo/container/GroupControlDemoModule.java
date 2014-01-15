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
public class GroupControlDemoModule extends DemoModule {

	/**
	 * 
	 */
	public GroupControlDemoModule() {
		setTitle("GroupControl");
		setDescription("A container with a custom border layout and customizable inner layout");
		setGroup("Container");
		
	}
	
	/* (non-Javadoc)
	 * @see de.jwic.demo.DemoModule#createModule(de.jwic.base.IControlContainer)
	 */
	@Override
	public void createModule(IControlContainer container) {

		
		new GroupControlDemo(container);
		

	}

}
