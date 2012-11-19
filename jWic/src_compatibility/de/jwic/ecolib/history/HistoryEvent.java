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
 * de.jwic.ecolib.history.HistoryEvent
 * Created on 18.01.2006
 * $Id: HistoryEvent.java,v 1.2 2006/02/23 12:50:21 markwf_123 Exp $
 */
package de.jwic.ecolib.history;

import de.jwic.base.Event;

/**
 * @author Florian Lippisch
 * @version $Revision: 1.2 $
 */
public class HistoryEvent extends Event {

	private static final long serialVersionUID = -1812772733049066247L;
	private Object context = null;
	
	/**
	 * @param eventSource
	 */
	public HistoryEvent(Object eventSource, Object context) {
		super(eventSource);
		this.context = context;
	}

	/**
	 * @return Returns the context.
	 */
	public Object getContext() {
		return context;
	}

}
