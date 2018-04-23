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
import de.jwic.controls.Button;
import de.jwic.controls.InputBox;
import de.jwic.controls.slickgrid.SlickGrid;
import de.jwic.controls.slickgrid.SlickGridChange;
import de.jwic.controls.slickgrid.SlickGridColumn;
import de.jwic.controls.slickgrid.SlickGridListDataProvider;
import de.jwic.controls.slickgrid.SlickGridModel;

/**
 * @author Adrian Ionescu
 */
public class SlickGridDemo extends ControlContainer {

	private static final long serialVersionUID = -253541673677874852L;
	private InputBox ibSelectionData;
	private InputBox ibEditData;
	private SlickGrid<CostData> slickGrid;
	
	/**
	 * @param container
	 * @param name
	 */
	public SlickGridDemo(IControlContainer container) {
		super(container);

		//
		// THE GRID
		//
		
		slickGrid = new SlickGrid<>(this, "sg");
		slickGrid.getOptions().setWidth(1035);
		slickGrid.getOptions().setHeight(300);
		slickGrid.getOptions().setEditable(true);
		slickGrid.getOptions().setAutoEdit(false);
		slickGrid.getOptions().showColumnGrouping(true);
		slickGrid.getOptions().showTotalsRow(true);
		
		SlickGridModel<CostData> model = slickGrid.getModel();
		
		setupColumns(model);
		setupData(model);
		
		model.addElementSelectedListener(l -> {
			String text = "from event: " + l.getElement();
			text += "; from control: " + slickGrid.getSelectedElementUniqueId();
			text += "\n" + ibSelectionData.getText();
			ibSelectionData.setText(text);
		});
		
		//
		// TESTING CONTROLS
		//
		
		ibSelectionData = new InputBox(this, "ibSelectionData");
		ibSelectionData.setReadonly(true);
		ibSelectionData.setMultiLine(true);
		ibSelectionData.setWidth(200);
		ibSelectionData.setHeight(150);
		
		ibEditData = new InputBox(this, "ibEditData");
		ibEditData.setReadonly(true);
		ibEditData.setMultiLine(true);
		ibEditData.setWidth(400);
		ibEditData.setHeight(150);
		
		Button btSubmitChanges = new Button(this, "btSubmitChanges");
		btSubmitChanges.setTitle("Submit Changes");
		btSubmitChanges.addSelectionListener(l -> {
			List<SlickGridChange> changes = slickGrid.getChanges();
			StringBuilder sb = new StringBuilder();
			if (changes != null) {
				for (SlickGridChange change : changes) {
					sb.append(change).append("\n");
				}
				ibEditData.setText(sb.toString());
				slickGrid.clearRecordedChanges();
			}
		});
		
		Button btDummy = new Button(this, "btDummy");
		btDummy.setTitle("Dummy - just redraws the SlickGrid");
		btDummy.addSelectionListener(l -> slickGrid.requireRedraw());
		
		Button btAddNewRow = new Button(this, "btAddNewRow");
		btAddNewRow.setTitle("Add New Row");
		btAddNewRow.addSelectionListener(l -> {
			SlickGridListDataProvider<CostData> dp = (SlickGridListDataProvider<CostData>) model.getDataProvider();
			dp.getList().add(new CostData(11, "Contractor Service", "New Guy", false, false, true, 250, "Weekly", 6700, 2300, 100, 40d));
			slickGrid.reloadData();
		});
		
		Button btResetData = new Button(this, "btResetData");
		btResetData.setTitle("Reset Data");
		btResetData.addSelectionListener(l -> {
			setupData(model);
			slickGrid.reloadData();
		});
	}
	
	/**
	 * @param model
	 */
	private void setupData(SlickGridModel<CostData> model) {
		List<CostData> pojos = new ArrayList<>();		
		pojos.add(new CostData(1, "Contractor Service", "John Deer", true, false, true, 45, "Hourly", 100, 200, 300, 400d));
		pojos.add(new CostData(3, "Contractor Service", "Jane Doe", false, true, false, 105.5, "Hourly", 500, 500, 750, 1000d));
		pojos.add(new CostData(5, "Contractor Service", "Michael Buffalo", false, false, false, 5600, "Monthly", 5600, 5600, 5600, null));
		pojos.add(new CostData(7, "Contractor Travel", "John Deer", true, true, true, 1, "Cost $", 0, 0, 3400, 0d));
		pojos.add(new CostData(9, "Contractor Travel", "Jane Doe", false, true, true, 1, "Cost $", 1200, 0, 0, 4500d));
		
		SlickGridListDataProvider<CostData> provider = new SlickGridListDataProvider<CostData>(pojos) {
			@Override
			public String getUniqueIdentifier(CostData obj) {
				return String.valueOf(obj.getId());
			}
			
			@Override
			public boolean disableEditing(CostData obj, SlickGridColumn column) {
				if (obj.getItemName().equalsIgnoreCase("Michael Buffalo")) {
					switch (column.getId()) {
					case "itemName":
					case "internal":
					case "june":
					case "august":
						return true;
					}
				}
				
				if (obj.getItemName().equalsIgnoreCase("Jane Doe")) {
					switch (column.getId()) {
					case "approved":
						return true;
					}
				}
				
				return super.disableEditing(obj, column);
			}
		};
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
		
		col = new SlickGridColumn("paid", "Paid", 55);
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
		col.setTotalLabel("Total: ");
		model.addColumn(col);
		
		col = new SlickGridColumn("july", "July", 100);
		col.setColumnGroup("Q1 FY19");
		col.setEditor(SlickGridColumn.EDITOR_FLOAT);
		col.setCanBeSummedUp(true);
		col.setTotalLabel("Sum: ");
		model.addColumn(col);
		
		col = new SlickGridColumn("august", "August", 100);
		col.setColumnGroup("Q2 FY19");
		col.setEditor(SlickGridColumn.EDITOR_FLOAT);
		col.setCanBeSummedUp(true);
		model.addColumn(col);
	}
}