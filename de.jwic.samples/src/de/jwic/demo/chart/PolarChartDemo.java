package de.jwic.demo.chart;

import java.util.ArrayList;
import java.util.List;

import de.jwic.base.IControlContainer;
import de.jwic.controls.chart.api.ChartInconsistencyException;
import de.jwic.controls.chart.api.CircleValueListDataset;
import de.jwic.controls.chart.api.CircleValueListDatasetModel;
import de.jwic.controls.chart.impl.PolarChart;
import de.jwic.demo.chart.util.DataModelCreator;

/**
 * 
 * @author Karolina Marek (karolina-marek.eu)
 *
 * @date 19.10.2015
 */
public class PolarChartDemo extends
		ChartDemo<PolarChart, CircleValueListDatasetModel> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1899059941525891198L;

	public PolarChartDemo(IControlContainer container)
			throws ChartInconsistencyException {
		super(container);

	}

	@Override
	protected PolarChart createChart(CircleValueListDatasetModel model) {

		return new PolarChart(this, "chart", model);

	}

	@Override
	protected CircleValueListDatasetModel createModel() {
		return DataModelCreator.getCircleValueListDatasetModel();
	}

	@Override
	protected void changeFillColor(String color) throws ChartInconsistencyException {
		model.changeFillColor(0, color);
	}

	@Override
	protected void changeHighColor(String color) throws ChartInconsistencyException {
		model.changeHightlightColor(0, color);
	}

	@Override
	protected void addElementToTheChart(TableElement element) throws ChartInconsistencyException {
		model.addDataToModel(element.getTitle(), 0, Double.valueOf(element.getValue()));
	}

	@Override
	protected void updateElementInChart(TableElement selectedTableElement) throws ChartInconsistencyException {
		model.changeDataByModel(selectedTableElement.getTitle(), 0, 5D);
	}

	@Override
	protected void deleteElementFromChart(TableElement selectedTableElement) throws ChartInconsistencyException {
		model.removeDataFromModel(selectedTableElement.getTitle());
	}

	@Override
	protected List<TableElement> convertChartModelToTableElements() {
		List<TableElement> elements = new ArrayList<TableElement>();

		for (CircleValueListDataset set : model.getDatasets()) {
			int i = 0;
			for (Double in : set.getData()) {
				TableElement el = new TableElement();
				el.setTitle(model.getLabels().get(i));
				el.setValue(in.toString());
				el.setFillColor(set.getBackgroundColor().get(i));
				el.setHighlightColor(set.getHoverBackgroundColor().get(i));
				elements.add(el);
				i++;
			}
		}

		return elements;
	}

	

}
