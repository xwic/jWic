package de.jwic.demo.chart;

import java.util.ArrayList;
import java.util.List;

import de.jwic.base.IControlContainer;
import de.jwic.controls.chart.api.ValueListDataset;
import de.jwic.controls.chart.api.ValueListDatasetModel;
import de.jwic.controls.chart.api.exception.ChartInconsistencyException;
import de.jwic.controls.chart.impl.line.LineChart;
import de.jwic.demo.chart.util.DataModelCreator;

public class LineChartDemo extends ChartDemo<LineChart, ValueListDatasetModel> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1899059941525891198L;

	public LineChartDemo(IControlContainer container)
			throws ChartInconsistencyException {
		super(container);

	}

	@Override
	protected ValueListDatasetModel createModel() {

		return DataModelCreator.getValueListDatasetModel();
	}

	@Override
	protected List<TableElement> convertChartModelToTableElements() {
		List<TableElement> elements = new ArrayList<TableElement>();

		for (ValueListDataset set : model.getDatasets()) {
			int i = 0;
			for (String in : set.getData()) {
				TableElement el = new TableElement();
				el.setTitle(model.getLabels().get(i));
				el.setValue(in);
				elements.add(el);
				i++;
			}

		}

		return elements;

	}

	@Override
	protected LineChart createChart(ValueListDatasetModel model) {

		return new LineChart(this, "chart", model);
	}

	@Override
	protected void addElementToTheChart(TableElement element)
			throws ChartInconsistencyException {
		// TODO Auto-generated method stub

	}

	@Override
	protected void updateElementInChart(TableElement selectedTableElement)
			throws ChartInconsistencyException {
		// TODO Auto-generated method stub

	}

	@Override
	protected void deleteElementFromChart(TableElement selectedTableElement)
			throws ChartInconsistencyException {
		// TODO Auto-generated method stub

	}

}
