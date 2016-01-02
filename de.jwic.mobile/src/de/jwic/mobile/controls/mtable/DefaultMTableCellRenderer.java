package de.jwic.mobile.controls.mtable;

import java.lang.reflect.Field;

import org.apache.log4j.Logger;

/**
 * 
 * @author Karolina Marek (karolina-marek.eu)
 *
 * @date 21.12.2015
 */
public class DefaultMTableCellRenderer implements IMTableCellRenderer {

	private static final Logger LOGGER = Logger
			.getLogger(DefaultMTableCellRenderer.class);

	@Override
	public String getCell(String fieldName, Object object) {

		Field field;
		try {
			field = object.getClass().getDeclaredField(fieldName);
			if (field != null) {
				field.setAccessible(true);

				Object value = field.get(object);
				field.setAccessible(false);
				if (value != null) {
					return value.toString();
				}

				return "";
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

		return "";
	}

}
