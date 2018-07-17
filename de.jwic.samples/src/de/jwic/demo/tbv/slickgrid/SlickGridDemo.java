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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import de.jwic.base.ControlContainer;
import de.jwic.base.IControlContainer;
import de.jwic.controls.Button;
import de.jwic.controls.InputBox;
import de.jwic.controls.slickgrid.KeyTitlePair;
import de.jwic.controls.slickgrid.SlickGrid;
import de.jwic.controls.slickgrid.SlickGridChange;
import de.jwic.controls.slickgrid.SlickGridColumn;
import de.jwic.controls.slickgrid.SlickGridDefaultColumnValueProvider;
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
	
	private boolean addCommentColumn = false;
	
	private List<KeyTitlePair> spendTypes = new ArrayList<>();
	private List<KeyTitlePair> uoms = new ArrayList<>();
	
	int idIndex = 1;
	
	/**
	 * @param container
	 * @param name
	 */
	public SlickGridDemo(IControlContainer container) {
		super(container);
		
		spendTypes.add(new KeyTitlePair("", ""));
		spendTypes.add(new KeyTitlePair("cs", "Contractor Service"));
		spendTypes.add(new KeyTitlePair("ct", "Contractor Travel"));
		
		uoms.add(new KeyTitlePair("h", "Hourly"));
		uoms.add(new KeyTitlePair("w", "Weekly"));
		uoms.add(new KeyTitlePair("m", "Monthly"));
		uoms.add(new KeyTitlePair("c$", "Cost $"));

		//
		// THE GRID
		//
		
		slickGrid = new SlickGrid<>(this, "sg");
		slickGrid.getOptions().setWidth(945);
		slickGrid.getOptions().setHeight(300);
		slickGrid.getOptions().setEditable(true);
		slickGrid.getOptions().setAutoEdit(false);
		slickGrid.getOptions().setShowQuickFilters(true);
		slickGrid.getOptions().setShowColumnGrouping(true);
		slickGrid.getOptions().setShowTotalsRow(true);
		
		SlickGridModel<CostData> model = slickGrid.getModel();
		
		setupColumns(model);
		setupData(model);
		
		model.addElementSelectedListener(l -> {
			String text = "from event: " + l.getElement();
			text += "; from control: " + slickGrid.getSelectedElementUniqueId();
			text += "\n" + ibSelectionData.getText();
			ibSelectionData.setText(text);
		});
		
		slickGrid.getOptions().setSendCellChangesImmediatelyToServer(true);
		model.addCellChangedListener(l -> {
			ibEditData.setText(ibEditData.getText() + "\n" + l.getChange());
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
			dp.getList().add(new CostData(idIndex++, "cs", "New Guy", "new guy's comment", false, false, true, new Date(), 250, "w", 6700, 2300, 100, 40d));
			slickGrid.reloadData();
		});
		
		Button btAddNewColumn = new Button(this, "btAddNewColumn");
		btAddNewColumn.setTitle("Toggle 'Comment' Column");
		btAddNewColumn.addSelectionListener(l -> {
			this.addCommentColumn = !this.addCommentColumn;
			setupColumns(model);
			slickGrid.reloadColumns();
		});
		
		Button btResetData = new Button(this, "btResetData");
		btResetData.setTitle("Reset Data");
		btResetData.addSelectionListener(l -> {
			setupData(model);
			slickGrid.reloadData();
		});
		
		Button btSetValuesInBackend = new Button(this, "btSetValuesInBackend");
		btSetValuesInBackend.setTitle("Add 10 to May values");
		btSetValuesInBackend.addSelectionListener(l -> {
			for (Iterator<CostData> it = model.getDataProvider().getDataIterator() ; it.hasNext() ;) {
				CostData cd = it.next();
				cd.setMay(cd.getMay() + 10d);
			}
			
			slickGrid.reloadData();
		});
		
	}
	
	/**
	 * @param model
	 */
	private void setupData(SlickGridModel<CostData> model) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
		List<CostData> pojos = new ArrayList<>();
		try {
			pojos.add(new CostData(idIndex++, "cs", "John Deer", "comm1", true, false, true, sdf.parse("02-Feb-2018"), 45, "h", 100, 200, 300, 400d));
			pojos.add(new CostData(idIndex++, "cs", "Jane Doe", "comm2", false, true, false, sdf.parse("02-Nov-2018"), 105, "w", 500.5, 500, 750.8, 1000d));
			pojos.add(new CostData(idIndex++, "cs", "Michael Buffalo", "", false, false, false, sdf.parse("23-Feb-2018"), 5600, null, 5600.75, 5600, 5600, null));
			pojos.add(new CostData(idIndex++, "ct", "John Deer", "", true, true, true, sdf.parse("25-Dec-2018"), 1, "c$", 0, 0, 3400, 0d));
			pojos.add(new CostData(idIndex++, null, "Jane Doe", "something here", false, true, true, sdf.parse("12-Feb-2018"), 1, "m", 1200, 0, 0, 4500d));
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		
		SlickGridListDataProvider<CostData> provider = new SlickGridListDataProvider<CostData>(pojos) {
			@Override
			public String getUniqueIdentifier(CostData obj) {
				return String.valueOf(obj.getId());
			}
			
			@Override
			public boolean disableEditing(CostData obj, SlickGridColumn<CostData> column) {
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
		model.clearColumns();
		
		SlickGridColumn<CostData> col = new SlickGridColumn<>("spendType", "Spend Type", 150);
		col.setToolTip("What's here?");
		col.setFormatter(SlickGridColumn.Formatters.KEY_TITLE);
		col.setEditor(SlickGridColumn.Editors.KEY_TITLE_DROP_DOWN);
		col.setValueProvider(new SlickGridDefaultColumnValueProvider<CostData>() {
			@Override
			public List<KeyTitlePair> getKeyTitleValues() {
				return spendTypes;
			}
		});
		model.addColumn(col);
		
		col = new SlickGridColumn<>("itemName", "Item Name", 150);
		col.setToolTip("The name!");
		col.setEditor(SlickGridColumn.Editors.TEXT);
		model.addColumn(col);
		
		if (addCommentColumn) {
			col = new SlickGridColumn<>("comment", "Comment", 150);
			col.setEditor(SlickGridColumn.Editors.LONG_TEXT);
			model.addColumn(col);
		}
		
		col = new SlickGridColumn<>("internal", "Internal", 60);
		col.setEditor(SlickGridColumn.Editors.CHECKBOX);
		col.setFormatter(SlickGridColumn.Formatters.CHECKBOX);
		model.addColumn(col);
		
		col = new SlickGridColumn<>("approved", "Approved", 70);
		col.setEditor(SlickGridColumn.Editors.YES_NO);
		col.setFormatter(SlickGridColumn.Formatters.CHECKMARK);
		model.addColumn(col);
		
		col = new SlickGridColumn<>("paid", "Paid", 55);
		col.setEditor(SlickGridColumn.Editors.YES_NO);
		col.setFormatter(SlickGridColumn.Formatters.YES_NO);
		model.addColumn(col);
		
		col = new SlickGridColumn<>("startDate", "Start Date", 100);
		col.setEditor(SlickGridColumn.Editors.DATE);
		col.setFormatter(SlickGridColumn.Formatters.DATE);
		col.setDateFormat("dd/mm/yy");
		model.addColumn(col);
		
		col = new SlickGridColumn<>("rate", "Rate", 50);
		col.setEditor(SlickGridColumn.Editors.INTEGER);
		col.setFormatter(SlickGridColumn.Formatters.DECIMAL);
		col.setColumnGroup("Rate");
		model.addColumn(col);
		
		col = new SlickGridColumn<>("uom", "UOM", 100);
		col.setColumnGroup("Rate");
		col.setFormatter(SlickGridColumn.Formatters.KEY_TITLE);
		col.setEditor(SlickGridColumn.Editors.KEY_TITLE_DROP_DOWN);
		col.setValueProvider(new SlickGridDefaultColumnValueProvider<CostData>() {
			@Override
			public List<KeyTitlePair> getKeyTitleValues() {
				return uoms;
			}
		});
		model.addColumn(col);
		
		col = new SlickGridColumn<>("may", "May", 100);
		col.setColumnGroup("Q1 FY19");
		col.setEditor(SlickGridColumn.Editors.FLOAT);
		col.setFormatter(SlickGridColumn.Formatters.DECIMAL);
		col.setCanBeSummedUp(true);
		model.addColumn(col);
		
		col = new SlickGridColumn<>("june", "June", 100);
		col.setColumnGroup("Q1 FY19");
		col.setEditor(SlickGridColumn.Editors.FLOAT);
		col.setFormatter(SlickGridColumn.Formatters.DECIMAL);
		col.setCanBeSummedUp(true);
		col.setTotalLabel("Total: ");
		model.addColumn(col);
		
		col = new SlickGridColumn<>("july", "July", 100);
		col.setColumnGroup("Q1 FY19");
		col.setEditor(SlickGridColumn.Editors.FLOAT);
		col.setFormatter(SlickGridColumn.Formatters.DECIMAL);
		col.setCanBeSummedUp(true);
		col.setTotalLabel("Sum: ");
		model.addColumn(col);
		
		col = new SlickGridColumn<>("august", "August", 100);
		col.setColumnGroup("Q2 FY19");
		col.setEditor(SlickGridColumn.Editors.FLOAT);
		col.setFormatter(SlickGridColumn.Formatters.DECIMAL);
		col.setCanBeSummedUp(true);
		model.addColumn(col);
	}
}