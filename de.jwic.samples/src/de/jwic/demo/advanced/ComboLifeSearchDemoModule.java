/**
 * 
 */
package de.jwic.demo.advanced;

import de.jwic.base.IControlContainer;
import de.jwic.demo.DemoModule;

/**
 * @author lippisch
 *
 */
public class ComboLifeSearchDemoModule extends DemoModule {

	/**
	 * 
	 */
	public ComboLifeSearchDemoModule() {
		setTitle("Combo LifeSearch");
		setDescription("A combo box that allows autocompletion by performing a search on the server side");
		
		setGroup("Advanced");
		
	}

	/* (non-Javadoc)
	 * @see de.jwic.demo.DemoModule#createModule(de.jwic.base.IControlContainer)
	 */
	@Override
	public void createModule(IControlContainer container) {
		new ComboLifeSearchDemo(container, null);

	}

}
