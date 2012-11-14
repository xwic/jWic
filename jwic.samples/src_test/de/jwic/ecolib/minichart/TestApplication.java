package de.jwic.ecolib.minichart;

import java.awt.Color;

import de.jwic.base.Application;
import de.jwic.base.Control;
import de.jwic.base.IControlContainer;
import de.jwic.base.Page;

/**
 * Test application for mini charts.
 * @author Florian Lippisch
 *
 */
public class TestApplication extends Application {

	/**
	 * @author Florian Lippisch
	 *
	 */
	private final class TestDataProvider implements IChartDataProvider {
		/* (non-Javadoc)
		 * @see com.netapp.minichart.IChartDataProvider#getChartValues(java.lang.String, int)
		 */
		public Number[] getChartValues(String options, int maxValues) {
			Number[] numbers = {
				new Double(10),
				new Double(100),
				new Double(50),
				new Double(0),
				new Double(12),
				new Double(60),
				new Double(70),
				new Double(120),
				new Double(60),
				new Double(70),
				new Double(180),
				new Double(80)
			};
			
			if ("test1".equals(options)) {
				return new Number[] { new Integer(1), new Integer(2), new Integer(3) };
			} else
			if ("test2".equals(options)) {
				return new Number[] { new Integer(-50), new Integer(0), new Integer(100), new Integer(25), new Integer(-20), new Integer(60) };
			} else
			if ("test3".equals(options)) {
				return new Number[] { new Integer(1001), new Integer(2439), new Integer(1800), new Integer(4722), new Integer(1112) };
			}
				
			return numbers;
		}
	}

	/*
	 * (non-Javadoc)
	 * @see de.jwic.base.Application#createRootControl(de.jwic.base.IControlContainer)
	 */
	public Control createRootControl(IControlContainer container) {
		
		Page page = new Page(container);
		page.setTemplateName(TestApplication.class.getName());
		
		MiniChart chart = new MiniChart(page, "chart1");
		chart.getChartStyle().setBackgroundColor(new Color(0x4a, 0x69, 0x99));
		chart.getChartStyle().setDefaultFgColor(Color.WHITE);
		chart.setDataProvider(new TestDataProvider());
	
		MiniChart chart2 = new MiniChart(page, "chart2");
		chart2.setDataProvider(new TestDataProvider());

		
		return page;
	}

}
