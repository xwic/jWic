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
	@JsonChartName(bar = "fillColor", circle = "", line = "fillColor", polar = "", radar = "fillColor", dateTime = "", stacked = "", overlay = "fillColor")
	private String fillColor = "#66ccff";
	@JsonChartName(bar = "highlightFill", circle = "", line = "highlightFill", polar = "", radar = "pointHighlightFill", dateTime = "", stacked = "", overlay = "highlightFill")
	private String highlightColor = "#66ff33";
	@JsonChartName(bar = "strokeColor", circle = "", line = "strokeColor", polar = "", radar = "strokeColor", dateTime = "", stacked = "", overlay = "strokeColor")
	private String strokeColor = "#ffff00";
	@JsonChartName(bar = "highlightStroke", circle = "", line = "highlightStroke", polar = "", radar = "pointHighlightStroke", dateTime = "", stacked = "", overlay = "highlightStroke")
	private String highlightStroke = "#ffffcc";
	@JsonChartName(bar = "", circle = "", line = "", polar = "", radar = "", dateTime = "", stacked = "", overlay = "type")
	private String type = null;
	@JsonChartName(bar = "", circle = "", line = "", polar = "", radar = "", dateTime = "", stacked = "", overlay = "yAxesGroup")
	private String yAxesGroup = null;

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

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the yAxesGroup
	 */
	public String getyAxesGroup() {
		return yAxesGroup;
	}

	/**
	 * @param yAxesGroup the yAxesGroup to set
	 */
	public void setyAxesGroup(String yAxesGroup) {
		this.yAxesGroup = yAxesGroup;
	}

}
