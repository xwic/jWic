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
 * de.jwic.controls.combo.LifeSearchRange
 * Created on Jan 21, 2010
 * $Id: FilteredRange.java,v 1.2 2010/01/26 11:25:17 lordsam Exp $
 */
package de.jwic.controls.combo;

import de.jwic.data.Range;

/**
 *
 * @author lippisch
 */
public class FilteredRange extends Range {
	private static final long serialVersionUID = 1L;
	private String filter = null;

	
	
	public FilteredRange() {
		super();
	}

	public FilteredRange(int start, int max) {
		super(start, max);
	}

	/**
	 * @return the filter
	 */
	public String getFilter() {
		return filter;
	}

	/**
	 * @param filter the filter to set
	 */
	public void setFilter(String filter) {
		this.filter = filter;
	}
	
}
