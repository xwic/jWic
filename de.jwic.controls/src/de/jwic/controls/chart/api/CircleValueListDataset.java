/**
 * 
 */
package de.jwic.controls.chart.api;

import java.util.ArrayList;
import java.util.List;

import de.jwic.controls.chart.impl.util.DataConverter;

/**
 * @author vedad
 *
 */
public class CircleValueListDataset extends ChartDataset {
	
	private static final long serialVersionUID = 1L;
	
	private List<Double> data = new ArrayList<Double>();
	@JsonChartName(bar = "", circle = "backgroundColor", line = "", polar = "backgroundColor", radar = "", dateTime = "", stacked = "", overlay = "")
	private List<String> backgroundColor = new ArrayList<String>();
	@JsonChartName(bar = "", circle = "hoverBackgroundColor", line = "", polar = "hoverBackgroundColor", radar = "", dateTime = "", stacked = "", overlay = "")
	private List<String> hoverBackgroundColor = new ArrayList<String>();

	/**
	 * @param label
	 */
	public CircleValueListDataset(String label, List<Double> values) {
		super(label);
		if (values != null)
			this.data.addAll(values);
	}
	
	/**
	 * 
	 * @return
	 */
	public List<Double> getData() {
		return data;
	}

	/**
	 * 
	 * @param data
	 */
	public void setData(List<Double> data) {
		this.data = data;
	}

	/**
	 * @return the backgroundColor
	 */
	public List<String> getBackgroundColor() {
		return backgroundColor;
	}

	/**
	 * @param backgroundColor the backgroundColor to set
	 */
	public void setBackgroundColor(List<String> backgroundColor) {
		List<String> convertedColors = new ArrayList<String>();
		for (String col : backgroundColor){
			String color = DataConverter.convertToJSColor(col);
			if (color != null)
				convertedColors.add(color);
		}
		this.backgroundColor = convertedColors;
	}

	/**
	 * @return the hoverBackgroundColor
	 */
	public List<String> getHoverBackgroundColor() {
		return hoverBackgroundColor;
	}

	/**
	 * @param hoverBackgroundColor the hoverBackgroundColor to set
	 */
	public void setHoverBackgroundColor(List<String> hoverBackgroundColor) {
		List<String> convertedColors = new ArrayList<String>();
		for (String col : hoverBackgroundColor){
			String color = DataConverter.convertToJSColor(col);
			if (color != null)
				convertedColors.add(color);
		}
		this.hoverBackgroundColor = convertedColors;
	}

}
