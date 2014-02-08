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
public class FileUploadDemoModule extends DemoModule {

	/**
	 * 
	 */
	public FileUploadDemoModule() {
		setTitle("FileUpload");
		setDescription("Represents a simple file upload field.");
		
	}
	
	/* (non-Javadoc)
	 * @see de.jwic.demo.DemoModule#createModule(de.jwic.base.IControlContainer)
	 */
	@Override
	public void createModule(IControlContainer container) {

		
		new FileUploadDemo(container);
		

	}

}
