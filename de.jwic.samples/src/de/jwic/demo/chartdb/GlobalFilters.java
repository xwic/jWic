/**
 * 
 */
package de.jwic.demo.chartdb;

import de.jwic.base.ControlContainer;
import de.jwic.base.IControlContainer;
import de.jwic.controls.ToolBar;
import de.jwic.controls.ToolBarGroup;
import de.jwic.controls.combo.DropDown;
import de.jwic.data.SelectElement;
import de.jwic.events.ElementSelectedEvent;
import de.jwic.events.ElementSelectedListener;

/**
 * @author lippisch
 *
 */
public class GlobalFilters extends ControlContainer {

	private DashboardModel model;
	private DropDown lbYear;

	/**
	 * @param container
	 * @param name
	 */
	public GlobalFilters(IControlContainer container, String name, DashboardModel model) {
		super(container, name);
		this.model = model;
		
		createContent();
	}

	/**
	 * Create the filters
	 */
	private void createContent() {
		
		ToolBar toolbar = new ToolBar(this, "tb");
		ToolBarGroup tbGroup = toolbar.addGroup();
		
		tbGroup.addLabel("Year:");

		lbYear = new DropDown(tbGroup, "lbYear");
		
		lbYear.addElement("2015", "2015");
		lbYear.addElement("2016", "2016");
		lbYear.addElement("2017", "2017");
		
		lbYear.setSelectedElement(new SelectElement(model.getYear(), model.getYear()));
		lbYear.addElementSelectedListener(new ElementSelectedListener() {
			@Override
			public void elementSelected(ElementSelectedEvent event) {
				onYearSelection();
			}
		});
		
	}

	/**
	 * User has selected a year.
	 */
	protected void onYearSelection() {
		model.setYear(lbYear.getSelectedKey());		
	}

}
