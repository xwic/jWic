package de.jwic.controls.chart.impl.polar;

import java.util.List;

import de.jwic.controls.chart.api.ChartModel;

/**
 * 
 * @author Karolina Marek (karolina-marek.eu)
 *
 * @date 03.11.2015
 */
public class PolarChartModel extends ChartModel<PolarChartDataset> {

	public PolarChartModel(List<String> labels, List<PolarChartDataset> datasets) {
		super(labels, datasets);
	}

}
