package de.jwic.demo.chart;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map.Entry;

import de.jwic.base.IControlContainer;
import de.jwic.controls.chart.api.ChartInconsistencyException;
import de.jwic.controls.chart.impl.DateTimeChart;
import de.jwic.controls.chart.impl.DateTimeChartDataset;
import de.jwic.controls.chart.impl.DateTimeChartModel;
import de.jwic.demo.chart.util.DataModelCreator;

/**
 * 
 * @author Karolina Marek (karolina-marek.eu)
 *
 * @date 08.12.2015
 */
public class DateTimeChartDemo extends ChartDemo<DateTimeChart, DateTimeChartModel> {

	
	private static final DateFormat DF = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");

	/**
	 * 
	 */
	private static final long serialVersionUID = -1899059941525891198L;

	public DateTimeChartDemo(IControlContainer container) {
		super(container);

	}

	protected DateTimeChartModel createModel() {

		return DataModelCreator.getScatterChartModel();
	}

	@Override
	protected List<TableElement> convertChartModelToTableElements() {
		List<TableElement> elements = new ArrayList<TableElement>();

		for (DateTimeChartDataset set : model.getDatasets()) {
			int i = 0;
			for (Entry<Date, Double> in : set.getValues().entrySet()) {
				TableElement el = new TableElement();
				el.setTitle(DF.format(in.getKey()));
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
	protected DateTimeChart createChart(DateTimeChartModel model) {
		return new DateTimeChart(this, "chart", model);
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

