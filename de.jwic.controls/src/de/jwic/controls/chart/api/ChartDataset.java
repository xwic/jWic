package de.jwic.controls.chart.api;

import java.io.Serializable;

/**
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

	public ChartDataset(String label) {
		this.label = label;

	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

}
