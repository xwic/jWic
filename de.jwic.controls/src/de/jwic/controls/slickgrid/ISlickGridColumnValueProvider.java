/*
 * Copyright (c) NetApp Inc. - All Rights Reserved
 * 
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 * 
 * de.jwic.controls.slickgrid.ISlickGridDataProvider 
 */
package de.jwic.controls.slickgrid;

import java.io.Serializable;
import java.util.List;

/**
 * @author Adrian Ionescu
 */
public interface ISlickGridColumnValueProvider<T> extends Serializable {
	
	/**
	 * Offers the value of the column for the given object. In other words, the value of a particular cell.
	 * 
	 * @param column
	 * @param obj
	 * @return a JSON supported value: Boolean, Number or String
	 */
	public Object getValue(SlickGridColumn<T> column, T obj);
	
	/**
	 * Offers a list of values to be used in the cell editor
	 * 
	 * @return a list of JSON objects
	 */
	public default List<KeyTitlePair> getKeyTitleValues() {
		return null;
	}

}