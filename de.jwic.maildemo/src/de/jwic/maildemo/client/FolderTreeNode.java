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
package de.jwic.maildemo.client;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;

import javax.swing.tree.TreeNode;

import de.jwic.maildemo.api.IFolder;

/**
 * This class defines the FolderTreeNode
 *
 * @author Aron Cotrau
 */
public class FolderTreeNode implements TreeNode, Serializable {

	private List childs = null;
	private IFolder folder = null;
	private TreeNode parent = null;
	
	/** Map Iterator to Enumeration */
	class ItEnum implements Enumeration {
		private Iterator it = null;
		
		public ItEnum(Iterator iterator) {
			it = iterator;
		}
		
		public boolean hasMoreElements() {
			return it.hasNext();
		}

		public Object nextElement() {
			return it.next();
		}
	}
	
	/**
	 * @param myParent
	 * @param myFolder
	 */
	public FolderTreeNode(TreeNode myParent, IFolder myFolder) {
		parent = myParent;
		folder = myFolder;
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

	/* (non-Javadoc)
	 * @see javax.swing.tree.TreeNode#getAllowsChildren()
	 */
	public boolean getAllowsChildren() {
		return false;
	}

	/* (non-Javadoc)
	 * @see javax.swing.tree.TreeNode#getChildAt(int)
	 */
	public TreeNode getChildAt(int childIndex) {
		return (TreeNode)childs.get(childIndex);
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
	 * Returns the folder.
	 * @return
	 */
	public IFolder getFolder() {
		return folder;
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
	 * @see javax.swing.tree.TreeNode#getParent()
	 */
	public TreeNode getParent() {
		return parent;
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

	/**
	 * Load the list of subfolders.
	 *
	 */
	private void loadChilds() {
		childs = new ArrayList();
		
		List folders = folder.listFolders();
		if (folders.size() > 0) {
			for (Iterator it = folders.iterator(); it.hasNext();) {
				IFolder f = (IFolder) it.next();
				childs.add(new FolderTreeNode(this, f));
			}
		}
	}
	
	/**
	 * Return the name.
	 */
	public String toString() {
		return folder.getName();
	}
}
