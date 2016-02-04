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
import java.util.regex.Pattern;

import de.jwic.base.Control;
import de.jwic.base.IControlContainer;
import de.jwic.sourceviewer.main.SVModel;
import de.jwic.sourceviewer.model.ContainerElement;
import de.jwic.sourceviewer.model.NavigationElement;
import de.jwic.sourceviewer.viewer.IObjectViewer;

/**
 * Defines the abstract base class for the folders (packages, source-folders and regular folders) viewers.
 *
 * @author Aron Cotrau
 */
public abstract class AbstractFolderViewer extends Control implements IObjectViewer {

	protected NavigationElement[] contentNames = null;
	protected SVModel model = null;
	
	protected NavigationElement navigationElement = null; 
	
	public AbstractFolderViewer(IControlContainer container, String name) {
		super(container, name);
		setTemplateName(AbstractFolderViewer.class.getName());
	}

	/* (non-Javadoc)
	 * @see de.jwic.sourceviewer.viewer.IObjectViewer#init(de.jwic.sourceviewer.main.SVModel)
	 */
	public void init(SVModel model) {
		this.model = model;
	}
	
	/* (non-Javadoc)
	 * @see de.jwic.sourceviewer.viewer.IObjectViewer#setNavigationElement(de.jwic.sourceviewer.model.NavigationElement)
	 */
	public void setNavigationElement(NavigationElement element) {
		
		navigationElement = element;
		
		if (element != null && element instanceof ContainerElement) {
			List childs = ((ContainerElement)element).getChilds();
			List contents = new ArrayList();
			
			for (Iterator it = childs.iterator(); it.hasNext();) {
				NavigationElement content = (NavigationElement) it.next();
				contents.add(content);
			}
			
			contentNames = (NavigationElement[]) contents.toArray(new NavigationElement[contents.size()]);
		}
	}
	
	/**
	 * @return the contentNames
	 */
	public NavigationElement[] getContentNames() {
		return contentNames;
	}

	/**
	 * @return the model
	 */
	public SVModel getModel() {
		return model;
	}
	
	/**
	 * handles selection of a file
	 * @param fileName
	 */
	public void actionSelect(String fileName) {
		NavigationElement element = null;
		for (int i = 0; i < contentNames.length && null == element; i++) {
			if (contentNames[i].getName().equals(fileName)) {
				element = contentNames[i];
			}
		}
		
		if (null != element) {
			model.setCurrentElement(element);
		} else {
			// try to find the file "somewhere else"
			model.openEntryByName(fileName);
		}
	}
	
	/**
	 * Adds interactivity to the comments.
	 * @param comment
	 * @return
	 */
	public String fixComment(String comment) {
		
		if (comment == null) {
			return null;
		}
		Pattern p = Pattern.compile("<sv:link name=\"([^\"]*)\">([^<]*)</sv:link>", Pattern.MULTILINE | Pattern.CASE_INSENSITIVE);
		String replace = "<a href=\"" + createActionURL("select", "$1") + "\">$2</a>";
		comment = p.matcher(comment).replaceAll(replace);
		// 2nd step
		p = Pattern.compile("<sv:link>([^<]*)</sv:link>", Pattern.MULTILINE | Pattern.CASE_INSENSITIVE);
		replace = "<a href=\"" + createActionURL("select", "$1") + "\">$1</a>";
		return p.matcher(comment).replaceAll(replace);
		
	}

	/**
	 * @return the navigationElement
	 */
	public NavigationElement getNavigationElement() {
		return navigationElement;
	}
}
