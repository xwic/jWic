/**
 * 
 */
package de.jwic.demo.chartdb;

import de.jwic.base.IControlContainer;
import de.jwic.controls.chart.api.Chart;
import de.jwic.controls.chart.api.Chart.LegendLocation;
import de.jwic.controls.chart.api.ValueListDatasetModel;
import de.jwic.controls.chart.impl.StackedBarChart;
import de.jwic.controls.chart.impl.StackedChartConfiguration;

/**
 * Displays a randomly generated stacked bar chart with number of users registered per month and user type.
 *
 * @author <a href="mailto:Vitaliy.Zhovtyuk@netapp.com">Vitaliy Zhovtyuk</a>
 */
public class MonthUserTypeView extends ReportView<ValueListDatasetModel, StackedChartConfiguration> {

	/**
	 * @param container
	 * @param name
	 * @param model
	 */
	public MonthUserTypeView(IControlContainer container, String name, DashboardModel model) {
		super(container, name, model);
		setTitle("Users registered by type");
	}

	/* (non-Javadoc)
	 * @see de.jwic.demo.chartdb.ReportView#createChart()
	 */
	@Override
	protected Chart<ValueListDatasetModel, StackedChartConfiguration> createChart() {

		ValueListDatasetModel dsModel = model.getDataProvider().getTotalUsersByType(model.getYear());
		
		StackedBarChart chart = new StackedBarChart(this, "chart", dsModel);
		StackedChartConfiguration cfg = chart.getConfiguration();
		
		cfg.setResponsive(true);
		cfg.setAnimationSteps(20);
		
		chart.setLegendLocation(LegendLocation.RIGHT);
		
		return chart;
	}

}
