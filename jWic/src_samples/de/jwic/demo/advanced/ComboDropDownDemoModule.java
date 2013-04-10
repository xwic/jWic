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
public class ComboDropDownDemoModule extends DemoModule {

	/**
	 * 
	 */
	public ComboDropDownDemoModule() {
		setTitle("Combo DropDown");
		setDescription("A combo box that allows search, render customization and auto-complete");
		
		setGroup("Advanced");
		
	}

	/* (non-Javadoc)
	 * @see de.jwic.demo.DemoModule#createModule(de.jwic.base.IControlContainer)
	 */
	@Override
	public void createModule(IControlContainer container) {
		new ComboDropDownDemo(container, null);

	}

}
