package de.jwic.controls.chart.impl.util;

import java.awt.Color;
import java.io.IOException;
import java.util.List;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import de.jwic.controls.chart.api.ChartDataset;

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

	public static String convertToJSColor(Color color) {
		return "rgba(" + color.getRed() + "," + color.getGreen() + ","
				+ color.getBlue() + ",.1)";
	}

	public static String convertToJSColor(Color color, String alfa) {
		return "rgba(" + color.getRed() + "," + color.getGreen() + ","
				+ color.getBlue() + "," + alfa + ")";
	}

	public static String convertToJson(List<? extends Object> datasets)
			throws JsonGenerationException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		 return  mapper.writeValueAsString(datasets);
		
	}
}
