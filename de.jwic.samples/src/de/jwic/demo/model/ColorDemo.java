/**
 * 
 */
package de.jwic.demo.model;

import de.jwic.base.IControlContainer;
import de.jwic.demo.DemoModule;

/**
 * @author lippisch
 *
 */
public class ColorDemo extends DemoModule {

	/**
	 * 
	 */
	public ColorDemo() {
		setTitle("Color Picker");
		setDescription("Pick a color");
		setGroup("Advanced");
	}

	/* (non-Javadoc)
	 * @see de.jwic.demo.DemoModule#createModule(de.jwic.base.IControlContainer)
	 */
	@Override
	public void createModule(IControlContainer container) {

		new ColorDemoContainer(container);

	}

}
