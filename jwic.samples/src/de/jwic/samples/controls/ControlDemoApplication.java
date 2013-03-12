/*
 * Copyright 2005 jWic group (http://www.jwic.de)
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * de.jwic.samples.controls.ControlDemoApplication
 * Created on 28.10.2005
 * $Id: ControlDemoApplication.java,v 1.22 2012/07/31 09:48:49 lordsam Exp $
 */
package de.jwic.samples.controls;

import de.jwic.base.Application;
import de.jwic.base.Control;
import de.jwic.base.ControlContainer;
import de.jwic.base.IControlContainer;
import de.jwic.base.Page;
import de.jwic.controls.Button;
import de.jwic.controls.TabControl;
import de.jwic.controls.TabStripControl;
import de.jwic.events.SelectionEvent;
import de.jwic.events.SelectionListener;

/**
 * This application demonstrates the usage of the controls in the 
 * jWic project. This application is used by the developers to 
 * test each control as well as a how-to demo of the controls.
 * 
 * @author Florian Lippisch
 * @version $Revision: 1.22 $
 */
public class ControlDemoApplication extends Application {

	/* (non-Javadoc)
	 * @see de.jwic.base.Application#createRootControl(de.jwic.base.IControlContainer)
	 */
	public Control createRootControl(IControlContainer container) {
		
		// specify an exit URL
		getSessionContext().setExitURL("byebye.html");
		
		// specify the page 
		Page page = new Page(container);
		page.setTitle("jWic Control Demo");
		page.setTemplateName("de.jwic.samples.controls.Page"); // specify a template
		
		Button btExit = new Button(page, "exit");
		btExit.setTitle("Exit");
		btExit.addSelectionListener(new SelectionListener() {
			public void objectSelected(SelectionEvent event) {
				exitApplication();
			};
		});
		btExit.setWidth(80);
		
		
		//WindowControl window = new WindowControl(page, "window");
		//window.setTitle("jWic Control Demo");
		ControlContainer sf = new ControlContainer(page, "window");
		//sf.setTitle("jWic Control Demo");
		
		createControls(sf);

		container.getSessionContext().setActionController(new TestRecorderActionController());
		
		return page;
	}

	/**
	 * Create the 'demo' pages. 
	 * @param container
	 */
	private void createControls(IControlContainer container) {
		
		//TabControl tab;
		
		TabStripControl mainTabStrip = new TabStripControl(container);
		mainTabStrip.setLocation(TabStripControl.LOCATION_LEFT);

		//tab = mainTabStrip.addTab("Basic");
		addBasicSamples(mainTabStrip);
		
		// 
		//tab = mainTabStrip.addTab("Advanced");
		addAdvancedSamples(mainTabStrip);

		
	}

	/**
	 * @param tab
	 */
	public void addAdvancedSamples(TabStripControl tabStrip) {
		
		//TabStripControl tabStrip = new TabStripControl(tab);
		TabControl tab;
		
		tab = tabStrip.addTab("DateInputBox");
		new DateInputBoxDemo(tab); 

		tab = tabStrip.addTab("Combo");
		new ComboDemo(tab, "combo");

		tab =tabStrip.addTab("ButtonImage");
		new ButtonImageDemo(tab);

		tab = tabStrip.addTab("ProgressBar");
		new ProgressBarDemo(tab); 
		
		tab = tabStrip.addTab("Group");
		new GroupControlDemo(tab); 
		
		tab = tabStrip.addTab("Inline Window");
		new InlineWindowDemo(tab);

		tab = tabStrip.addTab("Window");
		new WindowDemo(tab);

		tab = tabStrip.addTab("Tabs");
		new TabStripDemo(tab);

		tab = tabStrip.addTab("Tree");
		new TreeDemo(tab);
		
		tab = tabStrip.addTab("JavaScript");
		new JavaScriptDemo(tab);

		tab = tabStrip.addTab("AsyncContainer");
		new AsyncRenderContainerDemo(tab);

	}

	/**
	 * @param tab
	 */
	public void addBasicSamples(TabStripControl tabStrip) {
		
		//TabStripControl tabStrip = new TabStripControl(tab);
		
		TabControl tab;
		tab = tabStrip.addTab("Label");
		new LabelDemo(tab); 

		tab = tabStrip.addTab("AnchorLink");
		new AnchorLinkDemo(tab); 

		tab = tabStrip.addTab("Button");
		new ButtonDemo(tab);

		tab = tabStrip.addTab("InputBox");
		new InputBoxDemo(tab); 

		tab = tabStrip.addTab("Listbox");
		new ListBoxDemo(tab); 

		tab = tabStrip.addTab("Checkbox");
		new CheckboxDemo(tab, "cbDemo"); 

		tab = tabStrip.addTab("RadioGroup");
		new RadioDemo(tab, "redemo"); 

		tab = tabStrip.addTab("PleaseWait");
		new PleaseWaitDemo(tab); 

		tab = tabStrip.addTab("SysInfo");
		new SysInfoDemo(tab); 

	}

	/**
	 * Terminate the application session.
	 *
	 */
	protected void exitApplication() {
		
		getSessionContext().exit();
		
	}

}
