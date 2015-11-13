package de.jwic.demo.chart;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import de.jwic.base.IControlContainer;
import de.jwic.controls.chart.api.exception.ChartInconsistencyException;
import de.jwic.controls.chart.impl.polar.PolarChart;
import de.jwic.controls.chart.impl.polar.PolarChartDataset;
import de.jwic.controls.chart.impl.polar.PolarChartModel;

/**
 * 
 * @author Karolina Marek (karolina-marek.eu)
 *
 * @date 19.10.2015
 */
public class PolarChartDemo extends ChartDemo<PolarChart, PolarChartModel> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1899059941525891198L;

	public PolarChartDemo(IControlContainer container)
			throws ChartInconsistencyException {
		super(container);

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

	@Override
	protected PolarChart createChart(PolarChartModel model) {

		return new PolarChart(this, "chart", model);

	}

	@Override
	protected PolarChartModel createModel() {
		return new PolarChartModel(createDatasets());

	}

	@Override
	protected List<TableElement> convertChartModelToTableElements() {
		List<TableElement> elements = new ArrayList<TableElement>();
		for (PolarChartDataset set : model.getDatasets()) {
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
