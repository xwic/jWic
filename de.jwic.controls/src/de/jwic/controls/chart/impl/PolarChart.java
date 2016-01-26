package de.jwic.controls.chart.impl;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import de.jwic.base.IControlContainer;
import de.jwic.base.JavaScriptSupport;
import de.jwic.controls.chart.api.Chart;
import de.jwic.controls.chart.api.ChartType;
import de.jwic.controls.chart.api.SimpleValueDatasetModel;

/**
 * 
 * @author Karolina Marek (karolina-marek.eu)
 *
 * @date 29.11.2015
 */
@JavaScriptSupport
public class PolarChart extends
		Chart<SimpleValueDatasetModel, PolarChartConfiguration> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -198765787479863595L;

	/**
	 * 
	 * @param container
	 * @param name
	 * @param model
	 */
	public PolarChart(IControlContainer container, String name,
			SimpleValueDatasetModel model) {
		super(container, name, ChartType.POLAR, model);
		setConfiguration(new PolarChartConfiguration());

	}

	@Override
	public void attachResource(HttpServletRequest req, HttpServletResponse res) throws IOException {
		// TODO Auto-generated method stub
		
	}

}
