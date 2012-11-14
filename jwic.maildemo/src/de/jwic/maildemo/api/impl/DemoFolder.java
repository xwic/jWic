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
 * de.jwic.maildemo.api.impl.DemoFolder
 * Created on 23.04.2007
 * $Id: DemoFolder.java,v 1.2 2007/04/27 09:29:51 aroncotrau Exp $
 */
package de.jwic.maildemo.api.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import de.jwic.maildemo.api.IFolder;

/**
 *
 * @author Florian Lippisch
 */
public class DemoFolder implements IFolder, Serializable {

	private List mails = null;
	private String name;
	private List children = null;

	/**
	 * @param name
	 * @param mails
	 */
	public DemoFolder(String name, List mails) {
		super();
		this.name = name;
		this.mails = mails;
	}

	/* (non-Javadoc)
	 * @see de.jwic.maildemo.api.IFolder#listMails()
	 */
	public List listMails() {
		return mails;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/* (non-Javadoc)
	 * @see de.jwic.maildemo.api.IFolder#listFolders()
	 */
	public List listFolders() {
		return null != children ? children : new ArrayList();
	}

	
	/**
	 * @return the children
	 */
	public List getChildren() {
		return children;
	}

	/**
	 * @param children the children to set
	 */
	public void setChildren(List children) {
		this.children = children;
	}

	public void addFolder(IFolder folder) {
		if (null == children) {
			children = new ArrayList();
		}
		
		children.add(folder);
	}
}
