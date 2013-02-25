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
 * de.jwic.ecolib.dialogs.BasicDialog
 * Created on 27.04.2006
 * $Id: BasicDialog.java,v 1.2 2008/09/18 18:19:50 lordsam Exp $
 */
package de.jwic.ecolib.dialogs;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import de.jwic.base.ControlContainer;
import de.jwic.base.IControlContainer;
import de.jwic.base.Page;
import de.jwic.base.SessionContext;

/**
 * @author Florian Lippisch
 * @version $Revision: 1.2 $
 */
public abstract class BasicDialog implements Serializable {
	private static final long serialVersionUID = 1L;
	private IControlContainer parent;
	private ControlContainer container;
	private List<DialogListener> listeners = null;
	
	public BasicDialog(IControlContainer parent) {
		this.parent = parent;
	}
	
	/**
	 * Add a DialogListener.
	 * @param listener
	 */
	public synchronized void addDialogListener(DialogListener listener) {
		if (listeners == null) {
			listeners = new ArrayList<DialogListener>();
		}
		listeners.add(listener);
	}
	
	/**
	 * Remove a DialogListener instance.
	 * @param listener
	 */
	public synchronized void removeDialogListener(DialogListener listener) {
		if (listeners != null) {
			listeners.remove(listener);
		}
	}

	/**
	 * Cleanup resources.
	 */
	protected void destroy() {
		
		
		
	}
	
	/**
	 * Notifies the listeners that the dialog was 'finished' 
	 * and closes the dialog.
	 */
	public void finish() {
		if (listeners != null) {
			DialogEvent event = new DialogEvent(this);
			for (Iterator<DialogListener> it = listeners.iterator(); it.hasNext(); ) {
				DialogListener listener = it.next();
				listener.dialogFinished(event);
			}
		}
		close();
	}

	/**
	 * Notifies the listeners that the dialog was 'aborted' 
	 * and closes the dialog.
	 */
	public void abort() {
		if (listeners != null) {
			DialogEvent event = new DialogEvent(this);
			for (Iterator<DialogListener> it = listeners.iterator(); it.hasNext(); ) {
				DialogListener listener = it.next();
				listener.dialogAborted(event);
			}
		}
		close();
	}

	/**
	 * 
	 */
	private void close() {

		SessionContext sc = parent.getSessionContext();
		if (sc.getTopControl() == container) {
			sc.popTopControl();
		}
		container.destroy();
		destroy();
		
	}

	public Page openAsPage() {
		
		Page page = new Page(parent);
		
		createControls(page);
		parent.getSessionContext().pushTopControl(page);
		container = page;
		
		return page;
	}
	
	/**
	 * Create the content.
	 * @param container
	 */
	protected abstract void createControls(IControlContainer container);
	

}
