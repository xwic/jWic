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
import java.util.Iterator;
import java.util.List;

/**
 * @author Adrian Ionescu
 */
public class SlickGridModel<T> implements Serializable {
	
	private static final long serialVersionUID = 811658710857024884L;
	
	private List<SlickGridColumn> columns;
	private ISlickGridDataProvider<T> dataProvider;
	private ISlickGridColumnValueProvider defaultValueProvider = new SlickGridDefaultColumnValueProvider();
	
	/**
	 * 
	 */
	public SlickGridModel() {
		this.columns = new ArrayList<>();
	}
	
	/**
	 * @param column
	 */
	public void addColumn(SlickGridColumn column) {
		String field = column.getField();
		
		if (columns.stream().anyMatch(c -> c.getField().equals(field))) {
			throw new IllegalArgumentException("A column with field '" + field + "' already exists");
		}
		
		columns.add(column);
	}
	
	/**
	 * @return the columns
	 */
	public Iterable<SlickGridColumn> getColumns() {
		return columns;
	}
	
	/**
	 * @return the data
	 */
	public List<SlickGridDataRow> getDataRows() {
		List<SlickGridDataRow> rows = new ArrayList<>();
		
		Iterator<T> iterator = dataProvider.getDataIterator();
		while (iterator.hasNext()) {
			T obj = iterator.next();
			
			SlickGridDataRow row = new SlickGridDataRow();
			rows.add(row);
			
			for (SlickGridColumn column : columns) {
				Object value;
				if (column.getValueProvider() != null) {
					value = column.getValueProvider().getValue(column, obj);
				} else {
					value = defaultValueProvider.getValue(column, obj);
				}
				row.setValue(column, value);
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
	public ISlickGridColumnValueProvider getDefaultLabelProvider() {
		return defaultValueProvider;
	}

	/**
	 * @param defaultLabelProvider the defaultLabelProvider to set
	 */
	public void setDefaultLabelProvider(ISlickGridColumnValueProvider defaultLabelProvider) {
		this.defaultValueProvider = defaultLabelProvider;
	}
}
