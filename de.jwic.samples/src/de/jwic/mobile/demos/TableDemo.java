/**
 * 
 */
package de.jwic.mobile.demos;

import java.util.ArrayList;
import java.util.List;

import de.jwic.base.Control;
import de.jwic.base.ControlContainer;
import de.jwic.base.IControlContainer;
import de.jwic.controls.tableviewer.DefaultTableRenderer;
import de.jwic.controls.tableviewer.TableViewer;
import de.jwic.demo.tbv.DemoTask;
import de.jwic.demo.tbv.DemoTaskContentProvider;
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

		final ControlContainer container = new ControlContainer(controlContainer, "controlContainer");

		final TableViewer table = new TableViewer(container, "table1");
		DemoTaskContentProvider contentProvider = new DemoTaskContentProvider(createDemoData());
		table.setContentProvider(contentProvider);
		table.setMobile(true);

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

}
