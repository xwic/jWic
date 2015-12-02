/**
 * 
 */
package de.jwic.demo.wizard;

import de.jwic.base.IControlContainer;
import de.jwic.demo.DemoModule;

/**
 * @author lippisch
 *
 */
public class WizardDemo extends DemoModule {

	/**
	 * 
	 */
	public WizardDemo() {
		setTitle("Wizard");
		setDescription("A sample wizard that can generate the classes/files for a new wizard.");
		setGroup("Framework");
	}

	/* (non-Javadoc)
	 * @see de.jwic.demo.DemoModule#createModule(de.jwic.base.IControlContainer)
	 */
	@Override
	public void createModule(IControlContainer container) {

		new WizardDemoControl(container, "wizDemo");
		
	}

}
