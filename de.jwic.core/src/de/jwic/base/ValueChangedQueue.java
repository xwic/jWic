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
package de.jwic.base;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import de.jwic.events.ValueChangedEvent;
import de.jwic.events.ValueChangedListener;

/**
 * The ValueChangedQueue is used by the dispatcher to collect the 
 * ValueChangedEvents that occured during the field update. The
 * dispatcher will fire the events when all fields are updated.
 * 
 * @author Florian Lippisch
 * @version $Revision: 1.2 $
 */
public class ValueChangedQueue {

	private List<ListenerEvents> eventQueue = null;
	
	/**
	 * Container for the events. 
	 */
	private class ListenerEvents {
		ValueChangedListener[] listeners;
		ValueChangedEvent event;
		public ListenerEvents(ValueChangedListener[] l, ValueChangedEvent e) {
			this.listeners = l;
			this.event = e;
		}
	}
	
	/**
	 * Adds the listeners for the specified event to the event queue. The queue is processed
	 * after all fields have been updated.
	 * @param listeners
	 * @param event
	 */
	public void valueChanged(ValueChangedListener[] listeners, ValueChangedEvent event) {
		
		if (eventQueue == null) {
			eventQueue = new ArrayList<ListenerEvents>();
		}
		
		eventQueue.add(new ListenerEvents(listeners, event));
		
	}
	
	/**
	 * Process the event queue.
	 *
	 */
	public void processQueue() {
		
		if (eventQueue != null) {
			for (Iterator<ListenerEvents> it = eventQueue.iterator(); it.hasNext(); ) {
				ListenerEvents task = it.next();
				if (task.listeners != null) {
					for (int i = 0; i < task.listeners.length; i++) {
						task.listeners[i].valueChanged(task.event);
					}
				}
			}
		}
		
	}
	
}
