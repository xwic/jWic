package de.jwic.controls.chart.impl.line;

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
public class LineChart extends Chart<LineChartModel, LineChartOptions> {

	public LineChart(IControlContainer container, String name,
			LineChartModel model) throws ChartInconsistencyException {
		super(container, name, ChartType.LINE, model, new LineChartOptions());

	}

}
