/**
 * 
 */
package de.jwic.controls.chart.impl;

import de.jwic.controls.chart.api.ChartConfiguration;

/**
 * @author vedad
 *
 */
public class OverlayChartConfiguration extends ChartConfiguration {
	
	public OverlayChartConfiguration() {
		super("<ul class=\"<%=name.toLowerCase()%>-legend\"><% for (var i=0; i<datasets.length; i++){%><li><span style=\"background-color:<%=datasets[i].fillColor%>\"></span><%if(datasets[i].label){%><%=datasets[i].label%><%}%></li><%}%></ul>");
	}

	private int barValueSpacing = 1;
	private int barDatasetSpacing = 1;
	private boolean bezierCurve = true;
	private double bezierCurveTension = 0.4;
	private boolean pointDot = true;
	private int pointDotRadius = 4;
	private int pointDotStrokeWidth = 1;
	private int pointHitDetectionRadius = 20;
	private boolean datasetFill = true;
	private int scaleSteps = 1;
	private int scaleStepWidth = 1;
	private int scaleStartValue = 1;
	
	/**
	 * @return the barValueSpacing
	 */
	public int getBarValueSpacing() {
		return barValueSpacing;
	}
	/**
	 * @param barValueSpacing the barValueSpacing to set
	 */
	public void setBarValueSpacing(int barValueSpacing) {
		this.barValueSpacing = barValueSpacing;
	}
	/**
	 * @return the barDatasetSpacing
	 */
	public int getBarDatasetSpacing() {
		return barDatasetSpacing;
	}
	/**
	 * @param barDatasetSpacing the barDatasetSpacing to set
	 */
	public void setBarDatasetSpacing(int barDatasetSpacing) {
		this.barDatasetSpacing = barDatasetSpacing;
	}
	/**
	 * @return the bezierCurve
	 */
	public boolean isBezierCurve() {
		return bezierCurve;
	}
	/**
	 * @param bezierCurve the bezierCurve to set
	 */
	public void setBezierCurve(boolean bezierCurve) {
		this.bezierCurve = bezierCurve;
	}
	/**
	 * @return the bezierCurveTension
	 */
	public double getBezierCurveTension() {
		return bezierCurveTension;
	}
	/**
	 * @param bezierCurveTension the bezierCurveTension to set
	 */
	public void setBezierCurveTension(double bezierCurveTension) {
		this.bezierCurveTension = bezierCurveTension;
	}
	/**
	 * @return the pointDot
	 */
	public boolean isPointDot() {
		return pointDot;
	}
	/**
	 * @param pointDot the pointDot to set
	 */
	public void setPointDot(boolean pointDot) {
		this.pointDot = pointDot;
	}
	/**
	 * @return the pointDotRadius
	 */
	public int getPointDotRadius() {
		return pointDotRadius;
	}
	/**
	 * @param pointDotRadius the pointDotRadius to set
	 */
	public void setPointDotRadius(int pointDotRadius) {
		this.pointDotRadius = pointDotRadius;
	}
	/**
	 * @return the pointDotStrokeWidth
	 */
	public int getPointDotStrokeWidth() {
		return pointDotStrokeWidth;
	}
	/**
	 * @param pointDotStrokeWidth the pointDotStrokeWidth to set
	 */
	public void setPointDotStrokeWidth(int pointDotStrokeWidth) {
		this.pointDotStrokeWidth = pointDotStrokeWidth;
	}
	/**
	 * @return the pointHitDetectionRadius
	 */
	public int getPointHitDetectionRadius() {
		return pointHitDetectionRadius;
	}
	/**
	 * @param pointHitDetectionRadius the pointHitDetectionRadius to set
	 */
	public void setPointHitDetectionRadius(int pointHitDetectionRadius) {
		this.pointHitDetectionRadius = pointHitDetectionRadius;
	}
	/**
	 * @return the datasetFill
	 */
	public boolean isDatasetFill() {
		return datasetFill;
	}
	/**
	 * @param datasetFill the datasetFill to set
	 */
	public void setDatasetFill(boolean datasetFill) {
		this.datasetFill = datasetFill;
	}
	/**
	 * @return the scaleSteps
	 */
	public int getScaleSteps() {
		return scaleSteps;
	}
	/**
	 * @param scaleSteps the scaleSteps to set
	 */
	public void setScaleSteps(int scaleSteps) {
		this.scaleSteps = scaleSteps;
	}
	/**
	 * @return the scaleStepWidth
	 */
	public int getScaleStepWidth() {
		return scaleStepWidth;
	}
	/**
	 * @param scaleStepWidth the scaleStepWidth to set
	 */
	public void setScaleStepWidth(int scaleStepWidth) {
		this.scaleStepWidth = scaleStepWidth;
	}
	/**
	 * @return the scaleStartValue
	 */
	public int getScaleStartValue() {
		return scaleStartValue;
	}
	/**
	 * @param scaleStartValue the scaleStartValue to set
	 */
	public void setScaleStartValue(int scaleStartValue) {
		this.scaleStartValue = scaleStartValue;
	}

	


}
