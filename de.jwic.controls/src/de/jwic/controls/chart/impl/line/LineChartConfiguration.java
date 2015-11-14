package de.jwic.controls.chart.impl.line;

import de.jwic.controls.chart.api.configuration.ChartConfiguration;

/**
 * 
 * @author Karolina Marek (karolina-marek.eu)
 *
 * @date 14.11.2015
 */
public class LineChartConfiguration extends ChartConfiguration {

	public LineChartConfiguration() {
		super(
				"<ul class=\"<%=name.toLowerCase()%>-legend\"><% for (var i=0; i<datasets.length; i++){%><li><span style=\"background-color=<%=datasets[i].strokeColor%>\"></span><%if(datasets[i].label){%><%=datasets[i].label%><%}%></li><%}%></ul>");
	}

	// Boolean - Whether the line is curved between points
	private boolean bezierCurve = true;
	// Number - Tension of the bezier curve between points
	private double bezierCurveTension = 0.4;
	// Boolean - Whether to show a dot for each point
	private boolean pointDot = true;
	// Number - Radius of each point dot in pixels
	private int pointDotRadius = 4;
	// Number - Pixel width of point dot stroke
	private int pointDotStrokeWidth = 1;
	// Number - amount extra to add to the radius to cater for
	// hit detection outside the drawn point
	private int pointHitDetectionRadius = 20;
	// Boolean - Whether to fill the dataset with a colour
	private boolean datasetFill = true;

}
