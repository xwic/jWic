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
 * de.jwic.ecolib.samples.controls.ColumnSelectorResult
 * Created on 06.06.2011
 * $Id: ColumnSelectorResult.java,v 1.1 2011/06/06 12:13:19 lordsam Exp $
 */
package de.jwic.ecolib.samples.controls;

import java.util.List;

import de.jwic.base.Control;
import de.jwic.base.IControlContainer;
import de.jwic.ecolib.controls.coledit.ColumnSelector;
import de.jwic.ecolib.controls.coledit.ColumnStub;

/**
 * Simple control that is rendering the columns in the given order.
 * In a real application, you would apply the columnStub data to your own
 * table/column model.
 * @author lippisch
 */
public class ColumnSelectorResult extends Control {

	private final ColumnSelector selector;

	/**
	 * @param container
	 * @param name
	 */
	public ColumnSelectorResult(IControlContainer container, String name, ColumnSelector selector) {
		super(container, name);
		this.selector = selector;
		
	}

	/**
	 * @return
	 * @see de.jwic.ecolib.controls.coledit.ColumnSelector#getColumns()
	 */
	public List<ColumnStub> getColumns() {
		return selector.getColumns();
	}

	
	
}
