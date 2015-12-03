package de.jwic.controls.chart.api;

/**
 * 
 * @author karolina
 *
 */
public enum ChartType {
	LINE("line"), BAR("bar"), RADAR("radar"), POLAR("polar"), CIRCLE("circle");

	private String name;

	private ChartType(String name) {
		this.name = name;
	}

	/**
	 * 
	 * @return
	 */
	public String getChartName() {
		return name;
	}

	/**
	 * 
	 * @param element
	 * @return
	 */
	public static ChartType fromName(String element) {
		if (element != null) {
			if (LINE.name.equals(element)) {
				return LINE;
			} else if (CIRCLE.name.equals(element)) {
				return CIRCLE;
			} else if (BAR.name.equals(element)) {
				return BAR;
			} else if (RADAR.name.equals(element)) {
				return RADAR;
			} else if (POLAR.name.equals(element)) {
				return POLAR;
			}
		}
		return null;
	}
}
