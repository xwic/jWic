/*
 * Copyright 2005 jWic Group (http://www.jwic.de)
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
 * de.jwic.ajax.demo.FileTreeNode
 * Created on 28.04.2005
 * $Id: FileTreeNode.java,v 1.2 2008/09/18 18:19:42 lordsam Exp $
 */
package de.jwic.ecolib.samples.controls.tbv3;

import java.io.File;
import java.io.FileFilter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author Florian Lippisch
 * @version $Revision: 1.2 $
 */
public class FileTreeNode implements Serializable {

	private File file = null;
	private List<Serializable> childs = null;
	private String key = "0";
	
	/**
	 * Create a new node with the root key.
	 * @param myFile
	 */
	public FileTreeNode(File myFile) {
		this.file = myFile;
	}
	
	/**
	 * Construct a new node with a specified key.
	 * @param myFile
	 * @param key
	 */
	public FileTreeNode(File myFile, String key) {
		this.file = myFile;
		this.key = key;
	}

	/**
	 * Returns the file.
	 * @return
	 */
	public File getFile() {
		return file;
	}
	
	/* (non-Javadoc)
	 * @see javax.swing.tree.TreeNode#isLeaf()
	 */
	public boolean hasChildren() {
		if (childs == null) {
			loadChilds();
		}
		return childs.size() != 0;
	}

	/* (non-Javadoc)
	 * @see javax.swing.tree.TreeNode#children()
	 */
	public Iterator<Serializable> children() {
		if (childs == null) {
			loadChilds();
		}
		return childs.iterator();
	}

	/**
	 * Load the list of subdirectories.
	 *
	 */
	private void loadChilds() {
		childs = new ArrayList<Serializable>();
		if (file.isDirectory()) {
			File[] subdirs = file.listFiles(new FileFilter () {
				public boolean accept(File pathname) {
					return pathname.isDirectory();
				}
			});
			if (subdirs != null) {
				for (int i = 0; i < subdirs.length; i++) {
					childs.add(new FileTreeNode(subdirs[i], key + "-" + i));
				}
			}
		}
	}
	
	/**
	 * Return the name.
	 */
	public String toString() {
		return file.getName();
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return file.hashCode();
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		}
		if (obj instanceof FileTreeNode) {
			return file.equals(((FileTreeNode)obj).getFile());
		}
		return false;
	}


	/**
	 * @return the key
	 */
	public String getKey() {
		return key;
	}
	
}
