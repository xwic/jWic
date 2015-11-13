package de.jwic.demo.chart;

import java.util.List;

import de.jwic.base.ControlContainer;
import de.jwic.base.IControlContainer;
import de.jwic.controls.InputBox;
import de.jwic.controls.Label;
import de.jwic.controls.ListBoxControl;
import de.jwic.controls.chart.api.Chart;
import de.jwic.controls.chart.api.ChartModel;
import de.jwic.controls.tableviewer.TableColumn;
import de.jwic.controls.tableviewer.TableModel;
import de.jwic.controls.tableviewer.TableModelAdapter;
import de.jwic.controls.tableviewer.TableModelEvent;
import de.jwic.controls.tableviewer.TableViewer;
import de.jwic.data.ListContentProvider;
import de.jwic.events.ElementSelectedEvent;
import de.jwic.events.ElementSelectedListener;
import de.jwic.events.SelectionEvent;
import de.jwic.events.SelectionListener;
import de.jwic.samples.controls.propeditor.PropertyEditorView;

/**
 * 
 * @author Karolina Marek (karolina-marek.eu)
 *
 * @date 13.11.2015
 */
public abstract class ChartDemo<T extends Chart, M extends ChartModel> extends
		ControlContainer {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1899059941525891198L;
	private T chart;
	private ListContentProvider<TableElement> contentProvider;
	private TableViewer viewer;
	private InputBox inputBox;
	protected M model;

	public ChartDemo(IControlContainer container) {
		super(container);
		initialize();

	}

	private void initialize() {
		model = createModel();

		this.chart = createChart(model);
		setTemplateName(ChartDemo.class.getName());
		chart.addSelectionListener(new SelectionListener() {

			@Override
			public void objectSelected(SelectionEvent event) {
				String param = event.getEventSource().toString();
				inputBox.setText(param);
			}
		});
		chart.setWidth(400);
		chart.setHeight(400);

		// chart.getModel().addDataToModel("Danny", datasetNumber, value);
		// chart.getModel().removeDataFromModel(label, datasetNumber);
		// chart.getModel().changeDataByModel(label, datasetNumber);

		// chart.getModel().getDatasetAsList().get(0).getData().add("2");

		// Change chart visibility
		createListOfProperties();
		createProperties();
		createTable();
	}

	private void createProperties() {
		PropertyEditorView propEditor = new PropertyEditorView(this,
				"propertyEditor") {
			@Override
			public void loadValues() {
				super.loadValues();
				chart.requireRedraw();
			}
		};
		propEditor.setBean(chart);

	}

	protected abstract T createChart(M model);

	private void createListOfProperties() {
		ListBoxControl lbVisible = new ListBoxControl(this, "btVisible");
		lbVisible.addElement("True", "true");
		lbVisible.addElement("False", "false");
		lbVisible.setSelectedKey(chart.isVisible() ? "true" : "false");
		lbVisible.setChangeNotification(true);
		lbVisible.addElementSelectedListener(new ElementSelectedListener() {
			public void elementSelected(ElementSelectedEvent event) {
				chart.setVisible(event.getElement().equals("true"));
			};
		});
		Label label = new Label(this, "label");
		label.setText("Last selected element on the chart is: ");
		inputBox = new InputBox(this, "inputBox");
	}

	protected abstract M createModel();

	private void createColumns() {

		TableModel model = viewer.getModel();
		// add Columns

		TableColumn col = new TableColumn("Label");
		col.setUserObject("title");
		col.setWidth(250);
		model.addColumn(col);

		col = new TableColumn("value");
		col.setUserObject("value");
		col.setWidth(250);
		model.addColumn(col);

	}

	private void createTable() {
		// create the viewer
		viewer = new TableViewer(this, "table");

		contentProvider = new ListContentProvider<TableElement>(
				convertChartModelToTableElements());
		viewer.setContentProvider(contentProvider);
		viewer.setTableLabelProvider(new LabelProvider());
		viewer.setScrollable(true);
		viewer.setShowStatusBar(true);
		viewer.setResizeableColumns(true);
		viewer.setSelectableColumns(true);
		viewer.setWidth(700);
		viewer.setHeight(250);

		TableModel tableModel = viewer.getModel();
		tableModel.setMaxLines(50); // all

		BarSelectedElementListener listener = new BarSelectedElementListener();

		// add listener to demonstrate sorting/images
		tableModel.addTableModelListener(new TableModelAdapter() {
			public void columnSelected(TableModelEvent event) {

			}
		});
		tableModel.setSelectionMode(TableModel.SELECTION_SINGLE);
		createColumns();

	}

	/**
	 * @return
	 */
	protected abstract List<TableElement> convertChartModelToTableElements();

	private class BarSelectedElementListener implements ElementSelectedListener {
		public void elementSelected(ElementSelectedEvent event) {

			if (event.getElement() == null) {
			} else {
				TableElement task = contentProvider
						.getObjectFromKey((String) event.getElement());
				if (task != null) {

					if (event.isDblClick()) {
						getSessionContext().notifyMessage(
								"Element Selected: " + task.getTitle());
					}
				}
			}
		}
	}

}
