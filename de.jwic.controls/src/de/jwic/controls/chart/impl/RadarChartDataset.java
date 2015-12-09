package de.jwic.controls.chart.impl;

import java.util.List;

import de.jwic.controls.chart.api.ValueListDataset;
import de.jwic.controls.chart.impl.util.DataConverter;

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

	private String pointColor = "rgba(50,50,0,1)";
	private String pointStrokeColor = "rgba(60,60,0,1)";

	public RadarChartDataset(String label, List<Double> values) {
		super(label, values);

	}

	/**
	 * 
	 * @return -The colour of the point
	 */
	public String getPointColor() {
		return pointColor;
	}

	/**
	 * 
	 * @param pointColor
	 *            The colour of the point
	 */
	public void setPointColor(String pointColor) {
		String color = DataConverter.convertToJSColor(pointColor);
		this.pointColor = color;

	}

	/**
	 * 
	 * @return The colour of the stroke
	 */
	public String getPointStrokeColor() {
		return pointStrokeColor;
	}

	/**
	 * 
	 * @param pointStrokeColor
	 *            The colour of the stroke
	 */
	public void setPointStrokeColor(String pointStrokeColor) {
		String color = DataConverter.convertToJSColor(pointStrokeColor);
		this.pointStrokeColor = color;
	}

}
