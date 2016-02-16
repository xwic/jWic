package de.jwic.controls.chart.api;

import java.util.ArrayList;
import java.util.List;

import de.jwic.controls.chart.impl.util.DataConverter;

/**
 * 
 * @author Karolina Marek (karolina-marek.eu)
 *
 * @date 18.11.2015
 */
public class ValueListDataset extends ChartDataset {

	/**
	 * 
	 */
	private static final long serialVersionUID = 375389814468058669L;
	private List<Double> data = new ArrayList<Double>();
	@JsonChartName(bar = "fillColor", circle = "", line = "fillColor", polar = "", radar = "fillColor", dateTime = "", stacked = "")
	private String fillColor = "#66ccff";

	@JsonChartName(bar = "highlightFill", circle = "", line = "highlightFill", polar = "", radar = "pointHighlightFill", dateTime = "", stacked = "")
	private String highlightColor = "#66ff33";

	@JsonChartName(bar = "strokeColor", circle = "", line = "strokeColor", polar = "", radar = "strokeColor", dateTime = "", stacked = "")
	private String strokeColor = "#ffff00";

	@JsonChartName(bar = "highlightStroke", circle = "", line = "highlightStroke", polar = "", radar = "pointHighlightStroke", dateTime = "", stacked = "")
	private String highlightStroke = "#ffffcc";

	@JsonChartName(bar = "", circle = "", line = "pointColor", polar = "", radar = "pointColor", dateTime = "pointColor", stacked = "")
	private String pointColor = "#ffff00";

	@JsonChartName(bar = "", circle = "", line = "pointStrokeColor", polar = "", radar = "pointStrokeColor", dateTime = "pointStrokeColor", stacked = "")
	private String pointStrokeColor = "#fff";

	@JsonChartName(bar = "", circle = "", line = "pointHighlightFill", polar = "", radar = "pointHighlightFill", dateTime = "pointHighlightFill", stacked = "")
	private String pointHighlightFill = "#fff";

	@JsonChartName(bar = "", circle = "", line = "pointHighlightStroke", polar = "", radar = "pointHighlightStroke", dateTime = "pointHighlightStroke", stacked = "")
	private String pointHighlightStroke = "#00a6ed";

	/**
	 * 
	 * @param label
	 * @param values
	 */
	public ValueListDataset(String label, List<Double> values) {
		super(label);
		if (values != null)
			this.data.addAll(values);
	}

	/**
	 *
	 * @return
	 */
	public List<Double> getData() {
		return data;
	}

	/**
	 *
	 * @param data
	 */
	public void setData(List<Double> data) {
		this.data = data;
	}

	/**
	 *
	 * @param strokeColor
	 */
	public void setStrokeColor(String strokeColor) {
		String color = DataConverter.convertToJSColor(strokeColor);
		if (color != null)
			this.strokeColor = color;
	}

	/**
	 *
	 * @return
	 */
	public String getHighlightStroke() {
		return highlightStroke;
	}

	/**
	 *
	 * @param highlightStroke
	 */
	public void setHighlightStroke(String highlightStroke) {
		String color = DataConverter.convertToJSColor(highlightStroke);
		if (color != null)
			this.highlightStroke = color;
	}

	/**
	 *
	 * @return
	 */
	public String getStrokeColor() {
		return strokeColor;
	}

	/**
	 *
	 * @return
	 */
	public String getFillColor() {
		return fillColor;
	}

	/**
	 *
	 * @param fillColor
	 */
	public void setFillColor(String fillColor) {
		String color = DataConverter.convertToJSColor(fillColor);
		if (color != null)
			this.fillColor = color;
	}

	/**
	 *
	 * @return
	 */
	public String getHighlightColor() {
		return highlightColor;
	}

	/**
	 *
	 * @param highlightColor
	 */
	public void setHighlightColor(String highlightColor) {
		String color = DataConverter.convertToJSColor(highlightColor);
		if (color != null)
			this.highlightColor = color;
	}

	public String getPointColor() {
		return pointColor;
	}

	public void setPointColor(String pointColor) {
		this.pointColor = pointColor;
	}

	public String getPointStrokeColor() {
		return pointStrokeColor;
	}

	public void setPointStrokeColor(String pointStrokeColor) {
		this.pointStrokeColor = pointStrokeColor;
	}

	public String getPointHighlightFill() {
		return pointHighlightFill;
	}

	public void setPointHighlightFill(String pointHighlightFill) {
		this.pointHighlightFill = pointHighlightFill;
	}

	public String getPointHighlightStroke() {
		return pointHighlightStroke;
	}

	public void setPointHighlightStroke(String pointHighlightStroke) {
		this.pointHighlightStroke = pointHighlightStroke;
	}
}
