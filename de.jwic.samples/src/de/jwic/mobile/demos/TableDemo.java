/**
 * 
 */
package de.jwic.mobile.demos;

import java.util.ArrayList;
import java.util.List;

import de.jwic.base.Control;
import de.jwic.base.ControlContainer;
import de.jwic.base.IControlContainer;
import de.jwic.controls.tableviewer.MobileTableRenderer;
import de.jwic.controls.tableviewer.TableColumn;
import de.jwic.controls.tableviewer.TableModel;
import de.jwic.controls.tableviewer.TableViewer;
import de.jwic.demo.tbv.DemoTask;
import de.jwic.demo.tbv.DemoTaskContentProvider;
import de.jwic.demo.tbv.LabelProvider;
import de.jwic.mobile.MobileDemoModule;

/**
 * @author vedad
 *
 */
public class TableDemo extends MobileDemoModule {

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

		final TableViewer table = new TableViewer(container, "table1");
		DemoTaskContentProvider contentProvider = new DemoTaskContentProvider(createDemoData());
		table.setContentProvider(contentProvider);
		table.setTableLabelProvider(new LabelProvider());
		table.setTableRenderer(new MobileTableRenderer());

		TableModel model = table.getModel();

		model.setSelectionMode(TableModel.SELECTION_SINGLE);
		model.setColumnBtnText("Columns Button");
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

}
