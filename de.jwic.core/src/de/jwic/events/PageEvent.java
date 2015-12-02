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
package de.jwic.events;

import de.jwic.base.Dimension;
import de.jwic.base.Event;
import de.jwic.base.Page;

/**
 *
 * @author Developer
 */
public class PageEvent extends Event {
	private static final long serialVersionUID = 1L;
	private final Dimension pageSize;

	/**
	 * @param eventSource
	 */
	public PageEvent(Object eventSource) {
		super(eventSource);
		pageSize = null;
	}

	/**
	 * @param page
	 * @param pageSize
	 */
	public PageEvent(Page page, Dimension pageSize) {
		super(page);
		this.pageSize = pageSize;
	}

	/**
	 * Returns the event source as Page.
	 * @return
	 */
	public Page getPage() {
		return (Page)getEventSource();
	}

	/**
	 * @return the pageSize
	 */
	public Dimension getPageSize() {
		return pageSize;
	}
	
}
