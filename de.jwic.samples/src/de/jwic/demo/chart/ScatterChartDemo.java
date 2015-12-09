package de.jwic.demo.chart;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import de.jwic.base.IControlContainer;
import de.jwic.controls.chart.api.ChartInconsistencyException;
import de.jwic.controls.chart.api.ValueListDataset;
import de.jwic.controls.chart.api.ValueListDatasetModel;
import de.jwic.controls.chart.impl.BarChart;
import de.jwic.controls.chart.impl.ScatterChart;
import de.jwic.controls.chart.impl.ScatterChartDataset;
import de.jwic.controls.chart.impl.ScatterChartModel;
import de.jwic.demo.chart.util.DataModelCreator;

/**
 * 
 * @author Karolina Marek (karolina-marek.eu)
 *
 * @date 08.12.2015
 */
public class ScatterChartDemo extends ChartDemo<ScatterChart, ScatterChartModel> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1899059941525891198L;

	public ScatterChartDemo(IControlContainer container) {
		super(container);

	}

	protected ScatterChartModel createModel() {

		return DataModelCreator.getScatterChartModel();
	}

	@Override
	protected List<TableElement> convertChartModelToTableElements() {
		List<TableElement> elements = new ArrayList<TableElement>();

		for (ScatterChartDataset set : model.getDatasets()) {
			int i = 0;
			for (Entry<String, String> in : set.getValues().entrySet()) {
				TableElement el = new TableElement();
				el.setTitle(in.getKey());
				el.setValue(in.toString());
				el.setFillColor(set.getPointColor());
				el.setHighlightColor(set.getPointStrokeColor());
				elements.add(el);
				i++;
			}

		}

		return elements;
	}

	public void changePointColorOfTheDataset(String color) throws ChartInconsistencyException {
	//	model.changePointColor(1, color);
	}

	public void changeHightlightColorOfTheDataset(String color) throws ChartInconsistencyException {
	//	model.changeHightlightColor(1, color);
	}

	@Override
	protected ScatterChart createChart(ScatterChartModel model) {
		return new ScatterChart(this, "chart", model);
	}


	@Override
	protected void changeHighColor(String color) throws ChartInconsistencyException {
	
		
	}

	@Override
	protected void changeFillColor(String text)
			throws ChartInconsistencyException {
		// TODO Auto-generated method stub
		
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

