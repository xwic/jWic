package de.jwic.controls.chart.impl.line;

import java.awt.Color;

import de.jwic.controls.chart.api.configuration.ChartOptions;
import de.jwic.controls.chart.impl.util.DatenConverter;

/**
 * 
 * @author Karolina Marek (karolina-marek.eu)
 *
 * @date 29.10.2015
 */
public class LineChartOptions extends ChartOptions {

	// /Boolean - Whether grid lines are shown across the chart
	private boolean scaleShowGridLines = true;

	// String - Colour of the grid lines
	private Color scaleGridLineColor = new Color(0, 0, 0);

	// Number - Width of the grid lines
	private int scaleGridLineWidth = 1;

	// Boolean - Whether to show horizontal lines (except X
	// axis)
	private boolean scaleShowHorizontalLines = true;

	// Boolean - Whether to show vertical lines (except Y axis)
	private boolean scaleShowVerticalLines = true;

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

	// Boolean - Whether to show a stroke for datasets
	private boolean datasetStroke = true;

	// Number - Pixel width of dataset stroke
	private int datasetStrokeWidth = 2;

	// Boolean - Whether to fill the dataset with a colour
	private boolean datasetFill = true;

	// String - A legend template
	private String legendTemplate = "<ul class=\"<%=name.toLowerCase()%>-legend\"><% for (var i=0; i<datasets.length; i++){%><li><span style=\"background-color=<%=datasets[i].strokeColor%>\"></span><%if(datasets[i].label){%><%=datasets[i].label%><%}%></li><%}%></ul>";

	public boolean isScaleShowGridLines() {
		return scaleShowGridLines;
	}

	public void setScaleShowGridLines(boolean scaleShowGridLines) {
		this.scaleShowGridLines = scaleShowGridLines;
	}

	public String getScaleGridLineColor() {
		return DatenConverter.convertToJSColor(scaleGridLineColor, "0.5");
	}

	public void setScaleGridLineColor(Color scaleGridLineColor) {
		this.scaleGridLineColor = scaleGridLineColor;
	}

	public int getScaleGridLineWidth() {
		return scaleGridLineWidth;
	}

	public void setScaleGridLineWidth(int scaleGridLineWidth) {
		this.scaleGridLineWidth = scaleGridLineWidth;
	}

	public boolean isScaleShowHorizontalLines() {
		return scaleShowHorizontalLines;
	}

	public void setScaleShowHorizontalLines(boolean scaleShowHorizontalLines) {
		this.scaleShowHorizontalLines = scaleShowHorizontalLines;
	}

	public boolean isScaleShowVerticalLines() {
		return scaleShowVerticalLines;
	}

	public void setScaleShowVerticalLines(boolean scaleShowVerticalLines) {
		this.scaleShowVerticalLines = scaleShowVerticalLines;
	}

	public boolean isBezierCurve() {
		return bezierCurve;
	}

	public void setBezierCurve(boolean bezierCurve) {
		this.bezierCurve = bezierCurve;
	}

	public double getBezierCurveTension() {
		return bezierCurveTension;
	}

	public void setBezierCurveTension(double bezierCurveTension) {
		this.bezierCurveTension = bezierCurveTension;
	}

	public boolean isPointDot() {
		return pointDot;
	}

	public void setPointDot(boolean pointDot) {
		this.pointDot = pointDot;
	}

	public int getPointDotRadius() {
		return pointDotRadius;
	}

	public void setPointDotRadius(int pointDotRadius) {
		this.pointDotRadius = pointDotRadius;
	}

	public int getPointDotStrokeWidth() {
		return pointDotStrokeWidth;
	}

	public void setPointDotStrokeWidth(int pointDotStrokeWidth) {
		this.pointDotStrokeWidth = pointDotStrokeWidth;
	}

	public int getPointHitDetectionRadius() {
		return pointHitDetectionRadius;
	}

	public void setPointHitDetectionRadius(int pointHitDetectionRadius) {
		this.pointHitDetectionRadius = pointHitDetectionRadius;
	}

	public boolean isDatasetStroke() {
		return datasetStroke;
	}

	public void setDatasetStroke(boolean datasetStroke) {
		this.datasetStroke = datasetStroke;
	}

	public int getDatasetStrokeWidth() {
		return datasetStrokeWidth;
	}

	public void setDatasetStrokeWidth(int datasetStrokeWidth) {
		this.datasetStrokeWidth = datasetStrokeWidth;
	}

	public boolean isDatasetFill() {
		return datasetFill;
	}

	public void setDatasetFill(boolean datasetFill) {
		this.datasetFill = datasetFill;
	}

	public String getLegendTemplate() {
		return legendTemplate;
	}

	public void setLegendTemplate(String legendTemplate) {
		this.legendTemplate = legendTemplate;
	}

}
