package de.jwic.controls.chart.impl;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import de.jwic.base.IControlContainer;
import de.jwic.base.JavaScriptSupport;
import de.jwic.controls.chart.api.Chart;
import de.jwic.controls.chart.api.ChartType;
import de.jwic.controls.chart.api.ValueListDatasetModel;

/**
 * 
 * @author Karolina Marek (karolina-marek.eu)
 *
 * @date 29.11.2015
 */
@JavaScriptSupport
public class LineChart extends
		Chart<ValueListDatasetModel, LineChartConfiguration> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7439174125607228687L;

	/**
	 * 
	 * @param container
	 * @param name
	 * @param model
	 */
	public LineChart(IControlContainer container, String name,
			ValueListDatasetModel model) {
		super(container, name, ChartType.LINE, model);
		setConfiguration(new LineChartConfiguration());

	}

	@Override
	public void attachResource(HttpServletRequest req, HttpServletResponse res) throws IOException {
		// TODO Auto-generated method stub
		
	}

}
