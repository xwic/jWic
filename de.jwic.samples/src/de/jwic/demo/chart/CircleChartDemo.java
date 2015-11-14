package de.jwic.demo.chart;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import de.jwic.base.IControlContainer;
import de.jwic.controls.chart.api.exception.ChartInconsistencyException;
import de.jwic.controls.chart.impl.circle.CircleChart;
import de.jwic.controls.chart.impl.circle.CircleChartDataset;
import de.jwic.controls.chart.impl.circle.CircleChartModel;

/**
 * 
 * @author Karolina Marek (karolina-marek.eu)
 *
 * @date 19.10.2015
 */
public class CircleChartDemo extends
		ChartDemo<CircleChart, CircleChartModel> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1899059941525891198L;

	public CircleChartDemo(IControlContainer container)
			throws ChartInconsistencyException {
		super(container);
	}

	@Override
	protected CircleChart createChart(CircleChartModel model) {
		return new CircleChart(this, "chart", model);
	}

	@Override
	protected CircleChartModel createModel() {
		List<CircleChartDataset> datasets = new ArrayList<CircleChartDataset>();

		CircleChartDataset chartd1 = new CircleChartDataset("First", "1",
				Color.red, Color.BLUE);
		CircleChartDataset chartd2 = new CircleChartDataset("Second", "2",
				Color.black, Color.BLUE);
		CircleChartDataset chartd3 = new CircleChartDataset("Third", "3",
				Color.CYAN, Color.BLUE);
		CircleChartDataset chartd4 = new CircleChartDataset("Fourth", "4",
				Color.yellow, Color.BLUE);
		CircleChartDataset chartd5 = new CircleChartDataset("Fifth", "5",
				Color.DARK_GRAY, Color.BLUE);
		CircleChartDataset chartd6 = new CircleChartDataset("Sexsth", "6",
				Color.CYAN, Color.BLUE);

		datasets.add(chartd1);
		datasets.add(chartd2);
		datasets.add(chartd3);
		datasets.add(chartd4);
		datasets.add(chartd5);
		datasets.add(chartd6);
		return new CircleChartModel(datasets);
	}

	@Override
	protected List<TableElement> convertChartModelToTableElements() {
		List<TableElement> elements = new ArrayList<TableElement>();
		for (CircleChartDataset set : model.getDatasets()) {
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
