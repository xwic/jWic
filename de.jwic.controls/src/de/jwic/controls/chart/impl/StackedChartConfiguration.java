package de.jwic.controls.chart.impl;

import de.jwic.controls.chart.api.ChartConfiguration;

/**
 * 
 * @author karolinamarek
 *
 */
public class StackedChartConfiguration extends ChartConfiguration {

	public StackedChartConfiguration() {
		super(
				"<ul class=\"<%=name.toLowerCase()%>-legend\"><% for (var i=0; i<datasets.length; i++){%><li><span style=\"background-color:<%=datasets[i].fillColor%>\"></span><%if(datasets[i].label){%><%=datasets[i].label%><%}%></li><%}%></ul>");
	}

	private int barValueSpacing = 5;
	private boolean relativeBars = false;
	private boolean showTotal = false;
	private String totalColor = "#fff";
	private String totalLabel = "Total";
	private boolean tooltipHideZero = false;

	/**
	 * 
	 * @return - Number - Spacing between each of the X value sets
	 */
	public int getBarValueSpacing() {
		return barValueSpacing;
	}

	/**
	 * 
	 * @param barValueSpacing
	 *            - Number - Spacing between each of the X value sets
	 */
	public void setBarValueSpacing(int barValueSpacing) {
		this.barValueSpacing = barValueSpacing;
	}

	/**
	 * 
	 * @return - Whether bars should be rendered on a percentage base
	 */
	public boolean isRelativeBars() {
		return relativeBars;
	}

	/**
	 * 
	 * @param relativeBars
	 *            - Whether bars should be rendered on a percentage base
	 */
	public void setRelativeBars(boolean relativeBars) {
		this.relativeBars = relativeBars;
	}

	/**
	 * 
	 * @return Show total legend
	 */
	public boolean isShowTotal() {
		return showTotal;
	}

	/**
	 * 
	 * @param showTotal
	 *            Show total legend
	 */
	public void setShowTotal(boolean showTotal) {
		this.showTotal = showTotal;
	}

	/**
	 * 
	 * @return - Color of total legend
	 */
	public String getTotalColor() {
		return totalColor;
	}

	/**
	 * 
	 * @param totalColor
	 *            - Color of total legend
	 */
	public void setTotalColor(String totalColor) {
		this.totalColor = totalColor;
	}

	/**
	 * 
	 * @return - text presented as total label
	 */
	public String getTotalLabel() {
		return totalLabel;
	}

	/**
	 * 
	 * 
	 * @param totalLabel
	 *            - text presented as total label
	 */
	public void setTotalLabel(String totalLabel) {
		this.totalLabel = totalLabel;
	}

	/**
	 * 
	 * @return - Hide labels with value set to 0
	 */
	public boolean isTooltipHideZero() {
		return tooltipHideZero;
	}

	/**
	 * 
	 * @param tooltipHideZero
	 *            - Hide labels with value set to 0
	 */
	public void setTooltipHideZero(boolean tooltipHideZero) {
		this.tooltipHideZero = tooltipHideZero;
	}

}
