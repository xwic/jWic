package de.jwic.controls.chart.impl.polar;

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
public class PolarChart extends Chart<PolarChartModel> {

	public PolarChart(IControlContainer container, String name,
			PolarChartModel model) throws ChartInconsistencyException {
		super(container, name, ChartType.BAR, model);

	}

}
