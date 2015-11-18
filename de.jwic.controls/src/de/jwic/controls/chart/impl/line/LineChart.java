package de.jwic.controls.chart.impl.line;

import de.jwic.base.IControlContainer;
import de.jwic.base.JavaScriptSupport;
import de.jwic.controls.chart.api.Chart;
import de.jwic.controls.chart.api.ChartType;
import de.jwic.controls.chart.api.ValueListDatasetModel;

/**
 * 
 * @author Karolina Marek (karolina-marek.eu)
 *
 * @date 29.11.2015
 */
@JavaScriptSupport
public class LineChart extends
		Chart<ValueListDatasetModel, LineChartConfiguration> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7439174125607228687L;

	public LineChart(IControlContainer container, String name,
			ValueListDatasetModel model) {
		super(container, name, ChartType.LINE, model);
		setLocalChartConfiguration(new LineChartConfiguration());

	}

}
