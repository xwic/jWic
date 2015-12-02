package de.jwic.controls.chart.impl;

import de.jwic.controls.chart.api.ChartConfiguration;

/**
 * 
 * @author Karolina Marek (karolina-marek.eu)
 *
 * @date 14.11.2015
 */
public class CircleChartConfiguration extends ChartConfiguration {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int percentageInnerCutout = 50;

	public CircleChartConfiguration() {
		super(
				"<ul class=\"<%=name.toLowerCase()%>-legend\"><% for (var i=0; i<segments.length; i++){%><li><span style=\"background-color=<%=segments[i].fillColor%>\"></span><%if(segments[i].label){%><%=segments[i].label%><%}%></li><%}%></ul>");
	}

	/**
	 * 
	 * @return Number - The percentage of the chart that we cut out of the
	 *         middle - 0 for pie charts
	 */
	public int getPercentageInnerCutout() {
		return percentageInnerCutout;
	}

	/**
	 * 
	 * @param percentageInnerCutout
	 *            Number - The percentage of the chart that we cut out of the
	 *            middle - 0 for pie charts
	 */
	public void setPercentageInnerCutout(int percentageInnerCutout) {
		this.percentageInnerCutout = percentageInnerCutout;
	}

}
