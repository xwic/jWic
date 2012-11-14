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
 * de.jwic.wap.core.HistoryController
 * Created on 18.01.2006
 * $Id: HistoryController.java,v 1.4 2008/09/18 18:20:32 lordsam Exp $
 */
package de.jwic.ecolib.history;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import de.jwic.base.Control;
import de.jwic.base.IControlContainer;

/**
 * Supports the History functions of a webbrowser. The control can set a 'savePoint' which 
 * is stored as a new 'location' in the browsers history. When the user navigates through the
 * history, the 'savePoint' is selected and the associated HistoryListener is invoked. 
 * 
 * <p>The control depends upon the dhtmlHistory.js library from Brad Neuberg. See
 * the copyright notice in the dhtmlHistory.js file for details.
 * 
 * @author Florian Lippisch
 * @version $Revision: 1.4 $
 */
public class HistoryController extends Control {

	private static final long serialVersionUID = 6161007094915577435L;
	private boolean doAdd = false;
	private String newHistoryLocation = null;
	private List<HistoryEntry> currList = null;
	
	private long idCount = 0;
	private long lastSelection = -1;
	private Map<String, List<HistoryEntry>> historyMap = new HashMap<String, List<HistoryEntry>>();
	private boolean isHandleLocationChange = false;
	
	private class HistoryEntry implements Serializable {
		public HistoryListener listener;
		public Object context = null;
		public HistoryEntry(HistoryListener hisListener, Object ctx) {
			this.listener = hisListener;
			this.context = ctx;
		}
	}
	
	/**
	 * @param container
	 * @param name
	 */
	public HistoryController(IControlContainer container, String name) {
		super(container, name);
	}

	/* (non-Javadoc)
	 * @see de.jwic.base.Control#actionPerformed(java.lang.String, java.lang.String)
	 */
	public void actionPerformed(String actionId, String parameter) {
		if (actionId.equals("locationChange")) {
			if (parameter.length() != 0) {
				lastSelection = Long.parseLong(parameter);
				isHandleLocationChange = true;
				try {
					List<HistoryEntry> listeners = historyMap.get(parameter);
					if (listeners != null) {
						for (Iterator<HistoryEntry> it = listeners.iterator(); it.hasNext(); ) {
							HistoryEntry entry = it.next();
							entry.listener.savepointSelected(new HistoryEvent(this, entry.context));
						}
					}
				} finally {
					isHandleLocationChange = false;
				}
			}
		}
	}
	
	public boolean isDoAdd() {
		return doAdd;
	}

	public String getNewHistoryLocation() {
		return newHistoryLocation;
	}

	/**
	 * Notifier called from the renderer when the history has been added.
	 *
	 */
	public void addHistoryDone() {
		doAdd = false;
		currList = null;
	}

	/**
	 * Set a savepoint. The listeners callback method is called when the user
	 * navigates to the specified savepoint.
	 * 
	 * <p>If the control is currently processing the selection of an savePoint, 
	 * no new savePoint is set and false is returned.</p>
	 * 
	 * @param listener
	 * @param context
	 * @return true if the savePoint has been set
	 */
	public synchronized boolean setSavePoint(HistoryListener listener, Object context) {

		if (isHandleLocationChange) {
			// When a history savePoint is selected, most listeners 'navigate' to the
			// location they have been before. This navigation often leads to a new
			// savePoint that is set. This is mostly unnessasary and destroys the
			// history as it would lead to an immidiatly 'forward'.
			// log.debug("setSavePoint called while a savePoint selection is processed - request ignored.");
			return false;
		} else {
			
			if (lastSelection != -1) {
				// if a new savePoint is set when the user has navigated back in the history,
				// the 'forward' history is lost. This means we can clean up the savePoints 
				// that have an higher ID then the lastSelection.
				for(Iterator<String> it = historyMap.keySet().iterator(); it.hasNext(); ) {
					String s = it.next();
					if (Long.parseLong(s) > lastSelection) {
						it.remove();
					}
				}
				lastSelection = -1;
			}
			if (currList == null) {
				newHistoryLocation = Long.toString(idCount++);
				currList = new ArrayList<HistoryEntry>();
				historyMap.put(newHistoryLocation, currList);
			}
			currList.add(new HistoryEntry(listener, context));
			doAdd = true;
			requireRedraw();
		}
		return true;
	}
	
	
	
}
