/*
 * Copyright (c) NetApp Inc. - All Rights Reserved
 * 
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 * 
 * de.jwic.controls.slickgrid.SlickGrid 
 */
package de.jwic.controls.slickgrid;

import java.lang.reflect.Type;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import de.jwic.base.Control;
import de.jwic.base.Field;
import de.jwic.base.IControlContainer;
import de.jwic.base.JavaScriptSupport;
import de.jwic.events.ValueChangedListener;

/**
 * @author Adrian Ionescu
 */
@JavaScriptSupport
public class SlickGrid<T> extends Control {

	private static final long serialVersionUID = 3616435322002219296L;

	private SlickGridOptions options;
	private SlickGridModel<T> model;
	
	private Field changes;
	
	private int width = 600;
	private int height = 300;
	
	private static Gson gson;
	
	static {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setPrettyPrinting().serializeNulls();
		gsonBuilder.registerTypeAdapter(SlickGridDataRow.class, new SlickGridDataRowGsonAdapter());
		gson = gsonBuilder.create();
	}
	
	/**
	 * @param container
	 * @param name
	 */
	public SlickGrid(IControlContainer container, String name) {
		super(container, name);

		this.options = new SlickGridOptions();
		this.model = new SlickGridModel<T>();
		
		changes = new Field(this, "changes");
	}
	
	/**
	 * @return the options
	 */
	public SlickGridOptions getOptions() {
		return options;
	}
	
	/**
	 * @return the model
	 */
	public SlickGridModel<T> getModel() {
		return model;
	}

	/**
	 * @return the width
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * @param width the width to set
	 */
	public void setWidth(int width) {
		this.width = width;
	}

	/**
	 * @return the height
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * @param height the height to set
	 */
	public void setHeight(int height) {
		this.height = height;
	}
	
	/**
	 * Add a value changed listener to the <b>field</b> used by this
	 * input box control.
	 * @param listener
	 */
	public void addValueChangedListener(ValueChangedListener listener) {
		changes.addValueChangedListener(listener);
	}
	
	/**
	 * @return
	 */
	public boolean hasColumnGrouping() {
		return model.getColumns().stream().anyMatch(c -> (c.getColumnGroup() != null && !c.getColumnGroup().trim().isEmpty()));
	}

	// ***************************************************
	// JSON related methods
	// ***************************************************
	
	/**
	 * @return the options
	 */
	public String getOptionsAsJson() {
		if (options.isEnableColumnReorder() && hasColumnGrouping()) {
			// forcefully disable column reorder if we have column grouping present
			options.setEnableColumnReorder(false);
		}
		String json = gson.toJson(options);
		return json;
	}
	
	/**
	 * @return
	 */
	public String getColumnsAsJson() {
		Type listType = new TypeToken<ArrayList<SlickGridColumn>>() {}.getType();
		String json = gson.toJson(model.getColumns(), listType);
		return json;
	}
	
	/**
	 * @return
	 */
	public String getDataAsJson() {
		Type listType = new TypeToken<ArrayList<SlickGridDataRow>>() {}.getType();
		String json = gson.toJson(model.getDataRows(), listType);		
		return json;
	}
}