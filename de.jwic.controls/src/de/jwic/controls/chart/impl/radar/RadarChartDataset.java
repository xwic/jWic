package de.jwic.controls.chart.impl.radar;

import java.util.List;

import de.jwic.controls.chart.api.ValueListDataset;
import de.jwic.controls.chart.impl.util.DatenConverter;

/**
 * 
 * @author Karolina Marek (karolina-marek.eu)
 *
 * @date 03.11.2015
 */
public class RadarChartDataset extends ValueListDataset {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8193309934771457725L;

	private String pointColor = "";
	private String pointStrokeColor = "";

	public RadarChartDataset(String label, List<String> values) {
		super(label, values);

	}

	public String getPointColor() {
		return pointColor;
	}

	public void setPointColor(String pointColor) {
		String color = DatenConverter.convertToJSColor(pointColor);
		this.pointColor = color;

	}

	public String getPointStrokeColor() {
		return pointStrokeColor;
	}

	public void setPointStrokeColor(String pointStrokeColor) {
		String color = DatenConverter.convertToJSColor(pointStrokeColor);
		this.pointStrokeColor = color;
	}

}
