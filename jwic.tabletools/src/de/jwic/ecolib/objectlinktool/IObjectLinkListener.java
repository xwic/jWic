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
 * de.jwic.ecolib.objectlinktool.IObjectLinkListener
 * Created on Apr 5, 2007
 * $Id: IObjectLinkListener.java,v 1.1 2007/04/25 09:59:13 lordsam Exp $
 */
package de.jwic.ecolib.objectlinktool;

import java.io.Serializable;

/**
 * This class is used as a listener by the ObjectLinkTool.
 * 
 * @author Aron Cotrau
 */
public interface IObjectLinkListener extends Serializable {

	/**
	 * notifies that 2 elements are selected and the "Link" button is being
	 * pressed.
	 */
	public void linkElementsRequested(ObjectLinkEvent event);
	
	/**
	 * Invoked when the left or right data has been modified and views need
	 * to be refreshed.  
	 * @param event
	 */
	public void dataModified(ObjectLinkEvent event);
	
	/**
	 * Invoked when the view mode is changed.
	 * @param event
	 */
	public void modeChanged(ObjectLinkEvent event);
	
}
