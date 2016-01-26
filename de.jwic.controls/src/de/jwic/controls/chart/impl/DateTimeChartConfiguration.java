package de.jwic.controls.chart.impl;

import de.jwic.controls.chart.api.ChartConfiguration;
import de.jwic.controls.chart.impl.util.DataConverter;

/**
 * 
 * @author Karolina Marek (karolina-marek.eu)
 *
 * @date 09.12.2015
 */
public class DateTimeChartConfiguration extends ChartConfiguration {

	private String scaleArgLabel = "<%=value%>";

	private String emptyDataMessage = "chart has no data";

	private Boolean xScaleOverride = false;

	private int xScaleSteps = 5;

	private int xScaleStepWidth = 5;

	private int xScaleStartValue = 1;

	// VERTICAL SCALE RANGE
	private int scaleSteps = 1;

	private int scaleStepWidth = 1;

	private int scaleStartValue = 1;

	private String scaleType = "date";

	private Boolean useUtc = true;

	private String scaleDateFormat = "mmm d";

	private String scaleTimeFormat = "HH:MM";

	private String scaleDateTimeFormat = "mmm :dd: yyyy, HH:MM";

	private String datasetStrokeColor = "#007ACC";

	private String datasetPointStrokeColor = "057ACC";

	private boolean bezierCurve = true;

	private Double bezierCurveTension = 0.4;

	// Interpolated JS string - can access point fields=
	// argLabel, valueLabel, arg, value, datasetLabel, size
	private String multiTooltipTemplate = "<%=argLabel%>; <%=valueLabel%>";

	public DateTimeChartConfiguration() {
		super("<ul class=\"<%=name.toLowerCase()%>-legend\"><%for(var i=0;i<datasets.length;i++){%><li><span class=\"<%=name.toLowerCase()%>-legend-marker\" style=\"background-color=<%=datasets[i].strokeColor%>\"></span><%=datasets[i].label%></li><%}%></ul>");

		super.setTooltipTemplate("<%if (datasetLabel){%><%=datasetLabel%>= <%}%><%=argLabel%>; <%=valueLabel%>");
	}

	/**
	 * 
	 * @return Interpolated JS string - can access value
	 */
	public String getScaleArgLabel() {
		return scaleArgLabel;
	}

	/**
	 * 
	 * @param scaleArgLabel
	 *            - Interpolated JS string - can access value
	 */
	public void setScaleArgLabel(String scaleArgLabel) {
		this.scaleArgLabel = scaleArgLabel;
	}

	/**
	 * 
	 * @return String - Message for empty data
	 */
	public String getEmptyDataMessage() {
		return emptyDataMessage;
	}

	/**
	 * 
	 * @param emptyDataMessage
	 *            - String - Message for empty data
	 */
	public void setEmptyDataMessage(String emptyDataMessage) {
		this.emptyDataMessage = emptyDataMessage;
	}

	/**
	 * 
	 * @return Boolean - If we want to override with a hard coded x scale
	 */
	public Boolean getxScaleOverride() {
		return xScaleOverride;
	}

	/**
	 * 
	 * @param xScaleOverride
	 *            - Boolean - If we want to override with a hard coded x scale
	 */
	public void setxScaleOverride(Boolean xScaleOverride) {
		this.xScaleOverride = xScaleOverride;
	}

	/**
	 * 
	 * @return Required if scaleOverride is true - Number - The number of steps
	 *         in a hard coded x scale
	 */
	public int getxScaleSteps() {
		return xScaleSteps;
	}

	/**
	 * 
	 * @param xScaleSteps
	 *            - Required if scaleOverride is true - Number - The number of
	 *            steps in a hard coded x scale
	 */
	public void setxScaleSteps(int xScaleSteps) {
		this.xScaleSteps = xScaleSteps;
	}

	/**
	 * 
	 * @return Required if scaleOverride is true - Number - The value jump in
	 *         the hard coded x scale
	 */
	public int getxScaleStepWidth() {
		return xScaleStepWidth;
	}

	/**
	 * 
	 * @param xScaleStepWidth
	 *            - Required if scaleOverride is true - Number - The value jump
	 *            in the hard coded x scale
	 */
	public void setxScaleStepWidth(int xScaleStepWidth) {
		this.xScaleStepWidth = xScaleStepWidth;
	}

	/**
	 * 
	 * @return Required if scaleOverride is true - Number - The x scale starting
	 *         value
	 */
	public int getxScaleStartValue() {
		return xScaleStartValue;
	}

	/**
	 * 
	 * @param xScaleStartValue
	 *            Required if scaleOverride is true - Number - The x scale
	 *            starting value
	 */
	public void setxScaleStartValue(int xScaleStartValue) {
		this.xScaleStartValue = xScaleStartValue;
	}

	/**
	 * 
	 * @return - The number of steps in a hard coded scale
	 */
	public int getScaleSteps() {
		return scaleSteps;
	}

	/**
	 * 
	 * @param scaleSteps - The number of steps in a hard coded scale
	 */
	public void setScaleSteps(int scaleSteps) {
		this.scaleSteps = scaleSteps;
	}

	/**
	 * 
	 * @return - The value jump in the hard coded scale
	 */
	public int getScaleStepWidth() {
		return scaleStepWidth;
	}

	/**
	 * 
	 * @param scaleStepWidth - The value jump in the hard coded scale
	 */
	public void setScaleStepWidth(int scaleStepWidth) {
		this.scaleStepWidth = scaleStepWidth;
	}

	/**
	 * 
	 * @return - The scale starting value
	 */
	public int getScaleStartValue() {
		return scaleStartValue;
	}

	/**
	 * 
	 * @param scaleStartValue - The scale starting value
	 *            
	 */
	public void setScaleStartValue(int scaleStartValue) {
		this.scaleStartValue = scaleStartValue;
	}


	/**
	 * 
	 * @return - Boolean - Whether to use UTC dates instead local
	 */
	public Boolean getUseUtc() {
		return useUtc;
	}

	/**
	 * 
	 * @param useUtc
	 *            - Boolean - Whether to use UTC dates instead local
	 */
	public void setUseUtc(Boolean useUtc) {
		this.useUtc = useUtc;
	}

	/**
	 * 
	 * @return - String - short date format (used for scale labels)
	 */
	public String getScaleDateFormat() {
		return scaleDateFormat;
	}

	/**
	 * 
	 * @param scaleDateFormat
	 *            - String - short date format (used for scale labels)
	 */
	public void setScaleDateFormat(String scaleDateFormat) {
		this.scaleDateFormat = scaleDateFormat;
	}

	/**
	 * 
	 * @return - String - short time format (used for scale labels)
	 */
	public String getScaleTimeFormat() {
		return scaleTimeFormat;
	}

	/**
	 * 
	 * @param scaleTimeFormat
	 *            - String - short time format (used for scale labels)
	 */
	public void setScaleTimeFormat(String scaleTimeFormat) {
		this.scaleTimeFormat = scaleTimeFormat;
	}

	/**
	 * 
	 * @return - String - full date format (used for point labels)
	 */
	public String getScaleDateTimeFormat() {
		return scaleDateTimeFormat;
	}

	/**
	 * 
	 * @param scaleDateTimeFormat
	 *            - String - full date format (used for point labels)
	 */
	public void setScaleDateTimeFormat(String scaleDateTimeFormat) {
		this.scaleDateTimeFormat = scaleDateTimeFormat;
	}

	/**
	 * 
	 * @return - String - Color of dataset stroke
	 */
	public String getDatasetStrokeColor() {
		return datasetStrokeColor;
	}

	/**
	 * 
	 * @param datasetStrokeColor
	 *            - String - Color of dataset stroke
	 */
	public void setDatasetStrokeColor(String datasetStrokeColor) {
		String color = DataConverter.convertToJSColor(datasetStrokeColor);
		if (color != null) {
			this.datasetStrokeColor = color;
		}

	}

	/**
	 * 
	 * @return - String - Color of dataset stroke
	 */
	public String getDatasetPointStrokeColor() {
		return datasetPointStrokeColor;
	}

	/**
	 * 
	 * @param datasetPointStrokeColor
	 *            - String - Color of dataset stroke
	 */
	public void setDatasetPointStrokeColor(String datasetPointStrokeColor) {
		String color = DataConverter.convertToJSColor(datasetPointStrokeColor);
		if (color != null) {
			this.datasetPointStrokeColor = color;
		}

	}

	/**
	 * 
	 * @return - Boolean - Whether the line is curved between points
	 */
	public boolean isBezierCurve() {
		return bezierCurve;
	}

	/**
	 * 
	 * @param bezierCurve
	 *            - Boolean - Whether the line is curved between points
	 */
	public void setBezierCurve(boolean bezierCurve) {
		this.bezierCurve = bezierCurve;
	}

	/**
	 * 
	 * @return - Number - Tension of the bezier curve between points
	 */
	public Double getBezierCurveTension() {
		return bezierCurveTension;
	}

	/**
	 * 
	 * @param bezierCurveTension
	 *            - Number - Tension of the bezier curve between points
	 */
	public void setBezierCurveTension(Double bezierCurveTension) {
		this.bezierCurveTension = bezierCurveTension;
	}

	public String getMultiTooltipTemplate() {
		return multiTooltipTemplate;
	}

	public void setMultiTooltipTemplate(String multiTooltipTemplate) {
		this.multiTooltipTemplate = multiTooltipTemplate;
	}

}
