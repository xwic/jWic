package de.jwic.controls.chart.impl;

import java.util.Date;
import java.util.List;

import de.jwic.controls.chart.api.ChartInconsistencyException;
import de.jwic.controls.chart.api.ChartModel;
import de.jwic.controls.chart.impl.util.DataConverter;

/**
 * 
 * @author Karolina Marek (karolina-marek.eu)
 *
 * @date 08.12.2015
 */
public class DateTimeChartModel extends ChartModel<DateTimeChartDataset> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7014827274374612553L;

	public DateTimeChartModel(List<DateTimeChartDataset> datasets) {
		super(datasets);
	}

	/**
	 * returns the dataset as json array which will be rendered directly on java
	 * script site
	 * 
	 * @return
	 */
	public String getDatasetsJson() {

		return DataConverter.convertDateTimeModelToJson(getDatasets());

	}

	/**
	 * adds new data to the model with the new label name
	 * 
	 * @param label
	 * @param datasetNumber
	 * @param value
	 * @throws ChartInconsistencyException
	 */
	public void addDataToModel(int datasetNumber, Date newValueX,
			Double newValueY) throws ChartInconsistencyException {
		if (newValueX == null) {
			throw new ChartInconsistencyException("Value can not be empty ");
		}
		if (getDatasets().size() <= datasetNumber) {
			throw new ChartInconsistencyException(
					"Array of datasets smaller than " + datasetNumber);
		}

		DateTimeChartDataset dataset = getDatasets().get(datasetNumber);
		Double result = dataset.getValues().get(newValueX);
		if (result != null) {
			throw new ChartInconsistencyException(
					"Can not add already existing label " + newValueX.getTime());
		}
		dataset.add(newValueX, newValueY);

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
			Date newValueX, Double newValueY)
			throws ChartInconsistencyException {
		if (getDatasets().size() <= datasetNumber) {
			throw new ChartInconsistencyException(
					"Array of datasets smaller than " + datasetNumber);
		}
		if (newValueX == null) {
			throw new ChartInconsistencyException("Value can not be empty ");
		}
		DateTimeChartDataset dataset = getDatasets().get(datasetNumber);
		Double result = dataset.getValues().get(newValueX);
		if (result == null) {
			throw new ChartInconsistencyException(
					"Can not update not existing label " + newValueX.getTime());
		}
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
	public void removeDataFromModel(String label, int datasetNumber,
			Date newValueX)
			throws ChartInconsistencyException {
		if (getDatasets().size() <= datasetNumber) {
			throw new ChartInconsistencyException(
					"Array of datasets smaller than " + datasetNumber);
		}
		if (newValueX == null) {
			throw new ChartInconsistencyException("Value can not be empty ");
		}
		DateTimeChartDataset dataset = getDatasets().get(datasetNumber);
		Double result = dataset.getValues().get(newValueX);
		if (result == null) {
			throw new ChartInconsistencyException(
					"Can not remove not existing label " + newValueX.getTime());
		}
		dataset.remove(newValueX);
		getDatasets().add(dataset);
		update();

	}

}
