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
 * $Id: ControlDemoApplication.java,v 1.23 2011/06/13 20:29:12 lordsam Exp $
 */
package de.jwic.ecolib.samples.controls;

import de.jwic.base.Application;
import de.jwic.base.Control;
import de.jwic.base.ControlContainer;
import de.jwic.base.IControlContainer;
import de.jwic.base.Page;
import de.jwic.controls.Button;
import de.jwic.controls.LabelControl;
import de.jwic.controls.TabControl;
import de.jwic.controls.TabStripControl;
import de.jwic.ecolib.samples.controls.tbv.TableViewerDemo;
import de.jwic.ecolib.samples.controls.tbv2.TableViewerDemo2;
import de.jwic.ecolib.samples.controls.tbv3.TableViewerDemo3;
import de.jwic.events.SelectionEvent;
import de.jwic.events.SelectionListener;

/**
 * This application demonstrates the usage of the controls in the 
 * jWic project. This application is used by the developers to 
 * test each control as well as a how-to demo of the controls.
 * 
 * @author Florian Lippisch
 * @version $Revision: 1.23 $
 */
public class ControlDemoApplication extends Application {

	private static final long serialVersionUID = -1277999077573033431L;

	/* (non-Javadoc)
	 * @see de.jwic.base.Application#createRootControl(de.jwic.base.IControlContainer)
	 */
	public Control createRootControl(IControlContainer container) {
		
		// specify an exit URL
		getSessionContext().setExitURL("byebye.html");
		
		// specify the page 
		Page page = new Page(container);
		page.setTitle("jWic Control Demo (ecolib)");
		page.setTemplateName("de.jwic.ecolib.samples.controls.Page"); // specify a template
		
		Button btExit = new Button(page, "exit");
		btExit.setTitle("Exit");
		btExit.addSelectionListener(new SelectionListener() {
			private static final long serialVersionUID = -3536357186776092171L;

			public void objectSelected(SelectionEvent event) {
				exitApplication();
			};
		});
		
		
		//WindowControl window = new WindowControl(page, "window");
		//window.setTitle("jWic Control Demo");
		ControlContainer window = new ControlContainer(page, "window");
		
		createControls(window);

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
		
		de.jwic.samples.controls.ControlDemoApplication jwicDemo = new de.jwic.samples.controls.ControlDemoApplication(); 
		
		//tab = mainTabStrip.addTab("Basic");
		jwicDemo.addBasicSamples(mainTabStrip); //new TabStripControl(tab));
		
		//tab = mainTabStrip.addTab("Advanced");
		jwicDemo.addAdvancedSamples(mainTabStrip);//new TabStripControl(tab));

		
		//tab = mainTabStrip.addTab("Extended (ecolib)");
		addExtendedSamples(mainTabStrip);

	}

	/**
	 * @param tab
	 */
	private void addExtendedSamples(TabStripControl tabStrip) {
		
		new FormDemo(tabStrip.addTab("Form"), "formdemo");
		
		new ColumnSelectorDemo(tabStrip.addTab("ColumnSelector"), "les");
		
		TabControl tab = tabStrip.addTab("MenuButton");
		new MenuButtonDemo(tab); 
		
		tab = tabStrip.addTab("Dialogs");
		new DialogDemo(tab); 

		tab = tabStrip.addTab("Toolbar");
		new ToolbarDemo(tab, "demo");

		tab = tabStrip.addTab("TableViewer");
		new TableViewerDemo(tab); 

		tab = tabStrip.addTab("TableViewer (2)");
		new TableViewerDemo2(tab); 

		tab = tabStrip.addTab("TableViewer (3)");
		new TableViewerDemo3(tab); 

		tab = tabStrip.addTab("CKEditor");
		new CKEditorDemo(tab); 

		tab = tabStrip.addTab("JFreeChart");
		try {
			new ChartDemo(tab, "chart");
		} catch (NoClassDefFoundError e1) {
			tab.removeControl("chart");
			new LabelControl(tab).setText("JFreeChart not available - install relevant jar files.");
		}
		
		tab = tabStrip.addTab("ASyncInfo");
		new AsyncDemo(tab);
		
		tab = tabStrip.addTab("Balloon");
		new BalloonDemo(tab);
	
		/* -- feature not comepleted
		tab = tabStrip.addTab("Time Picker");
		new TimePickerDemo(tab);
		*/
		
		
		tab = tabStrip.addTab("Date Picker");
		new DatePickerDemo(tab,"DatePicker");
		
		
		/* -- feature not comepleted
		tab = tabStrip.addTab("Editable Listbox");
		new EditableListBoxDemo(tab);
		*/
		
		/*tab = tabStrip.addTab("Fish Eye List");
		new FishEyeDemo(tab);*/
	}

	/**
	 * Terminate the application session.
	 *
	 */
	protected void exitApplication() {
		
		getSessionContext().exit();
		
	}

}
