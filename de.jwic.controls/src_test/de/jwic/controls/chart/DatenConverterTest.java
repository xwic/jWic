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
		List<String> values = new ArrayList<String>();
		values.add("1");
		values.add("2");
		values.add("3");
		values.add("4");
		values.add("5");
		values.add("6");
		values.add("7");
		values.add("8");
		ValueListDataset chartd1 = new ValueListDataset("First", values);
		datasets.add(chartd1);

		List<String> values2 = new ArrayList<String>();
		values2.add("10");
		values2.add("22");
		values2.add("33");
		values2.add("4");
		values2.add("5");
		values2.add("6");
		values2.add("7");
		values2.add("8");

		ChartDataset chartd2 = new ValueListDataset("Second", values2);
		datasets.add(chartd2);

		System.out.println(DataConverter.convertToJson(datasets));
	}
}
