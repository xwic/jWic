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
public abstract class ChartModel<Dataset extends ChartDataset> implements
		Serializable {
	private static final Logger LOG = Logger.getLogger(ChartModel.class);
	private List<Dataset> datasets;
	private Chart chart;

	public ChartModel(List<Dataset> datasets) {
		this.datasets = datasets;
	}

	public String getDatasetsJson() {
	
			return DataConverter.convertToJson(datasets);
		
	
	}

	public List<Dataset> getDatasets() {
		return datasets;
	}

	public void setDatasets(List<Dataset> datasets) {
		this.datasets = datasets;
	}

	void setChart(Chart chart) {
		this.chart = chart;
	}

	public void update() {
		chart.requireRedraw();
	}

}
