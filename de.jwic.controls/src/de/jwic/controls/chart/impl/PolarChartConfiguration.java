package de.jwic.controls.chart.impl;

import java.awt.Color;

import de.jwic.controls.chart.api.ChartConfiguration;

/**
 * 
 * @author Karolina Marek (karolina-marek.eu)
 *
 * @date 14.11.2015
 */
public class PolarChartConfiguration extends ChartConfiguration {

	// Boolean - Show a backdrop to the scale label
	private boolean scaleShowLabelBackdrop = true;
	// String - The colour of the label backdrop
	private String scaleBackdropColor = "rgba(255,255,255)";
	// Number - The backdrop padding above & below the label in
	// pixels
	private int scaleBackdropPaddingY = 2;
	// Number - The backdrop padding to the side of the label in
	// pixels
	private int scaleBackdropPaddingX = 2;
	// Boolean - Show line for each value in the scale
	private boolean scaleShowLine = true;

	public PolarChartConfiguration() {
		super(
				"<ul class=\"<%=name.toLowerCase()%>-legend\"><% for (var i=0; i<segments.length; i++){%><li><span style=\"background-color:<%=segments[i].fillColor%>\"></span><%if(segments[i].label){%><%=segments[i].label%><%}%></li><%}%></ul>");

	}

	public boolean isScaleShowLabelBackdrop() {
		return scaleShowLabelBackdrop;
	}

	public void setScaleShowLabelBackdrop(boolean scaleShowLabelBackdrop) {
		this.scaleShowLabelBackdrop = scaleShowLabelBackdrop;
	}

	public String getScaleBackdropColor() {
		return scaleBackdropColor;
	}

	public void setScaleBackdropColor(String scaleBackdropColor) {
		this.scaleBackdropColor = scaleBackdropColor;
	}

	public int getScaleBackdropPaddingY() {
		return scaleBackdropPaddingY;
	}

	public void setScaleBackdropPaddingY(int scaleBackdropPaddingY) {
		this.scaleBackdropPaddingY = scaleBackdropPaddingY;
	}

	public int getScaleBackdropPaddingX() {
		return scaleBackdropPaddingX;
	}

	public void setScaleBackdropPaddingX(int scaleBackdropPaddingX) {
		this.scaleBackdropPaddingX = scaleBackdropPaddingX;
	}

	public boolean isScaleShowLine() {
		return scaleShowLine;
	}

	public void setScaleShowLine(boolean scaleShowLine) {
		this.scaleShowLine = scaleShowLine;
	}
	
	

}
