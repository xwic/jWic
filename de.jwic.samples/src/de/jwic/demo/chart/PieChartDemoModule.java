package de.jwic.demo.chart;

import de.jwic.base.IControlContainer;
import de.jwic.controls.chart.api.exception.ChartInconsistencyException;
import de.jwic.demo.DemoModule;

/**
 * 
 * @author Karolina Marek (karolina-marek.eu)
 *
 * @date 03.11.2015
 */
public class PieChartDemoModule extends DemoModule {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5121835342369183226L;

	/**
	 * 
	 */
	public PieChartDemoModule() {
		setTitle("Pie Chart");
		setDescription("Pie Chart");
		setGroup("Chart");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.jwic.demo.DemoModule#createModule(de.jwic.base.IControlContainer)
	 */
	@Override
	public void createModule(IControlContainer container) {

		try {
			new PieChartDemo(container);
			
		} catch (ChartInconsistencyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}