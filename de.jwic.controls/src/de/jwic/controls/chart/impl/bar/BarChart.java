package de.jwic.controls.chart.impl.bar;

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
@JavaScriptSupport()
public class BarChart extends Chart<BarChartModel, BarChartOptions> {

	public BarChart(IControlContainer container, String name,
			BarChartModel model) throws ChartInconsistencyException {
		super(container, name, ChartType.BAR, model, new BarChartOptions());
		setTemplateName(Chart.class.getName());
	}
  // instead of data double should be data of  iSelectElement
	// add action listener for click and tooltip listener
	public void actionClick(String param) {
		System.out.println(param);
	}
}
