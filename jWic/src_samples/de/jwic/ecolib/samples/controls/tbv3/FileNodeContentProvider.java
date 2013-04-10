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
 * de.jwic.ecolib.samples.controls.tbv.DemoTaskContentProvider
 * Created on 15.03.2007
 * $Id: FileNodeContentProvider.java,v 1.2 2008/09/18 18:19:41 lordsam Exp $
 */
package de.jwic.ecolib.samples.controls.tbv3;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import de.jwic.ecolib.tableviewer.IContentProvider;
import de.jwic.ecolib.tableviewer.Range;

/**
 * Provides the content in form of FileTreeNode objects.
 * @author Florian Lippisch
 */
public class FileNodeContentProvider implements IContentProvider {

	private FileTreeNode root;
	private List<FileTreeNode> rootList = new ArrayList<FileTreeNode>();
	private boolean showRootElement = true;
	
	/**
	 * @param list
	 */
	public FileNodeContentProvider(FileTreeNode root) {
		this.root = root;
		rootList.add(root);
	}
	
	/* (non-Javadoc)
	 * @see de.jwic.ecolib.tableviewer.defaults.ListContentProvider#getUniqueKey(java.lang.Object)
	 */
	public String getUniqueKey(Object object) {
		FileTreeNode node = (FileTreeNode)object;
		return node.getKey();
	}

	/* (non-Javadoc)
	 * @see de.jwic.ecolib.treeviewer.ITreeContentProvider#getChildren(java.lang.Object)
	 */
	public Iterator<Serializable> getChildren(Object object) {
		FileTreeNode node = (FileTreeNode)object;
		return node.children();
	}

	/* (non-Javadoc)
	 * @see de.jwic.ecolib.treeviewer.ITreeContentProvider#getRootIterator()
	 */
	public Iterator getContentIterator(Range range) {
		return showRootElement ? rootList.iterator() : root.children();
	}

	/* (non-Javadoc)
	 * @see de.jwic.ecolib.treeviewer.ITreeContentProvider#hasChildren(java.lang.Object)
	 */
	public boolean hasChildren(Object object) {
		FileTreeNode node = (FileTreeNode)object;
		return node.hasChildren();
	}
	/* (non-Javadoc)
	 * @see de.jwic.ecolib.tableviewer.IContentProvider#getTotalSize()
	 */
	public int getTotalSize() {
		return 1;
	}
}
