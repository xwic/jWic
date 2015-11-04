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
import de.jwic.controls.chart.impl.doughnut.DoughnutChart;
import de.jwic.controls.chart.impl.doughnut.DoughnutChartDataset;
import de.jwic.controls.chart.impl.doughnut.DoughnutChartModel;
import de.jwic.controls.chart.impl.pie.PieChartDataset;
import de.jwic.events.ElementSelectedEvent;
import de.jwic.events.ElementSelectedListener;

/**
 * 
 * @author Karolina Marek (karolina-marek.eu)
 *
 * @date 19.10.2015
 */
public class DoughnutChartDemo extends ControlContainer {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1899059941525891198L;
	private Chart chart;

	public DoughnutChartDemo(IControlContainer container)
			throws ChartInconsistencyException {
		super(container);

		DoughnutChartModel model = new DoughnutChartModel(createDatasets());

		this.chart = new DoughnutChart(this, "chart", model);

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

	private List<DoughnutChartDataset> createDatasets() {
		List<DoughnutChartDataset> datasets = new ArrayList<DoughnutChartDataset>();

		DoughnutChartDataset chartd1 = new DoughnutChartDataset("First", "1",
				Color.red, Color.BLUE);
		DoughnutChartDataset chartd2 = new DoughnutChartDataset("Second", "2",
				Color.black, Color.BLUE);
		DoughnutChartDataset chartd3 = new DoughnutChartDataset("Third", "3",
				Color.CYAN, Color.BLUE);
		DoughnutChartDataset chartd4 = new DoughnutChartDataset("Fourth", "4",
				Color.yellow, Color.BLUE);
		DoughnutChartDataset chartd5 = new DoughnutChartDataset("Fifth", "5",
				Color.DARK_GRAY, Color.BLUE);
		DoughnutChartDataset chartd6 = new DoughnutChartDataset("Sexsth", "6",
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
