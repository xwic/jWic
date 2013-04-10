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
 * de.jwic.samples.controls.FolderContentProvider
 * Created on Apr 16, 2010
 * $Id: FolderContentProvider.java,v 1.1 2010/04/22 16:00:11 lordsam Exp $
 */
package de.jwic.samples.controls;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import de.jwic.base.Range;
import de.jwic.data.IContentProvider;

/**
 * Simple content provider for TreeDemo.
 * @author Lippisch
 */
public class FolderContentProvider implements IContentProvider<Folder>, Serializable {

	private Folder root = null;
	
	/**
	 * Constructor.
	 * @param root
	 */
	public FolderContentProvider(Folder root) {
		super();
		this.root = root;
	}

	/* (non-Javadoc)
	 * @see de.jwic.data.IContentProvider#getChildren(java.lang.Object)
	 */
	public Iterator<Folder> getChildren(Folder folder) {
		return folder.getSubFolders().iterator();
	}

	/* (non-Javadoc)
	 * @see de.jwic.data.IContentProvider#getContentIterator(de.jwic.base.Range)
	 */
	public Iterator<Folder> getContentIterator(Range range) {
		List<Folder> childs = root.getSubFolders();
		if (range.getMax() > 0) {
			if (range.getStart() < childs.size()) {
				int max = Math.min(range.getStart() + range.getMax(), childs.size());
				return childs.subList(range.getStart(), max).iterator();
			} else {
				return new ArrayList<Folder>().iterator(); // return empty iterator
			}
		}
		return childs.iterator();
	}

	/* (non-Javadoc)
	 * @see de.jwic.data.IContentProvider#getObjectFromKey(java.lang.String)
	 */
	public Folder getObjectFromKey(String uniqueKey) {
		return new Folder(uniqueKey);
	}

	/* (non-Javadoc)
	 * @see de.jwic.data.IContentProvider#getTotalSize()
	 */
	public int getTotalSize() {
		return root.getSubFolders().size();
	}

	/* (non-Javadoc)
	 * @see de.jwic.data.IContentProvider#getUniqueKey(java.lang.Object)
	 */
	public String getUniqueKey(Folder object) {
		return object.getUniqueId();
	}

	/* (non-Javadoc)
	 * @see de.jwic.data.IContentProvider#hasChildren(java.lang.Object)
	 */
	public boolean hasChildren(Folder folder) {
		return !folder.isLeaf();
	}

}
