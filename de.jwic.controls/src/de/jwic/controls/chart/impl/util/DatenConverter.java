package de.jwic.controls.chart.impl.util;

import java.awt.Color;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONException;
import org.json.JSONObject;

import de.jwic.controls.chart.api.ChartType;
import de.jwic.controls.chart.api.configuration.ChartConfiguration;
import de.jwic.controls.chart.api.configuration.JsonChartName;

/**
 * 
 * @author Karolina Marek (karolina-marek.eu)
 *
 * @date 29.10.2015
 */
public class DatenConverter {

	/**
	 * convert list to the position like :
	 * 
	 * ["January", "February", "March", "April", "May", "June", "July"]
	 * 
	 * @param list
	 * @return
	 */
	public static String convertToJSArray(List<String> list) {
		StringBuilder builder = new StringBuilder();
		builder.append("[");
		boolean hasElementInserted = false;
		for (String obj : list) {
			if (hasElementInserted) {
				builder.append(",");
			}
			builder.append("'");
			builder.append(obj);
			builder.append("'");
			hasElementInserted = true;
		}
		builder.append("]");
		return builder.toString();
	}

	public static String convertToJSColor(Color scaleLineColor) {
		return "rgba(" + scaleLineColor.getRed() + ","
				+ scaleLineColor.getGreen() + "," + scaleLineColor.getBlue()
				+ ",.1)";
	}

	public static String convertToJSColor(String color) {
		if (color == null) {
			return null;
		}
		if (color.startsWith("#")) {
			return color;
		}
		if (color.contains(";")) {
			String[] arr = color.split(";");
			if (arr.length >= 3) {

			}
			return null;
		}
		return null;
	}

	public static String convertToJSColor(Color color, String alfa) {
		return "rgba(" + color.getRed() + "," + color.getGreen() + ","
				+ color.getBlue() + "," + alfa + ")";
	}

	public static String convertToJson(List<? extends Object> datasets)
			throws JsonGenerationException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(datasets);

	}

	public static String convertToJson(Object obj)
			throws JsonGenerationException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(obj);

	}

	public static String convertToJson(ChartConfiguration obj, ChartType type)
			throws JsonGenerationException, JsonMappingException, IOException,
			JSONException, IllegalArgumentException, IllegalAccessException {
		// ObjectMapper mapper = new ObjectMapper();
		// JSONObject jsonAccountInquiry;
		// jsonAccountInquiry = new JSONObject(mapper.writeValueAsString(obj));

		JSONObject object = new JSONObject();
		for (Field field : obj.getClass().getDeclaredFields()) {
			String fieldName = getNameForChartType(type, field);
			field.setAccessible(true);
			Object fieldValue = field.get(obj);
			field.setAccessible(false);
			object.append(fieldName, fieldValue);
		}
		return object.toString();
	}

	private static String getNameForChartType(ChartType type, Field field) {
		JsonChartName col;
		String value = null;
		if ((col = field.getAnnotation(JsonChartName.class)) != null) {
			switch (type) {
			case BAR:
				value = col.bar();
			case CIRCLE:
				;
			case LINE:
				;
			case POLAR:
				;
			case RADAR:
				;
			default:
				return field.getName();

			}
		}
		if (StringUtils.isEmpty(value)) {
			value = field.getName();
		}
		return value;

	}
}
