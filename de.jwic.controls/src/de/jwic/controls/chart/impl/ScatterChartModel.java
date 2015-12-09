package de.jwic.controls.chart.impl;

import java.util.List;

import de.jwic.controls.chart.api.ChartInconsistencyException;
import de.jwic.controls.chart.api.ChartModel;

/**
 * 
 * @author Karolina Marek (karolina-marek.eu)
 *
 * @date 08.12.2015
 */
public class ScatterChartModel extends ChartModel<ScatterChartDataset> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7014827274374612553L;

	public ScatterChartModel(List<ScatterChartDataset> datasets) {
		super(datasets);
	}

	/**
	 * adds new data to the model with the new label name
	 * 
	 * @param label
	 * @param datasetNumber
	 * @param value
	 * @throws ChartInconsistencyException
	 */
	public void addDataToModel(int datasetNumber, String newValueX,
			Double newValueY) throws ChartInconsistencyException {
		if (newValueX == null) {
			throw new ChartInconsistencyException("Value can not be empty ");
		}
		ScatterChartDataset dataset = getDatasets().get(datasetNumber);
		dataset.add(newValueX, newValueY);
		getDatasets().add(dataset);
		update();
	}

	/**
	 * change the value of the given dataset at defined label
	 * 
	 * @param label
	 * @param datasetNumber
	 * @param newValue
	 * @throws ChartInconsistencyException
	 */
	public void changeDataByModel(String label, int datasetNumber,
			Double newValueX, Double newValueY)
			throws ChartInconsistencyException {
		if (getDatasets().size() <= datasetNumber) {
			throw new ChartInconsistencyException(
					"Array of datasets smaller than " + datasetNumber);
		}

		update();

	}

}
