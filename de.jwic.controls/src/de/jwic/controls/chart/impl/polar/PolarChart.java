package de.jwic.controls.chart.impl.polar;

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
public class PolarChart extends Chart<PolarChartModel, PolarChartConfiguration> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -198765787479863595L;

	public PolarChart(IControlContainer container, String name,
			PolarChartModel model) {
		super(container, name, ChartType.POLAR, model);

	}

}
