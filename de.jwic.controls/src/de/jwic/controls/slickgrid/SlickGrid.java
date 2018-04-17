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
import java.util.Collections;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import de.jwic.base.Control;
import de.jwic.base.Field;
import de.jwic.base.IControlContainer;
import de.jwic.base.JavaScriptSupport;

/**
 * @author Adrian Ionescu
 */
@JavaScriptSupport
public class SlickGrid<T> extends Control {

	private static final long serialVersionUID = 3616435322002219296L;
	
	private SlickGridOptions options;
	private SlickGridModel<T> model;
	
	private Field fldSelection;
	private Field fldChanges;

	private boolean clearChanges = false;
	
	private final Gson gson;
	
	/**
	 * @param container
	 * @param name
	 */
	public SlickGrid(IControlContainer container, String name) {
		super(container, name);

		this.options = new SlickGridOptions();
		this.model = new SlickGridModel<T>();
		
		fldSelection = new Field(this, "fldSelection");
		fldChanges = new Field(this, "fldChanges");
		
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setPrettyPrinting().serializeNulls();
		gsonBuilder.registerTypeAdapter(SlickGridDataRow.class, new SlickGridDataRowGsonAdapter());
		this.gson = gsonBuilder.create();
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
	 * @return
	 */
	public boolean hasColumnGrouping() {
		return model.getColumns().stream().anyMatch(c -> (c.getColumnGroup() != null && !c.getColumnGroup().trim().isEmpty()));
	}
	
	/* (non-Javadoc)
	 * @see de.jwic.base.Control#actionPerformed(java.lang.String, java.lang.String)
	 */
	@Override
	public void actionPerformed(String actionId, String parameter) {
		switch (actionId) {
		case "rowSelected":
			model.fireRowSelectedEvent(parameter);			
			break;
		default:
			super.actionPerformed(actionId, parameter);
			break;
		}
	}
	
	/**
	 * 
	 */
	public String getSelectedElementUniqueId() {
		return fldSelection.getValue();
	}
	
	/**
	 * @return
	 */
	public List<SlickGridChange> getChanges() {
		String strChanges = fldChanges.getValue();
		
		if (strChanges == null || strChanges.trim().isEmpty()) {
			return Collections.emptyList();
		}
		
		Type listType = new TypeToken<ArrayList<SlickGridChange>>() {}.getType();
		List<SlickGridChange> result = gson.fromJson(strChanges, listType);
		
		return result;
	}

	/**
	 * 
	 */
	public void clearChanges() {
		fldChanges.setValue("");
		clearChanges = true;
		
		requireRedraw();
	}

	/**
	 * @return the clearChanges
	 */
	public boolean isClearChanges() {
		return clearChanges;
	}
	
	/**
	 * 
	 */
	public void redrawComplete() {
		clearChanges = false;
	}

	// ***************************************************
	// JSON related methods
	// ***************************************************
	
	/**
	 * @return the options
	 */
	public String getOptionsAsJson() {
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