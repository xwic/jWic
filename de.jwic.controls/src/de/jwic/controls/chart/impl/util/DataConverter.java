package de.jwic.controls.chart.impl.util;

import java.awt.Color;
import java.lang.reflect.Field;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONObject;

import de.jwic.controls.chart.api.ChartDataset;
import de.jwic.controls.chart.api.ChartType;
import de.jwic.controls.chart.api.configuration.ChartConfiguration;
import de.jwic.controls.chart.api.configuration.JsonChartName;

/**
 * 
 * @author Karolina Marek (karolina-marek.eu)
 *
 * @date 29.10.2015
 */
public class DataConverter {

	private static final Logger LOGGER = Logger.getLogger(DataConverter.class);

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

	public static String convertToJSColor(String color) {
		if (color == null) {
			return null;
		}
		if (color.startsWith("#")) {
			return color;
		} else if (color.startsWith("rgba")) {
			return color;
		}
		if (color.contains(",")) {
			String[] arr = color.split(",");
			if (arr.length >= 3) {
				return "rgba(" + color + ")";
			}
			return null;
		}
		return null;
	}

	public static String convertToJSColor(Color color, String alfa) {
		return "rgba(" + color.getRed() + "," + color.getGreen() + ","
				+ color.getBlue() + "," + alfa + ")";
	}

	public static String convertToJson(List<? extends ChartDataset> datasets,
			ChartType type) {
		try {
			JSONArray array = new JSONArray();
			for (ChartDataset dataset : datasets) {
				JSONObject object = new JSONObject();
				for (Field field : dataset.getClass().getFields()) {
					String fieldName = getNameForChartType(type, field);
					if (StringUtils.isEmpty(fieldName)) {
						fieldName = field.getName();
					}
					field.setAccessible(true);
					Object fieldValue = field.get(dataset);
					field.setAccessible(false);
					object.append(fieldName, fieldValue);

				}
				array.put(object);
			}
			return array.toString();
		} catch (Exception e) {
			LOGGER.error("Can not parse configuration for chart because of error: "
					+ e.getMessage());
			return "{}";
		}

	}

	public static String convertToJson(Object obj) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			return mapper.writeValueAsString(obj);
		} catch (Exception e) {
			LOGGER.error("Can not parse configuration for chart because of error: "
					+ e.getMessage());
			return "{}";
		}

	}

	public static String convertToJson(ChartConfiguration obj, ChartType type) {
		try {
			JSONObject object = new JSONObject();
			for (Field field : obj.getClass().getDeclaredFields()) {
				String fieldName = getNameForChartType(type, field);
				if (!StringUtils.isEmpty(fieldName)) {
					field.setAccessible(true);
					Object fieldValue = field.get(obj);
					field.setAccessible(false);
					object.put(fieldName, fieldValue);
				}
			}
			if (obj.getClass().getSuperclass() != null) {
				for (Field field : obj.getClass().getSuperclass()
						.getDeclaredFields()) {
					String fieldName = getNameForChartType(type, field);
					if (!StringUtils.isEmpty(fieldName)) {
						field.setAccessible(true);
						Object fieldValue = field.get(obj);
						field.setAccessible(false);
						object.put(fieldName, fieldValue);
					}
				}
			}
			return object.toString();
		} catch (Exception e) {
			LOGGER.error("Can not parse configuration for chart because of error: "
					+ e.getMessage());
			return "{}";
		}
	}

	private static String getNameForChartType(ChartType type, Field field) {
		JsonChartName col;
		String value = null;
		if ((col = field.getAnnotation(JsonChartName.class)) != null) {
			switch (type) {
			case BAR:
				value = col.bar();
				break;
			case CIRCLE:
				value = col.circle();
				break;
			case LINE:
				value = col.line();
				break;
			case POLAR:
				value = col.polar();
				break;
			case RADAR:
				value = col.radar();
				break;
			default:
				value = null;

			}
		} else {

			value = field.getName();

		}
		return value;

	}
}
