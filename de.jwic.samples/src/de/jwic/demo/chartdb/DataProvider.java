/**
 * 
 */
package de.jwic.demo.chartdb;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import de.jwic.controls.chart.api.SimpleValueDataset;
import de.jwic.controls.chart.api.SimpleValueDatasetModel;
import de.jwic.controls.chart.api.ValueListDataset;
import de.jwic.controls.chart.api.ValueListDatasetModel;
import de.jwic.controls.chart.impl.util.DataConverter;

/**
 * Provides the data based upon filter criteria.
 * @author lippisch
 */
public class DataProvider {

	private final static String[] MONTH = {
		"Jan", "Feb", "Mar", "Apr", "May", "Jun",
		"Jul", "Aug", "Sep", "Oct", "Nov", "Dec"
	};
	
	private final static String[] USER_TYPES = {
		"Commercial", "Education", "Personal Use", "Unknown"
	};
	private final static String[] COLORS = {
		"#3333ee",
		"#6666ff",
		"#9999ff",
		"#BBffbb",
		"#66ff66",
		"#ffff66"
	};
	
	/**
	 * 
	 */
	public DataProvider() {

	}

	/**
	 * Return the # of users registered.
	 * @param year
	 * @return
	 */
	public ValueListDatasetModel getTotalUsers(String year) {
		
		List<String> labels = new ArrayList<String>();
		for (String m : MONTH) {
			labels.add(m + "-" + year);
		}

		List<ValueListDataset> datasets = new ArrayList<ValueListDataset>();
		List<Double> values = new ArrayList<Double>();

		// add some random data.
		Random rnd = new Random(year.hashCode());
		int total = rnd.nextInt(100);
		for (int i = 0; i < 12; i++) {
			values.add((double)total);
			total += rnd.nextInt(100);
		}
		
		ValueListDataset chartd1 = new ValueListDataset("Users", values);
		chartd1.setFillColor("70, 130, 223,0.9");
		chartd1.setStrokeColor("0, 91, 153,0.9");
		datasets.add(chartd1);


		ValueListDatasetModel model = new ValueListDatasetModel(labels, datasets);
		return model;
	}

	/**
	 * Returns the user distribution by year and type.
	 * @param year
	 * @return
	 */
	public ValueListDatasetModel getTotalUsersByType(String year) {
		List<String> labels = new ArrayList<String>();
		for (String m : MONTH) {
			labels.add(m + "-" + year);
		}

		List<ValueListDataset> datasets = new ArrayList<ValueListDataset>();


		for (int i = 0 ; i < USER_TYPES.length; i++) {
			List<Double> values = new ArrayList<Double>();
			// add some random data.
			Random rnd = new Random(year.hashCode()+i);
			int total = rnd.nextInt(10);
			for (int iM = 0; iM < 12; iM++) {
				values.add((double) total);
				total += rnd.nextInt(10);
			}

			ValueListDataset chartd = new ValueListDataset(USER_TYPES[i], values);
			chartd.setFillColor(DataConverter.convertToJSColor(COLORS[i]));
			datasets.add(chartd);
		}

		ValueListDatasetModel model = new ValueListDatasetModel(labels, datasets);
		return model;
	}

	/**
	 * Returns the user distribution.
	 * @return
	 */
	public SimpleValueDatasetModel getUserTypeDistribution(String year) {
		
		List<SimpleValueDataset> datasets = new ArrayList<SimpleValueDataset>();

		Random rnd = new Random(year.hashCode());
		
		for (int idx = 0 ; idx < USER_TYPES.length; idx++) {
			String ut = USER_TYPES[idx];
			String col = COLORS[idx];
			double value = (double)rnd.nextInt(100);
			SimpleValueDataset valDS = new SimpleValueDataset(ut, value, col, "#a0a0ff");
			datasets.add(valDS);
		}
		
		SimpleValueDatasetModel model = new SimpleValueDatasetModel(datasets);
		return model;
	}

}
