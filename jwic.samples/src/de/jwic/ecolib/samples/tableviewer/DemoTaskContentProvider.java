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
 * $Id: DemoTaskContentProvider.java,v 1.2 2008/09/18 18:20:21 lordsam Exp $
 */
package de.jwic.ecolib.samples.tableviewer;

import java.io.Serializable;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import de.jwic.ecolib.tableviewer.defaults.ListContentProvider;

/**
 * Handles the unique-key generation. A valid unique key is required
 * when the order changes, as it happens due to sorting.
 * @author Florian Lippisch
 */
public class DemoTaskContentProvider extends ListContentProvider {

	/**
	 * @param list
	 */
	public DemoTaskContentProvider(List<DemoTask> list) {
		super(list);
	}
	
	/* (non-Javadoc)
	 * @see de.jwic.ecolib.tableviewer.defaults.ListContentProvider#getUniqueKey(java.lang.Object)
	 */
	public String getUniqueKey(Object object) {
		DemoTask task = (DemoTask)object;
		return Integer.toString(task.id);
	}

	/**
	 * Sort the data. 
	 * @param field
	 */
	public void sortData(final String field, final boolean up) {
		
		Collections.sort(data, new Comparator() {
			/* (non-Javadoc)
			 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
			 */
			public int compare(Object o1, Object o2) {
				DemoTask t1 = (DemoTask)o1;
				DemoTask t2 = (DemoTask)o2;
				int result = 0;
				if (field.equals("done")) {
					if (t1.done != t2.done) {
						result = t1.done ? -1 : 1;
					}
				} else if (field.equals("title")) {
					result = t1.title.compareTo(t2.title);
				} else if (field.equals("owner")) {
					result = t1.owner.compareTo(t2.owner);
				} else if (field.equals("completed")) {
					result = t1.completed - t2.completed;
				}
				return up ? result : -result;
			}
		});
		
	}

	/**
	 * adds the DemoTask to the input list
	 * @param task
	 */
	public void addElement(DemoTask task) {
		data.add(task);
	}
	
	/**
	 * removes the DemoTask from the input list
	 * @param task
	 */
	public void removeElement(DemoTask task) {
		data.remove(task);
	}
	
	/**
	 * @param key
	 * @return the element with the given key
	 */
	public DemoTask getElement(String key) {
		int id = Integer.parseInt(key);
		
		for (Iterator<Serializable> it = data.iterator(); it.hasNext();) {
			DemoTask task = (DemoTask) it.next();
			if (task.id == id) {
				return task;
			}
		}
		
		return null;
	}
}
