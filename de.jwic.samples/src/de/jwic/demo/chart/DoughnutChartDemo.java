package de.jwic.demo.chart;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import de.jwic.base.IControlContainer;
import de.jwic.controls.chart.api.exception.ChartInconsistencyException;
import de.jwic.controls.chart.impl.doughnut.DoughnutChart;
import de.jwic.controls.chart.impl.doughnut.DoughnutChartDataset;
import de.jwic.controls.chart.impl.doughnut.DoughnutChartModel;

/**
 * 
 * @author Karolina Marek (karolina-marek.eu)
 *
 * @date 19.10.2015
 */
public class DoughnutChartDemo extends
		ChartDemo<DoughnutChart, DoughnutChartModel> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1899059941525891198L;

	public DoughnutChartDemo(IControlContainer container)
			throws ChartInconsistencyException {
		super(container);
	}

	@Override
	protected DoughnutChart createChart(DoughnutChartModel model) {
		return new DoughnutChart(this, "chart", model);
	}

	@Override
	protected DoughnutChartModel createModel() {
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
		return new DoughnutChartModel(datasets);
	}

	@Override
	protected List<TableElement> convertChartModelToTableElements() {
		List<TableElement> elements = new ArrayList<TableElement>();
		for (DoughnutChartDataset set : model.getDatasets()) {
			TableElement el = new TableElement();
			el.setTitle(set.getLabel());
			el.setValue(set.getValue());
			elements.add(el);
		}

		return elements;
	}

	@Override
	protected void addElementToTheChart(TableElement element)
			throws ChartInconsistencyException {
		model.addDataToModel(element.getTitle(),
				Double.valueOf(element.getValue()), Color.RED, Color.CYAN);

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

}
