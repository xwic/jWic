package de.jwic.demo.chart;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import de.jwic.base.IControlContainer;
import de.jwic.controls.chart.api.exception.ChartInconsistencyException;
import de.jwic.controls.chart.impl.pie.PieChart;
import de.jwic.controls.chart.impl.pie.PieChartDataset;
import de.jwic.controls.chart.impl.pie.PieChartModel;

/**
 * 
 * @author Karolina Marek (karolina-marek.eu)
 *
 * @date 19.10.2015
 */
public class PieChartDemo extends ChartDemo<PieChart, PieChartModel> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1899059941525891198L;

	public PieChartDemo(IControlContainer container)
			throws ChartInconsistencyException {
		super(container);

	}

	private List<PieChartDataset> createDatasets() {
		List<PieChartDataset> datasets = new ArrayList<PieChartDataset>();
		PieChartDataset chartd1 = new PieChartDataset("First", "1", Color.red,
				Color.BLUE);
		PieChartDataset chartd2 = new PieChartDataset("Second", "2",
				Color.black, Color.BLUE);
		PieChartDataset chartd3 = new PieChartDataset("Third", "3",
				Color.DARK_GRAY, Color.BLUE);
		PieChartDataset chartd4 = new PieChartDataset("Fourth", "4",
				Color.yellow, Color.BLUE);
		PieChartDataset chartd5 = new PieChartDataset("Fifth", "5",
				Color.DARK_GRAY, Color.BLUE);
		PieChartDataset chartd6 = new PieChartDataset("Sexsth", "6",
				Color.CYAN, Color.BLUE);

		datasets.add(chartd1);
		datasets.add(chartd2);
		datasets.add(chartd3);
		datasets.add(chartd4);
		datasets.add(chartd5);
		datasets.add(chartd6);
		return datasets;

	}

	@Override
	protected PieChart createChart(PieChartModel model) {
		return new PieChart(this, "chart", model);
	}

	@Override
	protected PieChartModel createModel() {
		return new PieChartModel(createDatasets());

	}

	@Override
	protected List<TableElement> convertChartModelToTableElements() {
		List<TableElement> elements = new ArrayList<TableElement>();
		for (PieChartDataset set : model.getDatasets()) {
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
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void updateElementInChart(TableElement selectedTableElement)
			throws ChartInconsistencyException {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void deleteElementFromChart(TableElement selectedTableElement)
			throws ChartInconsistencyException {
		// TODO Auto-generated method stub
		
	}

}
