/*
 * Copyright (c) NetApp Inc. - All Rights Reserved
 * 
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 * 
 * de.jwic.controls.slickgrid.SlickGridDataRowAdapter 
 */
package de.jwic.controls.slickgrid;

import java.lang.reflect.Type;
import java.util.Date;
import java.util.Map.Entry;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

/**
 * @author Adrian Ionescu
 */
class SlickGridDataRowGsonAdapter<T> implements JsonSerializer<SlickGridDataRow<T>> {

	/* (non-Javadoc)
	* @see com.google.gson.JsonSerializer#serialize(java.lang.Object, java.lang.reflect.Type, com.google.gson.JsonSerializationContext)
	*/
	@Override
	public JsonElement serialize(SlickGridDataRow<T> src, Type typeOfSrc, JsonSerializationContext context) {
		JsonObject obj = new JsonObject();
		
		for (Entry<String, Object> entry : src.getMap().entrySet()) {
			String key = entry.getKey();
			Object value = entry.getValue();
			
			if (value == null) {
				obj.addProperty(key, (String) null);
			} else if (value instanceof Boolean) {
				obj.addProperty(key, (Boolean) value);
			} else if (value instanceof Number) {
				obj.addProperty(key, (Number) value);
			} else if (value instanceof Date) {
				// dates are sent to the client as milliseconds
				// the grid column should have a date formatter to process them
				obj.addProperty(key, ((Date) value).getTime());
			} else if (value instanceof KeyTitlePair) {
				obj.addProperty(key, ((KeyTitlePair) value).getKey());
			} else {
				obj.addProperty(key, value.toString());
			}
		}

		return obj;
	}
}