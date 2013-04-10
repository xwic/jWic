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
 * de.jwic.samples.controls.PartObjectContentProvider
 * Created on Jan 21, 2010
 * $Id: PartObjectContentProvider.java,v 1.3 2010/04/22 16:00:11 lordsam Exp $
 */
package de.jwic.samples.controls;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import de.jwic.base.Range;
import de.jwic.controls.combo.FilteredRange;
import de.jwic.data.ListContentProvider;

/**
 *
 * @author lippisch
 */
public class PartObjectContentProvider extends ListContentProvider<PartObject> {

	
	/**
	 * @param list
	 */
	public PartObjectContentProvider(List<PartObject> list) {
		super(list);
	}

	/* (non-Javadoc)
	 * @see de.jwic.controls.combo.ListContentProvider#getContentIterator(de.jwic.controls.combo.Range)
	 */
	@Override
	public Iterator<PartObject> getContentIterator(Range range) {
		
		// usually the system would make a database lookup here, but for the sake of the demo...
		
		List<PartObject> tmp = data;
		if (range instanceof FilteredRange) {
			FilteredRange fRange = (FilteredRange)range;
			if (fRange.getFilter() != null) {
				String filter = fRange.getFilter().trim().toLowerCase();
				tmp = new ArrayList<PartObject>();
				for (PartObject po : data) {
					if (po.getTitle().toLowerCase().contains(filter)) {
						tmp.add(po);
						if (fRange.getMax() > 0 && (fRange.getStart() + fRange.getMax() <= tmp.size())) {
							break; 
						}
					}
				}
			} 
		}
		if (range.getMax() != -1) {
			
			if (tmp.size() == 0) {
				return tmp.iterator();
			}
			int start = range.getStart();
			if (start >= tmp.size()) {
				start = tmp.size() - 1;
			}
			int end = start + range.getMax();
			if (end > tmp.size()) {
				end = tmp.size();
			}
			return tmp.subList(start, end).iterator();
		} else {
			return tmp.iterator();
		}

	}

}
