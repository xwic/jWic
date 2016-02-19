/**
 * 
 */
package de.jwic.demo.chartdb;

import de.jwic.base.ControlContainer;
import de.jwic.base.IControlContainer;

/**
 * Defines the basic layout of the page, containing a global filter and individual
 * report views.
 * 
 * @author lippisch
 */
public class DashboardPage extends ControlContainer {

	private DashboardModel model;

	/**
	 * @param container
	 * @param model 
	 * @param string 
	 */
	public DashboardPage(IControlContainer container, String string, DashboardModel model) {
		super(container);
		this.model = model;

		createContent();
	}

	/**
	 * 
	 */
	private void createContent() {
		
		new GlobalFilters(this, "filter", model);
		
		new TotalUserView(this, "totalUser", model);
		new UserTypeView(this,  "userType", model);
		new MonthUserTypeView(this, "monthUserType", model);
		
		
	}


}
