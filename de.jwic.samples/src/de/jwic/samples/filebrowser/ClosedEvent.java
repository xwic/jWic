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
 * de.jwic.samples.filebrowser.ClosedEvent
 * Created on 25.05.2005
 * $Id: ClosedEvent.java,v 1.1 2006/01/16 08:31:31 lordsam Exp $
 */
package de.jwic.samples.filebrowser;

import de.jwic.base.Event;

/**
 * @author Florian Lippisch
 * @version $Revision: 1.1 $
 */
public class ClosedEvent extends Event {

	/**
	 * @param sourceControl
	 */
	public ClosedEvent(Object source) {
		super(source);
	}

}
