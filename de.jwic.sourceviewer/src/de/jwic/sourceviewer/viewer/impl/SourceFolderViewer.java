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

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import de.jwic.base.IControlContainer;
import de.jwic.sourceviewer.model.NavigationElement;
import de.jwic.sourceviewer.model.SourceFolder;
import de.jwic.sourceviewer.model.SourcePackage;
import de.jwic.sourceviewer.viewer.IObjectViewer;

/**
 * Defines the source-code folders viewer.
 *
 * @author Aron Cotrau
 */
public class SourceFolderViewer extends AbstractFolderViewer implements IObjectViewer {

	public SourceFolderViewer(IControlContainer container, String name) {
		super(container, name);
		setTemplateName(SourceFolderViewer.class.getName());
	}

	/* (non-Javadoc)
	 * @see de.jwic.sourceviewer.viewer.IObjectViewer#isSupported(de.jwic.sourceviewer.model.NavigationElement)
	 */
	public boolean isSupported(NavigationElement element) {
		return element instanceof SourceFolder;
	}

	/* (non-Javadoc)
	 * @see de.jwic.sourceviewer.viewer.IObjectViewer#setNavigationElement(de.jwic.sourceviewer.model.NavigationElement)
	 */
	public void setNavigationElement(NavigationElement element) {
		navigationElement = element;
		if (element == null) {
			return;				// direct exit 
		}
		
		SourceFolder folder = (SourceFolder) element;
		List childs = folder.getChilds();
		List contents = new ArrayList();
		
		for (Iterator it = childs.iterator(); it.hasNext();) {
			NavigationElement content = (NavigationElement) it.next();
			if (content instanceof SourcePackage) {
				contents.add(content);
			}
		}
		
		contentNames = (NavigationElement[]) contents.toArray(new NavigationElement[contents.size()]);
	}
}
