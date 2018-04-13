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
	
	/**
	 * @param container
	 * @param name
	 */
	public SlickGridDemo(IControlContainer container) {
		super(container);

		SlickGrid<CostData> slickGrid = new SlickGrid<>(this, "sg");
		slickGrid.setWidth(982);
		slickGrid.setHeight(300);
		slickGrid.getOptions().setEditable(true);
		slickGrid.getOptions().setCreatePreHeaderPanel(true);
		slickGrid.getOptions().setShowPreHeaderPanel(true);
		slickGrid.getOptions().setCreateFooterRow(true);
		slickGrid.getOptions().setShowFooterRow(true);
		
		SlickGridModel<CostData> model = slickGrid.getModel();
		
		setupColumns(model);
		setupData(model);
	}
	
	/**
	 * @param model
	 */
	private void setupData(SlickGridModel<CostData> model) {
		List<CostData> pojos = new ArrayList<>();		
		pojos.add(new CostData("Contractor Service", "John Deer", true, false, true, 45, "Hourly", 100, 200, 300, 400));
		pojos.add(new CostData("Contractor Service", "Jane Doe", false, true, false, 105.5, "Hourly", 500, 500, 750, 1000));
		pojos.add(new CostData("Contractor Service", "Michael Buffalo", false, false, false, 5600, "Monthly", 5600, 5600, 5600, 5600));
		pojos.add(new CostData("Contractor Travel", "John Deer", true, true, true, 1, "Cost $", 0, 0, 3400, 0));
		pojos.add(new CostData("Contractor Travel", "Jane Doe", false, true, true, 1, "Cost $", 1200, 0, 0, 4500));
		
		SlickGridListDataProvider<CostData> provider = new SlickGridListDataProvider<>(pojos);
		model.setDataProvider(provider);
	}

	/**
	 * @param model
	 */
	private void setupColumns(SlickGridModel<CostData> model) {
		SlickGridColumn col = new SlickGridColumn("spendType", "Spend Type", 150);
		col.setToolTip("What's here?");
		col.setEditor(SlickGridColumn.EDITOR_TEXT);
		model.addColumn(col);
		
		col = new SlickGridColumn("itemName", "Item Name", 150);
		col.setToolTip("The name!");
		col.setEditor(SlickGridColumn.EDITOR_LONG_TEXT);
		model.addColumn(col);
		
		col = new SlickGridColumn("internal", "Internal", 60);
		col.setEditor(SlickGridColumn.EDITOR_CHECKBOX);
		col.setFormatter(SlickGridColumn.FORMATTER_CHECKBOX);
		model.addColumn(col);
		
		col = new SlickGridColumn("approved", "Approved", 70);
		col.setEditor(SlickGridColumn.EDITOR_YES_NO);
		col.setFormatter(SlickGridColumn.FORMATTER_CHECKMARK);
		model.addColumn(col);
		
		col = new SlickGridColumn("paid", "Paid", 50);
		col.setEditor(SlickGridColumn.EDITOR_YES_NO);
		col.setFormatter(SlickGridColumn.FORMATTER_YES_NO);
		model.addColumn(col);
		
		col = new SlickGridColumn("rate", "Rate", 50);
		col.setColumnGroup("Rate");
		model.addColumn(col);
		
		col = new SlickGridColumn("uom", "UOM", 100);
		col.setColumnGroup("Rate");
		model.addColumn(col);
		
		col = new SlickGridColumn("may", "May", 100);
		col.setColumnGroup("Q1 FY19");
		col.setEditor(SlickGridColumn.EDITOR_FLOAT);
		col.setCanBeSummedUp(true);
		model.addColumn(col);
		
		col = new SlickGridColumn("june", "June", 100);
		col.setColumnGroup("Q1 FY19");
		col.setEditor(SlickGridColumn.EDITOR_FLOAT);
		col.setCanBeSummedUp(true);
		model.addColumn(col);
		
		col = new SlickGridColumn("july", "July", 100);
		col.setColumnGroup("Q1 FY19");
		col.setEditor(SlickGridColumn.EDITOR_FLOAT);
		col.setCanBeSummedUp(true);
		model.addColumn(col);
		
		col = new SlickGridColumn("august", "August", 100);
		col.setColumnGroup("Q2 FY19");
		col.setEditor(SlickGridColumn.EDITOR_FLOAT);
		col.setCanBeSummedUp(true);
		model.addColumn(col);
	}
}