package de.jwic.controls.chart.api;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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

	private List<String> data = new ArrayList<String>();

	public ChartDataset(String label, List<String> values) {
		this.label = label;
		if (values != null)
			this.data.addAll(values);
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public List<String> getData() {
		return data;
	}

	public void setData(List<String> values) {
		this.data = values;
	}

}
