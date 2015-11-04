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
import de.jwic.controls.chart.impl.polar.PolarChart;
import de.jwic.controls.chart.impl.polar.PolarChartDataset;
import de.jwic.controls.chart.impl.polar.PolarChartModel;
import de.jwic.events.ElementSelectedEvent;
import de.jwic.events.ElementSelectedListener;

/**
 * 
 * @author Karolina Marek (karolina-marek.eu)
 *
 * @date 19.10.2015
 */
public class PolarChartDemo extends ControlContainer {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1899059941525891198L;
	private Chart chart;

	public PolarChartDemo(IControlContainer container)
			throws ChartInconsistencyException {
		super(container);

		PolarChartModel model = new PolarChartModel(createDatasets());

		this.chart = new PolarChart(this, "chart", model);

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

	private List<PolarChartDataset> createDatasets() {
		List<PolarChartDataset> datasets = new ArrayList<PolarChartDataset>();

		PolarChartDataset chartd1 = new PolarChartDataset("First", "1",
				Color.red, Color.BLUE);
		PolarChartDataset chartd2 = new PolarChartDataset("Second", "2",
				Color.black, Color.BLUE);
		PolarChartDataset chartd3 = new PolarChartDataset("Third", "3",
				Color.CYAN, Color.BLUE);
		PolarChartDataset chartd4 = new PolarChartDataset("Fourth", "4",
				Color.yellow, Color.BLUE);
		PolarChartDataset chartd5 = new PolarChartDataset("Fifth", "5",
				Color.DARK_GRAY, Color.BLUE);
		PolarChartDataset chartd6 = new PolarChartDataset("Sexsth", "6",
				Color.CYAN, Color.BLUE);

		datasets.add(chartd1);
		datasets.add(chartd2);
		datasets.add(chartd3);
		datasets.add(chartd4);
		datasets.add(chartd5);
		datasets.add(chartd6);
		return datasets;
	}

}
