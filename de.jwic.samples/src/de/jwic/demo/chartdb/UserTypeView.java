/**
 * 
 */
package de.jwic.demo.chartdb;

import de.jwic.base.IControlContainer;
import de.jwic.controls.chart.api.Chart;
import de.jwic.controls.chart.api.SimpleValueDatasetModel;
import de.jwic.controls.chart.api.Chart.LegendLocation;
import de.jwic.controls.chart.impl.CircleChart;
import de.jwic.controls.chart.impl.CircleChartConfiguration;

/**
 * Displays an imaginary line chart with the number of users registered at the
 * demo site. Uses a simple line chart.
 * 
 * @author lippisch
 */
public class UserTypeView extends ReportView<SimpleValueDatasetModel, CircleChartConfiguration> {
	
	/**
	 * @param container
	 * @param name
	 * @param model
	 */
	public UserTypeView(IControlContainer container, String name, DashboardModel model) {
		super(container, name, model);
		setTitle("By User Type");
	}

	/* (non-Javadoc)
	 * @see de.jwic.demo.chartdb.ReportView#createChart()
	 */
	@Override
	protected Chart<SimpleValueDatasetModel, CircleChartConfiguration> createChart() {

		SimpleValueDatasetModel dsModel = model.getDataProvider().getUserTypeDistribution(model.getYear());
		
		CircleChart chart = new CircleChart(this, "chart", dsModel);
		CircleChartConfiguration cfg = chart.getConfiguration();
		
		cfg.setResponsive(true);
		cfg.setAnimationSteps(20);
		
		chart.setLegendLocation(LegendLocation.RIGHT);
		
		return chart;
	}

}
