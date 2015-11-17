package de.jwic.controls.chart.impl.polar;

import de.jwic.controls.chart.api.ChartDataset;
import de.jwic.controls.chart.impl.util.DatenConverter;

/**
 * 
 * @author Karolina Marek (karolina-marek.eu)
 *
 * @date 03.11.2015
 */
public class PolarChartDataset extends ChartDataset {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String value;
	private String color;
	private String highlight;

	public PolarChartDataset(String label, String value, String color,
			String highlight) {
		super(label);
		this.value = value;
		this.color = DatenConverter.convertToJSColor(color);
		;
		this.highlight = DatenConverter.convertToJSColor(highlight);
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

	public void setColor(String color) {
		this.color = DatenConverter.convertToJSColor(color);
	}

	public String getHighlight() {
		return highlight;
	}

	public void setHighlight(String highlight) {
		this.highlight = DatenConverter.convertToJSColor(highlight);
	}

}
