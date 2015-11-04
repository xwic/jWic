package de.jwic.controls.chart.impl.radar;

import java.awt.Color;

import de.jwic.controls.chart.api.configuration.ChartOptions;
import de.jwic.controls.chart.impl.util.DatenConverter;

/**
 * 
 * @author Karolina Marek (karolina-marek.eu)
 *
 * @date 27.10.2015
 */
public class RadarChartOptions extends ChartOptions {

	// Boolean - Whether to show lines for each scale point
	private boolean scaleShowLine = true;

	// Boolean - Whether we show the angle lines out of the
	// radar
	private boolean angleShowLineOut = true;

	// Boolean - Whether to show labels on the scale
	private boolean scaleShowLabels = false;

	// Boolean - Whether the scale should begin at zero
	private boolean scaleBeginAtZero = true;

	// String - Colour of the angle line
	private Color angleLineColor = new Color(1, 1, 1);

	// Number - Pixel width of the angle line
	private int angleLineWidth = 1;

	// String - Point label font declaration
	private String pointLabelFontFamily = "Arial";

	// String - Point label font weight
	private String pointLabelFontStyle = "normal";

	// Number - Point label font size in pixels
	private int pointLabelFontSize = 10;

	// String - Point label font colour
	private String pointLabelFontColor = "#666";

	// Boolean - Whether to show a dot for each point
	private boolean pointDot = true;

	// Number - Radius of each point dot in pixels
	private int pointDotRadius = 3;

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

	public boolean isScaleShowLine() {
		return scaleShowLine;
	}

	public void setScaleShowLine(boolean scaleShowLine) {
		this.scaleShowLine = scaleShowLine;
	}

	public boolean isAngleShowLineOut() {
		return angleShowLineOut;
	}

	public void setAngleShowLineOut(boolean angleShowLineOut) {
		this.angleShowLineOut = angleShowLineOut;
	}

	public boolean isScaleShowLabels() {
		return scaleShowLabels;
	}

	public void setScaleShowLabels(boolean scaleShowLabels) {
		this.scaleShowLabels = scaleShowLabels;
	}

	public boolean isScaleBeginAtZero() {
		return scaleBeginAtZero;
	}

	public void setScaleBeginAtZero(boolean scaleBeginAtZero) {
		this.scaleBeginAtZero = scaleBeginAtZero;
	}

	public String getAngleLineColor() {
		return DatenConverter.convertToJSColor(angleLineColor);
	}

	public void setAngleLineColor(Color angleLineColor) {
		this.angleLineColor = angleLineColor;
	}

	public int getAngleLineWidth() {
		return angleLineWidth;
	}

	public void setAngleLineWidth(int angleLineWidth) {
		this.angleLineWidth = angleLineWidth;
	}

	public String getPointLabelFontFamily() {
		return pointLabelFontFamily;
	}

	public void setPointLabelFontFamily(String pointLabelFontFamily) {
		this.pointLabelFontFamily = pointLabelFontFamily;
	}

	public String getPointLabelFontStyle() {
		return pointLabelFontStyle;
	}

	public void setPointLabelFontStyle(String pointLabelFontStyle) {
		this.pointLabelFontStyle = pointLabelFontStyle;
	}

	public int getPointLabelFontSize() {
		return pointLabelFontSize;
	}

	public void setPointLabelFontSize(int pointLabelFontSize) {
		this.pointLabelFontSize = pointLabelFontSize;
	}

	public String getPointLabelFontColor() {
		return pointLabelFontColor;
	}

	public void setPointLabelFontColor(String pointLabelFontColor) {
		this.pointLabelFontColor = pointLabelFontColor;
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
