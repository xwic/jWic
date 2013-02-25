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
 * de.jwic.ecolib.tableviewer.PagingControl
 * Created on 13.03.2007
 * $Id: PagingControl.java,v 1.3 2007/04/20 12:46:19 lordsam Exp $
 */
package de.jwic.ecolib.tableviewer;

import java.text.MessageFormat;

import de.jwic.base.Control;
import de.jwic.base.IControlContainer;

/**
 *
 * @author Florian Lippisch
 */
public class PagingControl extends Control {
	private static final long serialVersionUID = 1L;
	private TableModel model;
	private boolean enabled = true;
	
	/**
	 * @param container
	 * @param name
	 */
	public PagingControl(IControlContainer container, String name, TableModel model) {
		super(container, name);
		this.model = model;
	}
	
	/**
	 * goto first row.
	 */
	public void actionFirst() {
		if (enabled) {
			model.pageFirst();
		}
	}
	/**
	 * goto first row.
	 */
	public void actionPrev() {
		if (enabled) {
			model.pagePrev();
		}
	}
	/**
	 * goto first row.
	 */
	public void actionNext() {
		if (enabled) {
			model.pageNext();
		}
	}
	/**
	 * goto first row.
	 */
	public void actionLast() {
		if (enabled) {
			model.pageLast();
		}
	}


	/**
	 * Returns the text displayed as info
	 * @return
	 */
	public String getInfoText() {
		Range range = model.getRange();
		String msg = "{0} to {1} of {2} rows"; // TODO externalize
		String msgNoRows = "no rows available"; // TODO externalize
		
		int count = model.getLastRowRenderCount();
		int start = range.getStart() + 1;
		int end = range.getStart() + count;
		int total = model.getContentProvider().getTotalSize(); 
		
		if (count == 0) {
			return msgNoRows;
		}
		
		return MessageFormat.format(msg, new Object[] { new Integer(start), new Integer(end), new Integer(total)});
	}

	/**
	 * @return the enabled
	 */
	public boolean isEnabled() {
		return enabled;
	}

	/**
	 * @param enabled the enabled to set
	 */
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	
}
