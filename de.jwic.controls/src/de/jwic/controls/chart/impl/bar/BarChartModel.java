package de.jwic.controls.chart.impl.bar;

import java.util.List;

import de.jwic.controls.chart.api.ChartModel;

/**
 * 
 * @author karolina
 *
 */
public class BarChartModel extends ChartModel<BarChartDataset> {

	public BarChartModel(List<String> labels, List<BarChartDataset> datasets) {
		super(labels, datasets);
	}

}
