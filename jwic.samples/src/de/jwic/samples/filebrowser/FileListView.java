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
 * de.jwic.samples.filebrowser.FileListView
 * Created on 03.06.2005
 * $Id: FileListView.java,v 1.1 2006/01/16 08:31:31 lordsam Exp $
 */
package de.jwic.samples.filebrowser;

import de.jwic.base.ControlContainer;
import de.jwic.base.IControlContainer;

/**
 * A view that lists files.
 * @author Florian Lippisch
 * @version $Revision: 1.1 $
 */
public class FileListView extends ControlContainer {

	private FileListControl fileList = null;
	
	/**
	 * @param container
	 * @param name
	 */
	public FileListView(IControlContainer container, String name) {
		super(container, name);
		
		fileList = new FileListControl(this, "list");
		
	}
	
	/* (non-Javadoc)
	 * @see de.jwic.base.IControl#actionPerformed(java.lang.String, java.lang.String)
	 */
	public void actionPerformed(String actionId, String parameter) {
		
		if (actionId.equals("sort")) {
			fileList.setSort(parameter);
		}

	}
	
	/**
	 * Returns the underlying FileListControl.
	 * @return
	 */
	public FileListControl getFileListControl() {
		return fileList;
	}
	
}
