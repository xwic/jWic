package de.jwic.controls.chart.impl.line;

import java.util.List;

import de.jwic.controls.chart.api.ChartModel;
import de.jwic.controls.chart.impl.util.DatenConverter;

/**
 * 
 * @author Karolina Marek (karolina-marek.eu)
 *
 * @date 29.10.2015
 */
public class LineChartModel extends ChartModel<LineChartDataset> {

	private List<String> labels;

	public LineChartModel(List<String> labels, List<LineChartDataset> datasets) {
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
