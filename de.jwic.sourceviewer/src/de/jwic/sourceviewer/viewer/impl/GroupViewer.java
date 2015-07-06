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
import de.jwic.sourceviewer.model.Group;
import de.jwic.sourceviewer.model.NavigationElement;
import de.jwic.sourceviewer.viewer.IObjectViewer;

/**
 * 
 *
 * @author Aron Cotrau
 */
public class GroupViewer extends AbstractFolderViewer implements IObjectViewer {

	public GroupViewer(IControlContainer container, String name) {
		super(container, name);
		setTemplateName(getClass().getName()); // restore related template
	}

	/* (non-Javadoc)
	 * @see de.jwic.sourceviewer.viewer.IObjectViewer#isSupported(de.jwic.sourceviewer.model.NavigationElement)
	 */
	public boolean isSupported(NavigationElement element) {
		return element instanceof Group;
	}
	
	/* (non-Javadoc)
	 * @see de.jwic.sourceviewer.viewer.IObjectViewer#setNavigationElement(de.jwic.sourceviewer.model.NavigationElement)
	 */
	public void setNavigationElement(NavigationElement element) {
		super.setNavigationElement(element);
		requireRedraw();
	}
}
