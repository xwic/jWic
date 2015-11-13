package de.jwic.demo.chart;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import de.jwic.base.IControlContainer;
import de.jwic.controls.chart.impl.bar.BarChart;
import de.jwic.controls.chart.impl.bar.BarChartDataset;
import de.jwic.controls.chart.impl.bar.BarChartModel;
import de.jwic.controls.chart.impl.line.LineChartDataset;

/**
 * 
 * @author Karolina Marek (karolina-marek.eu)
 *
 * @date 19.10.2015
 */
public class BarChartDemo extends ChartDemo<BarChart, BarChartModel> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1899059941525891198L;

	public BarChartDemo(IControlContainer container) {
		super(container);

		// chart.getModel().addDataToModel("Danny", datasetNumber, value);
		// chart.getModel().removeDataFromModel(label, datasetNumber);
		// chart.getModel().changeDataByModel(label, datasetNumber);

		// chart.getModel().getDatasetAsList().get(0).getData().add("2");

		// Change chart visibility

	}

	protected BarChartModel createModel() {
		List<String> labels = new ArrayList<String>();
		labels.add("Danny");
		labels.add("Karo");
		labels.add("Nix");
		labels.add("java");
		labels.add("plum");
		labels.add("6");
		labels.add("7");
		labels.add("8");

		List<BarChartDataset> datasets = new ArrayList<BarChartDataset>();
		List<String> values = new ArrayList<String>();
		values.add("1");
		values.add("2");
		values.add("3");
		values.add("4");
		values.add("5");
		values.add("6");
		values.add("7");
		values.add("8");
		BarChartDataset chartd1 = new BarChartDataset("First", values);
		chartd1.setFillColor(Color.RED);
		datasets.add(chartd1);

		List<String> values2 = new ArrayList<String>();
		values2.add("2");
		values2.add("2");
		values2.add("3");
		values2.add("4");
		values2.add("5");
		values2.add("6");
		values2.add("7");
		values2.add("8");

		BarChartDataset chartd2 = new BarChartDataset("Second", values2);
		datasets.add(chartd2);

		BarChartModel model = new BarChartModel(labels, datasets);
		return model;
	}

	@Override
	protected List<TableElement> convertChartModelToTableElements() {
		List<TableElement> elements = new ArrayList<TableElement>();

		for (BarChartDataset set : model.getDatasets()) {
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
	protected BarChart createChart(BarChartModel model) {
		return new BarChart(this, "chart", model);
	}

}
