package de.jwic.controls.chart.impl.line;

import java.awt.Color;
import java.util.List;

import de.jwic.controls.chart.api.ChartDataset;
import de.jwic.controls.chart.impl.util.DatenConverter;

/**
 * 
 * @author Karolina Marek (karolina-marek.eu)
 *
 * @date 03.11.2015
 */
public class LineChartDataset extends ChartDataset {

	public LineChartDataset(String label, List<String> values) {
		super(label, values);
	}

	private Color fillColor = new Color(220, 220, 220);
	private Color strokeColor = new Color(220, 220, 220);
	private Color highlightFill = new Color(220, 220, 220);
	private Color highlightStroke = new Color(220, 220, 220);

	public String getFillColor() {
		return DatenConverter.convertToJSColor(fillColor, "0.5");
	}

	public void setFillColor(Color fillColor) {
		this.fillColor = fillColor;
	}

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
}
