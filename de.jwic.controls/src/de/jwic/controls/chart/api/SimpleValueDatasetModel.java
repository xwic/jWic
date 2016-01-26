package de.jwic.controls.chart.api;

import java.util.List;

/**
 * 
 * @author Karolina Marek (karolina-marek.eu)
 *
 * @date 18.11.2015
 */
public class SimpleValueDatasetModel extends ChartModel<SimpleValueDataset> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6110011116843847513L;

	/**
	 * 
	 * @param datasets
	 */
	public SimpleValueDatasetModel(List<SimpleValueDataset> datasets) {
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
	public void addDataToModel(String label, Double value, String color,
			String highlightColor) throws ChartInconsistencyException {
		if (value == null) {
			throw new ChartInconsistencyException("Value can not be empty ");
		}
		SimpleValueDataset dataset = new SimpleValueDataset(label, value,
				color, highlightColor);
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
		SimpleValueDataset dataset = getDatasets().get(datasetNumber);
		dataset.setLabel(label);
		dataset.setValue(newValue);
		update();

	}

	/**
	 * set the element under given label in indicated dataset to 0
	 * 
	 * @param label
	 * @param datasetNumber
	 * @throws ChartInconsistencyException
	 */
	public void removeDataFromModel(String label)
			throws ChartInconsistencyException {
		SimpleValueDataset datasetToRemove = null;
		for (SimpleValueDataset dataset : getDatasets()) {
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

	/**
	 * Changes the color of defined dataset
	 * 
	 * @param datasetNumber
	 * @param color
	 * @throws ChartInconsistencyException
	 */
	public void changeColor(int datasetNumber, String color)
			throws ChartInconsistencyException {
		if (getDatasets().size() <= datasetNumber) {
			throw new ChartInconsistencyException(
					"Array of datasets smaller than " + datasetNumber);
		}
		SimpleValueDataset dataset = getDatasets().get(datasetNumber);
		dataset.setColor(color);
		update();
	}

	/**
	 * Changes the highlight color of defined dataset
	 * 
	 * @param datasetNumber
	 * @param color
	 * @throws ChartInconsistencyException
	 */
	public void changeHighlightColor(int datasetNumber, String color)
			throws ChartInconsistencyException {
		if (getDatasets().size() <= datasetNumber) {
			throw new ChartInconsistencyException(
					"Array of datasets smaller than " + datasetNumber);
		}
		SimpleValueDataset dataset = getDatasets().get(datasetNumber);
		dataset.setHighlight(color);
		update();
	}

}
