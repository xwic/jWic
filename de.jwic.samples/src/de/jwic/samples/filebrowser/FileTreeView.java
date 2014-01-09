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
 * de.jwic.samples.filebrowser.FileTreeView
 * Created on 25.05.2005
 * $Id: FileTreeView.java,v 1.6 2008/09/17 15:19:43 lordsam Exp $
 */
package de.jwic.samples.filebrowser;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.Serializable;
import java.util.Iterator;
import java.util.Set;
import java.util.Stack;

import de.jwic.base.Control;
import de.jwic.base.IControlContainer;
import de.jwic.controls.ScrollableContainer;
import de.jwic.controls.TreeControl;
import de.jwic.events.ElementSelectedEvent;
import de.jwic.events.ElementSelectedListener;

/**
 * @author Florian Lippisch
 * @version $Revision: 1.6 $
 */
public class FileTreeView extends ScrollableContainer {

	private TreeControl tree = null;
	protected DirectoryModel model = null;

	/**
	 * @param control
	 * @param string
	 */
	public FileTreeView(IControlContainer container, String name) {
		super(container, name);
		
		tree = new TreeControl(this);
		tree.addElementSelectedListener(new TreeSelectionController());
		tree.setClickMode(TreeControl.CLICK_MODE_SELECT);
		
	}
	
	/**
	 * Set the model.
	 * @param model
	 */
	public void initView(DirectoryModel model, File rootFile) {
		this.model = model;
		tree.setRootNode(new FileTreeNode( null, rootFile ));
		tree.expand("0"); // expand the root node
		model.addPropertyChangeListener(new ModelObserver() );
		selectTreeNode(model.getDirectory());
	}
	
	/* (non-Javadoc)
	 * @see de.jwic.base.Control#isRequireRedraw()
	 */
	public boolean isRequireRedraw() {
		// FLI: This fixes an issue with the scroll area on the firefox. 
		// A final solution is pending...
		
		if (super.isRequireRedraw()) {
			return true;
		}
		for (Iterator<Control> it = getControls(); it.hasNext(); ) {
			Control control = it.next();
			if (control.isRequireRedraw()) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Select the file element in the tree. 
	 * @param file
	 */
	public void selectTreeNode(File file) {

		if (file.isDirectory()) {
			// update tree
			
			// dont update if specified file is already selected
			Set<?> selected = tree.getSelected(); 
			if (selected.size() == 1) {
				FileTreeNode currentNode = (FileTreeNode) tree.getNode((String)selected.iterator().next());
				if (currentNode.getFile().equals(file)) {
					// nothing to do.
					return;
				}
			}
			
			FileTreeNode rootNode = (FileTreeNode)tree.getRootNode(); 
			File root = rootNode.getFile();
			Stack<File> path = new Stack<File>();
			path.add(file);
			File currFile = file;
			while (!currFile.equals(root)) {
				currFile = currFile.getParentFile();
				path.add(currFile);
			}

			tree.clearSelection();
			// now walk down the path and expand/select
			FileTreeNode currNode = rootNode;
			String nodeId = "";
			while (!path.isEmpty()) {
				File f = path.pop();
				FileTreeNode fnode = new FileTreeNode(null, f); 
				if (nodeId.length() == 0) { // root 
					nodeId = "0";
				} else {
					int i = currNode.getIndex(fnode);
					if (i == -1) {
						// this should never happen.
						break;
					}
					nodeId += "-" + i;
					currNode = (FileTreeNode)currNode.getChildAt(i);
				}
				if (path.isEmpty()) {
					tree.select(nodeId);
				} else {
					tree.expand(nodeId);
				}
			}
			
		}
	}

	/**
	 * Handle selections in the tree 
	 */
	class TreeSelectionController implements ElementSelectedListener {
		public void elementSelected(ElementSelectedEvent event) {
			
			String nodeid = (String)event.getElement();
			FileTreeNode node = (FileTreeNode)tree.getNode(nodeid);
			model.setDirectory(node.getFile());
			
		}
	}

	/**
	 * Observes the model...
	 */
	private class ModelObserver implements PropertyChangeListener, Serializable {
		public void propertyChange(PropertyChangeEvent evt) {
			selectTreeNode((File)evt.getNewValue());
		} 
	}

	
}
