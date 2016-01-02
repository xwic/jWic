package de.jwic.mobile.demos;

import java.util.ArrayList;
import java.util.List;

import de.jwic.base.Control;
import de.jwic.base.ControlContainer;
import de.jwic.base.IControlContainer;
import de.jwic.mobile.MobileDemoModule;
import de.jwic.mobile.controls.mtable.MColumn;
import de.jwic.mobile.controls.mtable.MTable;
import de.jwic.mobile.controls.mtable.MTableModel;

public class TableDemo extends MobileDemoModule {

	public TableDemo() {
		super("Table Demo");
	}

	@Override
	public Control createPage(IControlContainer controlContainer) {
		final ControlContainer container = new ControlContainer(
				controlContainer);

		final MTable mtable = new MTable(container, "mTable");
		mtable.setModel(createTableModel());
		mtable.setText("table example");

		return container;
	}

	private MTableModel createTableModel() {
		MTableModel tableModel = new MTableModel();
		tableModel.setColumns(createColumns());
		tableModel.setRows(createRows());
		return tableModel;
	}

	private List<MColumn> createColumns() {
		List<MColumn> columns = new ArrayList<MColumn>();
		MColumn column = new MColumn();
		column.setFieldName("name");
		column.setName("Year");
		columns.add(column);
		
		MColumn column2 = new MColumn();
		column2.setFieldName("value");
		column2.setName("Percent");
		columns.add(column2);
		MColumn column3 = new MColumn();
		column3.setFieldName("value2");
		column3.setName("Categorie");
		columns.add(column3);
		return columns;
	}

	private List<Object> createRows() {
		List<Object> obj = new ArrayList<Object>();
		obj.add(new MTableRow("2013", "9,5%", "Food"));
		obj.add(new MTableRow("2014", "1,5%", "Water"));
		return obj;
	}
}
