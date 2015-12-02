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
/*
 * Created on 05.11.2004
 */
package de.jwic.controls;

import java.util.ArrayList;
import java.util.List;

import javax.swing.tree.TreeNode;

/**
 * TreeEntry encapsulate a TreeNode for rendering.
 * 
 * $Id: TreeEntry.java,v 1.2 2008/09/17 15:19:52 lordsam Exp $
 * @version $Revision: 1.2 $
 * @author JBornemann
 */
public class TreeEntry {

	protected TreeEntry parent = null;
	protected TreeNode node = null;
	protected String nodeID = null;
	protected int level = 0;
	protected int curr = 0;
	protected boolean selected = false;
	protected boolean expanded = false;
	protected boolean last = false;
	/**
	 * @return Returns the parent.
	 */
	public TreeEntry getParent() {
		return parent;
	}
	/**
	 * @param parent The parent to set.
	 */
	public void setParent(TreeEntry parent) {
		this.parent = parent;
	}
	/**
	 * Returns the TreeEntry parents of this.
	 * List is empty if no parent exists.
	 * @return
	 */
	public List<TreeEntry> getPath() {
		ArrayList<TreeEntry> path = new ArrayList<TreeEntry>();
		TreeEntry entry = getParent();
		while (entry != null) {
			path.add(0, entry);
			entry = entry.getParent();
		}
		return path;
	}
	/**
	 * @return Returns the expanded.
	 */
	public boolean isExpanded() {
		return expanded;
	}
	/**
	 * @param expanded The expanded to set.
	 */
	public void setExpanded(boolean expanded) {
		this.expanded = expanded;
	}
	/**
	 * Returns the int level: no parent is 0, one parent is 1 a.s.o. 
	 * @return Returns the level.
	 */
	public int getLevel() {
		return level;
	}
	/**
	 * Sets the level: no parent is 0, one parent is 1 a.s.o. 
	 * @param level The level to set.
	 */
	public void setLevel(int level) {
		this.level = level;
	}
	/**
	 * @return Returns the TreeNode.
	 */
	public TreeNode getNode() {
		return node;
	}
	/**
	 * @param node The TreeNode to set.
	 */
	public void setNode(TreeNode node) {
		this.node = node;
	}
	/**
	 * @return Returns the nodeID.
	 */
	public String getNodeID() {
		return nodeID;
	}
	/**
	 * @param nodeID The nodeID to set.
	 */
	public void setNodeID(String nodeID) {
		this.nodeID = nodeID;
	}
	/**
	 * @return Returns the selected.
	 */
	public boolean isSelected() {
		return selected;
	}
	/**
	 * @param selected The selected to set.
	 */
	public void setSelected(boolean selected) {
		this.selected = selected;
	}
	/**
	 * Returns if this is the last of its parent.
	 * @return boolean.
	 */
	public boolean isLast() {
		return last;
	}
	/**
	 * @param last The last to set.
	 */
	public void setLast(boolean last) {
		this.last = last;
	}
}
