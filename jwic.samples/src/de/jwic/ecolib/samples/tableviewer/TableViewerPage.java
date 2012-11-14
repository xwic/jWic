/*
 * Copyright 2005-2007 jWic group (http://www.jwic.de)
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
 * de.jwic.ecolib.samples.controls.tbv.TableViewerPage
 * Created on Apr 4, 2007
 * $Id: TableViewerPage.java,v 1.2 2010/02/07 14:26:33 lordsam Exp $
 */
package de.jwic.ecolib.samples.tableviewer;

import de.jwic.base.ControlContainer;
import de.jwic.base.IControlContainer;
import de.jwic.base.Page;
import de.jwic.controls.Button;
import de.jwic.controls.TabControl;
import de.jwic.controls.TabStripControl;
import de.jwic.events.SelectionEvent;
import de.jwic.events.SelectionListener;

/**
 * 
 *
 * @author Aron Cotrau
 */
public class TableViewerPage extends Page {

	/**
	 * @param container
	 */
	public TableViewerPage(IControlContainer container) {
		super(container);
		init();
	}

	/**
	 * @param container
	 * @param name
	 */
	public TableViewerPage(IControlContainer container, String name) {
		super(container, name);
		init();
	}

	private void init() {
		// specify an exit URL
		getSessionContext().setExitURL("byebye.html");

		this.setTitle("TableViewer Demo");
		this.setTemplateName("de.jwic.ecolib.samples.tableviewer.TableViewerDemoPage");
		
		Button btExit = new Button(this, "btOk");
		btExit.setTitle("Ok");
		btExit.setWidth(80);
		btExit.addSelectionListener(new SelectionListener() {
			public void objectSelected(SelectionEvent event) {
				getSessionContext().pushTopControl(createMainPage());
			};
		});
	}
	
	private Page createMainPage() {
		// specify the page 
		Page page = new Page(this);
		page.setTitle("TableViewer Demo");
		page.setTemplateName("de.jwic.ecolib.samples.tableviewer.TableViewerPage"); // specify a template
		
		Button btExit = new Button(page, "exit");
		btExit.setTitle("Exit");
		btExit.addSelectionListener(new SelectionListener() {
			public void objectSelected(SelectionEvent event) {
				exitApplication();
			};
		});
		
		ControlContainer window = new ControlContainer(page, "window");
		TabStripControl tabStrip = new TabStripControl(window);
		
		TabControl tab = tabStrip.addTab("Table Viewer");
		TableViewerContainer cont = new TableViewerContainer(tab);
		cont.setWidthHeight();
		
		return page;
	}

	/**
	 * Terminate the application session.
	 */
	protected void exitApplication() {
		getSessionContext().exit();
	}
}
