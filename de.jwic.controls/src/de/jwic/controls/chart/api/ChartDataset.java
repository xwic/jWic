package de.jwic.controls.chart.api;

import java.io.Serializable;

/**
 * Common implementation of the chart dataset. Valid for all chart types .
 * 
 * @author Karolina Marek (karolina-marek.eu)
 *
 * @date 29.10.2015
 */
public class ChartDataset implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4158845670199165687L;

	private String label;

	/**
	 * 
	 * @param label
	 */
	public ChartDataset(String label) {
		this.label = label;

	}

	/**
	 * 
	 * @return
	 */
	public String getLabel() {
		return label;
	}

	/**
	 * 
	 * @param label
	 */
	public void setLabel(String label) {
		this.label = label;
	}

}
