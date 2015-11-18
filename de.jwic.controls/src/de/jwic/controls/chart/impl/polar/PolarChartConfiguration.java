package de.jwic.controls.chart.impl.polar;

import java.awt.Color;

import de.jwic.controls.chart.api.configuration.ChartConfiguration;

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
	private Color scaleBackdropColor = new Color(255, 255, 255);
	// Boolean - Whether the scale should begin at zero
	private boolean scaleBeginAtZero = true;
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

}
