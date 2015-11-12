package de.jwic.controls.chart.api;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;

import de.jwic.controls.chart.impl.util.DatenConverter;

/**
 * 
 * @author Karolina Marek (karolina-marek.eu)
 *
 * @date 29.10.2015
 */
public abstract class ChartModel<Dataset extends ChartDataset> implements
		Serializable {

	
	private List<Dataset> datasets;

	public ChartModel( List<Dataset> datasets) {
	
		this.datasets = datasets;
	}


	public String getDatasets() {
		try {
			return DatenConverter.convertToJson(datasets);
		} catch (JsonGenerationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "{}";
	}

	public List<Dataset> getDatasetAsList() {
		return datasets;
	}

	public void setDatasets(List<Dataset> datasets) {
		this.datasets = datasets;
	}

}
