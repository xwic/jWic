package de.jwic.controls.chart.impl;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import de.jwic.base.IControlContainer;
import de.jwic.base.JavaScriptSupport;
import de.jwic.controls.chart.api.Chart;
import de.jwic.controls.chart.api.ChartType;

/**
 * 
 * @author Karolina Marek (karolina-marek.eu)
 *
 * @date 29.11.2015
 */
@JavaScriptSupport
public class RadarChart extends Chart<RadarChartModel, RadarChartConfiguration> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2415451817006044807L;

	/**
	 * 
	 * @param container
	 * @param name
	 * @param model
	 */
	public RadarChart(IControlContainer container, String name,
			RadarChartModel model) {
		super(container, name, ChartType.RADAR, model);
		setConfiguration(new RadarChartConfiguration());

	}

	@Override
	public void attachResource(HttpServletRequest req, HttpServletResponse res) throws IOException {
		// TODO Auto-generated method stub
		
	}

}
