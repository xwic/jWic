package de.jwic.controls.chart.api.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * 
 * @author Karolina Marek (karolina-marek.eu)
 *
 * @date 09.12.2015
 */
public class ChartHelper {
	public static String getFileContent(String fileName) {
		StringBuilder contentBuilder = new StringBuilder();
		if (fileName != null) {
			File file = new File(fileName);
			if (file != null && file.exists()) {
				try {
					BufferedReader in = new BufferedReader(new FileReader(
							fileName));
					String str;
					while ((str = in.readLine()) != null) {
						contentBuilder.append(str);
					}
					in.close();
				} catch (IOException e) {
				}
				String content = contentBuilder.toString();
				return content;
			} else {
				return "";
			}
		}
		return null;
	}
}
