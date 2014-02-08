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
 * de.jwic.sourceviewer.viewer.IObjectViewer
 * Created on 26.04.2007
 * $Id: IObjectViewer.java,v 1.2 2007/05/03 14:27:50 aroncotrau Exp $
 */
package de.jwic.sourceviewer.viewer;

import de.jwic.base.IControl;
import de.jwic.sourceviewer.main.SVModel;
import de.jwic.sourceviewer.model.NavigationElement;

/**
 * An object viewer is used to display the content and/or informations
 * about an object.
 * 
 * @author Florian Lippisch
 */
public interface IObjectViewer extends IControl {

	/**
	 * Returns ture if the viewer supports the specified element.
	 * @param element
	 * @return
	 */
	public boolean isSupported(NavigationElement element);
	
	/**
	 * Set the NavigationElement to be displayed or <code>null</code>
	 * if the viewer is no longer requird.
	 * @param element
	 */
	public void setNavigationElement(NavigationElement element);
	
	/**
	 * inits the viewer with the given model
	 * @param model
	 */
	public void init(SVModel model);
}
