/*
 * Copyright (c) NetApp Inc. - All Rights Reserved
 * 
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 * 
 * de.jwic.controls.slickgrid.ISlickGridDataProvider 
 */
package de.jwic.controls.slickgrid;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * The default value provider for the SlickGrid.
 * This only works if the name of the fields in the object match the fields of the columns.
 * 
 * @author Adrian Ionescu
 */
public class SlickGridDefaultColumnValueProvider implements ISlickGridColumnValueProvider {

	private static final long serialVersionUID = -1273955457665038383L;
	
	private final static Log log = LogFactory.getLog(SlickGridDefaultColumnValueProvider.class);

	/* (non-Javadoc)
	 * @see de.jwic.controls.slickgrid.ISlickGridValueProvider#getValue(de.jwic.controls.slickgrid.SlickGridColumn, java.lang.Object)
	 */
	@Override
	public Object getValue(SlickGridColumn column, Object obj) {
		if (obj == null) {
			return "";
		}

		Object result;
		
		String fieldName = column.getField();
		try {
			Field field = obj.getClass().getDeclaredField(fieldName);
			if (field == null) {
				throw new IllegalArgumentException("The field '" + fieldName + "' cannot be found in the POJO");
			}
			field.setAccessible(true);
			Object value = field.get(obj);
			if (value == null || value instanceof Boolean || value instanceof Number) {
				result = value;
			} else if (value instanceof Date && column.getDateFormat() != null) {
				result = new SimpleDateFormat(column.getDateFormat()).format(value);
			} else {
				result = value.toString();
			}
		} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
			log.error(e.getMessage(), e);
			result = "!error!";
		}

		return result;
	}
}