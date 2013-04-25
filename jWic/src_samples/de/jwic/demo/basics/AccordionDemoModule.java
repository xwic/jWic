package de.jwic.demo.basics;

import de.jwic.base.IControlContainer;
import de.jwic.demo.DemoModule;

public class AccordionDemoModule extends DemoModule {

	/**
	 * 
	 */
	public AccordionDemoModule() {
		setTitle("Accordion");
		setDescription("Accordion Layout, directly rendered");
		
	}
	
	/* (non-Javadoc)
	 * @see de.jwic.demo.DemoModule#createModule(de.jwic.base.IControlContainer)
	 */
	@Override
	public void createModule(IControlContainer container) {

		new AccordionDemo(container, null);
		

	}

}
