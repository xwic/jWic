/**
 * 
 */
package de.jwic.demo.chartdb;

import de.jwic.base.ControlContainer;
import de.jwic.base.IControlContainer;
import de.jwic.controls.chart.api.Chart;
import de.jwic.controls.chart.api.ChartConfiguration;
import de.jwic.controls.chart.api.ChartModel;
import de.jwic.util.SerObservable;
import de.jwic.util.SerObserver;

/**
 * @author lippisch
 *
 */
public abstract class ReportView<M extends ChartModel<?>, L extends ChartConfiguration> extends ControlContainer {

	protected String title;
	protected DashboardModel model;
	private Chart<M, L> chart;

	/**
	 * @param container
	 * @param name
	 */
	public ReportView(IControlContainer container, String name, DashboardModel model) {
		super(container, name);
		this.model = model;
		
		setTemplateName(ReportView.class.getName()); // make sure that classes extending this class use the "ReportView" template
		setTitle("Unnamed");
		
		chart = createChart();
		
		model.addObserver(new SerObserver() {
			
			@Override
			public void update(SerObservable o, Object arg) {
				onFilterChange();
			}
		});
		
	}

	/**
	 * Reload the data for the Chart based on the updated filter criterias.
	 */
	protected void onFilterChange() {
		// for simplicity, simply re-create the chart.
		
		if (chart != null) {
			removeControl(chart.getName());
			removeControl("legend");
		}
		chart = createChart();
		
	}

	/**
	 * Create the chart.
	 */
	protected abstract Chart<M, L> createChart();

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * Returns the underlying Chart control.
	 * @return the chart
	 */
	public Chart<M, L> getChart() {
		return chart;
	}

}
