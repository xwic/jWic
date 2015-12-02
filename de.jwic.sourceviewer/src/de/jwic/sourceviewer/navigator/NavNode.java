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
package de.jwic.sourceviewer.navigator;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;

import javax.swing.tree.TreeNode;

import de.jwic.base.ImageRef;
import de.jwic.sourceviewer.model.ContainerElement;
import de.jwic.sourceviewer.model.FileElement;
import de.jwic.sourceviewer.model.Folder;
import de.jwic.sourceviewer.model.Group;
import de.jwic.sourceviewer.model.NavigationElement;
import de.jwic.sourceviewer.model.Project;
import de.jwic.sourceviewer.model.SourceFolder;
import de.jwic.sourceviewer.model.SourcePackage;

/**
 * Encapsulates NavigationElements.
 * 
 * @author Florian Lippisch
 */
public class NavNode implements TreeNode, Serializable {
	
	private NavigationElement element = null;
	private List childs = null;
	private boolean initialized = false;
	private TreeNode parent = null;
	
	private String name = "";
	private ImageRef image = null;
	
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
	 * Constructor.
	 * @param element
	 */
	public NavNode(NavigationElement element, NavNode parent) {
		this.parent = parent;
		this.element = element;
		this.name = element.getDisplayName();
		
		if (name.length() == 0) {
			name = "[unnamed]";
		}
		
		image = new ImageRef("/jwic/gfx/clear.gif", 16, 16);
		if (element instanceof Project) {
			image = new ImageRef("icons/prj_obj.gif");
		} else if (element instanceof SourceFolder) {
			image = new ImageRef("icons/packagefolder_obj.gif");
		} else if (element instanceof Group) {
			image = new ImageRef("icons/workingsets.gif");
		} else if (element instanceof SourcePackage) {
			image = new ImageRef("icons/package_obj.gif");
		} else if (element instanceof Folder) {
			image = new ImageRef("icons/fldr_obj.gif");
		} else if (element instanceof FileElement) {
			FileElement fe = (FileElement)element;
			image = fe.getType().getImage();
		}
	
		
	}

	/**
	 * Initialize and build the child list.
	 *
	 */
	private void initChilds() {
		initialized = true;
		childs = new ArrayList();
		if (element instanceof ContainerElement) {
			ContainerElement cont = (ContainerElement)element;
			for (Iterator it = cont.getChilds().iterator(); it.hasNext();) {
				NavigationElement child = (NavigationElement)it.next();
				childs.add(new NavNode(child, this));
			}
		}
	}
	
	/* (non-Javadoc)
	 * @see javax.swing.tree.TreeNode#children()
	 */
	public Enumeration children() {
		if (!initialized) {
			initChilds();
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
		if (!initialized) {
			initChilds();
		}
		return (TreeNode)childs.get(childIndex);
	}

	/* (non-Javadoc)
	 * @see javax.swing.tree.TreeNode#getChildCount()
	 */
	public int getChildCount() {
		if (!initialized) {
			initChilds();
		}
		return childs.size();
	}

	/* (non-Javadoc)
	 * @see javax.swing.tree.TreeNode#getIndex(javax.swing.tree.TreeNode)
	 */
	public int getIndex(TreeNode node) {
		if (!initialized) {
			initChilds();
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
		if (!initialized) {
			initChilds();
		}
		return childs.size() == 0;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return name;
	}

	/**
	 * @return the image
	 */
	public ImageRef getImage() {
		return image;
	}

	/**
	 * @return the element
	 */
	public NavigationElement getElement() {
		return element;
	}
	
}
