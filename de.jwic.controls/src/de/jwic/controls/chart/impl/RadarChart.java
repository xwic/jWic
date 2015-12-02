package de.jwic.controls.chart.impl;

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

	public RadarChart(IControlContainer container, String name,
			RadarChartModel model) {
		super(container, name, ChartType.RADAR, model);
		setConfiguration(new RadarChartConfiguration());

	}

}
