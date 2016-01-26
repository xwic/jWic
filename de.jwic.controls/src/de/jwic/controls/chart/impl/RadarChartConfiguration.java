package de.jwic.controls.chart.impl;

import de.jwic.controls.chart.api.ChartConfiguration;
import de.jwic.controls.chart.impl.util.DataConverter;

/**
 * 
 * @author Karolina Marek (karolina-marek.eu)
 *
 * @date 14.11.2015
 */
public class RadarChartConfiguration extends ChartConfiguration {

	private boolean scaleShowLabelBackdrop = true;
	private String scaleBackdropColor = "rgba(255,255,255,0,1)";
	private int scaleBackdropPaddingY = 2;
	private int scaleBackdropPaddingX = 2;
	private boolean scaleShowLine = true;

	public RadarChartConfiguration() {
		super("<ul class=\"<%=name.toLowerCase()%>-legend\"><% for (var i=0; i<datasets.length; i++){%><li><span style=\"background-color:<%=datasets[i].strokeColor%>\"></span><%if(datasets[i].label){%><%=datasets[i].label%><%}%></li><%}%></ul>");
	}

	/**
	 * 
	 * @return Boolean - Show a backdrop to the scale label
	 */
	public boolean isScaleShowLabelBackdrop() {
		return scaleShowLabelBackdrop;
	}

	/**
	 * 
	 * @param scaleShowLabelBackdrop
	 *            - Boolean - Show a backdrop to the scale label
	 */
	public void setScaleShowLabelBackdrop(boolean scaleShowLabelBackdrop) {
		this.scaleShowLabelBackdrop = scaleShowLabelBackdrop;
	}

	/**
	 * 
	 * @return String - The colour of the label backdrop
	 */
	public String getScaleBackdropColor() {
		return scaleBackdropColor;
	}

	/**
	 * 
	 * @param scaleBackdropColor
	 *            - String - The colour of the label backdrop
	 */
	public void setScaleBackdropColor(String scaleBackdropColor) {
		String color = DataConverter.convertToJSColor(scaleBackdropColor);
		if (color != null) {
			this.scaleBackdropColor = color;
		}

	}

	/**
	 * 
	 * @return - Number - The backdrop padding above & below the label in pixels
	 */
	public int getScaleBackdropPaddingY() {
		return scaleBackdropPaddingY;
	}

	/**
	 * 
	 * @param scaleBackdropPaddingY
	 *            - Number - The backdrop padding above & below the label in
	 *            pixels
	 */
	public void setScaleBackdropPaddingY(int scaleBackdropPaddingY) {
		this.scaleBackdropPaddingY = scaleBackdropPaddingY;
	}

	/**
	 * 
	 * @return Number - The backdrop padding to the side of the label in pixels
	 */
	public int getScaleBackdropPaddingX() {
		return scaleBackdropPaddingX;
	}

	/**
	 * 
	 * @param scaleBackdropPaddingX
	 *            -Number - The backdrop padding to the side of the label in
	 *            pixels
	 */
	public void setScaleBackdropPaddingX(int scaleBackdropPaddingX) {
		this.scaleBackdropPaddingX = scaleBackdropPaddingX;
	}

	/**
	 * 
	 * @return Boolean - Show line for each value in the scale
	 */
	public boolean isScaleShowLine() {
		return scaleShowLine;
	}

	/**
	 * 
	 * @param scaleShowLine
	 *            Boolean - Show line for each value in the scale
	 */
	public void setScaleShowLine(boolean scaleShowLine) {
		this.scaleShowLine = scaleShowLine;
	}

}
