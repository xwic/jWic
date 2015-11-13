package de.jwic.demo.chart;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import de.jwic.base.ControlContainer;
import de.jwic.base.IControlContainer;
import de.jwic.controls.ListBoxControl;
import de.jwic.controls.chart.api.Chart;
import de.jwic.controls.chart.api.ChartType;
import de.jwic.controls.chart.api.exception.ChartInconsistencyException;
import de.jwic.controls.chart.impl.bar.BarChart;
import de.jwic.controls.chart.impl.bar.BarChartDataset;
import de.jwic.controls.chart.impl.bar.BarChartModel;
import de.jwic.controls.chart.impl.line.LineChartDataset;
import de.jwic.controls.chart.impl.radar.RadarChart;
import de.jwic.controls.chart.impl.radar.RadarChartDataset;
import de.jwic.controls.chart.impl.radar.RadarChartModel;
import de.jwic.events.ElementSelectedEvent;
import de.jwic.events.ElementSelectedListener;

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
	private Chart chart;

	public RadarChartDemo(IControlContainer container)
			throws ChartInconsistencyException {
		super(container);

	}

	private List<RadarChartDataset> createDatasets() {
		List<RadarChartDataset> datasets = new ArrayList<RadarChartDataset>();
		List<String> values = new ArrayList<String>();
		values.add("1");
		values.add("2");
		values.add("3");
		values.add("4");
		values.add("5");
		values.add("6");
		values.add("7");
		values.add("8");
		RadarChartDataset chartd1 = new RadarChartDataset("First", values);
		chartd1.setFillColor(Color.RED);
		datasets.add(chartd1);

		List<String> values2 = new ArrayList<String>();
		values2.add("5");
		values2.add("4");
		values2.add("12");
		values2.add("8");
		values2.add("7");
		values2.add("4");
		values2.add("2");
		values2.add("10");

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

}
