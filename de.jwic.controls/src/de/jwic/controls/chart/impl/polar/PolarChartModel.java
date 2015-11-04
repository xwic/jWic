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

	/**
	 * 
	 */
	private static final long serialVersionUID = -4238619025328662192L;

	public PolarChartModel(List<PolarChartDataset> datasets) {
		super(null, datasets);
	}

}
