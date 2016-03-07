/**
 * 
 */
package de.jwic.demo.pojoedit;

import de.jwic.base.ControlContainer;
import de.jwic.base.IControlContainer;
import de.jwic.demo.DemoModule;

/**
 * Demo of the PojoEditor.
 * @author lippisch
 */
public class PojoEditorDemo extends DemoModule {

	/**
	 * 
	 */
	public PojoEditorDemo() {
		super();
		
		setTitle("Pojo Editor");
		setDescription("A sample how to create a generic editor for simple pojo objects.");
		setGroup("Framework");

	}

	/* (non-Javadoc)
	 * @see de.jwic.demo.DemoModule#createModule(de.jwic.base.IControlContainer)
	 */
	@Override
	public void createModule(IControlContainer container) {

		
		new MyPojoEditor(container, "pojoEditor");
		
	}

}
