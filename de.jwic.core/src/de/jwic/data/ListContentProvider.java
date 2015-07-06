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
package de.jwic.data;

import java.util.Iterator;
import java.util.List;


/**
 * A simple content provider that provides the data from a List object.
 * 
 * @author Florian Lippisch
 */
public class ListContentProvider<A> implements IContentProvider<A> {
	private static final long serialVersionUID = 1L;
	protected List<A> data;

	/**
	 * Constructor.
	 * @param data
	 */
	public ListContentProvider(List<A> list) {
		this.data = list;
	}
	
	/*
	 * (non-Javadoc)
	 * @see de.jwic.ecolib.tableviewer.IContentProvider#getContentIterator(de.jwic.ecolib.tableviewer.Range)
	 */
	public Iterator<A> getContentIterator(Range range) {
		
		if (range.getMax() != -1) {
			
			int start = range.getStart();
			if (start >= data.size() && data.size() > 0) {
				start = data.size() - 1;
			}
			int end = start + range.getMax();
			if (end > data.size()) {
				end = data.size();
			}
			return data.subList(start, end).iterator();
		} else {
			return data.iterator();
		}
	}
	
	/* (non-Javadoc)
	 * @see de.jwic.ecolib.tableviewer.IContentProvider#getTotalSize()
	 */
	public int getTotalSize() {
		return data.size();
	}
	
	/* (non-Javadoc)
	 * @see de.jwic.ecolib.tableviewer.IContentProvider#getUniqueKey(java.lang.Object)
	 */
	public String getUniqueKey(A object) {
		// this way to generate a unique key is kind a unsafe, because
		// it fails when an entry is inserted or deleted from the list.
		return Integer.toString(data.indexOf(object));
	}
	
	/* (non-Javadoc)
	 * @see de.jwic.controls.combo.IContentProvider#getObjectFromKey(java.lang.String)
	 */
	public A getObjectFromKey(String uniqueKey) {
		if (uniqueKey == null || uniqueKey.length() == 0) {
			return null;
		}
		int idx = Integer.parseInt(uniqueKey);
		if(idx < 0 || idx >= data.size())
			return null;
		return data.get(idx);
	}

	/* (non-Javadoc)
	 * @see de.jwic.ecolib.tableviewer.IContentProvider#getChildren(java.lang.Object)
	 */
	public Iterator<A> getChildren(A object) {
		return null;
	}

	/* (non-Javadoc)
	 * @see de.jwic.ecolib.tableviewer.IContentProvider#hasChildren(java.lang.Object)
	 */
	public boolean hasChildren(A object) {
		return false;
	}
}