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
 * de.jwic.sourceviewer.viewer.ContentViewer
 * Created on 27.04.2007
 * $Id: ContentViewer.java,v 1.1 2007/04/27 13:50:41 lordsam Exp $
 */
package de.jwic.sourceviewer.viewer;

import de.jwic.base.IControlContainer;
import de.jwic.controls.ScrollableContainer;

/**
 *
 * @author Florian Lippisch
 */
public class ContentViewer extends ScrollableContainer {

	private String currControl = null;
	
	/**
	 * @param container
	 * @param name
	 */
	public ContentViewer(IControlContainer container, String name) {
		super(container, name);
		
	}

	/**
	 * @return the currControl
	 */
	public String getCurrControl() {
		return currControl;
	}

	/**
	 * @param currControl the currControl to set
	 */
	public void setCurrControl(String currControl) {
		this.currControl = currControl;
	}

}
