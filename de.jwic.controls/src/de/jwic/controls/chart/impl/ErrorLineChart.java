package de.jwic.controls.chart.impl;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import de.jwic.base.IControlContainer;
import de.jwic.controls.chart.api.Chart;
import de.jwic.controls.chart.api.ChartType;
import de.jwic.controls.chart.api.ValueListDatasetModel;

/**
 * 
 * @author Karolina Marek (karolina-marek.eu)
 *
 * @date 08.12.2015
 */
public class ErrorLineChart extends
		Chart<ValueListDatasetModel, BarChartConfiguration> {

	/**
	 * 
	 * @param container
	 * @param name
	 * @param model
	 */
	public ErrorLineChart(IControlContainer container, String name,
			ValueListDatasetModel model) {
		super(container, name, ChartType.ERROR_LINE, model);
		setConfiguration(new BarChartConfiguration());

	}

	@Override
	public void attachResource(HttpServletRequest req, HttpServletResponse res) throws IOException {
		// TODO Auto-generated method stub
		
	}

}
