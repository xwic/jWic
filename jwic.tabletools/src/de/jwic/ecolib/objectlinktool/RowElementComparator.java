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
 * de.jwic.ecolib.objectlinktool.RowElementComparator
 * Created on 13.04.2007
 * $Id: RowElementComparator.java,v 1.1 2007/04/25 09:59:13 lordsam Exp $
 */
package de.jwic.ecolib.objectlinktool;

import java.util.Comparator;

/**
 *
 * @author Florian Lippisch
 */
public class RowElementComparator implements Comparator {

	private int index = 0;
	private boolean invert = false;
	
	/**
	 * Constructor.
	 * @param index
	 */
	public RowElementComparator(int index) {
		this.index = index;
	}

	/**
	 * Constructor.
	 * @param index
	 * @param inverted
	 */
	public RowElementComparator(int index, boolean inverted) {
		this.index = index;
		this.invert = inverted;
	}
	
	/* (non-Javadoc)
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 */
	public int compare(Object o1, Object o2) {
		RowElement r1 = (RowElement)o1;
		RowElement r2 = (RowElement)o2;
		
		int result = 0;
		if (index == -1) { // sort by score
			result = new Double(r2.getScore()).compareTo(new Double(r1.getScore()));
		} else {
			result = r1.getData()[index].compareTo(r2.getData()[index]);
		}
		
		return invert ? -result : result;
	}

}
