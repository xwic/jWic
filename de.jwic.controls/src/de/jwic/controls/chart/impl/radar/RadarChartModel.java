package de.jwic.controls.chart.impl.radar;

import java.util.List;

import de.jwic.controls.chart.api.ChartModel;

/**
 * 
 * @author Karolina Marek (karolina-marek.eu)
 *
 * @date 03.11.2015
 */
public class RadarChartModel extends ChartModel<RadarChartDataset> {

	public RadarChartModel(List<String> labels, List<RadarChartDataset> datasets) {
		super(labels, datasets);
	}

}
