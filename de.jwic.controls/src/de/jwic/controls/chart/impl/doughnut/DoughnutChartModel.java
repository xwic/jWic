package de.jwic.controls.chart.impl.doughnut;

import java.util.List;

import de.jwic.controls.chart.api.ChartModel;

/**
 * 
 * @author Karolina Marek (karolina-marek.eu)
 *
 * @date 29.10.2015
 */
public class DoughnutChartModel extends ChartModel<DoughnutChartDataset> {

	public DoughnutChartModel(List<String> labels, List<DoughnutChartDataset> datasets) {
		super(labels, datasets);
	}

}
