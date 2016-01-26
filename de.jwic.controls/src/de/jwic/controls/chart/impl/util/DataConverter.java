package de.jwic.controls.chart.impl.util;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Field;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map.Entry;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import de.jwic.controls.chart.api.ChartConfiguration;
import de.jwic.controls.chart.api.ChartDataset;
import de.jwic.controls.chart.api.ChartType;
import de.jwic.controls.chart.api.JsonChartName;
import de.jwic.controls.chart.impl.DateTimeChartDataset;

/**
 * 
 * @author Karolina Marek (karolina-marek.eu)
 *
 * @date 29.10.2015
 */
public class DataConverter {

	private static final DateFormat DF = new SimpleDateFormat("yyyy-MM-dd HH:mm");

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

	/**
	 * convert the color into the appropriate one used on java sript site
	 * 
	 * @param color
	 * @return
	 */
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

	/**
	 * 
	 * @param color
	 * @param alfa
	 * @return
	 */
	public static String convertToJSColor(Color color, String alfa) {
		return "rgba(" + color.getRed() + "," + color.getGreen() + "," + color.getBlue() + "," + alfa + ")";
	}

	/**
	 * 
	 * @param datasets
	 * @param type
	 * @return
	 */
	public static String convertToJson(List<? extends ChartDataset> datasets, ChartType type) {
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
			LOGGER.error("Can not parse configuration for chart because of error: " + e.getMessage());
			return "{}";
		}

	}

	/**
	 * 
	 * @param obj
	 * @return
	 */
	public static String convertToJson(Object obj) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			return mapper.writeValueAsString(obj);
		} catch (Exception e) {
			LOGGER.error("Can not parse configuration for chart because of error: " + e.getMessage());
			return "{}";
		}

	}

	/**
	 * 
	 * @param obj
	 * @return
	 */
	public static String convertDateTimeModelToJson(List<DateTimeChartDataset> list) {
		JSONArray array = new JSONArray();
		try {

			for (DateTimeChartDataset ds : list) {
				JSONObject obj = new JSONObject();
				obj.put("label", ds.getLabel());

				obj.put("pointColor", ds.getPointColor());

				obj.put("pointStrokeColor", ds.getPointStrokeColor());
				obj.put("strokeColor", ds.getStrokeColor());
				JSONArray ar = new JSONArray();
				for (Entry<Date, Double> mapEntry : ds.getValues().entrySet()) {
					JSONObject entry = new JSONObject();

					entry.put("x", DF.format(mapEntry.getKey()));
					entry.put("y", mapEntry.getValue());
					ar.put(entry);
				}
				obj.put("data", ar);
				array.put(obj);
			}

		} catch (JSONException e) {
			LOGGER.error("Can not parse model for chart because of error: " + e.getMessage());
		}
		String json = array.toString();
		// json = json.replaceAll("\"new", "new");
		// json = json.replaceAll("')\"", "')");
		return json;
	}

	/**
	 * convert the configuration into the json array
	 * 
	 * @param obj
	 * @param type
	 * @return
	 */
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
				for (Field field : obj.getClass().getSuperclass().getDeclaredFields()) {
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
			LOGGER.error("Can not parse configuration for chart because of error: " + e.getMessage());
			return "{}";
		}
	}

	/**
	 * 
	 * @param type
	 * @param field
	 * @return
	 */
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
			case STACKED_BAR:
				value = col.stacked();
				break;
			case DATE_TIME:
				value = col.dateTime();
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
