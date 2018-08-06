/*
 * Copyright (c) NetApp Inc. - All Rights Reserved
 * 
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 * 
 * de.jwic.controls.slickgrid.SlickGridModel 
 */
package de.jwic.controls.slickgrid;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import de.jwic.base.EventSupport;
import de.jwic.base.IEventListener;
import de.jwic.events.ElementSelectedEvent;

/**
 * @author Adrian Ionescu
 */
public class SlickGridModel<T> implements Serializable {
	
	private static final long serialVersionUID = 811658710857024884L;
	
	private List<SlickGridColumn<T>> columns;
	private ISlickGridDataProvider<T> dataProvider;
	private ISlickGridColumnValueProvider<T> defaultValueProvider = new SlickGridDefaultColumnValueProvider<T>();
	
	private EventSupport<ElementSelectedEvent> elementSelected = new EventSupport<>();
	private EventSupport<SlickGridChangeEvent> cellChanged = new EventSupport<>();
	
	/**
	 * 
	 */
	public SlickGridModel() {
		this.columns = new ArrayList<>();
	}
	
	/**
	 * @param column
	 */
	public void addColumn(SlickGridColumn<T> column) {
		String field = column.getField();
		
		if (columns.stream().anyMatch(c -> c.getField().equals(field))) {
			throw new IllegalArgumentException("A column with field '" + field + "' already exists");
		}
		
		columns.add(column);
	}
	
	/**
	 * @return the columns
	 */
	public Collection<SlickGridColumn<T>> getColumns() {
		return columns;
	}
	
	/**
	 * @return the columns
	 */
	public void clearColumns() {
		columns.clear();
	}
	
	/**
	 * @return the data
	 */
	public List<SlickGridDataRow<T>> getDataRows() {
		List<SlickGridDataRow<T>> rows = new ArrayList<>();
		
		Iterator<T> iterator = dataProvider.getDataIterator();
		while (iterator.hasNext()) {
			T obj = iterator.next();
			
			SlickGridDataRow<T> row = new SlickGridDataRow<T>(dataProvider.getUniqueIdentifier(obj));
			rows.add(row);
			
			for (SlickGridColumn<T> column : columns) {
				Object value;
				if (column.getValueProvider() != null) {
					value = column.getValueProvider().getValue(column, obj);
				} else {
					value = defaultValueProvider.getValue(column, obj);
				}
				row.setValue(column, value);
				
				if (dataProvider.disableEditing(obj, column)) {
					row.addNonEditableProperty(column.getId());
				}
			}
		}
		return rows;
	}

	/**
	 * @return the dataProvider
	 */
	public ISlickGridDataProvider<T> getDataProvider() {
		return dataProvider;
	}

	/**
	 * @param dataProvider the dataProvider to set
	 */
	public void setDataProvider(ISlickGridDataProvider<T> dataProvider) {
		this.dataProvider = dataProvider;
	}

	/**
	 * @return the defaultLabelProvider
	 */
	public ISlickGridColumnValueProvider<T> getDefaultLabelProvider() {
		return defaultValueProvider;
	}

	/**
	 * @param defaultLabelProvider the defaultLabelProvider to set
	 */
	public void setDefaultLabelProvider(ISlickGridColumnValueProvider<T> defaultLabelProvider) {
		this.defaultValueProvider = defaultLabelProvider;
	}
	
	/**
	 * Please note: the row selected event will not be triggered if the grid is configured with autoEdit = true, as the 
	 * event triggering interferes with the autoEdit functionality.
	 * <br/>
	 * See {@link SlickGridOptions#setAutoEdit SlickGridOptions.setAutoEdit()}
	 *  
	 * @param listener
	 */
	public void addElementSelectedListener(IEventListener<ElementSelectedEvent> listener) {
		elementSelected.addListener(listener);
	}
	
	/**
	 * @param listener
	 */
	public void addCellChangedListener(IEventListener<SlickGridChangeEvent> listener) {
		cellChanged.addListener(listener);
	}

	/**
	 * @param rowKey
	 */
	public void fireRowSelectedEvent(String rowKey) {
		elementSelected.fireEvent(new ElementSelectedEvent(this, rowKey));
	}
	
	/**
	 * @param change
	 */
	public void fireCellChangedEvent(SlickGridChange change) {
		cellChanged.fireEvent(new SlickGridChangeEvent(this, change));
	}
}
