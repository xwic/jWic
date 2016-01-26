package de.jwic.controls.chart;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.junit.Test;

import de.jwic.controls.chart.api.ChartDataset;
import de.jwic.controls.chart.api.ValueListDataset;
import de.jwic.controls.chart.impl.util.DataConverter;

public class DatenConverterTest {

	/**
	 * ["January", "February", "March", "April", "May", "June", "July"]
	 */
	@Test
	public void convertToJSArrayTest() {

		List<String> list = new ArrayList<String>();
		list.add("January");
		list.add("February");
		list.add("March");
		list.add("April");
		list.add("May");
		list.add("June");
		list.add("July");
		System.out.println(DataConverter.convertToJSArray(list));
	}

	@Test
	public void convertToJsonTest() throws JsonGenerationException,
			JsonMappingException, IOException {
		List<ChartDataset> datasets = new ArrayList<ChartDataset>();
		List<Double> values = new ArrayList<Double>();
		values.add(1D);
		values.add(2D);
		values.add(3D);
		values.add(4D);
		values.add(5D);
		values.add(6D);
		values.add(7D);
		values.add(8D);
		ValueListDataset chartd1 = new ValueListDataset("First", values);
		datasets.add(chartd1);

		List<Double> values2 = new ArrayList<Double>();
		values2.add(10D);
		values2.add(22D);
		values2.add(33D);
		values2.add(4D);
		values2.add(5D);
		values2.add(6D);
		values2.add(7D);
		values2.add(8D);

		ChartDataset chartd2 = new ValueListDataset("Second", values2);
		datasets.add(chartd2);

		System.out.println(DataConverter.convertToJson(datasets));
	}
}
