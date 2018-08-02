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
 * The details of the JavaScript control behind this jWic control is found here: https://github.com/6pac/SlickGrid
 * The source files are located in de.xwic.jwic.jwic-web/resources/jwic/lib/slickgrid
 * 
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
	private boolean reloadData = false;
	private boolean reloadColumns = false;
	private boolean clearSelection = false;
	private boolean clearFilters = false;
	private boolean rerender = false;
	
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
		gsonBuilder.registerTypeAdapter(SlickGridDataRow.class, new SlickGridDataRowGsonAdapter<T>());
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
		case "cellEdited":
			SlickGridChange change = gson.fromJson(parameter, SlickGridChange.class);
			model.fireCellChangedEvent(change);
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
	 * Returns true if the grid contains any pending changes.
	 * @return
	 */
	public boolean hasChanges() {
		String strChanges = fldChanges.getValue();
		return strChanges != null && !strChanges.trim().isEmpty();
	}
	
	/**
	 * Gather the changes recorded so far on the control and return them as a list. <br/><br/>
	 * You need to reset the changes manually (using {@link #resetChanges() resetChanges}), otherwise a subsequent call to this methoid will return them again.
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
	 * Clear the changes pending on the control. <br/><br/>
	 * Please note that this will not trigger a data reload on the JS grid.
	 */
	public void clearRecordedChanges() {
		fldChanges.setValue("");
		clearChanges = true;
		requireRedraw();
	}

	/**
	 * Clear the changes pending on the control and also trigger a data reload on the JS grid. <br/>
	 * This method is an alias for {@link #reloadData() reloadData}
	 */
	public void undoChanges() {
		reloadData();
	}
	
	/**
	 * Causes the JS grid to reload the columns and the data as provided by the jWic control. <br/></br/> 
	 * This will also clear any pending changes, therefore you should process them before returning to the client.
	 */
	public void reloadColumns() {
		reloadColumns = true;
		reloadData();
	}
	
	/**
	 * Causes the JS grid to clear the selected row(s) 
	 */
	public void clearSelection() {
		clearSelection = true;
		requireRedraw();
	}
	
	/**
	 * 
	 */
	public void clearFilters() {
		clearFilters = true;
		requireRedraw();
	}
	
	/**
	 * 
	 */
	public void rerender() {
		rerender = true;
		requireRedraw();
	}	
	
	/**
	 * Causes the JS grid to reload the data as provided by the jWic control. <br/><br/> 
	 * This will also clear any pending changes, therefore you should process them before returning to the client.
	 */
	public void reloadData() {
		reloadData = true;		
		clearRecordedChanges();
		requireRedraw();
	}

	/**
	 * @return the clearChanges
	 */
	public boolean isClearChanges() {
		return clearChanges;
	}
	
	/**
	 * @return the reloadData
	 */
	public boolean isReloadData() {
		return reloadData;
	}
	
	/**
	 * @return the reloadColumns
	 */
	public boolean isReloadColumns() {
		return reloadColumns;
	}
	
	/**
	 * @return the clearSelection
	 */
	public boolean isClearSelection() {
		return clearSelection;
	}
	
	/**
	 * @return the clearFilters
	 */
	public boolean isClearFilters() {
		return clearFilters;
	}
	
	/**
	 * @return the rerender
	 */
	public boolean isRerender() {
		return rerender;
	}
	
	/**
	 * 
	 */
	public void redrawComplete() {
		clearChanges = false;
		reloadData = false;
		reloadColumns = false;
		clearSelection = false;
		clearFilters = false;
		rerender = false;
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
		Type listType = new TypeToken<ArrayList<SlickGridColumn<T>>>() {}.getType();
		String json = gson.toJson(model.getColumns(), listType);
		return json;
	}
	
	/**
	 * @return
	 */
	public String getDataAsJson() {
		Type listType = new TypeToken<ArrayList<SlickGridDataRow<T>>>() {}.getType();
		String json = gson.toJson(model.getDataRows(), listType);		
		return json;
	}
	
	/**
	 * @param col
	 * @return
	 */
	public String getKeyTitleValuesAsJson(SlickGridColumn<T> col) {
		List<KeyTitlePair> values = null;
		if (col.getValueProvider() != null) {
			values = col.getValueProvider().getKeyTitleValues();
		}
		
		if (values == null) {
			values = Collections.emptyList();
		}
		
		Type listType = new TypeToken<ArrayList<KeyTitlePair>>() {}.getType();
		String json = gson.toJson(values, listType);		
		return json;
	}
}