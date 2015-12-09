package de.jwic.controls.chart.impl;

import de.jwic.base.IControlContainer;
import de.jwic.controls.chart.api.Chart;
import de.jwic.controls.chart.api.ChartType;

/**
 * 
 * @author Karolina Marek (karolina-marek.eu)
 *
 * @date 08.12.2015
 */
public class ScatterChart extends
		Chart<ScatterChartModel, ScatterChartConfiguration> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8659387226581875727L;

	/**
	 * 
	 * @param container
	 * @param name
	 * @param model
	 */
	public ScatterChart(IControlContainer container, String name,
			ScatterChartModel model) {
		super(container, name, ChartType.SCATTER, model);
		setConfiguration(new ScatterChartConfiguration());

	}

}
