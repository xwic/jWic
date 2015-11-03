package de.jwic.controls.chart.impl.doughnut;

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
public class DoughnutChart extends
		Chart<DoughnutChartModel, DoughnutChartOptions> {

	public DoughnutChart(IControlContainer container, String name,
			DoughnutChartModel model) throws ChartInconsistencyException {
		super(container, name, ChartType.DOUGHNUT, model,
				new DoughnutChartOptions());

	}

}
