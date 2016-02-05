/**
 * 
 */
package de.jwic.demo.chartdb;

import de.jwic.base.IControlContainer;
import de.jwic.controls.chart.api.Chart;
import de.jwic.controls.chart.api.ValueListDatasetModel;
import de.jwic.controls.chart.api.Chart.LegendLocation;
import de.jwic.controls.chart.impl.LineChart;
import de.jwic.controls.chart.impl.LineChartConfiguration;

/**
 * Displays an imaginary line chart with the number of users registered at the
 * demo site. Uses a simple line chart.
 * 
 * @author lippisch
 */
public class TotalUserView extends ReportView<ValueListDatasetModel, LineChartConfiguration> {
	
	/**
	 * @param container
	 * @param name
	 * @param model
	 */
	public TotalUserView(IControlContainer container, String name, DashboardModel model) {
		super(container, name, model);
		setTitle("Total Users Registered");
	}

	/* (non-Javadoc)
	 * @see de.jwic.demo.chartdb.ReportView#createChart()
	 */
	@Override
	protected Chart<ValueListDatasetModel, LineChartConfiguration> createChart() {

		ValueListDatasetModel dataSet = model.getDataProvider().getTotalUsers(model.getYear());
		LineChart chart = new LineChart(this, "chart", dataSet);
		LineChartConfiguration cfg = chart.getConfiguration();
		
		chart.setLegendLocation(LegendLocation.RIGHT);
		
		cfg.setCustomTooltip(true);
		cfg.setCustomTooltipGenerator("JWic.controls.Chart.customTooltipGenerators.standardList");
		
		cfg.setResponsive(true);
		cfg.setAnimationSteps(20);
		

		cfg.setWidth(300);
		cfg.setHeight(300);

		return chart;
	}

}
