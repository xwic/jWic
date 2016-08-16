/**
 * 
 */
package de.jwic.controls.chart.api;

import java.util.ArrayList;
import java.util.List;

import de.jwic.controls.chart.impl.util.DataConverter;

/**
 * @author vedad
 *
 */
public class CircleValueListDatasetModel extends ChartModel<CircleValueListDataset> {

	private static final long serialVersionUID = 1L;

	private List<String> labels;

	/**
	 * 
	 * @param labels
	 * @param datasets
	 */
	public CircleValueListDatasetModel(List<String> labels, List<CircleValueListDataset> datasets,
			Animation animation) {
		super(datasets, animation);
		this.labels = labels;
	}

	/**
	 * 
	 * @param labels
	 */
	public void setLabels(List<String> labels) {
		this.labels = labels;
	}

	/**
	 * returns the labels as json which will be rendered on java script site
	 * 
	 * @return
	 */
	@Override
	public String getLabelsJson() {
		return DataConverter.convertToJSArray(labels);
	}

	/**
	 * 
	 * @return
	 */
	public List<String> getLabels() {
		return labels;
	}

	/**
	 * adds new data to the model with the new label name
	 * 
	 * @param label
	 * @param datasetNumber
	 * @param value
	 * @throws ChartInconsistencyException
	 */
	public void addDataToModel(String label, int datasetNumber, Double value) throws ChartInconsistencyException {
		if (getDatasets().size() <= datasetNumber) {
			throw new ChartInconsistencyException("Array of datasets smaller than " + datasetNumber);
		}
		if (value == null) {
			throw new ChartInconsistencyException("Value can not be empty ");
		}
		CircleValueListDataset dataset = getDatasets().get(datasetNumber);
		labels.add(label);
		dataset.getData().add(value);
		update();
	}

	/**
	 * Changes the highlight color of defined dataset
	 * 
	 * @param datasetNumber
	 * @param color
	 * @throws ChartInconsistencyException
	 */
	public void changeHightlightColor(int datasetNumber, String color) throws ChartInconsistencyException {
		if (getDatasets() != null && getDatasets().size() > 0){
			CircleValueListDataset dataset = getDatasets().get(0);
			if (dataset.getHoverBackgroundColor().size() <= datasetNumber) {
				throw new ChartInconsistencyException("Array of hover background colors smaller than " + datasetNumber);
			}
			dataset.getBackgroundColor().set(datasetNumber, DataConverter.convertToJSColor(color));
			update();
		} else {
			throw new ChartInconsistencyException("No dataset found");
		}
	}

	/**
	 * Changes the fill color of defined dataset
	 * 
	 * @param datasetNumber
	 * @param color
	 * @throws ChartInconsistencyException
	 */
	public void changeFillColor(int datasetNumber, String color) throws ChartInconsistencyException {
		if (getDatasets() != null && getDatasets().size() > 0){
			CircleValueListDataset dataset = getDatasets().get(0);
			if (dataset.getBackgroundColor().size() <= datasetNumber + 1) {
				throw new ChartInconsistencyException("Array of background colors smaller than " + datasetNumber);
			}
			dataset.getBackgroundColor().set(datasetNumber, DataConverter.convertToJSColor(color));
			update();
		} else {
			throw new ChartInconsistencyException("No dataset found");
		}
	}

	/**
	 * change the value of the given dataset at defined label
	 * 
	 * @param label
	 * @param datasetNumber
	 * @param newValue
	 * @throws ChartInconsistencyException
	 */
	public void changeDataByModel(String label, int datasetNumber, Double newValue) throws ChartInconsistencyException {
		int indexOfElement = labels.indexOf(label);
		if (indexOfElement < 0) {
			throw new ChartInconsistencyException("No label with name:" + label);
		}
		if (getDatasets().size() <= datasetNumber) {
			throw new ChartInconsistencyException("Array of datasets smaller than " + datasetNumber);
		}
		if (newValue == null) {
			throw new ChartInconsistencyException("Value can not be empty ");
		}
		CircleValueListDataset dataset = getDatasets().get(datasetNumber);
		dataset.getData().set(indexOfElement, newValue);
		update();

	}

	/**
	 * sets the element under given label in indicated dataset to 0
	 * 
	 * @param label
	 * @param datasetNumber
	 * @throws ChartInconsistencyException
	 */
	public void removeDataFromModel(String label, int datasetNumber) throws ChartInconsistencyException {
		changeDataByModel(label, datasetNumber, 0D);

	}

	/**
	 * removes elements from all dataset after given label
	 * 
	 * @param label
	 * @throws ChartInconsistencyException
	 */
	public void removeDataFromModel(String label) throws ChartInconsistencyException {
		int indexOfElement = labels.indexOf(label);
		if (indexOfElement < 0) {
			throw new ChartInconsistencyException("No label with name:" + label);
		}

		labels.remove(indexOfElement);
		for (CircleValueListDataset dataset : getDatasets()) {
			dataset.getData().remove(indexOfElement);
		}
		update();

	}

}
