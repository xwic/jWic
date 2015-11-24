package de.jwic.demo.chart.util;

import java.util.ArrayList;
import java.util.List;

import de.jwic.controls.chart.api.SimpleValueDataset;
import de.jwic.controls.chart.api.SimpleValueDatasetModel;
import de.jwic.controls.chart.api.ValueListDataset;
import de.jwic.controls.chart.api.ValueListDatasetModel;

/**
 * 
 * @author Karolina Marek (karolina-marek.eu)
 *
 * @date 18.11.2015
 */
public class DataModelCreator {

	public static ValueListDatasetModel getValueListDatasetModel() {
		List<String> labels = new ArrayList<String>();
		labels.add("January");
		labels.add("February");
		labels.add("Marz");
		labels.add("April");
		labels.add("May");
		labels.add("Juni");
		labels.add("Juli");
		labels.add("August");

		List<ValueListDataset> datasets = new ArrayList<ValueListDataset>();
		List<String> values = new ArrayList<String>();
		values.add("10");
		values.add("20");
		values.add("14.4");
		values.add("4");
		values.add("8");
		values.add("6");
		values.add("12");
		values.add("8");
		ValueListDataset chartd1 = new ValueListDataset("First", values);
		chartd1.setFillColor("0, 51, 153,0.9");
		datasets.add(chartd1);

		List<String> values2 = new ArrayList<String>();
		values2.add("20");
		values2.add("22");
		values2.add("3.5");
		values2.add("8");
		values2.add("12");
		values2.add("16");
		values2.add("7");
		values2.add("4");

		ValueListDataset chartd2 = new ValueListDataset("Second", values2);
		datasets.add(chartd2);
		chartd2.setFillColor("204, 0, 0,0.9");
		ValueListDatasetModel model = new ValueListDatasetModel(labels,
				datasets);
		return model;
	}

	public static SimpleValueDatasetModel getSimpleValueDatasetModel() {
		List<SimpleValueDataset> datasets = new ArrayList<SimpleValueDataset>();

		SimpleValueDataset chartd1 = new SimpleValueDataset("First", 1D,
				"#ff6666", "#66ff33");
		SimpleValueDataset chartd2 = new SimpleValueDataset("Second", 2D,
				"#ffff99", "#66ff33");
		SimpleValueDataset chartd3 = new SimpleValueDataset("Third", 3D,
				"#0099ff", "#66ff33");
		SimpleValueDataset chartd4 = new SimpleValueDataset("Fourth", 4D,
				"#ffcc66", "#66ff33");
		SimpleValueDataset chartd5 = new SimpleValueDataset("Fifth", 5D,
				"#ff66cc", "#66ff33");
		SimpleValueDataset chartd6 = new SimpleValueDataset("Sexsth", 6D,
				"#003300", "#66ff33");

		datasets.add(chartd1);
		datasets.add(chartd2);
		datasets.add(chartd3);
		datasets.add(chartd4);
		datasets.add(chartd5);
		datasets.add(chartd6);
		SimpleValueDatasetModel model = new SimpleValueDatasetModel(datasets);
		return model;
	}
}
