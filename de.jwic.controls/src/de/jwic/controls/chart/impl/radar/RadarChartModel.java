package de.jwic.controls.chart.impl.radar;

import java.util.List;

import de.jwic.controls.chart.api.ChartModel;
import de.jwic.controls.chart.impl.util.DatenConverter;

/**
 * 
 * @author Karolina Marek (karolina-marek.eu)
 *
 * @date 03.11.2015
 */
public class RadarChartModel extends ChartModel<RadarChartDataset> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5147442172614245742L;
	private List<String> labels;

	public RadarChartModel(List<String> labels, List<RadarChartDataset> datasets) {
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
