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

	/**
	 * 
	 */
	private static final long serialVersionUID = 6110011116843847513L;

	public DoughnutChartModel(List<DoughnutChartDataset> datasets) {
		super(datasets);
	}

}
