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
class SlickGridDataRow implements Serializable {
	
	private static final long serialVersionUID = 671484606921050314L;
	
	private static final String UNIQUE_IDENTIFIER_PROPERTY = "slick_grid_uid";
	
	private Map<String, Object> map;
	
	/**
	 * @param uniqueIdentifier
	 */
	public SlickGridDataRow(String uniqueIdentifier) {
		map = new LinkedHashMap<>();

		map.put(UNIQUE_IDENTIFIER_PROPERTY, uniqueIdentifier);
	}
	
	/**
	 * @param column
	 * @param value
	 */
	public void setValue(SlickGridColumn column, Object value) {
		map.put(column.getField(), value);
	}
	
	/**
	 * @return the map
	 */
	public Map<String, Object> getMap() {
		return map;
	}
}