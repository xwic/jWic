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
import de.jwic.sourceviewer.model.FileElement;
import de.jwic.sourceviewer.model.JavaElement;
import de.jwic.sourceviewer.model.NavigationElement;
import de.jwic.sourceviewer.model.SourcePackage;
import de.jwic.sourceviewer.viewer.IObjectViewer;

/**
 * The PackageViewer class for handling the selection of packages.
 *
 * @author Aron Cotrau
 */
public class PackageViewer extends AbstractFolderViewer implements IObjectViewer {

	private JavaElement[] javaSourceFiles = null;

	public PackageViewer(IControlContainer container, String name) {
		super(container, name);
		setTemplateName(PackageViewer.class.getName());
	}

	/* (non-Javadoc)
	 * @see de.jwic.sourceviewer.viewer.IObjectViewer#isSupported(de.jwic.sourceviewer.model.NavigationElement)
	 */
	public boolean isSupported(NavigationElement element) {
		return element instanceof SourcePackage;
	}

	/* (non-Javadoc)
	 * @see de.jwic.sourceviewer.viewer.IObjectViewer#setNavigationElement(de.jwic.sourceviewer.model.NavigationElement)
	 */
	public void setNavigationElement(NavigationElement element) {
		if (element == null) {
			return;				// direct exit 
		}
		
		SourcePackage pkg = (SourcePackage) element;
		List childs = pkg.getChilds();
		List javaFiles = new ArrayList();
		
		try {
			List list = new ArrayList();
			
			for (Iterator it = childs.iterator(); it.hasNext();) {
				FileElement file = (FileElement) it.next();
				list.add(file);
				
				// get the java files
				if (file instanceof JavaElement) {
					javaFiles.add(file);
				} 
			}
			
			// add the content from the package.html file
			contentNames = (NavigationElement[]) list.toArray(new NavigationElement[list.size()]);
			javaSourceFiles = (JavaElement[]) javaFiles.toArray(new JavaElement[javaFiles.size()]);
		} catch (Exception e) {
			log.error("Error creating HTML code", e);
			element.setComment("Error creating html code: " + e);
		}
	}

	/**
	 * @return javaSourceFiles
	 */
	public JavaElement[] getJavaSourceFiles() {
		return javaSourceFiles;
	}
}
