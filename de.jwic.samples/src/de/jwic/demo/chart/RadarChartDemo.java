package de.jwic.demo.chart;

import java.util.ArrayList;
import java.util.List;

import de.jwic.base.IControlContainer;
import de.jwic.controls.chart.api.ChartInconsistencyException;
import de.jwic.controls.chart.impl.RadarChart;
import de.jwic.controls.chart.impl.RadarChartDataset;
import de.jwic.controls.chart.impl.RadarChartModel;

/**
 * 
 * @author Karolina Marek (karolina-marek.eu)
 *
 * @date 19.10.2015
 */
public class RadarChartDemo extends ChartDemo<RadarChart, RadarChartModel> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1899059941525891198L;

	public RadarChartDemo(IControlContainer container)
			throws ChartInconsistencyException {
		super(container);

	}

	private List<RadarChartDataset> createDatasets() {
		List<RadarChartDataset> datasets = new ArrayList<RadarChartDataset>();
		List<Double> values = new ArrayList<Double>();
		values.add(1D);
		values.add(2D);
		values.add(3D);
		values.add(4D);
		values.add(5D);
		values.add(6D);
		values.add(7D);
		values.add(10D);
		RadarChartDataset chartd1 = new RadarChartDataset("First", values);
		chartd1.setFillColor("#3366cc");
		datasets.add(chartd1);

		List<Double> values2 = new ArrayList<Double>();
		values2.add(5D);
		values2.add(4D);
		values2.add(12D);
		values2.add(8D);
		values2.add(7D);
		values2.add(4D);
		values2.add(2D);
		values2.add(10D);

		RadarChartDataset chartd2 = new RadarChartDataset("Second", values2);
		datasets.add(chartd2);
		return datasets;
	}

	@Override
	protected RadarChart createChart(RadarChartModel model) {
		return new RadarChart(this, "chart", model);
	}

	@Override
	protected RadarChartModel createModel() {
		List<String> labels = new ArrayList<String>();
		labels.add("Eating");
		labels.add("Drinking");
		labels.add("Sleeping");
		labels.add("Testing");
		labels.add("Driving");
		labels.add("Working");
		labels.add("Sweeming");
		labels.add("Running");
		return new RadarChartModel(labels, createDatasets());

	}

	@Override
	protected List<TableElement> convertChartModelToTableElements() {
		List<TableElement> elements = new ArrayList<TableElement>();

		for (RadarChartDataset set : model.getDatasets()) {
			int i = 0;
			for (Double in : set.getData()) {
				TableElement el = new TableElement();
				el.setTitle(model.getLabels().get(i));
				el.setValue(in.toString());
				el.setFillColor(set.getFillColor());
				el.setHighlightColor(set.getHighlightColor());
				elements.add(el);
				i++;
			}

		}
		return elements;
	}

	@Override
	protected void addElementToTheChart(TableElement element)
			throws ChartInconsistencyException {
		model.addDataToModel(element.getTitle(), 1,
				Double.valueOf(element.getValue()));

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
	protected void changeFillColor(String color)
			throws ChartInconsistencyException {
		model.changeFillColor(1, color);

	}

	@Override
	protected void changeHighColor(String color)
			throws ChartInconsistencyException {
		model.changeHightlightColor(1, color);

	}

}
