package de.jwic.controls.chart.api;

import java.io.Serializable;
import java.util.List;

import org.apache.log4j.Logger;

import de.jwic.controls.chart.impl.util.DataConverter;

/**
 * 
 * @author Karolina Marek (karolina-marek.eu)
 *
 * @date 29.10.2015
 */
public abstract class ChartModel<Dataset extends ChartDataset> implements Serializable {
	private static final Logger LOG = Logger.getLogger(ChartModel.class);
	private List<Dataset> datasets;
	private Chart chart;

	/**
	 * 
	 * @param datasets
	 */
	public ChartModel(List<Dataset> datasets) {
		this.datasets = datasets;
	}

	/**
	 * returns the dataset as json array which will be rendered directly on java
	 * script site
	 * 
	 * @return
	 */
	public String getDatasetsJson() {

		return DataConverter.convertToJson(datasets);

	}
	
	/**
	 * Returns the labels as a json array. The default implementation returns an empty
	 * array.
	 * @return
	 */
	public String getLabelsJson() {
		return "{}";
	}

	/**
	 * 
	 * @return
	 */
	public List<Dataset> getDatasets() {
		return datasets;
	}

	/**
	 * 
	 * @param datasets
	 */
	public void setDatasets(List<Dataset> datasets) {
		this.datasets = datasets;
	}

	/**
	 * 
	 * @param chart
	 */
	void setChart(Chart chart) {
		this.chart = chart;
	}

	/**
	 * action called after adding, removing or updating new data in the chart
	 */
	public void update() {
		chart.requireRedraw();
	}

}
