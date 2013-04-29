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
public class ColumnSelectorDemoModule extends DemoModule {

	/**
	 * 
	 */
	public ColumnSelectorDemoModule() {
		setTitle("Column Selector");
		setDescription("Displays a sortable list to select elements from. Useful in combination with the TableViewer or simmilar scenarios");
		setGroup("Advanced");
	}
	
	/* (non-Javadoc)
	 * @see de.jwic.demo.DemoModule#createModule(de.jwic.base.IControlContainer)
	 */
	@Override
	public void createModule(IControlContainer container) {

		
		new ColumnSelectorDemo(container, "colSelector");
		

	}

}
