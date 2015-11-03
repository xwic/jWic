package de.jwic.controls.chart.impl.line;

import java.util.List;

import de.jwic.controls.chart.api.ChartModel;

/**
 * 
 * @author Karolina Marek (karolina-marek.eu)
 *
 * @date 29.10.2015
 */
public class LineChartModel extends ChartModel<LineChartDataset> {

	public LineChartModel(List<String> labels, List<LineChartDataset> datasets) {
		super(labels, datasets);
	}

}
