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
 * de.jwic.samples.controls.Folder
 * Created on Apr 16, 2010
 * $Id: Folder.java,v 1.1 2010/04/22 16:00:11 lordsam Exp $
 */
package de.jwic.samples.controls;

import java.io.File;
import java.io.FileFilter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * This is a simple wrapper for a directory that is displayed
 * in the Tree demo.
 * 
 * @author Lippisch
 */
public class Folder implements Serializable {

	private File file;
	private List<Folder> subFolders = null;

	/**
	 * Constructor.
	 * @param file
	 */
	public Folder(File file) {
		super();
		this.file = file;
	}

	/**
	 * Construct Folder from absolute path name.
	 * @param file
	 */
	public Folder(String pathName) {
		super();
		this.file = new File(pathName);
	}

	/**
	 * Returns the name of the folder.
	 * @return
	 */
	public String getName() {
		return file.getName();
	}
	
	/**
	 * Returns list of sub folders.
	 * @return
	 */
	public List<Folder> getSubFolders() {
		if (subFolders == null) {
			loadFolderList();
		}
		return subFolders;
	}
	
	/**
	 * Returns true if this folder has no sub folders.
	 * @return
	 */
	public boolean isLeaf() {
		if (subFolders == null) {
			loadFolderList();
		}
		return subFolders.isEmpty();
	}
	
	/**
	 * Initialize the folder list.
	 */
	private void loadFolderList() {
		List<Folder> folders = new ArrayList<Folder>();
		if (file.isDirectory()) {
			File[] files = file.listFiles(new FileFilter() { 
				/* (non-Javadoc)
				 * @see java.io.FileFilter#accept(java.io.File)
				 */
				public boolean accept(File f) {
					return f.isDirectory();
				}
			});
			for (File f : files) {
				folders.add(new Folder(f));
			}
		}
		this.subFolders = folders;
	}
	
	/**
	 * For the sake of simplicity, return the absolute path as key.
	 * @return
	 */
	public String getUniqueId() {
		return file.getAbsolutePath();
	}
	
}
