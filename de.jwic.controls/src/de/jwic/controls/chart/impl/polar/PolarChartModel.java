package de.jwic.controls.chart.impl.polar;

import java.awt.Color;
import java.util.List;

import de.jwic.controls.chart.api.ChartModel;
import de.jwic.controls.chart.api.exception.ChartInconsistencyException;

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
	public void addDataToModel(String label, Double value, Color color,
			Color highlightColor) throws ChartInconsistencyException {
		if (value == null) {
			throw new ChartInconsistencyException("Value can not be empty ");
		}
		PolarChartDataset dataset = new PolarChartDataset(label,
				value.toString(), color, highlightColor);
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
			Double newValue) throws ChartInconsistencyException {
		if (getDatasets().size() <= datasetNumber) {
			throw new ChartInconsistencyException(
					"Array of datasets smaller than " + datasetNumber);
		}
		if (newValue == null) {
			throw new ChartInconsistencyException("Value can not be empty ");
		}
		PolarChartDataset dataset = getDatasets().get(datasetNumber);
		dataset.setLabel(label);
		dataset.setValue(newValue.toString());
		update();

	}

	/**
	 * sets the element under given label in indicated dataset to 0
	 * 
	 * @param label
	 * @param datasetNumber
	 * @throws ChartInconsistencyException
	 */
	public void removeDataFromModel(String label)
			throws ChartInconsistencyException {
		PolarChartDataset datasetToRemove = null;
		for (PolarChartDataset dataset : getDatasets()) {
			if (dataset.getLabel().equals(label)) {
				datasetToRemove = dataset;
			}
		}
		if (datasetToRemove == null) {

			throw new ChartInconsistencyException("No label defined : " + label);

		}
		getDatasets().remove(datasetToRemove);
		update();
	}

}
