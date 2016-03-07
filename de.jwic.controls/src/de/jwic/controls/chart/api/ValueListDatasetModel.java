package de.jwic.controls.chart.api;

import java.util.List;

import de.jwic.controls.chart.impl.util.DataConverter;

/**
 * 
 * @author Karolina Marek (karolina-marek.eu)
 *
 * @date 18.11.2015
 */
public class ValueListDatasetModel extends ChartModel<ValueListDataset> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2421385147478923715L;
	private List<String> labels;
	private List<YAxes> yaxes;

	/**
	 * 
	 * @param labels
	 * @param datasets
	 */
	public ValueListDatasetModel(List<String> labels, List<ValueListDataset> datasets) {
		super(datasets);
		this.labels = labels;
	}
	
	/**
	 * 
	 * @param labels
	 * @param datasets
	 */
	public ValueListDatasetModel(List<String> labels, List<ValueListDataset> datasets, List<YAxes> yaxes) {
		super(datasets);
		this.labels = labels;
		this.yaxes = yaxes;
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
	 * @return the yaxes
	 */
	public List<YAxes> getYaxes() {
		return yaxes;
	}
	
	/**
	 * returns the yaxes as json which will be rendered on java script site
	 * 
	 * @return
	 */
	public String getYaxesJson() {
		return DataConverter.convertToJson(yaxes);
	}

	/**
	 * @param yaxes the yaxes to set
	 */
	public void setYaxes(List<YAxes> yaxes) {
		this.yaxes = yaxes;
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
		ValueListDataset dataset = getDatasets().get(datasetNumber);
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
		if (getDatasets().size() <= datasetNumber) {
			throw new ChartInconsistencyException("Array of datasets smaller than " + datasetNumber);
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
	public void changeFillColor(int datasetNumber, String color) throws ChartInconsistencyException {
		if (getDatasets().size() <= datasetNumber) {
			throw new ChartInconsistencyException("Array of datasets smaller than " + datasetNumber);
		}
		ValueListDataset dataset = getDatasets().get(datasetNumber);
		dataset.setFillColor(color);
		update();
	}

	/**
	 * Changes the stroke color of defined dataset
	 * 
	 * @param datasetNumber
	 * @param color
	 * @throws ChartInconsistencyException
	 */
	public void changeStrokeColor(int datasetNumber, String color) throws ChartInconsistencyException {
		if (getDatasets().size() <= datasetNumber) {
			throw new ChartInconsistencyException("Array of datasets smaller than " + datasetNumber);
		}
		ValueListDataset dataset = getDatasets().get(datasetNumber);
		dataset.setStrokeColor(color);
		update();
	}

	/**
	 * Changes the highlight stroke color of defined dataset
	 * 
	 * @param datasetNumber
	 * @param color
	 * @throws ChartInconsistencyException
	 */
	public void changeHighlightColorStroke(int datasetNumber, String color) throws ChartInconsistencyException {
		if (getDatasets().size() <= datasetNumber) {
			throw new ChartInconsistencyException("Array of datasets smaller than " + datasetNumber);
		}
		ValueListDataset dataset = getDatasets().get(datasetNumber);
		dataset.setHighlightStroke(color);
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
		ValueListDataset dataset = getDatasets().get(datasetNumber);
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
		for (ValueListDataset dataset : getDatasets()) {
			dataset.getData().remove(indexOfElement);
		}
		update();

	}

}
