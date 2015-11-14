package de.jwic.controls.chart.impl.line;

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
public class LineChart extends Chart<LineChartModel, LineChartConfiguration> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7439174125607228687L;

	public LineChart(IControlContainer container, String name,
			LineChartModel model) {
		super(container, name, ChartType.LINE, model);
		setLocalChartConfiguration(new LineChartConfiguration());

	}

}
