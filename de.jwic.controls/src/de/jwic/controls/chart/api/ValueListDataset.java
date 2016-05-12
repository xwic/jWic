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
	@JsonChartName(bar = "backgroundColor", circle = "", line = "backgroundColor", polar = "", radar = "backgroundColor", dateTime = "", stacked = "", overlay = "backgroundColor")
	private String backgroundColor = "#66ccff";
	@JsonChartName(bar = "borderColor", circle = "", line = "borderColor", polar = "", radar = "borderColor", dateTime = "", stacked = "", overlay = "borderColor")
	private String borderColor = "#66ff33";
	@JsonChartName(bar = "hoverBackgroundColor", circle = "", line = "hoverBackgroundColor", polar = "", radar = "hoverBackgroundColor", dateTime = "", stacked = "", overlay = "hoverBackgroundColor")
	private String hoverBackgroundColor = "#ffff00";
	@JsonChartName(bar = "hoverBorderColor", circle = "", line = "hoverBorderColor", polar = "", radar = "hoverBorderColor", dateTime = "", stacked = "", overlay = "hoverBorderColor")
	private String hoverBorderColor = "#ffffcc";
	@JsonChartName(bar = "", circle = "", line = "", polar = "", radar = "", dateTime = "", stacked = "", overlay = "type")
	private String type = null;
	@JsonChartName(bar = "borderWidth", circle = "borderWidth", line = "borderWidth", polar = "borderWidth", radar = "borderWidth", dateTime = "borderWidth", stacked = "borderWidth", overlay = "borderWidth")
	private int borderWidth = 1;
	@JsonChartName(bar = "", circle = "", line = "", polar = "", radar = "", dateTime = "", stacked = "", overlay = "yAxesGroup")
	private String yAxesGroup = null;
	
	@JsonChartName(bar = "", circle = "backgroundColor", line = "", polar = "backgroundColor", radar = "", dateTime = "", stacked = "", overlay = "")
	private List<String> circleBackgroundColor = new ArrayList<String>();
	@JsonChartName(bar = "", circle = "hoverBackgroundColor", line = "", polar = "hoverBackgroundColor", radar = "", dateTime = "", stacked = "", overlay = "")
	private List<String> circleHoverBackgroundColor = new ArrayList<String>();;

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
	 * @return the backgroundColor
	 */
	public String getBackgroundColor() {
		return backgroundColor;
	}

	/**
	 * @param backgroundColor the backgroundColor to set
	 */
	public void setBackgroundColor(String backgroundColor) {
		String color = DataConverter.convertToJSColor(backgroundColor);
		if (color != null)
			this.backgroundColor = color;
	}

	/**
	 * @return the borderColor
	 */
	public String getBorderColor() {
		return borderColor;
	}

	/**
	 * @param borderColor the borderColor to set
	 */
	public void setBorderColor(String borderColor) {
		String color = DataConverter.convertToJSColor(borderColor);
		if (color != null)
			this.borderColor = color;
	}

	/**
	 * @return the hoverBackgroundColor
	 */
	public String getHoverBackgroundColor() {
		return hoverBackgroundColor;
	}

	/**
	 * @param hoverBackgroundColor the hoverBackgroundColor to set
	 */
	public void setHoverBackgroundColor(String hoverBackgroundColor) {
		String color = DataConverter.convertToJSColor(hoverBackgroundColor);
		if (color != null)
			this.hoverBackgroundColor = color;
	}

	/**
	 * @return the hoverBorderColor
	 */
	public String getHoverBorderColor() {
		return hoverBorderColor;
	}

	/**
	 * @param hoverBorderColor the hoverBorderColor to set
	 */
	public void setHoverBorderColor(String hoverBorderColor) {
		String color = DataConverter.convertToJSColor(hoverBorderColor);
		if (color != null)
			this.hoverBorderColor = color;
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
	 * @return the borderWidth
	 */
	public int getBorderWidth() {
		return borderWidth;
	}

	/**
	 * @param borderWidth the borderWidth to set
	 */
	public void setBorderWidth(int borderWidth) {
		this.borderWidth = borderWidth;
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
