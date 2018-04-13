/*
 * Copyright (c) NetApp Inc. - All Rights Reserved
 * 
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 * 
 * de.jwic.controls.slickgrid.SlickGridOptions 
 */
package de.jwic.controls.slickgrid;

import java.io.Serializable;

/**
 * Explanations for these options can be found here: https://github.com/6pac/SlickGrid/wiki/Grid-Options
 * 
 * @author Adrian Ionescu
 */
public class SlickGridOptions implements Serializable {

	private static final long serialVersionUID = -7037218169331241766L;

	private boolean asyncEditorLoading = false;
	private boolean autoEdit = false;
	private boolean autoHeight = false;
	private boolean editable = false;
	private boolean enableAddRow = false;
	private boolean enableCellNavigation = true;
	private boolean enableColumnReorder = true;
	private boolean enableTextSelectionOnCells = true;
	private boolean forceFitColumns = false;
	private boolean fullWidthRows = false;
	private boolean multiColumnSort = true;
	private boolean multiSelect = false;
	private boolean syncColumnCellResize = true;	
	private boolean createPreHeaderPanel = false;
	private boolean showPreHeaderPanel = false;
	private boolean createFooterRow = false;
	private boolean showFooterRow = false;
	
	private String cellFlashingCssClass = "flashing";
	private String cellHighlightCssClass = "selected";
	private String selectedCellCssClass = "selected";
	private String totalLabel = "Total: ";

	private int asyncEditorLoadDelay = 100;
	private int defaultColumnWidth = 100;
	private int rowHeight = 25;
	private int headerRowHeight = 53;
	private int preHeaderPanelHeight = 23;
	private int footerRowHeight = 21;

	/**
	 * 
	 */
	public SlickGridOptions() {

	}

	/**
	 * @return the asyncEditorLoading
	 */
	public boolean isAsyncEditorLoading() {
		return asyncEditorLoading;
	}

	/**
	 * @param asyncEditorLoading the asyncEditorLoading to set
	 */
	public void setAsyncEditorLoading(boolean asyncEditorLoading) {
		this.asyncEditorLoading = asyncEditorLoading;
	}

	/**
	 * @return the autoEdit
	 */
	public boolean isAutoEdit() {
		return autoEdit;
	}

	/**
	 * @param autoEdit the autoEdit to set
	 */
	public void setAutoEdit(boolean autoEdit) {
		this.autoEdit = autoEdit;
	}

	/**
	 * @return the autoHeight
	 */
	public boolean isAutoHeight() {
		return autoHeight;
	}

	/**
	 * @param autoHeight the autoHeight to set
	 */
	public void setAutoHeight(boolean autoHeight) {
		this.autoHeight = autoHeight;
	}

	/**
	 * @return the editable
	 */
	public boolean isEditable() {
		return editable;
	}

	/**
	 * @param editable the editable to set
	 */
	public void setEditable(boolean editable) {
		this.editable = editable;
	}

	/**
	 * @return the enableAddRow
	 */
	public boolean isEnableAddRow() {
		return enableAddRow;
	}

	/**
	 * @param enableAddRow the enableAddRow to set
	 */
	public void setEnableAddRow(boolean enableAddRow) {
		this.enableAddRow = enableAddRow;
	}

	/**
	 * @return the enableCellNavigation
	 */
	public boolean isEnableCellNavigation() {
		return enableCellNavigation;
	}

	/**
	 * @param enableCellNavigation the enableCellNavigation to set
	 */
	public void setEnableCellNavigation(boolean enableCellNavigation) {
		this.enableCellNavigation = enableCellNavigation;
	}

	/**
	 * @return the enableColumnReorder
	 */
	public boolean isEnableColumnReorder() {
		return enableColumnReorder;
	}

	/**
	 * @param enableColumnReorder the enableColumnReorder to set
	 */
	public void setEnableColumnReorder(boolean enableColumnReorder) {
		this.enableColumnReorder = enableColumnReorder;
	}

	/**
	 * @return the enableTextSelectionOnCells
	 */
	public boolean isEnableTextSelectionOnCells() {
		return enableTextSelectionOnCells;
	}

	/**
	 * @param enableTextSelectionOnCells the enableTextSelectionOnCells to set
	 */
	public void setEnableTextSelectionOnCells(boolean enableTextSelectionOnCells) {
		this.enableTextSelectionOnCells = enableTextSelectionOnCells;
	}

	/**
	 * @return the forceFitColumns
	 */
	public boolean isForceFitColumns() {
		return forceFitColumns;
	}

	/**
	 * @param forceFitColumns the forceFitColumns to set
	 */
	public void setForceFitColumns(boolean forceFitColumns) {
		this.forceFitColumns = forceFitColumns;
	}

	/**
	 * @return the fullWidthRows
	 */
	public boolean isFullWidthRows() {
		return fullWidthRows;
	}

	/**
	 * @param fullWidthRows the fullWidthRows to set
	 */
	public void setFullWidthRows(boolean fullWidthRows) {
		this.fullWidthRows = fullWidthRows;
	}

	/**
	 * @return the multiColumnSort
	 */
	public boolean isMultiColumnSort() {
		return multiColumnSort;
	}

	/**
	 * @param multiColumnSort the multiColumnSort to set
	 */
	public void setMultiColumnSort(boolean multiColumnSort) {
		this.multiColumnSort = multiColumnSort;
	}

	/**
	 * @return the multiSelect
	 */
	public boolean isMultiSelect() {
		return multiSelect;
	}

	/**
	 * @param multiSelect the multiSelect to set
	 */
	public void setMultiSelect(boolean multiSelect) {
		this.multiSelect = multiSelect;
	}

	/**
	 * @return the syncColumnCellResize
	 */
	public boolean isSyncColumnCellResize() {
		return syncColumnCellResize;
	}

	/**
	 * @param syncColumnCellResize the syncColumnCellResize to set
	 */
	public void setSyncColumnCellResize(boolean syncColumnCellResize) {
		this.syncColumnCellResize = syncColumnCellResize;
	}

	/**
	 * @return the cellFlashingCssClass
	 */
	public String getCellFlashingCssClass() {
		return cellFlashingCssClass;
	}

	/**
	 * @param cellFlashingCssClass the cellFlashingCssClass to set
	 */
	public void setCellFlashingCssClass(String cellFlashingCssClass) {
		this.cellFlashingCssClass = cellFlashingCssClass;
	}

	/**
	 * @return the cellHighlightCssClass
	 */
	public String getCellHighlightCssClass() {
		return cellHighlightCssClass;
	}

	/**
	 * @param cellHighlightCssClass the cellHighlightCssClass to set
	 */
	public void setCellHighlightCssClass(String cellHighlightCssClass) {
		this.cellHighlightCssClass = cellHighlightCssClass;
	}

	/**
	 * @return the selectedCellCssClass
	 */
	public String getSelectedCellCssClass() {
		return selectedCellCssClass;
	}

	/**
	 * @param selectedCellCssClass the selectedCellCssClass to set
	 */
	public void setSelectedCellCssClass(String selectedCellCssClass) {
		this.selectedCellCssClass = selectedCellCssClass;
	}

	/**
	 * @return the asyncEditorLoadDelay
	 */
	public int getAsyncEditorLoadDelay() {
		return asyncEditorLoadDelay;
	}

	/**
	 * @param asyncEditorLoadDelay the asyncEditorLoadDelay to set
	 */
	public void setAsyncEditorLoadDelay(int asyncEditorLoadDelay) {
		this.asyncEditorLoadDelay = asyncEditorLoadDelay;
	}

	/**
	 * @return the defaultColumnWidth
	 */
	public int getDefaultColumnWidth() {
		return defaultColumnWidth;
	}

	/**
	 * @param defaultColumnWidth the defaultColumnWidth to set
	 */
	public void setDefaultColumnWidth(int defaultColumnWidth) {
		this.defaultColumnWidth = defaultColumnWidth;
	}

	/**
	 * @return the rowHeight
	 */
	public int getRowHeight() {
		return rowHeight;
	}

	/**
	 * @param rowHeight the rowHeight to set
	 */
	public void setRowHeight(int rowHeight) {
		this.rowHeight = rowHeight;
	}

	/**
	 * @return the createPreHeaderPanel
	 */
	public boolean isCreatePreHeaderPanel() {
		return createPreHeaderPanel;
	}

	/**
	 * @param createPreHeaderPanel the createPreHeaderPanel to set
	 */
	public void setCreatePreHeaderPanel(boolean createPreHeaderPanel) {
		this.createPreHeaderPanel = createPreHeaderPanel;
	}

	/**
	 * @return the showPreHeaderPanel
	 */
	public boolean isShowPreHeaderPanel() {
		return showPreHeaderPanel;
	}

	/**
	 * @param showPreHeaderPanel the showPreHeaderPanel to set
	 */
	public void setShowPreHeaderPanel(boolean showPreHeaderPanel) {
		this.showPreHeaderPanel = showPreHeaderPanel;
	}

	/**
	 * @return the preHeaderPanelHeight
	 */
	public int getPreHeaderPanelHeight() {
		return preHeaderPanelHeight;
	}

	/**
	 * @param preHeaderPanelHeight the preHeaderPanelHeight to set
	 */
	public void setPreHeaderPanelHeight(int preHeaderPanelHeight) {
		this.preHeaderPanelHeight = preHeaderPanelHeight;
	}

	/**
	 * @return the headerRowHeight
	 */
	public int getHeaderRowHeight() {
		return headerRowHeight;
	}

	/**
	 * @param headerRowHeight the headerRowHeight to set
	 */
	public void setHeaderRowHeight(int headerRowHeight) {
		this.headerRowHeight = headerRowHeight;
	}

	/**
	 * @return the createFooterRow
	 */
	public boolean isCreateFooterRow() {
		return createFooterRow;
	}

	/**
	 * @param createFooterRow the createFooterRow to set
	 */
	public void setCreateFooterRow(boolean createFooterRow) {
		this.createFooterRow = createFooterRow;
	}

	/**
	 * @return the showFooterRow
	 */
	public boolean isShowFooterRow() {
		return showFooterRow;
	}

	/**
	 * @param showFooterRow the showFooterRow to set
	 */
	public void setShowFooterRow(boolean showFooterRow) {
		this.showFooterRow = showFooterRow;
	}

	/**
	 * @return the footerRowHeight
	 */
	public int getFooterRowHeight() {
		return footerRowHeight;
	}

	/**
	 * @param footerRowHeight the footerRowHeight to set
	 */
	public void setFooterRowHeight(int footerRowHeight) {
		this.footerRowHeight = footerRowHeight;
	}

	/**
	 * @return the totalLabel
	 */
	public String getTotalLabel() {
		return totalLabel;
	}

	/**
	 * @param totalLabel the totalLabel to set
	 */
	public void setTotalLabel(String totalLabel) {
		this.totalLabel = totalLabel;
	}
}
