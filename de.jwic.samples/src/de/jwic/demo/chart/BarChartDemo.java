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
import de.jwic.events.ElementSelectedEvent;
import de.jwic.events.ElementSelectedListener;

/**
 * 
 * @author Karolina Marek (karolina-marek.eu)
 *
 * @date 19.10.2015
 */
public class BarChartDemo extends ControlContainer {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1899059941525891198L;
	private BarChart chart;

	public BarChartDemo(IControlContainer container)
			throws ChartInconsistencyException {
		super(container);
		List<String> labels = new ArrayList<String>();
		labels.add("Danny");
		labels.add("Karo");
		labels.add("Nix");
		labels.add("java");
		labels.add("plum");
		labels.add("6");
		labels.add("7");
		labels.add("8");
		BarChartModel model = new BarChartModel(labels, createDatasets());

		this.chart = new BarChart(this, "chart", model);
	
		//chart.getModel().getDatasetAsList().get(0).getData().add("2");
		
		

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

		// Change Chart Type
		ListBoxControl lbType = new ListBoxControl(this, "btChartType");
		lbType.addElement(ChartType.BAR.getChartName());
		lbType.addElement(ChartType.DOUGHNUT.getChartName());
		lbType.addElement(ChartType.LINE.getChartName());
		lbType.addElement(ChartType.PIE.getChartName());
		lbType.addElement(ChartType.POLAR.getChartName());
		lbType.addElement(ChartType.RADAR.getChartName());
		lbType.setSelectedKey(chart.getChartType());
		lbType.setChangeNotification(true);
		lbType.addElementSelectedListener(new ElementSelectedListener() {
			public void elementSelected(ElementSelectedEvent event) {
				ChartType type = ChartType.fromName((String) event.getElement());
				chart.setChartType(type);
			};
		});

	}

	private List<BarChartDataset> createDatasets() {
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
		return datasets;
	}

}
