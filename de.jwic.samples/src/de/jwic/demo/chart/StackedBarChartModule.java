package de.jwic.demo.chart;

import de.jwic.base.IControlContainer;
import de.jwic.demo.DemoModule;

/**
 * 
 * @author Karolina Marek (karolina-marek.eu)
 *
 * @date 09.12.2015
 */
public class StackedBarChartModule extends DemoModule {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2647851157518864725L;

	/**
	 * 
	 */
	public StackedBarChartModule() {
		setTitle("Stacked Chart");
		setDescription("STACKED Chart");
		setGroup("Chart");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.jwic.demo.DemoModule#createModule(de.jwic.base.IControlContainer)
	 */
	@Override
	public void createModule(IControlContainer container) {

		new StackedBarDemo(container);

	}

}
