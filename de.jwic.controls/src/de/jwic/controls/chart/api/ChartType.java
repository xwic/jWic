package de.jwic.controls.chart.api;

/**
 * 
 * @author karolina
 *
 */
public enum ChartType {
	LINE("line"), BAR("bar"), RADAR("radar"), POLAR("polar"), PIE("pie"), DOUGHNUT(
			"doughnut");

	private String name;

	private ChartType(String name) {
		this.name = name;
	}

	public String getChartName() {
		return name;
	}

	public static ChartType fromName(String element) {
		if (element != null) {
			if (LINE.name.equals(element)) {
				return LINE;
			} else if (DOUGHNUT.name.equals(element)) {
				return DOUGHNUT;
			} else if (BAR.name.equals(element)) {
				return BAR;
			} else if (PIE.name.equals(element)) {
				return PIE;
			} else if (RADAR.name.equals(element)) {
				return RADAR;
			} else if (POLAR.name.equals(element)) {
				return POLAR;
			}
		}
		return null;
	}
}
