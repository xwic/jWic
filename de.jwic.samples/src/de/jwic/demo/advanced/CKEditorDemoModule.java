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
public class CKEditorDemoModule extends DemoModule {

	/**
	 * 
	 */
	public CKEditorDemoModule() {
		setTitle("CkEditor");
		setDescription("A WYSIWYG HTML Editor control");
		setGroup("Advanced");
	}
	
	/* (non-Javadoc)
	 * @see de.jwic.demo.DemoModule#createModule(de.jwic.base.IControlContainer)
	 */
	@Override
	public void createModule(IControlContainer container) {

		
		new CKEditorDemo(container);
		

	}

}
