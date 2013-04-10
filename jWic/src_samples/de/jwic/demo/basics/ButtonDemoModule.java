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
public class ButtonDemoModule extends DemoModule {

	/**
	 * 
	 */
	public ButtonDemoModule() {
		setTitle("Button");
		setDescription("A button is an element that can be clicked or toggled");
		
	}
	
	/* (non-Javadoc)
	 * @see de.jwic.demo.DemoModule#createModule(de.jwic.base.IControlContainer)
	 */
	@Override
	public void createModule(IControlContainer container) {

		
		new ButtonDemo(container);
		

	}

}
