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
	 * @return
	 */
	public Iterator<T> getDataIterator();

}
