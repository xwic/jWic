package de.jwic.controls.chart.impl;

import de.jwic.controls.chart.api.ChartConfiguration;

/**
 * 
 * @author Karolina Marek (karolina-marek.eu)
 *
 * @date 13.11.2015
 */
public class BarChartConfiguration extends ChartConfiguration {

	public BarChartConfiguration() {
		super("<ul class=\"<%=name.toLowerCase()%>-legend\"><% for (var i=0; i<datasets.length; i++){%><li><span style=\"background-color:<%=datasets[i].fillColor%>\"></span><%if(datasets[i].label){%><%=datasets[i].label%><%}%></li><%}%></ul>");
	}

	private int barValueSpacing = 1;
	private int barDatasetSpacing = 1;

	/**
	 * 
	 * @return Number - Spacing between each of the X value sets
	 */
	public int getBarValueSpacing() {
		return barValueSpacing;
	}

	/**
	 * Number - Spacing between each of the X value sets
	 * 
	 * @param barValueSpacing
	 */
	public void setBarValueSpacing(int barValueSpacing) {
		this.barValueSpacing = barValueSpacing;
	}

	public int getBarDatasetSpacing() {
		return barDatasetSpacing;
	}

	/**
	 * Number - Spacing between each of the X value sets
	 * 
	 * @param barDatasetSpacing
	 */
	public void setBarDatasetSpacing(int barDatasetSpacing) {
		this.barDatasetSpacing = barDatasetSpacing;
	}

}
