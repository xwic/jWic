package de.jwic.controls.chart.impl.polar;

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
public class PolarChartDataset extends ChartDataset {

	private String value;
	private Color color;
	private Color highlight;

	public PolarChartDataset(String label, String value, Color color,
			Color highlight) {
		super(label);
		this.value = value;
		this.color = color;
		this.highlight = highlight;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getColor() {
		return DatenConverter.convertToJSColor(color);
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public String getHighlight() {
		return DatenConverter.convertToJSColor(highlight);
	}

	public void setHighlight(Color highlight) {
		this.highlight = highlight;
	}

}
