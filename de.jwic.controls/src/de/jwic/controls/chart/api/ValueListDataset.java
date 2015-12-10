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
	@JsonChartName(bar = "fillColor", circle = "", line = "fillColor", polar = "", radar = "fillColor", dateTime = "")
	private String fillColor = "#66ccff";
	@JsonChartName(bar = "highlightFill", circle = "", line = "highlightFill", polar = "", radar = "pointHighlightFill", dateTime = "")
	private String highlightColor = "#66ff33";
	@JsonChartName(bar = "strokeColor", circle = "", line = "strokeColor", polar = "", radar = "strokeColor", dateTime = "")
	private String strokeColor = "#ffff00";
	@JsonChartName(bar = "highlightStroke", circle = "", line = "highlightStroke", polar = "", radar = "pointHighlightStroke", dateTime = "")
	private String highlightStroke = "#ffffcc";

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

}
