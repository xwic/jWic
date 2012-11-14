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
 * de.jwic.sourceviewer.navigator.GroupNavigator
 * Created on 25.04.2007
 * $Id: GroupNavigator.java,v 1.6 2007/05/07 12:04:42 lordsam Exp $
 */
package de.jwic.sourceviewer.navigator;

import java.util.Enumeration;

import javax.swing.tree.TreeNode;

import de.jwic.base.IControlContainer;
import de.jwic.controls.ScrollableContainer;
import de.jwic.controls.TreeControl;
import de.jwic.events.ElementSelectedEvent;
import de.jwic.events.ElementSelectedListener;
import de.jwic.sourceviewer.main.SVModel;
import de.jwic.sourceviewer.main.SVModelAdapter;
import de.jwic.sourceviewer.main.SVModelEvent;
import de.jwic.sourceviewer.model.NavigationElement;

/**
 *
 * @author Florian Lippisch
 */
public class GroupNavigator extends ScrollableContainer {

	private SVModel model;
	private TreeControl tree;
	
	private boolean updateModel = true;
	
	/**
	 * @param container
	 * @param name
	 */
	public GroupNavigator(IControlContainer container, String name, SVModel model) {
		super(container, name);
		this.model = model;
	
		model.addSVModelListener(new SVModelAdapter() {
			public void groupSelected(SVModelEvent event) {
				updateGroup();
			}
			
			public void elementSelected(SVModelEvent event) {
				updateTreeSelection(event.getElement());
			}
		});
		
		createControls();
		
	}
	
	/**
	 * @param element
	 */
	protected void updateTreeSelection(NavigationElement element) {
		
		NavNode node = findNode(tree.getRootNode(), element);
		
		if (null != node) {
			String nodeID = TreeControl.getNodeID(node);
			updateModel = false;
			tree.clearSelection();
			tree.expandAll(nodeID);
			tree.select(nodeID);
		}
	}
	
	private NavNode findNode(TreeNode node, NavigationElement element) {
		if (node instanceof NavNode) {
			NavNode nn = (NavNode)node;
			if (nn.getElement().equals(element)) {
				return nn;
			}
			if (!nn.isLeaf()) {
				for (Enumeration e = nn.children(); e.hasMoreElements(); ) {
					NavNode n = findNode((TreeNode)e.nextElement(), element);
					if (n != null) {
						return n;
					}
				}
			}
		}
		return null;
	}

	/**
	 * 
	 */
	protected void updateGroup() {
		
		tree.setRootNode(new NavNode(model.getCurrentGroup(), null));
		tree.expand("0"); // expand the root node.
		tree.select("0");
	}

	/**
	 * Create the controls..
	 *
	 */
	private void createControls() {
		
		tree = new TreeControl(this, "tree");
		//tree.setRenderRootNode(false);
		tree.setTemplateName("de.jwic.sourceviewer.navigator.TreeControl");
		tree.addElementSelectedListener(new ElementSelectedListener() {
			public void elementSelected(ElementSelectedEvent event) {
				handleSelection((String)event.getElement());
			}
		});
		
		updateGroup();
	}

	/**
	 * @param string
	 */
	protected void handleSelection(String nodeID) {
		
		try {
			NavNode node = (NavNode)tree.getNode(nodeID);
			
			if (updateModel) {
				model.setCurrentElement(node.getElement());
			} else {
				updateModel = true;
			}
		} catch (IndexOutOfBoundsException e) {
			// no such node...
			log.warn("No such node - the tree has probably been updated in the meantime. " + nodeID);
		}
	}

}
