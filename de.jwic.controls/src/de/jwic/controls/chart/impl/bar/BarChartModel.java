package de.jwic.controls.chart.impl.bar;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import de.jwic.controls.chart.api.ChartModel;
import de.jwic.controls.chart.api.exception.ChartInconsistencyException;
import de.jwic.controls.chart.impl.util.DatenConverter;

/**
 * 
 * @author karolina
 *
 */
public class BarChartModel extends ChartModel<BarChartDataset> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8317049871668813963L;
	private List<String> labels;

	public BarChartModel(List<String> labels, List<BarChartDataset> datasets) {
		super(datasets);
		this.labels = labels;
	}

	public void setLabels(List<String> labels) {
		this.labels = labels;
	}

	public String getLabelsJson() {
		return DatenConverter.convertToJSArray(labels);
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
	
		if (getDatasets().size() <= datasetNumber) {
			throw new ChartInconsistencyException(
					"Array of datasets smaller than " + datasetNumber);
		}
		if (value == null) {
			throw new ChartInconsistencyException("Value can not be empty ");
		}
		BarChartDataset dataset = getDatasets().get(datasetNumber);
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
		BarChartDataset dataset = getDatasets().get(datasetNumber);
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
		for (BarChartDataset dataset : getDatasets()) {
			dataset.getData().remove(indexOfElement);
		}
		update();

	}

}
