package de.jwic.demo.chart;

import java.util.ArrayList;
import java.util.List;

import de.jwic.base.IControlContainer;
import de.jwic.controls.chart.api.ChartInconsistencyException;
import de.jwic.controls.chart.api.ValueListDataset;
import de.jwic.controls.chart.api.ValueListDatasetModel;
import de.jwic.controls.chart.impl.LineChart;
import de.jwic.demo.chart.util.DataModelCreator;

public class LineChartDemo extends ChartDemo<LineChart, ValueListDatasetModel> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1899059941525891198L;

	public LineChartDemo(IControlContainer container) throws ChartInconsistencyException {
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
			for (Double in : set.getData()) {
				TableElement el = new TableElement();
				el.setTitle(model.getLabels().get(i));
				el.setFillColor(set.getFillColor());
				el.setHighlightColor(set.getHighlightColor());
				el.setValue(in.toString());
				elements.add(el);
				i++;
			}

		}

		return elements;

	}

	@Override
	protected LineChart createChart(ValueListDatasetModel model) {

		LineChart chart = new LineChart(this, "chart", model);
		chart.getConfiguration().setCustomTooltip(true);
		chart.getConfiguration()
			.setCustomTooltipGenerator("JWic.controls.Chart.customTooltipGenerators.standardList");

		return chart;
	}

	@Override
	protected void addElementToTheChart(TableElement element) throws ChartInconsistencyException {
		model.addDataToModel(element.getTitle(), 1, Double.valueOf(element.getValue()));

	}

	@Override
	protected void updateElementInChart(TableElement selectedTableElement) throws ChartInconsistencyException {
		model.changeDataByModel(selectedTableElement.getTitle(), 1, 5D);

	}

	@Override
	protected void deleteElementFromChart(TableElement selectedTableElement) throws ChartInconsistencyException {
		model.removeDataFromModel(selectedTableElement.getTitle());
	}

	@Override
	protected void changeFillColor(String color) throws ChartInconsistencyException {
		model.changeFillColor(1, color);

	}

	@Override
	protected void changeHighColor(String color) throws ChartInconsistencyException {
		model.changeHightlightColor(1, color);

	}

}
