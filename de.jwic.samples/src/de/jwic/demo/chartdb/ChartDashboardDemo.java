/**
 * 
 */
package de.jwic.demo.chartdb;

import de.jwic.base.IControlContainer;
import de.jwic.demo.DemoModule;

/**
 * A simple dashboard with multiple charts on a single page.
 *  
 * @author lippisch
 */
public class ChartDashboardDemo extends DemoModule {

	/**
	 * 
	 */
	public ChartDashboardDemo() {
		setTitle("Dashboard Demo");
		setDescription("A page with multiple charts on it");
		setGroup("Chart");
	}

	/* (non-Javadoc)
	 * @see de.jwic.demo.DemoModule#createModule(de.jwic.base.IControlContainer)
	 */
	@Override
	public void createModule(IControlContainer container) {

		DashboardModel model = new DashboardModel();
		new DashboardPage(container, "page", model);
		
	}

}
