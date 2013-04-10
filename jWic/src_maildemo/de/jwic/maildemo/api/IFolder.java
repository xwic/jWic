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
 * de.jwic.maildemo.api.IFolder
 * Created on 23.04.2007
 * $Id: IFolder.java,v 1.2 2007/04/27 09:29:52 aroncotrau Exp $
 */
package de.jwic.maildemo.api;

import java.util.List;

/**
 * A folder contains IMails objects. 
 * @author Florian Lippisch
 */
public interface IFolder {

	/**
	 * Lists all mails in this folder.
	 * @return
	 */
	public List listMails();
	
	/**
	 * Returns the name of the folder.
	 * @return
	 */
	public String getName();
	
	/**
	 * Returns a list of all folders.
	 * @return
	 */
	public List listFolders();
	
	/**
	 * Adds given folder to children list
	 * @param folder
	 */
	public void addFolder(IFolder folder);
}
