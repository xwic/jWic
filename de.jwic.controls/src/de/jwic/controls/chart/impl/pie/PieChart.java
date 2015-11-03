package de.jwic.controls.chart.impl.pie;

import de.jwic.base.IControlContainer;
import de.jwic.base.JavaScriptSupport;
import de.jwic.controls.chart.api.Chart;
import de.jwic.controls.chart.api.ChartType;
import de.jwic.controls.chart.api.exception.ChartInconsistencyException;

/**
 * 
 * @author Karolina Marek (karolina-marek.eu)
 *
 * @date 29.11.2015
 */
@JavaScriptSupport
public class PieChart extends Chart<PieChartModel, PieChartOptions> {

	public PieChart(IControlContainer container, String name,
			PieChartModel model) throws ChartInconsistencyException {
		super(container, name, ChartType.PIE, model, new PieChartOptions());

	}

}
