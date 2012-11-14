package de.jwic.ecolib.controls;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;

import de.jwic.base.Control;
import de.jwic.base.IControlContainer;
import de.jwic.ecolib.controls.chart.ChartControl;
import de.jwic.test.ControlTestCase;
import demo.PieChart3DDemo3;

public class ChartControlTest extends ControlTestCase {

	private ChartControl chart = null;
	
	public Control createControl(IControlContainer container) {
		chart = new ChartControl(container);
		return chart;
	}
	
	public void testSetChart() {
		JFreeChart jfreechart = ((ChartPanel)PieChart3DDemo3.createDemoPanel()).getChart();
		chart.setChart(jfreechart);		
		assertEquals(jfreechart, chart.getChart());		
	}
	
	public void testRequireRedraw() {
		JFreeChart jfreechart = ((ChartPanel)PieChart3DDemo3.createDemoPanel()).getChart();
		chart.setChart(jfreechart);
		assertTrue(chart.isRequireRedraw());
	}
	
	public void testNullFileName() {
		chart.setChart(null);
		assertNotNull(chart.getFilename());
	}
		

}
