package de.jwic.controls.chart.api;

/**
 * 
 * @author karolina
 *
 */
public enum ChartType {
	LINE("line"), BAR("bar"), RADAR("radar"), POLAR("polar"), CIRCLE("circle"), DATE_TIME(
			"scatter"), ERROR_BAR("errorbar"), ERROR_LINE("errorline"), STACKED_BAR(
			"stackedbar"), OVERLAY("overlay");

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
			} else if (ERROR_BAR.name.equals(element)) {
				return ERROR_BAR;
			} else if (ERROR_LINE.name.equals(element)) {
				return ERROR_LINE;
			} else if (DATE_TIME.name.equals(element)) {
				return DATE_TIME;
			} else if (STACKED_BAR.name.equals(element)) {
				return STACKED_BAR;
			} else if (OVERLAY.name.equals(element)) {
				return OVERLAY;
			}
		}
		return null;
	}
}
