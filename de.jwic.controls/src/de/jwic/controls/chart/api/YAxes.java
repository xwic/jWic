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

}
