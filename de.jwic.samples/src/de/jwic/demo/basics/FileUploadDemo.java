/*******************************************************************************
 * Copyright 2015 xWic group (http://www.xwic.de)
 *
 * Licensed under the Apache License, Version 2.0 (the "License").
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * 		http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software 
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and 
 * limitations under the License.
 *  
 *******************************************************************************/
package de.jwic.demo.basics;

import de.jwic.base.ControlContainer;
import de.jwic.base.IControlContainer;
import de.jwic.controls.FileUpload;
import de.jwic.samples.controls.propeditor.PropertyEditorView;

/**
 * 
 * Demonstrates the usage of the ButtonControl.
 * 
 * @author Florian Lippisch
 * @version $Revision: 1.4 $
 */
public class FileUploadDemo extends ControlContainer {

	private FileUpload fileUpload;
	
	/**
	 * Constructor.
	 * @param container
	 */
	public FileUploadDemo(IControlContainer container) {
		super(container);
		
		fileUpload = new FileUpload(this, "fileUpload");
		fileUpload.setWidth(300);
		
		// create the property editor
		final PropertyEditorView propEditor = new PropertyEditorView(this, "propEditor");
		propEditor.setBean(fileUpload);
		
	}
}
