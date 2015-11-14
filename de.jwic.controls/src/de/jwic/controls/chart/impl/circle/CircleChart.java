package de.jwic.controls.chart.impl.circle;

import de.jwic.base.IControlContainer;
import de.jwic.controls.chart.api.Chart;
import de.jwic.controls.chart.api.ChartType;

/**
 * 
 * @author Karolina Marek (karolina-marek.eu)
 *
 * @date 29.11.2015
 */
public class CircleChart extends
		Chart<CircleChartModel, CircleChartConfiguration> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5777810923589673770L;

	public CircleChart(IControlContainer container, String name,
			CircleChartModel model) {
		super(container, name, ChartType.DOUGHNUT, model);
		setLocalChartConfiguration(new CircleChartConfiguration());

	}

}
