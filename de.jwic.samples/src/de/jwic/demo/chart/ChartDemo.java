package de.jwic.demo.chart;

import java.util.List;

import de.jwic.base.ControlContainer;
import de.jwic.base.IControlContainer;
import de.jwic.controls.Button;
import de.jwic.controls.InputBox;
import de.jwic.controls.Label;
import de.jwic.controls.ListBoxControl;
import de.jwic.controls.ToolBar;
import de.jwic.controls.ToolBarGroup;
import de.jwic.controls.actions.Action;
import de.jwic.controls.actions.IAction;
import de.jwic.controls.chart.api.Chart;
import de.jwic.controls.chart.api.ChartInconsistencyException;
import de.jwic.controls.chart.api.ChartModel;
import de.jwic.controls.dialogs.DialogAdapter;
import de.jwic.controls.dialogs.DialogEvent;
import de.jwic.controls.tableviewer.TableColumn;
import de.jwic.controls.tableviewer.TableModel;
import de.jwic.controls.tableviewer.TableViewer;
import de.jwic.demo.ImageLibrary;
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
public abstract class ChartDemo<T extends Chart, M extends ChartModel> extends ControlContainer {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1899059941525891198L;
	protected T chart;
	private TableElementContentProvider contentProvider;
	private TableViewer viewer;
	private InputBox inputBox;
	private InputBox fillColor;
	private InputBox highlightColor;
	protected M model;
	private IAction deleteElement;
	private IAction updateElement;
	private TableElement selectedTableElement;
	private Button addFill;
	private Button addHigh;

	public ChartDemo(IControlContainer container) {
		super(container);
		initialize();

	}

	private void initialize() {
		model = createModel();

		this.chart = createChart(model);
		this.chart.setLegendLocation(Chart.LegendLocation.RIGHT);
		setTemplateName(ChartDemo.class.getName());
		chart.addSelectionListener(new SelectionListener() {

			@Override
			public void objectSelected(SelectionEvent event) {
				String param = event.getEventSource().toString();
				inputBox.setText(param);
			}
		});

		createListOfProperties();
		createProperties();
		createTable();
	}

	private void createProperties() {
		PropertyEditorView propEditor = new PropertyEditorView(this, "propertyEditor") {
			@Override
			public void loadValues() {
				super.loadValues();
				chart.requireRedraw();
			}
		};

		propEditor.setBean(chart.getConfiguration());

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

		fillColor = new InputBox(this, "fillColor");

		highlightColor = new InputBox(this, "highlightColor");

		addFill = new Button(this, "addFill");
		addFill.setTitle("Change");
		addFill.addSelectionListener(new SelectionListener() {

			@Override
			public void objectSelected(SelectionEvent event) {
				fillColor.getText();
				try {
					changeFillColor(fillColor.getText());
				} catch (ChartInconsistencyException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		addHigh = new Button(this, "addHigh");
		addHigh.setTitle("Change");
		addHigh.addSelectionListener(new SelectionListener() {

			@Override
			public void objectSelected(SelectionEvent event) {
				try {
					changeHighColor(highlightColor.getText());
				} catch (ChartInconsistencyException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});
	}

	protected abstract void changeFillColor(String text) throws ChartInconsistencyException;

	protected abstract void changeHighColor(String text) throws ChartInconsistencyException;

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
		viewer = new TableViewer(this, "table");

		contentProvider = new TableElementContentProvider(convertChartModelToTableElements());
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
		tableModel.setSelectionMode(TableModel.SELECTION_SINGLE);
		createColumns();

		ToolBar tb = new ToolBar(this, "toolbar");
		tb.setCssClass("j-toolbar ui-corner-top");
		ToolBarGroup group = tb.addGroup();
		Button btNew = group.addButton();
		btNew.setTitle("Add Chart element");
		btNew.setIconEnabled(ImageLibrary.IMG_ADD);
		btNew.addSelectionListener(new SelectionListener() {
			@Override
			public void objectSelected(SelectionEvent event) {
				addTableElement();
			}
		});

		createActions();
		group.addAction(deleteElement);
		group.addAction(updateElement);
		tableModel.addElementSelectedListener(listener);

	}

	/**
	 * @param task
	 */
	public void refreshActions(TableElement tableElement) {

		deleteElement.setEnabled(tableElement != null);
		updateElement.setEnabled(tableElement != null);
	}

	private void createActions() {

		deleteElement = new Action() {
			public void run() {

				try {
					deleteElementFromChart(selectedTableElement);
					requireRedraw();
				} catch (ChartInconsistencyException e) {
					getSessionContext().notifyMessage(e.getMessage());
				}

			}
		};
		deleteElement.setTitle("Delete");
		deleteElement.setIconEnabled(ImageLibrary.IMG_CROSS);

		updateElement = new Action() {
			public void run() {

				try {
					updateElementInChart(selectedTableElement);
					chart.requireRedraw();
				} catch (ChartInconsistencyException e) {
					getSessionContext().notifyMessage(e.getMessage());
				}

			}

		};
		updateElement.setTitle("Update");
		updateElement.setIconEnabled(ImageLibrary.IMG_LIST_VIEW);

		refreshActions(null);
	}

	protected void addTableElement() {

		AddTableElementDialog dialog = new AddTableElementDialog(viewer.getContainer());
		dialog.addDialogListener(new DialogAdapter() {
			public void dialogFinished(DialogEvent event) {
				AddTableElementDialog dialog = ((AddTableElementDialog) event.getEventSource());
				TableElement element = dialog.getTableElement();
				contentProvider.addElement(element);
				try {
					addElementToTheChart(element);
				} catch (ChartInconsistencyException e) {
					getSessionContext().notifyMessage(e.getMessage());
				}
				viewer.setRequireRedraw(true);
			}

		});
		dialog.openAsPage();

	}

	protected abstract void addElementToTheChart(TableElement element) throws ChartInconsistencyException;

	protected abstract void updateElementInChart(TableElement selectedTableElement) throws ChartInconsistencyException;

	protected abstract void deleteElementFromChart(TableElement selectedTableElement)
			throws ChartInconsistencyException;

	/**
	 * @return
	 */
	protected abstract List<TableElement> convertChartModelToTableElements();

	private class BarSelectedElementListener implements ElementSelectedListener {
		public void elementSelected(ElementSelectedEvent event) {

			if (event.getElement() == null) {
			} else {
				TableElement el = contentProvider.getObjectFromKey((String) event.getElement());
				refreshActions(el);
				selectedTableElement = el;
				if (el != null) {

					if (event.isDblClick()) {
						getSessionContext().notifyMessage("Element Selected: " + el.getTitle());
					}
				}
			}
		}
	}

}
