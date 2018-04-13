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
package de.jwic.demo.tbv.slickgrid;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import de.jwic.base.ControlContainer;
import de.jwic.base.IControlContainer;
import de.jwic.controls.slickgrid.SlickGrid;
import de.jwic.controls.slickgrid.SlickGridColumn;
import de.jwic.controls.slickgrid.SlickGridListDataProvider;
import de.jwic.controls.slickgrid.SlickGridModel;

/**
 * @author Adrian Ionescu
 */
public class SlickGridDemo extends ControlContainer {

	private static final long serialVersionUID = -253541673677874852L;
	
	private SlickGrid<User> slickGrid;
	
	/**
	 * @param container
	 * @param name
	 */
	public SlickGridDemo(IControlContainer container) {
		super(container);

		slickGrid = new SlickGrid<User>(this, "sg");
		slickGrid.setWidth(957);
		slickGrid.setHeight(300);
		slickGrid.getOptions().setEditable(true);
		
		SlickGridModel<User> model = slickGrid.getModel();
		setupColumns(model);
		setupData(model);
	}

	/**
	 * @param model
	 */
	private void setupData(SlickGridModel<User> model) {
		Calendar cal = Calendar.getInstance();
		
		List<User> pojos = new ArrayList<>();		
		pojos.add(new User("ionescu", "Adrian", "Ionescu", "adi@burger.com", cal.getTime(), 23, true, false, true));
		cal.add(Calendar.DAY_OF_YEAR, -3);
		pojos.add(new User("lippisch", "Florian", "Lippisch", "flo@beer.com",  cal.getTime(), 29, false, true, false));
		cal.add(Calendar.DAY_OF_YEAR, -12);
		pojos.add(new User("ronnyp", "Ronny", "Pfretzschner", "ronny@steak.com", cal.getTime(), 16, true, false, false));
		cal.add(Calendar.DAY_OF_YEAR, -450);
		pojos.add(new User("otto", "Daniel", "Ionescu", "dani@cordonbleu.com", cal.getTime(), 26, false, false, false));
		cal.add(Calendar.DAY_OF_YEAR, 100);
		pojos.add(new User("georger", "George", "Morge", "gm@pommes.com", cal.getTime(), 12, true, true, true));
		
		SlickGridListDataProvider<User> provider = new SlickGridListDataProvider<>(pojos);
		model.setDataProvider(provider);
	}

	/**
	 * @param model
	 */
	private void setupColumns(SlickGridModel<User> model) {
		SlickGridColumn col = new SlickGridColumn("username", "Username", 100);
		col.setToolTip("What's here?");
		col.setEditor(SlickGridColumn.EDITOR_TEXT);
		model.addColumn(col);
		
		col = new SlickGridColumn("firstName", "First Name", 150);
		col.setToolTip("The name!");
		col.setEditor(SlickGridColumn.EDITOR_LONG_TEXT);
		model.addColumn(col);
		
		col = new SlickGridColumn("lastName", "Last Name", 150);
		model.addColumn(col);
		
		col = new SlickGridColumn("email", "Email", 150);
		model.addColumn(col);
		
		col = new SlickGridColumn("registrationDate", "Registration Date", 120);
		col.setEditor(SlickGridColumn.EDITOR_DATE);
		col.setDateFormat("dd-MMM-yyyy");
		model.addColumn(col);
		
		col = new SlickGridColumn("age", "Age", 50);
		col.setEditor(SlickGridColumn.EDITOR_INTEGER);
		model.addColumn(col);
		
		col = new SlickGridColumn("retired", "Retired", 60);
		col.setEditor(SlickGridColumn.EDITOR_CHECKBOX);
		col.setFormatter(SlickGridColumn.FORMATTER_CHECKBOX);
		model.addColumn(col);
		
		col = new SlickGridColumn("suspended", "Suspended", 80);
		col.setEditor(SlickGridColumn.EDITOR_YES_NO);
		col.setFormatter(SlickGridColumn.FORMATTER_YES_NO);
		model.addColumn(col);
		
		col = new SlickGridColumn("approved", "Approved", 80);
		col.setEditor(SlickGridColumn.EDITOR_YES_NO);
		col.setFormatter(SlickGridColumn.FORMATTER_CHECKMARK);
		model.addColumn(col);
	}
}