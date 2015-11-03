package de.jwic.controls.chart.impl.radar;

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
public class RadarChart extends Chart<RadarChartModel, RadarChartOptions> {

	public RadarChart(IControlContainer container, String name,
			RadarChartModel model) throws ChartInconsistencyException {
		super(container, name, ChartType.RADAR, model, new RadarChartOptions());

	}

}
