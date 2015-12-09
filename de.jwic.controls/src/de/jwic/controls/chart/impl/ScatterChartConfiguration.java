package de.jwic.controls.chart.impl;

import de.jwic.controls.chart.api.ChartConfiguration;

/**
 * 
 * @author Karolina Marek (karolina-marek.eu)
 *
 * @date 09.12.2015
 */
public class ScatterChartConfiguration extends ChartConfiguration {

	// Boolean - If we should show the scale at all
	private Boolean showScale = true;

	// String - Colour of the scale line
	private String scaleLineColor = "rgba(0,0,0,.1)";

	// Number - Pixel width of the scale line
	private Integer scaleLineWidth = 1;

	// Boolean - Whether to show labels on the scale
	private Boolean scaleShowLabels = true;

	// Interpolated JS string - can access value
	private String scaleLabel = "<%=value%>";

	// Interpolated JS string - can access value
	private String scaleArgLabel = "<%=value%>";

	// String - Message for empty data
	private String emptyDataMessage = "chart has no data";

	// GRID LINES

	// Boolean - Whether grid lines are shown across the chart
	private Boolean scaleShowGridLines = true;

	// Number - Width of the grid lines
	private Integer scaleGridLineWidth = 1;

	// String - Colour of the grid lines
	private String scaleGridLineColor = "rgba(0,0,0,.05)";

	// Boolean - Whether to show horizontal lines (except X axis)
	private Boolean scaleShowHorizontalLines = true;

	// Boolean - Whether to show vertical lines (except Y axis)
	private Boolean scaleShowVerticalLines = true;

	// HORIZONTAL SCALE RANGE

	// Boolean - If we want to override with a hard coded x scale
	private Boolean xScaleOverride = false;

	// ** Required if scaleOverride is true **
	// Number - The number of steps in a hard coded x scale
	private Integer xScaleSteps = null;

	// Number - The value jump in the hard coded x scale
	private Integer xScaleStepWidth = null;

	// Number - The x scale starting value
	private Integer xScaleStartValue = null;

	// VERTICAL SCALE RANGE

	// Boolean - If we want to override with a hard coded y scale
	private Boolean scaleOverride = false;

	// ** Required if scaleOverride is true **
	// Number - The number of steps in a hard coded y scale
	private Integer scaleSteps = null;

	// Number - The value jump in the hard coded y scale
	private Integer scaleStepWidth = null;

	// Number - The y scale starting value
	private Integer scaleStartValue = null;

	// DATE SCALE

	// String - scale type= "number" or "date"
	private String scaleType = "number";

	// Boolean - Whether to use UTC dates instead local
	private Boolean useUtc = true;

	// String - short date format (used for scale labels)
	private String scaleDateFormat = "mmm d";

	// String - short time format (used for scale labels)
	private String scaleTimeFormat = "h=MM";

	// String - full date format (used for point labels)
	private String scaleDateTimeFormat = "mmm d, yyyy, hh=MM";

	// LINES

	// Boolean - Whether to show a stroke for datasets
	private Boolean datasetStroke = true;

	// Number - Pixel width of dataset stroke
	private Integer datasetStrokeWidth = 2;

	// String - Color of dataset stroke
	private String datasetStrokeColor = "#007ACC";

	// String - Color of dataset stroke
	private String datasetPointStrokeColor = "white";

	// Boolean - Whether the line is curved between points
	private boolean bezierCurve = true;

	// Number - Tension of the bezier curve between points
	private Double bezierCurveTension = 0.4;

	// POINTS

	// Boolean - Whether to show a dot for each point
	private boolean pointDot = true;

	// Number - Pixel width of point dot stroke
	private Integer pointDotStrokeWidth = 1;

	// Number - Radius of each point dot in pixels
	private Integer pointDotRadius = 4;

	// Number - amount extra to add to the radius to cater for hit detection
	// outside the drawn point
	private Integer pointHitDetectionRadius = 4;

	// TEMPLATES

	// Interpolated JS string - can access point fields=
	// argLabel, valueLabel, arg, value, datasetLabel, size
	private String tooltipTemplate = "<%if (datasetLabel){%><%=datasetLabel%>= <%}%><%=argLabel%>; <%=valueLabel%>";

	// Interpolated JS string - can access point fields=
	// argLabel, valueLabel, arg, value, datasetLabel, size
	private String multiTooltipTemplate = "<%=argLabel%>; <%=valueLabel%>";

	public ScatterChartConfiguration() {
		super(
				"<ul class=\"<%=name.toLowerCase()%>-legend\"><%for(var i=0;i<datasets.length;i++){%><li><span class=\"<%=name.toLowerCase()%>-legend-marker\" style=\"background-color=<%=datasets[i].strokeColor%>\"></span><%=datasets[i].label%></li><%}%></ul>");
	}

}
