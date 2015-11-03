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
public class LineChartDemoModule extends DemoModule {

	/**
	 * 
	 */
	public LineChartDemoModule() {
		setTitle("Line Chart");
		setDescription("Line Chart");
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
			new LineChartDemo(container);

		} catch (ChartInconsistencyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
