package de.jwic.mobile.controls.mtable;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.List;

import org.apache.log4j.Logger;

/**
 * 
 * @author Karolina Marek (karolina-marek.eu)
 *
 * @date 02.01.2016
 */
public class MTableRow implements Serializable {

	private static final Logger LOGGER = Logger.getLogger(MTableRow.class);
	private Object object;
	private List<String> visibleCells;
	private String cssClass;

	public MTableRow(Object objectToTransform, List<String> visibleCells) {
		this.object = objectToTransform;
		this.visibleCells = visibleCells;
	}

	public String getHtmlRow() {
		StringBuilder builder = new StringBuilder();
		for (String cell : visibleCells) {
			Field field;
			try {
				field = object.getClass().getDeclaredField(cell);
				if (field != null) {
					field.setAccessible(true);

					Object value = field.get(object);
					builder.append("<td class='" + cssClass + "'>");
					builder.append(value);
					builder.append("</td>");
					field.setAccessible(false);
				}
			} catch (IllegalArgumentException e) {
				LOGGER.warn(e.getMessage());
			} catch (IllegalAccessException e) {
				LOGGER.warn(e.getMessage());
			} catch (NoSuchFieldException e) {
				LOGGER.warn(e.getMessage());
			} catch (SecurityException e) {
				LOGGER.warn(e.getMessage());
			}

		}
		return builder.toString();
	}
}
