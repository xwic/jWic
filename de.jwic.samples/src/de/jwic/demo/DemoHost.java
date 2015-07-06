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
/**
 * 
 */
package de.jwic.demo;

import de.jwic.base.ControlContainer;
import de.jwic.base.IControlContainer;
import de.jwic.controls.LabelControl;
import de.jwic.util.SerObservable;
import de.jwic.util.SerObserver;

/**
 * The host is displaying one demo at a time. If a user switches modules, the old one gets destroyed
 * so that resources are freed up again. This is a good practice as you would not want the entire application
 * stack to be in memory.
 * 
 * @author lippisch
 */
public class DemoHost extends ControlContainer {

	private DemoModel model;
	private LabelControl lbTitle;
	private LabelControl lbDescription;
	
	private ControlContainer content;

	/**
	 * @param container
	 * @param name
	 */
	public DemoHost(IControlContainer container, String name, DemoModel model) {
		super(container, name);
		this.model = model;

	
		lbTitle = new LabelControl(this, "lbTitle");
		lbTitle.setText("Choose your Demo!");
		
		lbDescription = new LabelControl(this, "lbDescription");
		lbDescription.setText("");
		
		// create dummy control as current demo content
		content = new ControlContainer(this, "demo");
		
		model.addObserver(new SerObserver() {
			@Override
			public void update(SerObservable o, Object arg) {
				onModuleChange();
				
			}
		});
	
	}

	/**
	 * 
	 */
	protected void onModuleChange() {

		DemoModule module = model.getActiveModule();
		
		if (module != null) {
			lbTitle.setText(module.getTitle());
			lbDescription.setText(module.getDescription());
			
			// clear content
			content.destroy();
			content = new ControlContainer(this, "demo");
			
			module.createModule(content);
			requireRedraw();
			
		}
		
	}

}
