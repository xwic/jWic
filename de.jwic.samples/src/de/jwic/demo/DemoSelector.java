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

import java.util.List;

import de.jwic.base.Control;
import de.jwic.base.IControlContainer;
import de.jwic.base.JavaScriptSupport;

/**
 * @author lippisch
 *
 */
@JavaScriptSupport
public class DemoSelector extends Control {

	
	private DemoModel model;

	/**
	 * @param container
	 * @param name
	 */
	public DemoSelector(IControlContainer container, String name, DemoModel model) {
		super(container, name);
		this.model = model;
	}

	/**
	 * @return the modules
	 */
	public List<DemoModule> getModules() {
		return model.getModules();
	}

	/**
	 * This action handler is invoked by clicking on a module.
	 * @param idx
	 */
	public void actionSelection(String idx) {
		int num = Integer.parseInt(idx) - 1;
		DemoModule module = model.getModules().get(num);
		model.setActiveModule(module);
		
	}
	
	/**
	 * Returns the index of the active group.
	 * @return
	 */
	public int getActiveGroupIndex() {
		
		DemoModule active = model.getActiveModule();
		if (active != null) {
			String oldGroup = null;
			int grpIdx = -1;
			for (DemoModule module : model.getModules()) {
				if (oldGroup == null || !oldGroup.equals(module.getGroup())) {
					grpIdx++;
					oldGroup = module.getGroup();
				}
				if (active == module) {
					break;
				}
			}
			return grpIdx;
		}
		return 0;
	}
	
}
