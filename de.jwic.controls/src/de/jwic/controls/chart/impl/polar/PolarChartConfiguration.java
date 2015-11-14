package de.jwic.controls.chart.impl.polar;

import java.awt.Color;

import de.jwic.controls.chart.api.configuration.ChartConfiguration;


/**
 * 
 * @author Karolina Marek (karolina-marek.eu)
 *
 * @date 14.11.2015
 */
public class PolarChartConfiguration extends ChartConfiguration{

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
	// Boolean - Stroke a line around each segment in the chart
	private boolean segmentShowStroke = true;
	// String - The colour of the stroke on each segement.
	private String segmentStrokeColor = "#fff";
	// Number - The width of the stroke value in pixels
	private int segmentStrokeWidth = 2;
	// Number - Amount of animation steps
	private int animationSteps = 100;
	// String - Animation easing effect.
	private String animationEasing = "easeOutBounce";
	// Boolean - Whether to animate the rotation of the chart
	private boolean animateRotate = true;

	
	
	public PolarChartConfiguration(String legend) {
		super("<ul class=\"<%=name.toLowerCase()%>-legend\"><% for (var i=0; i<segments.length; i++){%><li><span style=\"background-color:<%=segments[i].fillColor%>\"></span><%if(segments[i].label){%><%=segments[i].label%><%}%></li><%}%></ul>");
		// TODO Auto-generated constructor stub
	}

}
