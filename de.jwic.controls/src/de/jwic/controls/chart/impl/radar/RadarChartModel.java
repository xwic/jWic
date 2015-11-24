package de.jwic.controls.chart.impl.radar;

import java.util.List;

import de.jwic.controls.chart.api.ChartModel;
import de.jwic.controls.chart.api.exception.ChartInconsistencyException;
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

	public RadarChartModel(List<String> labels, List<RadarChartDataset> datasets) {
		super(datasets);
		this.labels = labels;
	}

	public void setLabels(List<String> labels) {
		this.labels = labels;
	}

	public String getLabelsJson() {
		return DataConverter.convertToJSArray(labels);
	}
	
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
//		int indexOfElement = labels.indexOf(label);
//		if (indexOfElement < 0) {
//			throw new ChartInconsistencyException("No label with name:" + label);
//		}
		if (getDatasets().size() <= datasetNumber) {
			throw new ChartInconsistencyException(
					"Array of datasets smaller than " + datasetNumber);
		}
		if (value == null) {
			throw new ChartInconsistencyException("Value can not be empty ");
		}
		RadarChartDataset dataset = getDatasets().get(datasetNumber);
		labels.add(label);
		dataset.getData().add(value.toString());
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
		dataset.getData().set(indexOfElement, newValue.toString());
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

}
