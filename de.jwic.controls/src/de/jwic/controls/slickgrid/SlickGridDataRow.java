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
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author Adrian Ionescu
 */
class SlickGridDataRow<T> implements Serializable {
	
	private static final long serialVersionUID = 671484606921050314L;
	
	/**
	 * The SlickGrid data view requires a field named 'id' as unique identifier
	 * This is also used in SlickGrid.static.js
	 */
	private static final String ID = "id";
	
	/**
	 * This name is used in JS.. see SlickGrid.js and SlickGrid.static.js
	 */
	private static final String NON_EDITABLE_PROPERTIES_FIELD_NAME = "slickGridNonEditableProperties";
	
	private Map<String, Object> map;
	
	/**
	 * @param uniqueIdentifier
	 */
	public SlickGridDataRow(String uniqueIdentifier) {
		map = new LinkedHashMap<>();

		map.put(ID, uniqueIdentifier);
		map.put(NON_EDITABLE_PROPERTIES_FIELD_NAME, "");
	}
	
	/**
	 * @param column
	 * @param value
	 */
	public void setValue(SlickGridColumn<T> column, Object value) {
		map.put(column.getField(), value);
	}
	
	/**
	 * This allows the grid to disable editing on individual cells, even if their column is marked as editable
	 */
	public void addNonEditableProperty(String property) {
		String newValue = (String) map.get(NON_EDITABLE_PROPERTIES_FIELD_NAME);
		newValue += "<" + property + ">";
		map.put(NON_EDITABLE_PROPERTIES_FIELD_NAME, newValue);
	}
	
	/**
	 * @return the map
	 */
	public Map<String, Object> getMap() {
		return map;
	}
}