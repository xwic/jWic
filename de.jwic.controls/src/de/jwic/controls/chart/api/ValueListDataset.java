package de.jwic.controls.chart.api;

import java.util.ArrayList;
import java.util.List;

import de.jwic.controls.chart.api.configuration.JsonChartName;
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
	private List<String> data = new ArrayList<String>();
	@JsonChartName(bar = "fillColor", circle = "", line = "fillColor", polar = "", radar = "fillColor")
	private String fillColor = "#66ccff";
	@JsonChartName(bar = "highlightFill", circle = "", line = "highlightFill", polar = "", radar = "pointHighlightFill")
	private String highlightColor = "#66ff33";
	@JsonChartName(bar = "strokeColor", circle = "", line = "strokeColor", polar = "", radar = "strokeColor")
	private String strokeColor = "#ffff00";
	@JsonChartName(bar = "highlightStroke", circle = "", line = "highlightStroke", polar = "", radar = "pointHighlightStroke")
	private String highlightStroke = "#ffffcc";

	public ValueListDataset(String label, List<String> values) {
		super(label);
		if (values != null)
			this.data.addAll(values);
	}

	public List<String> getData() {
		return data;
	}

	public void setData(List<String> data) {
		this.data = data;
	}

	public void setStrokeColor(String strokeColor) {
		String color = DataConverter.convertToJSColor(strokeColor);
		if (color != null)
			this.strokeColor = color;
	}

	public String getHighlightStroke() {
		return highlightStroke;
	}

	public void setHighlightStroke(String highlightStroke) {
		String color = DataConverter.convertToJSColor(highlightStroke);
		if (color != null)
			this.highlightStroke = color;
	}

	public String getStrokeColor() {
		return strokeColor;
	}

	public String getFillColor() {
		return fillColor;
	}

	public void setFillColor(String fillColor) {
		String color = DataConverter.convertToJSColor(fillColor);
		if (color != null)
			this.fillColor = color;
	}

	public String getHighlightColor() {
		return highlightColor;
	}

	public void setHighlightColor(String highlightColor) {
		String color = DataConverter.convertToJSColor(highlightColor);
		if (color != null)
			this.highlightColor = color;
	}

}
