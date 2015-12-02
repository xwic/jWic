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

import de.jwic.base.IControlContainer;
import de.jwic.base.Page;
import de.jwic.controls.ListBoxControl;
import de.jwic.events.ElementSelectedEvent;
import de.jwic.events.ElementSelectedListener;

/**
 * Defines the layout of the page and acts as the root control.
 * 
 * @author lippisch
 */
public class DemoPage extends Page {

	private DemoModel model;

	/**
	 * @param container
	 * @param modules 
	 */
	public DemoPage(IControlContainer container, List<DemoModule> modules) {
		super(container);
		setTitle("Application Demo");
		
		createThemeSelector();

		model = new DemoModel(modules);
		new DemoSelector(this, "demoSelector", model);
		new DemoHost(this, "demoHost", model);
		
	}

	public void actionRestart() {
		getSessionContext().setExitURL("demo.xwic");
		getSessionContext().exit();
	}

	/**
	 * 
	 */
	private void createThemeSelector() {
		ListBoxControl lbTheme = new ListBoxControl(this, "lbTheme");
		lbTheme.addElement("Default Theme", "default");
		lbTheme.addElement("Start Theme", "start");
		lbTheme.addElement("UI Darkness", "ui-darkness");
		
		lbTheme.setSelectedKey("default");
		lbTheme.setChangeNotification(true);
		lbTheme.addElementSelectedListener(new ElementSelectedListener() {
			public void elementSelected(ElementSelectedEvent event) {
				onThemeChange((String)event.getElement());
			}
		});
		
	}

	/**
	 * @param theme
	 */
	protected void onThemeChange(String theme) {
		getSessionContext().setThemeName(theme);
		
	}


}
