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
import de.jwic.controls.chart.impl.line.LineChart;
import de.jwic.controls.chart.impl.line.LineChartDataset;
import de.jwic.controls.chart.impl.line.LineChartModel;
import de.jwic.events.ElementSelectedEvent;
import de.jwic.events.ElementSelectedListener;

public class LineChartDemo extends ControlContainer {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1899059941525891198L;
	private Chart chart;

	public LineChartDemo(IControlContainer container)
			throws ChartInconsistencyException {
		super(container);
		List<String> labels = new ArrayList<String>();
		labels.add("1");
		labels.add("2");
		labels.add("3");
		labels.add("4");
		labels.add("5");
		labels.add("6");
		labels.add("7");
		labels.add("8");
		LineChartModel model = new LineChartModel(labels, createDatasets());

		this.chart = new LineChart(this, "chart", model);

		// Change chart visibility
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

	}

	private List<LineChartDataset> createDatasets() {
		List<LineChartDataset> datasets = new ArrayList<LineChartDataset>();
		List<String> values = new ArrayList<String>();
		values.add("28");
		values.add("48");
		values.add("40");
		values.add("10");
		values.add("25");
		values.add("30");
		values.add("10");
		values.add("2");
		LineChartDataset chartd1 = new LineChartDataset("First", values);
	
		datasets.add(chartd1);

		List<String> values2 = new ArrayList<String>();
		values2.add("1");
		values2.add("3");
		values2.add("12");
		values2.add("10");
		values2.add("2");
		values2.add("5");
		values2.add("7");
		values2.add("8");

		LineChartDataset chartd2 = new LineChartDataset("Second", values2);
		datasets.add(chartd2);
		return datasets;
	}

}
