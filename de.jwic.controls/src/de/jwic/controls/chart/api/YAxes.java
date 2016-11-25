/**
 * 
 */
package de.jwic.controls.chart.api;

import java.io.Serializable;

import de.jwic.controls.chart.impl.util.DataConverter;

/**
 * @author vedad
 *
 */
public class YAxes implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String name = null;
	@JsonChartName(bar = "", circle = "", line = "", polar = "", radar = "", dateTime = "", stacked = "", overlay = "scalePositionLeft")
	private boolean scalePositionLeft = false;
	@JsonChartName(bar = "", circle = "", line = "", polar = "", radar = "", dateTime = "", stacked = "", overlay = "scaleFontColor")
	private String scaleFontColor = null;
	@JsonChartName(bar = "scaleLabel", circle = "", line = "scaleLabel", polar = "", radar = "", dateTime = "", stacked = "", overlay = "scaleLabel")
	private String scaleLabel = null;

	/**
	 * 
	 */
	public YAxes(String name) {
		super();
		this.name = name;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the scalePositionLeft
	 */
	public boolean isScalePositionLeft() {
		return scalePositionLeft;
	}

	/**
	 * @param scalePositionLeft the scalePositionLeft to set
	 */
	public void setScalePositionLeft(boolean scalePositionLeft) {
		this.scalePositionLeft = scalePositionLeft;
	}

	/**
	 * @return the scaleFontColor
	 */
	public String getScaleFontColor() {
		return scaleFontColor;
	}

	/**
	 * @param scaleFontColor the scaleFontColor to set
	 */
	public void setScaleFontColor(String scaleFontColor) {
		String color = DataConverter.convertToJSColor(scaleFontColor);
		if (color != null)
			this.scaleFontColor = color;
	}

	
	/**
	 * @return the scaleLabel
	 */
	public String getScaleLabel() {
		return scaleLabel;
	}

	
	/**
	 * @param scaleLabel the scaleLabel to set
	 */
	public void setScaleLabel(String scaleLabel) {
		this.scaleLabel = scaleLabel;
	}

}
