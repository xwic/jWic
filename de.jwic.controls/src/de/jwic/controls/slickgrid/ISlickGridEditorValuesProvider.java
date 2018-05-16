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

import com.google.gson.JsonObject;

/**
 * @author Adrian Ionescu
 */
public interface ISlickGridEditorValuesProvider extends Serializable {
	
	/**
	 * Offers a list of values to be used in a cell editor
	 * 
	 * @return a list of JSON objects
	 */
	public List<JsonObject> getEditorValues();

}