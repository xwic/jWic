package de.jwic.controls.chart.impl.bar;

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
@JavaScriptSupport()
public class BarChart extends Chart<BarChartModel, BarChartConfiguration> {

	public BarChart(IControlContainer container, String name,
			BarChartModel model) {
		super(container, name, ChartType.BAR, model);
		setLocalChartConfiguration(new BarChartConfiguration());

	}

}
