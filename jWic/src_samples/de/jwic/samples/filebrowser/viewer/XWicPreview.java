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
 * de.jwic.samples.filebrowser.viewer.XWicPreview
 * Created on 03.06.2005
 * $Id: XWicPreview.java,v 1.1 2006/01/16 08:31:31 lordsam Exp $
 */
package de.jwic.samples.filebrowser.viewer;

import java.io.File;

import de.jwic.base.Control;
import de.jwic.base.IControlContainer;
import de.jwic.base.XmlApplicationSetup;

/**
 * Preview the file content of xwic files.
 * 
 * @author Florian Lippisch
 * @version $Revision: 1.1 $
 */
public class XWicPreview extends Control {

	private String error = "";
	private XmlApplicationSetup appSetup = null; 
	
	/**
	 * @param file
	 */
	public XWicPreview(IControlContainer container, String name, File file) {
		super(container, name);
		try {
			appSetup = new XmlApplicationSetup(file.getAbsolutePath());
		} catch (Exception e) {
			error = e.toString();
		}
		
	}

	/* (non-Javadoc)
	 * @see de.jwic.base.IControl#actionPerformed(java.lang.String, java.lang.String)
	 */
	public void actionPerformed(String actionId, String parameter) {

	}
	

	/**
	 * @return Returns the appSetup.
	 */
	public XmlApplicationSetup getAppSetup() {
		return appSetup;
	}
	/**
	 * @return Returns the error.
	 */
	public String getError() {
		return error;
	}
}
