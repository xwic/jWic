package de.jwic.controls.chart.impl.circle;

import java.awt.Color;

import de.jwic.controls.chart.api.ChartDataset;
import de.jwic.controls.chart.impl.util.DatenConverter;

/**
 * 
 * @author Karolina Marek (karolina-marek.eu)
 *
 * @date 03.11.2015
 */
public class CircleChartDataset extends ChartDataset {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8648692098015663778L;
	private String value;
	private Color color;
	private Color highlight;
// as double alwazs the value						
	public CircleChartDataset(String label, String value, Color color,
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
