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
package de.jwic.sourceviewer.viewer.impl;

import de.jwic.base.IControlContainer;
import de.jwic.sourceviewer.model.Folder;
import de.jwic.sourceviewer.model.NavigationElement;
import de.jwic.sourceviewer.viewer.IObjectViewer;

/**
 * Defines the Folders viewer. 
 *
 * @author Aron Cotrau
 */
public class FolderViewer extends AbstractFolderViewer implements IObjectViewer {

	public FolderViewer(IControlContainer container, String name) {
		super(container, name);
	}

	/* (non-Javadoc)
	 * @see de.jwic.sourceviewer.viewer.IObjectViewer#isSupported(de.jwic.sourceviewer.model.NavigationElement)
	 */
	public boolean isSupported(NavigationElement element) {
		return element instanceof Folder;
	}

}
