package de.jwic.demo.chart;

import java.util.ArrayList;
import java.util.List;

import de.jwic.base.IControlContainer;
import de.jwic.controls.chart.api.ChartInconsistencyException;
import de.jwic.controls.chart.api.SimpleValueDataset;
import de.jwic.controls.chart.api.SimpleValueDatasetModel;
import de.jwic.controls.chart.impl.CircleChart;
import de.jwic.demo.chart.util.DataModelCreator;

/**
 * 
 * @author Karolina Marek (karolina-marek.eu)
 *
 * @date 19.10.2015
 */
public class CircleChartDemo extends
		ChartDemo<CircleChart, SimpleValueDatasetModel> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1899059941525891198L;

	public CircleChartDemo(IControlContainer container)
			throws ChartInconsistencyException {
		super(container);
	}

	@Override
	protected CircleChart createChart(SimpleValueDatasetModel model) {
		CircleChart chart = new CircleChart(this, "chart", model);
		
		return chart;
	}

	@Override
	protected SimpleValueDatasetModel createModel() {

		return DataModelCreator.getSimpleValueDatasetModel();
	}

	@Override
	protected List<TableElement> convertChartModelToTableElements() {
		List<TableElement> elements = new ArrayList<TableElement>();
		for (SimpleValueDataset set : model.getDatasets()) {
			TableElement el = new TableElement();
			el.setTitle(set.getLabel());
			el.setValue(set.getValue().toString());
			el.setFillColor(set.getColor());
			el.setHighlightColor(set.getHighlight());
			elements.add(el);
		}

		return elements;
	}

	@Override
	protected void addElementToTheChart(TableElement element)
			throws ChartInconsistencyException {
		model.addDataToModel(element.getTitle(),
				Double.valueOf(element.getValue()), "", "");

	}

	@Override
	protected void updateElementInChart(TableElement selectedTableElement)
			throws ChartInconsistencyException {
		model.changeDataByModel(selectedTableElement.getTitle(), 1, 5D);

	}

	@Override
	protected void deleteElementFromChart(TableElement selectedTableElement)
			throws ChartInconsistencyException {
		model.removeDataFromModel(selectedTableElement.getTitle());

	}

	@Override
	protected void changeFillColor(String color) {
		model.getDatasets().get(1).setColor(color);

	}

	@Override
	protected void changeHighColor(String highlight) {
		model.getDatasets().get(1).setHighlight(highlight);

	}

}
