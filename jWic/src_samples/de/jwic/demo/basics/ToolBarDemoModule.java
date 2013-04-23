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
public class ToolBarDemoModule extends DemoModule {

	/**
	 * 
	 */
	public ToolBarDemoModule() {
		setTitle("ToolBar");
		setDescription("Container for buttons, menues and other controls that define UI actions");
		
	}
	
	/* (non-Javadoc)
	 * @see de.jwic.demo.DemoModule#createModule(de.jwic.base.IControlContainer)
	 */
	@Override
	public void createModule(IControlContainer container) {

		
		new ToolBarDemo(container, "tbDemo");
		

	}

}
