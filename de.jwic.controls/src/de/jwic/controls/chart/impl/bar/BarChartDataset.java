package de.jwic.controls.chart.impl.bar;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import de.jwic.base.IncludeJsOption;
import de.jwic.controls.chart.api.ChartDataset;
import de.jwic.controls.chart.impl.util.DatenConverter;

/**
 * 
 * @author Karolina Marek (karolina-marek.eu)
 *
 * @date 03.11.2015
 */
public class BarChartDataset extends ChartDataset {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5378276021464430867L;
	private List<String> data = new ArrayList<String>();
	private Color fillColor = new Color(220, 220, 220);
	private Color strokeColor = new Color(220, 220, 220);
	private Color highlightFill = new Color(220, 220, 220);
	private Color highlightStroke = new Color(220, 220, 220);

	public BarChartDataset(String label, List<String> values) {
		super(label);
		if (values != null)
			this.data.addAll(values);
	}

	public String getFillColor() {
		return DatenConverter.convertToJSColor(fillColor, "0.5");
	}

	public void setFillColor(Color fillColor) {
		this.fillColor = fillColor;
	}

	// TODO -everythink with json obption and no options more only in chart
	// musst this defined
	@IncludeJsOption
	public String getStrokeColor() {
		return DatenConverter.convertToJSColor(strokeColor, "0.8");
	}

	public void setStrokeColor(Color strokeColor) {
		this.strokeColor = strokeColor;
	}

	public String getHighlightFill() {
		return DatenConverter.convertToJSColor(highlightFill, "0.75");
	}

	public void setHighlightFill(Color highlightFill) {
		this.highlightFill = highlightFill;
	}

	public String getHighlightStroke() {
		return DatenConverter.convertToJSColor(highlightStroke, "1");
	}

	public void setHighlightStroke(Color highlightStroke) {
		this.highlightStroke = highlightStroke;
	}

	public List<String> getData() {
		return data;
	}

	public void setData(List<String> data) {
		this.data = data;
	}

}
