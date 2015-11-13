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
public class DoughnutChartDemoModule extends DemoModule {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7104108590012784537L;

	/**
	 * 
	 */
	public DoughnutChartDemoModule() {
		setTitle("Doughnut Chart");
		setDescription("Chart");
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
			new DoughnutChartDemo(container);
			
		} catch (ChartInconsistencyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}