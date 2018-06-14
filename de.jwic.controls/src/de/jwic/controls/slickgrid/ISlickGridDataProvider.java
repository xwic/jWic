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
import java.util.Iterator;

/**
 * @author Adrian Ionescu
 */
public interface ISlickGridDataProvider<T> extends Serializable {
	
	/**
	 * An iterator throught the data provided by this data provider
	 * @return
	 */
	public Iterator<T> getDataIterator();
	
	/**
	 * @return
	 */
	public String getUniqueIdentifier(T obj);

	/**
	 * @param obj
	 * @param column
	 * @return
	 */
	public default boolean disableEditing(T obj, SlickGridColumn<T> column) {
		return false;
	}

}
