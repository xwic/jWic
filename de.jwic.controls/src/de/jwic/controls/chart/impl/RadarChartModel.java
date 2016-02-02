package de.jwic.controls.chart.impl;

import java.util.List;

import de.jwic.controls.chart.api.ChartInconsistencyException;
import de.jwic.controls.chart.api.ChartModel;
import de.jwic.controls.chart.api.ValueListDataset;
import de.jwic.controls.chart.impl.util.DataConverter;

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

	/**
	 * 
	 * @param labels
	 * @param datasets
	 */
	public RadarChartModel(List<String> labels, List<RadarChartDataset> datasets) {
		super(datasets);
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
	public void addDataToModel(String label, int datasetNumber, Double value)
			throws ChartInconsistencyException {
		if (getDatasets().size() <= datasetNumber) {
			throw new ChartInconsistencyException(
					"Array of datasets smaller than " + datasetNumber);
		}
		if (value == null) {
			throw new ChartInconsistencyException("Value can not be empty ");
		}
		RadarChartDataset dataset = getDatasets().get(datasetNumber);
		labels.add(label);
		dataset.getData().add(value);
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
		int indexOfElement = labels.indexOf(label);
		if (indexOfElement < 0) {
			throw new ChartInconsistencyException("No label with name:" + label);
		}
		if (getDatasets().size() <= datasetNumber) {
			throw new ChartInconsistencyException(
					"Array of datasets smaller than " + datasetNumber);
		}
		if (newValue == null) {
			throw new ChartInconsistencyException("Value can not be empty ");
		}
		RadarChartDataset dataset = getDatasets().get(datasetNumber);
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
	public void removeDataFromModel(String label, int datasetNumber)
			throws ChartInconsistencyException {
		changeDataByModel(label, datasetNumber, 0D);

	}

	/**
	 * removes elements from all dataset after given label
	 * 
	 * @param label
	 * @throws ChartInconsistencyException
	 */
	public void removeDataFromModel(String label)
			throws ChartInconsistencyException {
		int indexOfElement = labels.indexOf(label);
		if (indexOfElement < 0) {
			throw new ChartInconsistencyException("No label with name:" + label);
		}

		labels.remove(indexOfElement);
		for (RadarChartDataset dataset : getDatasets()) {
			dataset.getData().remove(indexOfElement);
		}
		update();

	}

	/**
	 * Changes the highlight color of defined dataset
	 * 
	 * @param datasetNumber
	 * @param color
	 * @throws ChartInconsistencyException
	 */
	public void changeHightlightColor(int datasetNumber, String color)
			throws ChartInconsistencyException {
		if (getDatasets().size() <= datasetNumber) {
			throw new ChartInconsistencyException(
					"Array of datasets smaller than " + datasetNumber);
		}
		ValueListDataset dataset = getDatasets().get(datasetNumber);
		dataset.setHighlightColor(color);
		update();
	}

	/**
	 * Changes the fill color of defined dataset
	 * 
	 * @param datasetNumber
	 * @param color
	 * @throws ChartInconsistencyException
	 */
	public void changeFillColor(int datasetNumber, String color)
			throws ChartInconsistencyException {
		if (getDatasets().size() <= datasetNumber) {
			throw new ChartInconsistencyException(
					"Array of datasets smaller than " + datasetNumber);
		}
		ValueListDataset dataset = getDatasets().get(datasetNumber);
		dataset.setFillColor(color);
		update();
	}

}
