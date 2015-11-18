package de.jwic.controls.chart.api;

import org.apache.log4j.Logger;

import de.jwic.controls.chart.impl.util.DatenConverter;

/**
 * 
 * @author Karolina Marek (karolina-marek.eu)
 *
 * @date 18.11.2015
 */
public class SimpleValueDataset extends ChartDataset {

	private static final Logger LOGGER = Logger
			.getLogger(SimpleValueDataset.class);
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Double value;
	private String color;
	private String highlight;

	public SimpleValueDataset(String label, Double data, String color,
			String highlight) {
		super(label);
		this.value = data;
		this.color = DatenConverter.convertToJSColor(color);
		this.highlight = DatenConverter.convertToJSColor(highlight);

	}

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		String col = DatenConverter.convertToJSColor(color);
		if (color != null) {
			this.color = col;
		} else {
			LOGGER.warn("color can not be set with value: " + color);
		}

	}

	public String getHighlight() {
		return highlight;
	}

	public void setHighlight(String highlight) {
		String color = DatenConverter.convertToJSColor(highlight);
		if (color != null) {
			this.highlight = color;
		} else {
			LOGGER.warn("highlight can not be set with value: " + highlight);
		}
	}

}
