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
 * $Id: FileTreeNode.java,v 1.2 2008/09/17 15:19:43 lordsam Exp $
 */
package de.jwic.samples.filebrowser;

import java.io.File;
import java.io.FileFilter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;

import javax.swing.tree.TreeNode;

/**
 * @author Florian Lippisch
 * @version $Revision: 1.2 $
 */
@SuppressWarnings("unchecked")
public class FileTreeNode implements TreeNode, Serializable {

	File file = null;
	List<FileTreeNode> childs = null;
	TreeNode parent = null;
	
	/** Map Iterator to Enumeration */
	class ItEnum implements Enumeration {

		private Iterator<FileTreeNode> it = null;
		public ItEnum(Iterator<FileTreeNode> iterator) {
			it = iterator;
		}
		
		public boolean hasMoreElements() {
			return it.hasNext();
		}

		public Object nextElement() {
			return it.next();
		}
		
	}
	
	public FileTreeNode(TreeNode myParent, File myFile) {
		parent = myParent;
		file = myFile;
	}
	
	/* (non-Javadoc)
	 * @see javax.swing.tree.TreeNode#getChildAt(int)
	 */
	public TreeNode getChildAt(int childIndex) {
		return childs.get(childIndex);
	}

	/* (non-Javadoc)
	 * @see javax.swing.tree.TreeNode#getChildCount()
	 */
	public int getChildCount() {
		if (childs == null) {
			loadChilds();
		}
		return childs.size();
	}

	/**
	 * Returns the file.
	 * @return
	 */
	public File getFile() {
		return file;
	}
	
	/* (non-Javadoc)
	 * @see javax.swing.tree.TreeNode#getParent()
	 */
	public TreeNode getParent() {
		return parent;
	}

	/* (non-Javadoc)
	 * @see javax.swing.tree.TreeNode#getIndex(javax.swing.tree.TreeNode)
	 */
	public int getIndex(TreeNode node) {
		if (childs == null) {
			loadChilds();
		}
		return childs.indexOf(node);
	}

	/* (non-Javadoc)
	 * @see javax.swing.tree.TreeNode#getAllowsChildren()
	 */
	public boolean getAllowsChildren() {
		return false;
	}

	/* (non-Javadoc)
	 * @see javax.swing.tree.TreeNode#isLeaf()
	 */
	public boolean isLeaf() {
		if (childs == null) {
			loadChilds();
		}
		return childs.size() == 0;
	}

	/* (non-Javadoc)
	 * @see javax.swing.tree.TreeNode#children()
	 */
	public Enumeration children() {
		if (childs == null) {
			loadChilds();
		}
		return new ItEnum(childs.iterator());
	}

	/**
	 * Load the list of subdirectories.
	 *
	 */
	private void loadChilds() {
		childs = new ArrayList<FileTreeNode>();
		if (file.isDirectory()) {
			File[] subdirs = file.listFiles(new FileFilter () {
				public boolean accept(File pathname) {
					return pathname.isDirectory();
				}
			});
			if (subdirs != null) {
				for (int i = 0; i < subdirs.length; i++) {
					childs.add(new FileTreeNode(this, subdirs[i]));
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
	
}
