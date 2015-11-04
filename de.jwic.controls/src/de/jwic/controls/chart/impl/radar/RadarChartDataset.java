package de.jwic.controls.chart.impl.radar;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import de.jwic.controls.chart.api.ChartDataset;
import de.jwic.controls.chart.impl.util.DatenConverter;

/**
 * 
 * @author Karolina Marek (karolina-marek.eu)
 *
 * @date 03.11.2015
 */
public class RadarChartDataset extends ChartDataset {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8193309934771457725L;

	public RadarChartDataset(String label, List<String> values) {
		super(label);
		if (values != null)
			this.data.addAll(values);

	}

	private List<String> data = new ArrayList<String>();
	private Color fillColor = new Color(220, 220, 220);
	private Color strokeColor = new Color(220, 220, 220);
	private Color pointColor = new Color(220, 220, 220);
	private Color pointStrokeColor = new Color(0, 0, 220);
	private Color pointHighlightFill = new Color(220, 220, 220);
	private Color pointHighlightStroke = new Color(220, 220, 220);

	public String getFillColor() {
		return DatenConverter.convertToJSColor(fillColor, "0.2");
	}

	public void setFillColor(Color fillColor) {
		this.fillColor = fillColor;
	}

	public String getStrokeColor() {
		return DatenConverter.convertToJSColor(strokeColor, "1");
	}

	public void setStrokeColor(Color strokeColor) {
		this.strokeColor = strokeColor;
	}

	public List<String> getData() {
		return data;
	}

	public void setData(List<String> data) {
		this.data = data;
	}

	public String getPointColor() {
		return DatenConverter.convertToJSColor(pointColor, "1");
	}

	public void setPointColor(Color pointColor) {
		this.pointColor = pointColor;
	}

	public String getPointStrokeColor() {
		return DatenConverter.convertToJSColor(pointStrokeColor);
	}

	public void setPointStrokeColor(Color pointStrokeColor) {
		this.pointStrokeColor = pointStrokeColor;
	}

	public String getPointHighlightFill() {
		return DatenConverter.convertToJSColor(pointHighlightFill);
	}

	public void setPointHighlightFill(Color pointHighlightFill) {
		this.pointHighlightFill = pointHighlightFill;
	}

	public String getPointHighlightStroke() {
		return DatenConverter.convertToJSColor(pointHighlightStroke);
	}

	public void setPointHighlightStroke(Color pointHighlightStroke) {
		this.pointHighlightStroke = pointHighlightStroke;
	}

}
