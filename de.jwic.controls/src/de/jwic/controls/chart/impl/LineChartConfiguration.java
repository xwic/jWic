package de.jwic.controls.chart.impl;

import de.jwic.controls.chart.api.ChartConfiguration;

/**
 * 
 * @author Karolina Marek (karolina-marek.eu)
 *
 * @date 14.11.2015
 */
public class LineChartConfiguration extends ChartConfiguration {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5669716044735403107L;

	public LineChartConfiguration() {
		super("<ul class=\"<%=name.toLowerCase()%>-legend\"><% for (var i=0; i<datasets.length; i++){%><li><span style=\"background-color=<%=datasets[i].strokeColor%>\"></span><%if(datasets[i].label){%><%=datasets[i].label%><%}%></li><%}%></ul>");
	}

	private boolean bezierCurve = true;
	private double bezierCurveTension = 0.4;
	private boolean pointDot = true;
	private int pointDotRadius = 4;
	private int pointDotStrokeWidth = 1;
	private int pointHitDetectionRadius = 20;
	private boolean datasetFill = true;

	/**
	 * 
	 * @return boolean - Whether the line is curved between points
	 */
	public boolean isBezierCurve() {
		return bezierCurve;
	}

	/**
	 * 
	 * @param bezierCurve
	 *            boolean - Whether the line is curved between points
	 */
	public void setBezierCurve(boolean bezierCurve) {
		this.bezierCurve = bezierCurve;
	}

	/**
	 * 
	 * @return Number - Tension of the bezier curve between points
	 */
	public double getBezierCurveTension() {
		return bezierCurveTension;
	}

	/**
	 * 
	 * @param bezierCurveTension
	 *            Number - Tension of the bezier curve between points
	 */
	public void setBezierCurveTension(double bezierCurveTension) {
		this.bezierCurveTension = bezierCurveTension;
	}

	/**
	 * 
	 * @return - Boolean - Whether to show a dot for each point
	 */
	public boolean isPointDot() {
		return pointDot;
	}

	/**
	 * 
	 * @param pointDot
	 *            - Boolean - Whether to show a dot for each point
	 */
	public void setPointDot(boolean pointDot) {
		this.pointDot = pointDot;
	}

	/**
	 * 
	 * @return Number - Radius of each point dot in pixels
	 */
	public int getPointDotRadius() {
		return pointDotRadius;
	}

	/**
	 * 
	 * @param pointDotRadius
	 *            Number - Radius of each point dot in pixels
	 */
	public void setPointDotRadius(int pointDotRadius) {
		this.pointDotRadius = pointDotRadius;
	}

	/**
	 * 
	 * @return Number - Pixel width of point dot stroke
	 */
	public int getPointDotStrokeWidth() {
		return pointDotStrokeWidth;
	}

	/**
	 * 
	 * @param pointDotStrokeWidth
	 *            - Number - Pixel width of point dot stroke
	 */
	public void setPointDotStrokeWidth(int pointDotStrokeWidth) {
		this.pointDotStrokeWidth = pointDotStrokeWidth;
	}

	/**
	 * 
	 * @return Number - amount extra to add to the radius to cater for hit
	 *         detection outside the drawn point
	 */
	public int getPointHitDetectionRadius() {
		return pointHitDetectionRadius;
	}

	/**
	 * 
	 * @param pointHitDetectionRadius
	 *            - Number - amount extra to add to the radius to cater for hit
	 *            detection outside the drawn point
	 */
	public void setPointHitDetectionRadius(int pointHitDetectionRadius) {
		this.pointHitDetectionRadius = pointHitDetectionRadius;
	}

	/**
	 * 
	 * @return Boolean - Whether to fill the dataset with a colour
	 */
	public boolean isDatasetFill() {
		return datasetFill;
	}

	/**
	 * 
	 * @param datasetFill
	 *            - Boolean - Whether to fill the dataset with a colour
	 */
	public void setDatasetFill(boolean datasetFill) {
		this.datasetFill = datasetFill;
	}

}
