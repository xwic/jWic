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
public class ListBoxDemoModule extends DemoModule {

	/**
	 * 
	 */
	public ListBoxDemoModule() {
		setTitle("ListBox");
		setDescription("A list of elements represented by the HTML SELECT element.");
		
	}
	
	/* (non-Javadoc)
	 * @see de.jwic.demo.DemoModule#createModule(de.jwic.base.IControlContainer)
	 */
	@Override
	public void createModule(IControlContainer container) {

		
		new ListBoxDemo(container);
		

	}

}
