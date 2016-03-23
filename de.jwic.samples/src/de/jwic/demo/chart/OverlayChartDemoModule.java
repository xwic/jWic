/**
 * 
 */
package de.jwic.demo.chart;

import de.jwic.base.IControlContainer;
import de.jwic.demo.DemoModule;

/**
 * @author vedad
 *
 */
public class OverlayChartDemoModule extends DemoModule {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public OverlayChartDemoModule() {
		setTitle("Overlay Chart");
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
		new OverlayChartDemo(container);
	}

}
