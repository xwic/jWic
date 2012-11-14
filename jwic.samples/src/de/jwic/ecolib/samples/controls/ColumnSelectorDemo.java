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
 * de.jwic.ecolib.samples.controls.ListElementSelectorDemo
 * Created on Feb 10, 2010
 * $Id: ColumnSelectorDemo.java,v 1.1 2011/06/06 12:13:19 lordsam Exp $
 */
package de.jwic.ecolib.samples.controls;

import de.jwic.base.ControlContainer;
import de.jwic.base.IControlContainer;
import de.jwic.ecolib.controls.coledit.ColumnSelector;
import de.jwic.ecolib.controls.coledit.ColumnSelectorEvent;
import de.jwic.ecolib.controls.coledit.ColumnStub;
import de.jwic.ecolib.controls.coledit.IColumnSelectorListener;

/**
 *
 * @author lippisch
 */
public class ColumnSelectorDemo extends ControlContainer {

	private ColumnSelectorResult result;
	
	/**
	 * @param container
	 * @param name
	 */
	public ColumnSelectorDemo(IControlContainer container, String name) {
		super(container, name);


		ColumnSelector selector = new ColumnSelector(this, "selector");
		selector.setImmediateUpdate(true);
		
		selector.addColumn(new ColumnStub(true, "Number", "Sales Order: Number", "nr"));
		selector.addColumn(new ColumnStub(true, "Customer", "Sales Order : Customer : Name", "name"));
		selector.addColumn(new ColumnStub(true, "Country", "Sales Order: Customer : Country", "country"));
		selector.addColumn(new ColumnStub(false, "City", "Sales Order: Customer : City", "city"));
		selector.addColumn(new ColumnStub(false, "Amount", "The total bookings amount in USD", "amount"));
		selector.addColumn(new ColumnStub(false, "Status", "", "status"));
		selector.addColumn(new ColumnStub(false, "Created At", "The date the element was created.", "createdAt"));
		selector.addColumn(new ColumnStub(false, "Created From", "This is obviously the person (or system) who created " +
				"this element. A further description seems not required.", "createdFrom"));
		selector.addColumn(new ColumnStub(false, "Product Group", "The Product Group", "amount"));
		
		selector.addColumnSelectorListener(new IColumnSelectorListener() { 
			public void columnsUpdated(ColumnSelectorEvent event) {
				result.requireRedraw();
			}
		});
		
		result = new ColumnSelectorResult(this, "result", selector);
		
	}

}
