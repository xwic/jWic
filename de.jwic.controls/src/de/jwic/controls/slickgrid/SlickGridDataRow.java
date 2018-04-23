/*
 * Copyright (c) NetApp Inc. - All Rights Reserved
 * 
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 * 
 * de.jwic.controls.slickgrid.SlickGridData 
 */
package de.jwic.controls.slickgrid;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Adrian Ionescu
 */
class SlickGridDataRow implements Serializable {
	
	private static final long serialVersionUID = 671484606921050314L;
	
	/**
	 * This name is used in JS.. see SlickGrid.static.js
	 */
	private static final String UNIQUE_IDENTIFIER_FIELD_NAME = "slickGridRowUID";
	
	/**
	 * This name is used in JS.. see SlickGrid.js
	 */
	private static final String NON_EDITABLE_PROPERTIES_FIELD_NAME = "slickGridNonEditableProperties";
	
	private List<String> readonlyProperties = new ArrayList<>();
	private Map<String, Object> map;
	
	/**
	 * @param uniqueIdentifier
	 */
	public SlickGridDataRow(String uniqueIdentifier) {
		map = new LinkedHashMap<>();

		map.put(UNIQUE_IDENTIFIER_FIELD_NAME, uniqueIdentifier);
		map.put(NON_EDITABLE_PROPERTIES_FIELD_NAME, readonlyProperties);
	}
	
	/**
	 * @param column
	 * @param value
	 */
	public void setValue(SlickGridColumn column, Object value) {
		map.put(column.getField(), value);
	}
	
	/**
	 * This allows the grid to disable editing on individual cells, even if their column is marked as editable
	 */
	@SuppressWarnings("unchecked")
	public void addNonEditableProperty(String property) {
		((List<String>)map.get(NON_EDITABLE_PROPERTIES_FIELD_NAME)).add(property);
	}
	
	/**
	 * @return the map
	 */
	public Map<String, Object> getMap() {
		return map;
	}
}