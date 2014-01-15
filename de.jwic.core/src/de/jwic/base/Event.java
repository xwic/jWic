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
 * de.jwic.base.Event
 * Created on 28.08.2005
 * $Id: Event.java,v 1.2 2006/08/14 09:34:58 lordsam Exp $
 */
package de.jwic.base;

import java.io.Serializable;

/**
 * Superclass for events. 
 * 
 * This class replaces the deprecated JWicEvent.
 * 
 * @author Florian Lippisch
 * @version $Revision: 1.2 $
 */
public class Event implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Object eventSource = null;
	
	/**
	 * Constructs a new jWic event.
	 * @param sourceControl
	 */
	public Event(Object eventSource) {
		this.eventSource = eventSource;
	}
	
	/**
	 * @return Returns the eventSource.
	 */
	public Object getEventSource() {
		return eventSource;
	}

}
