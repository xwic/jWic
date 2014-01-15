/*
 * Copyright 2005 jWic Group (http://www.jwic.de)
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
 * de.jwic.samples.filebrowser.FileBrowserDemo
 * Created on 24.05.2005
 * $Id: FileBrowserDemo.java,v 1.5 2010/01/07 10:47:24 lordsam Exp $
 */
package de.jwic.samples.filebrowser;

import de.jwic.base.Application;
import de.jwic.base.Control;
import de.jwic.base.IControlContainer;
import de.jwic.base.Page;
import de.jwic.base.SessionContext;
import de.jwic.controls.InlineWindow;

/**
 * Demonstrates the FileBrowserControl.
 * @author Florian Lippisch
 * @version $Revision: 1.5 $
 */
public class FileBrowserDemo extends Application {

	private String title = "File Browser Demo";
	
	/* (non-Javadoc)
	 * @see de.jwic.base.AbstractApplication#initialize(de.jwic.base.SessionContext)
	 */
	public void initialize(SessionContext context) {
		super.initialize(context);
		context.setExitURL("byebye.html");
	}
	
	/* (non-Javadoc)
	 * @see de.jwic.base.AbstractApplication#createRootControl(de.jwic.base.IControlContainer)
	 */
	public Control createRootControl(IControlContainer container) {

		Page page = new Page(container);
		
		page.setTitle(getTitle());
		page.setTemplateName(getClass().getName());
		
		InlineWindow win = new InlineWindow(page, "win");
		win.setText("File Browser");
		win.setPosition(InlineWindow.Position.CENTER);
		win.setWidth(900);
		win.setHeight(500);

		new FileBrowserControl(win);	//add the FileBrowserControl to the window.
		
		return page;
		
	}

	/**
	 * @return Returns the title.
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title The title to set.
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	
}
