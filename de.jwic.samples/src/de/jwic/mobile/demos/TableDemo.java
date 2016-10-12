/**
 * 
 */
package de.jwic.mobile.demos;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import de.jwic.base.Control;
import de.jwic.base.ControlContainer;
import de.jwic.base.IControlContainer;
import de.jwic.controls.tableviewer.MobileTableRenderer;
import de.jwic.controls.tableviewer.TableColumn;
import de.jwic.controls.tableviewer.TableModel;
import de.jwic.controls.tableviewer.TableModelAdapter;
import de.jwic.controls.tableviewer.TableModelEvent;
import de.jwic.controls.tableviewer.TableViewer;
import de.jwic.demo.tbv.DemoTask;
import de.jwic.demo.tbv.DemoTaskContentProvider;
import de.jwic.demo.tbv.LabelProvider;
import de.jwic.events.ElementSelectedEvent;
import de.jwic.events.ElementSelectedListener;
import de.jwic.mobile.MobileDemoModule;

/**
 * @author vedad
 *
 */
public class TableDemo extends MobileDemoModule {

	private static final long serialVersionUID = 1L;
	
	private TableViewer table;
	private DemoTaskContentProvider contentProvider;

	/**
	 * @param title
	 */
	public TableDemo() {
		super("Table Demo");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.jwic.mobile.MobileDemoModule#createPage(de.jwic.base.
	 * IControlContainer)
	 */
	@Override
	public Control createPage(IControlContainer controlContainer) {
		final ControlContainer container = new ControlContainer(controlContainer, "container");

		table = new TableViewer(container, "table1");
		
		final TableModel model = table.getModel();
		
		contentProvider = new DemoTaskContentProvider(createDemoData());
		table.setContentProvider(contentProvider);
		table.setTableLabelProvider(new LabelProvider());
		table.setTableRenderer(new MobileTableRenderer());
		table.setWidth(700);
		table.setHeight(200);

		model.setMaxLines(-1); 
		model.addTableModelListener(new TableModelAdapter() {
			private static final long serialVersionUID = 1L;
			public void columnSelected(TableModelEvent event) {
				handleSorting(event.getTableColumn());
			}
		});
		model.setSelectionMode(TableModel.SELECTION_SINGLE);
		
		createColumns(table);

		return container;
	}

	private List<DemoTask> createDemoData() {
		List<DemoTask> data = new ArrayList<DemoTask>();

		data.add(new DemoTask("Implement Demo", "Sam", 0));
		DemoTask task = new DemoTask("Write Docu", "Mark", 0);
		data.add(task);
		data.add(new DemoTask("Setup buildserver", "Ronny", 20));
		data.add(new DemoTask("Update jwic homepage", "?", 0));
		data.add(new DemoTask("Unknown", "", 0));
		data.add(new DemoTask("Change default implementation", "Sam", 10));
		data.add(new DemoTask("Evaluate library XYZ for relevance", "Mark", 50));

		return data;
	}

	/**
	 * 
	 */
	private void createColumns(TableViewer viewer) {

		TableModel model = viewer.getModel();
		// add Columns
		TableColumn col = new TableColumn("Check");
		col.setWidth(20);
		col.setUserObject("done");
		model.addColumn(col);

		col = new TableColumn("Task");
		col.setUserObject("title");
		col.setWidth(250);
		model.addColumn(col);

		col = new TableColumn("Owner");
		col.setUserObject("owner");
		col.setWidth(120);
		model.addColumn(col);

		col = new TableColumn("Complete");
		col.setUserObject("completed");
		col.setWidth(80);
		model.addColumn(col);

	}
	
	/**
	 * Change the sort icon.
	 * @param tableColumn
	 */
	protected void handleSorting(TableColumn tableColumn) {
		
		if (tableColumn.getSortIcon() == TableColumn.SORT_ICON_NONE) {
			// clear all columns
			for (Iterator<TableColumn> it = table.getModel().getColumnIterator(); it.hasNext(); ) {
				TableColumn col = it.next();
				col.setSortIcon(TableColumn.SORT_ICON_NONE);
			}
		}
		boolean up = true;
		switch (tableColumn.getSortIcon()) {
		case TableColumn.SORT_ICON_NONE: 
			tableColumn.setSortIcon(TableColumn.SORT_ICON_UP);
			break;
		case TableColumn.SORT_ICON_UP:
			tableColumn.setSortIcon(TableColumn.SORT_ICON_DOWN);
			up = false;
			break;
		case TableColumn.SORT_ICON_DOWN:
			// once sorted, the list can not be displayed in the
			// original order as we sort the original table,
			// therefor loosing the original order.
			tableColumn.setSortIcon(TableColumn.SORT_ICON_UP);
			//tableColumn.setSortIcon(TableColumn.SORT_ICON_NONE);
			break;
		}
		
		// do the sort
		contentProvider.sortData((String)tableColumn.getUserObject(), up);
		
		table.setRequireRedraw(true);
		
	}

}
