package de.jwic.controls.chart.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import de.jwic.controls.chart.api.ChartDataset;
import de.jwic.controls.chart.impl.util.DataConverter;

/**
 * 
 * @author Karolina Marek (karolina-marek.eu)
 *
 * @date 08.12.2015
 */
public class DateTimeChartDataset extends ChartDataset {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5542485131372000461L;

	private Map<Date, Double> values = new HashMap<Date, Double>();

	private String pointColor = "#F16220";

	private String pointStrokeColor = "#66ff33";

	private String strokeColor = "#F16220";

	public DateTimeChartDataset(String label, Map<Date, Double> dataModel) {
		super(label);
		this.values = dataModel;

	}

	/**
	 * 
	 * @return
	 */
	public Map<Date, Double> getValues() {
		return values;
	}

	/**
	 * 
	 * @param values
	 */
	public void setValues(Map<Date, Double> values) {
		this.values = values;
	}

	public void add(Date label, Double value) {
		values.put(label, value);
	}

	/**
	 * 
	 * @return
	 */
	public String getPointColor() {
		return pointColor;
	}

	/**
	 * 
	 * @param pointColor
	 */
	public void setPointColor(String pointColor) {
		String color = DataConverter.convertToJSColor(pointColor);
		if (color != null) {
			this.pointColor = color;
		}
	}

	/**
	 * 
	 * @return
	 */
	public String getPointStrokeColor() {
		return pointStrokeColor;
	}

	/**
	 * 
	 * @param pointStrokeColor
	 */
	public void setPointStrokeColor(String pointStrokeColor) {
		String color = DataConverter.convertToJSColor(pointStrokeColor);
		if (color != null) {
			this.pointStrokeColor = color;
		}

	}

	/**
	 * 
	 * @return
	 */
	public String getStrokeColor() {
		return strokeColor;
	}

	/**
	 * 
	 * @param strokeColor
	 */
	public void setStrokeColor(String strokeColor) {
		String color = DataConverter.convertToJSColor(strokeColor);
		if (color != null) {
			this.strokeColor = color;
		}
	}

	public void remove(Date newValueX) {
		values.remove(newValueX);

	}

}
