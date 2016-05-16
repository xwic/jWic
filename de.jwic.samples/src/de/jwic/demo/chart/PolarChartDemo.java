package de.jwic.demo.chart;

import java.util.List;

import de.jwic.base.IControlContainer;
import de.jwic.controls.chart.api.ChartInconsistencyException;
import de.jwic.controls.chart.api.CircleValueListDatasetModel;
import de.jwic.controls.chart.impl.PolarChart;
import de.jwic.demo.chart.util.DataModelCreator;

/**
 * 
 * @author Karolina Marek (karolina-marek.eu)
 *
 * @date 19.10.2015
 */
public class PolarChartDemo extends
		ChartDemo<PolarChart, CircleValueListDatasetModel> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1899059941525891198L;

	public PolarChartDemo(IControlContainer container)
			throws ChartInconsistencyException {
		super(container);

	}

	@Override
	protected PolarChart createChart(CircleValueListDatasetModel model) {

		return new PolarChart(this, "chart", model);

	}

	@Override
	protected CircleValueListDatasetModel createModel() {
		return DataModelCreator.getCircleValueListDatasetModel();

	}

	@Override
	protected void changeFillColor(String text) throws ChartInconsistencyException {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void changeHighColor(String text) throws ChartInconsistencyException {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void addElementToTheChart(TableElement element) throws ChartInconsistencyException {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void updateElementInChart(TableElement selectedTableElement) throws ChartInconsistencyException {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void deleteElementFromChart(TableElement selectedTableElement) throws ChartInconsistencyException {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected List<TableElement> convertChartModelToTableElements() {
		// TODO Auto-generated method stub
		return null;
	}

	

}
