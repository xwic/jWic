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
 * Created on 04.11.2004
 */
package de.jwic.controls;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.Stack;
import java.util.StringTokenizer;

import javax.swing.tree.TreeNode;

import de.jwic.base.Control;
import de.jwic.base.IControlContainer;
import de.jwic.events.ElementSelectedEvent;
import de.jwic.events.ElementSelectedListener;

/**
 * A TreeControl is a tree that displays TreeNode objects that can be clicked.
 * 
 * $Id: TreeControl.java,v 1.2 2008/09/17 15:19:53 lordsam Exp $
 * @version $Revision: 1.2 $
 * @author JBornemann
 */
public class TreeControl extends Control {

	private static final long serialVersionUID = 1L;

	/** Click action for clicking on a node's title */
	public final static String ACTION_CLICK = "click";
	public final static String ACTION_SELECT = "select";
	public final static String ACTION_DESELECT = "deselect";
	public final static String ACTION_EXPAND = "expand";
	public final static String ACTION_COLLAPSE = "collapse";
	
	/**
	 * Select the clicked node. If selected nodes exist before the sel is cleard.
	 * If the node has children it is expanded.
	 * ElemententSelectedEvent is dispatched.
	 * This is the default click mode.
	 */
	public final static int CLICK_MODE_SELECT_EXPAND = 0;
	/**
	 * Select or deselect the clicked node.
	 * ElemententSelectedEvent is dispatched.
	 * Allows multi select. 
	 */
	public final static int CLICK_MODE_MULTI_SELECT_DESELECT = 1;
	/**
	 * Only ElemententSelectedEvent is dispatched.
	 */
	public final static int CLICK_MODE_DISPATCH_ONLY = 2;
	/**
	 * Select the clicked node. If selected nodes exist before the sel is cleard.
	 * ElemententSelectedEvent is dispatched.
	 */
	public final static int CLICK_MODE_SELECT = 3;

	protected int clickMode = CLICK_MODE_SELECT_EXPAND;
	/** Set of nodeID String of selected TreeNodes */
	protected HashSet<String> selected = new HashSet<String>();
	/** Set of nodeID String of expanded TreeNodes */
	protected HashSet<String> expanded = new HashSet<String>();
	/** Root TreeNode of this tree */
	protected TreeNode rootNode = null;
	/** List of listender to inform */
	protected List<ElementSelectedListener> selectionListeners = null;
	/** Render root node */
	protected boolean renderRootNode = true; 

	
	/**
	 * @param container
	 */
	public TreeControl(IControlContainer container) {
		super(container, null);
	}
	/**
	 * @param container
	 * @param name
	 */
	public TreeControl(IControlContainer container, String name) {
		super(container, name);
	}

	/**
	 * Sets the root node.
	 * @param node
	 */
	public void setRootNode(TreeNode node) {
		rootNode = node;
		
		if (!renderRootNode && !rootNode.isLeaf()) {
			// root node is not displayed, so expand root node
			expand("0");
		}
		requireRedraw();
	}
	
	/**
	 * Returns the root node.
	 * @return
	 */
	public TreeNode getRootNode() {
		return rootNode;
	}

	/**
	 * Returns the TreeNode found by its nodeID.
	 * The root node's nodeID is "0".
	 * Root node's first child's nodeID is "0-0";
	 * @param nodeID
	 * @return
	 */
	public TreeNode getNode(String nodeID) {
		
		if (nodeID.equals("0")) {
			// return root
			return rootNode;
		} else {
			// remove root String "0-"
			nodeID = nodeID.substring(2);
		}
		
		TreeNode node = rootNode;
		for (Enumeration<?> en = new StringTokenizer(nodeID, "-"); en.hasMoreElements();) {
			String token = (String)en.nextElement();
			if (!token.equals("-")) {
				int idx = Integer.parseInt(token);
				node = node.getChildAt(idx);
			}
		}
		return node;
	}

	public static String getNodeID(TreeNode node) {
	    String key = null;
	    for (TreeNode parent = node.getParent(); parent != null; parent = node.getParent()) {
	        int idx = parent.getIndex(node);
	        if (key == null) {
	            key = String.valueOf(idx);
	        } else {
	            key = String.valueOf(idx) + "-" + key;
	        }
	        node = parent;
	    }
	    
	    return key == null ? "0" : "0-" + key;
	}
	
	/* (non-Javadoc)
	 * @see de.jwic.base.IControl#actionPerformed(java.lang.String, java.lang.String)
	 */
	public void actionPerformed(String actionId, String parameter) {
		
		if (ACTION_CLICK.equals(actionId)) {
			// node will be clicked
			click(parameter);
		} else if (ACTION_SELECT.equals(actionId)) {
			// node will be selected
			select(parameter);
		} else if (ACTION_DESELECT.equals(actionId)) {
			// node will be deselected
			deselect(parameter);
		} else if (ACTION_EXPAND.equals(actionId)) {
			// node will be expanded
			expand(parameter);
		} else if (ACTION_COLLAPSE.equals(actionId)) {
			// node will be expanded
			collapse(parameter);
		}
	}

	/**
	 * Click the node.
	 * @param nodeID
	 */
	public void click(String nodeID) {
		
		switch (clickMode) {
			
			case CLICK_MODE_SELECT_EXPAND : {
				doSelectExpand(this, nodeID);
				break;
			}
			
			case CLICK_MODE_SELECT : {
				doSelect(this, nodeID);
				break;
			}

			case CLICK_MODE_MULTI_SELECT_DESELECT : {
				doMultiSelect(this, nodeID);
				break;
			}
			
			case CLICK_MODE_DISPATCH_ONLY : {
				// The event is dispatched, without selecting the node.
				// This is a somewhat strange behaivior, but it must remain for 
				// compatibility reasons. 
				sendElementSelectedEvent(nodeID);
				break;
			}
		}

		
	}

	/**
	 * Do the multi select.
	 * @param tree
	 * @param nodeID
	 */
	public static void doMultiSelect(TreeControl tree, String nodeID) {
		if (!tree.selected.contains(nodeID)) {
			// select the node if it is not selected
			tree.select(nodeID);
		} else {
			// deselect selected node
			tree.deselect(nodeID);
		}
	}

	/**
	 * Do the select and expand.
	 * @param tree
	 * @param nodeID
	 */
	public static void doSelectExpand(TreeControl tree, String nodeID) {
		doSelect(tree, nodeID);
		if (!tree.getNode(nodeID).isLeaf()) {
			// node isn't a leaf, so expand it
			tree.expand(nodeID);
		}
	}

	/**
	 * Do the select and expand.
	 * @param tree
	 * @param nodeID
	 */
	public static void doSelect(TreeControl tree, String nodeID) {
		// select and expand the node
		if (tree.selected.size() > 0) {
			// remove proevious selection
			tree.clearSelection();
		}
		tree.select(nodeID);
	}

	/**
	 * Clear the selected elements.
	 */
	public void clearSelection() {
		selected.clear();
	}
	
	/**
	 * Select the node.
	 * @param nodeID
	 */
	public void select(String nodeID) {
		selected.add(nodeID);
		// dispatched ElementedSelectedEvent
		sendElementSelectedEvent(nodeID);
		setRequireRedraw(true);
	}

	/**
	 * Deselect the node.
	 * @param nodeID
	 */
	public void deselect(String nodeID) {
		selected.remove(nodeID);
		setRequireRedraw(true);
	}

	/**
	 * Expand the node.
	 * @param nodeID
	 */
	public void expand(String nodeID) {
		expanded.add(nodeID);
		setRequireRedraw(true);
	}
	/**
	 * Expands the node and all its parents. 
	 * @param nodeID
	 */
	public void expandAll(String nodeID) {
		// expand all its parents
		String parentID = null;
		for (Enumeration<?> en = new StringTokenizer(nodeID, "-"); en.hasMoreElements();) {
			String token = (String)en.nextElement();
			if (parentID != null) {
				parentID += "-" + token;
			} else {
				parentID = token;
			}
			// expand parent
			expand(parentID);
		}
	}
	/**
	 * Collapse the node.
	 * @param nodeID
	 */
	public void collapse(String nodeID) {
		expanded.remove(nodeID);
		setRequireRedraw(true);
	}
	/**
	 * Returns an Iterator of TreeControlNode objects that hold the TreeNode
	 * for rendering. 
	 * @return
	 */
	public Iterator<?> getEntries() {
		return new TreeEntryIterator(this, rootNode);
	}
	/**
	 * Returns the Set of the selected nodeID Strings.
	 * @return
	 */
	public Set<String> getSelected() {
		return selected;
	}
	/**
	 * Returns the Set of the expanded nodeID Strings.
	 * @return
	 */
	public Set<String> getExpanded() {
		return expanded;
	}
	/** 
	 * Sets the clicked mode.
	 * CLICK_MODE_SELECT_EXPAND and CLICK_MODE_MULTI_SELECT_DESELECT are supported.
	 * @return Returns the clickMode.
	 */
	public int getClickMode() {
		return clickMode;
	}
	/**
	 * Return the clicked mode.
	 * CLICK_MODE_SELECT_EXPAND and CLICK_MODE_MULTI_SELECT_DESELECT are supported.
	 * @param clickMode
	 */
	public void setClickMode(int clickMode) {
		this.clickMode = clickMode;
	}
	/**
	 * Register a listener that will be notified when node will be selected.
	 * @param listener
	 */
	public void addElementSelectedListener(ElementSelectedListener listener) {
		if (selectionListeners == null) {
			selectionListeners = new ArrayList<ElementSelectedListener>();
		}
		selectionListeners.add(listener);
	}
	/**
	 * Remove a listener.
	 * @param listener
	 */
	public void removeElementSelectedListener(ElementSelectedListener listener) {
		if (selectionListeners != null) {
			selectionListeners.remove(listener);
		}
	}
	/**
	 * Send the element selected event to the registerd listeners.
	 */
	protected void sendElementSelectedEvent(String nodeID) {
		
		if (selectionListeners != null) {
			ElementSelectedEvent e = new ElementSelectedEvent(this, nodeID);
			for (Iterator<ElementSelectedListener> it = selectionListeners.iterator(); it.hasNext(); ) {
				ElementSelectedListener osl = it.next();
				osl.elementSelected(e);
			}
		}

	}	
	/**
	 * If true (default) the root node is rendered.
	 * @return renderRootNode
	 */
	public boolean isRenderRootNode() {
		return renderRootNode;
	}
	/**
	 * Returns if the root node is renderd.
	 * @param renderRootNode
	 */
	public void setRenderRootNode(boolean renderRootNode) {
		this.renderRootNode = renderRootNode;
		
		// set root node again to check that it is expanded
		if (rootNode != null) {
			setRootNode(rootNode);
		}
	}
}

/**
 * TreeEntryIterator provides an Iterator of TreeEntry objects.
 */
class TreeEntryIterator implements Iterator<Object> {

	TreeControl treeControl = null;
	/** Holds the path of TreeEntry */
	Stack<TreeEntry> path = new Stack<TreeEntry>();
	/** Next TreeNode that will be return */ 
	TreeNode nextNode = null;
	
	/**
	 * NodeIterator constructor.
	 * @param rootNode
	 */
	public TreeEntryIterator(TreeControl treeControl, TreeNode rootNode) {
		this.treeControl = treeControl;
		nextNode = rootNode;
		
		if (!treeControl.isRenderRootNode()) {
			// hide root node, don't return it in method next()
			next();
		}
	}
	/* (non-Javadoc)
	 * @see java.util.Iterator#remove()
	 */
	public void remove() {
		throw new UnsupportedOperationException();
	}
	/* (non-Javadoc)
	 * @see java.util.Iterator#hasNext()
	 */
	public boolean hasNext() {
		return nextNode != null;
	}
	/* (non-Javadoc)
	 * @see java.util.Iterator#next()
	 */
	public Object next() {

		if (hasNext()) {
			
			String nodeID = "0";
			int level = 0;
			boolean isLast = true;
			if (parent() != null) {
				// use session
				nodeID = parent().nodeID + "-" + (parent().curr - 1);
				level = path.size();
				isLast = parent().curr >= parent().node.getChildCount();
			}
			
			// encapsulate the TreeNode
			TreeEntry entry = new TreeEntry();
			entry.setNode(nextNode);
			entry.setParent(parent());
			entry.setNodeID(nodeID);
			entry.setSelected(treeControl.selected.contains(nodeID));
			entry.setExpanded(treeControl.expanded.contains(nodeID));
			entry.setLast(isLast);
			entry.setLevel(level);
			
			// check if new parent needs to be created
			if (parent() == null || (level > 0 && entry.isExpanded() && nextNode.getChildCount() > 0)) {
				// create new parent
				path.push(entry);
			}

			// find next node
			nextNode = null;
			while (path.size() > 0 && nextNode == null) {
				if (treeControl.expanded.contains(parent().nodeID) && parent().curr < parent().node.getChildCount()) {
					// next child exists
					nextNode = parent().node.getChildAt(parent().curr++);
				} else {
					// end of session is reached, remove it from path
					path.pop();
				}
			}
			
			// return current TreeControlNode
			return entry;
		}
		
		return null;
	}
	/**
	 * Returns the current parent.
	 * Null is returned if no parent exists.
	 * @return
	 */
	private TreeEntry parent() {
		if (path.size() > 0) { 
			return path.lastElement();
		}
		
		return null;
	}
	
}

