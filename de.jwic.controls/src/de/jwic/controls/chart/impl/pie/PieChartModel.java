package de.jwic.controls.chart.impl.pie;

import java.util.List;

import de.jwic.controls.chart.api.ChartModel;

/**
 * 
 * @author Karolina Marek (karolina-marek.eu)
 *
 * @date 29.10.2015
 */
public class PieChartModel extends ChartModel<PieChartDataset> {

	public PieChartModel(List<String> labels, List<PieChartDataset> datasets) {
		super(labels, datasets);
	}

}
