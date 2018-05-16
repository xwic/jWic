/*
 * Copyright (c) NetApp Inc. - All Rights Reserved
 * 
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 * 
 * de.jwic.controls.slickgrid.SlickGridKeyValueEditorValuesProvider 
 */
package de.jwic.controls.slickgrid;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.google.gson.JsonObject;

/**
 * @author Adrian Ionescu
 */
public class SlickGridKeyValueEditorValuesProvider implements ISlickGridEditorValuesProvider {
	
	private static final long serialVersionUID = 1346079225130442465L;

	/**
	 * This is used in the SlickGrid.static.js - DropDownEditor
	 */
	private final static String KEY = "key";
	
	/**
	 * This is used in the SlickGrid.static.js - DropDownEditor
	 */
	private final static String TITLE = "title";
	
	private Map<String, String> keyValuePairs;

	/**
	 * @param keyValuePairs
	 */
	public SlickGridKeyValueEditorValuesProvider(Map<String, String> keyValuePairs) {
		this.keyValuePairs = keyValuePairs;
	}
	
	/* (non-Javadoc)
	 * @see de.jwic.controls.slickgrid.ISlickGridEditorValuesProvider#getEditorValues()
	 */
	@Override
	public List<JsonObject> getEditorValues() {
		List<JsonObject> values = new ArrayList<JsonObject>();
		
		for (Entry<String, String> entry : keyValuePairs.entrySet()) {
			JsonObject obj = new JsonObject();
			obj.addProperty(KEY, entry.getKey());
			obj.addProperty(TITLE, entry.getValue());
			
			values.add(obj);
		}
		
		return values;
	}

}
