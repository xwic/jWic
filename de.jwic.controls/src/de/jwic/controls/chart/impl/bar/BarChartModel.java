package de.jwic.controls.chart.impl.bar;

import java.util.List;

import de.jwic.controls.chart.api.ChartModel;
import de.jwic.controls.chart.impl.util.DatenConverter;

/**
 * 
 * @author karolina
 *
 */
public class BarChartModel extends ChartModel<BarChartDataset> {

	private List<String> labels;

	public BarChartModel(List<String> labels, List<BarChartDataset> datasets) {
		super(datasets);
		this.labels = labels;
	}

	public void setLabels(List<String> labels) {
		this.labels = labels;
	}

	public String getLabels() {
		return DatenConverter.convertToJSArray(labels);
	}

}
